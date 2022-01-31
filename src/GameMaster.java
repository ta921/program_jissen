import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class GameMaster extends Canvas implements KeyListener{

    Random r = new Random();

    Image buf;
    Graphics buf_gc;
    Dimension d;
    private int imgW, imgH;

    private int mode = 0;
    private int i, j;

    InetAddress myaddress;

    private int len = 39;
    int tmp;

    Character myChara = new Character(imgW, imgH);
    Character tmpChara = new Character(imgW, imgH);
    Character[] enemy = new Character[3];
    Character[] charaList = new Character[4];
    int livingCharaCount;
    
    
    Image charaImg = this.getToolkit().getImage("character.png");

    MapA map = new MapA();

    Image mapImgData = this.getToolkit().getImage("mapImage.png");
    Image mapImg;

    boolean isServer = true;
    Server ser;
    Client cli;
    String changedOutData;
    String inData;
    int[] changedInData = new int[32];
    int[] outData = new int[32];
    int clientNum;
    int connectingClientCount = 0;

    boolean isSingle  = true;

    int select = 100;
    boolean space = false;
    boolean enter = false;
    int count = 0;
    int[] walkPtn = {0,1,2,1};

    int[][][] charaNum = {
        {{16,232},{16,168},{16,41},{16,104}},
        {{112,232},{112,168},{112,41},{112,104}},
        {{208,232},{208,168},{208,41},{208,104}},
        {{304,232},{304,168},{304,41},{304,104}},
        {{16,488},{16,424},{16,296},{16,360}},
        {{112,488},{112,424},{112,296},{112,360}},
        {{208,488},{208,424},{208,296},{208,360}},
        {{304,488},{304,424},{304,296},{304,360}},
    };

    GameMaster(int imgW, int imgH){
        this.imgW = imgW;
        this.imgH = imgH;
        setSize(imgW, imgH);

        addKeyListener(this);

        //map.repaint();
        for (i=0; i<4; i++){
            charaList[i] = new Character(imgW, imgH);
            if (i>=1) {
                enemy[i-1] = new Character(imgW, imgH);
                tmpChara = setChara(tmpChara);

                charaList[i] = enemy[i-1];
                charaList[i].x = tmpChara.x;
                charaList[i].y = tmpChara.y;
            }else{
                charaList[i] = myChara;
                tmpChara = setChara(tmpChara);
                myChara.x=tmpChara.x;
                myChara.y=tmpChara.y;
            }
        }
    }

    public void backPaint(){
        for(i=0; i<18; i++){
            for(j=0; j<18; j++){
                if (map.map[i][j] == 1){
                    buf_gc.drawImage(mapImgData, 40*j, 40*i, 40*j+40, 40*i+40, map.x-map.w, map.y-map.h, map.x+map.w, map.y+map.h, null);
                }

            }
        }
    }

    public void addNotify(){
        super.addNotify();
        buf = createImage(imgW, imgH);
        buf_gc = buf.getGraphics();
    }

    public void paint(Graphics g){
        buf_gc.setColor(Color.white);
        buf_gc.fillRect(0, 0, imgW, imgH);
        count+=1;
        count %= 4;
        //backPaint();
        
        switch (mode) {
        case 0:
            //スタート画面
            //select 0 => single, 1=> multi
            buf_gc.setColor(Color.black);
            buf_gc.drawString("Please Push SPACE", 50, 20);
            buf_gc.drawString("Select Button by ↑ ↓ key", 50, 40);

            buf_gc.setColor(Color.red);

            if (select%2 == 0){
                buf_gc.drawLine(150, 360, 180, 360);
            }else{
                buf_gc.drawLine(550, 360, 580, 360);
            }

            buf_gc.setColor(Color.black);
            buf_gc.drawString("single", 150, 360);
            buf_gc.drawString("multi", 550, 360);

            if(space == true){
                if (select %2 == 0){
                    isSingle = true;
                    mode = 1;
                }else{
                    isSingle = false;
                    mode = 1;
                }
            }

            space = false;
            for (i=0;i<4;i++){
                charaList[i].hp=1;
            }
            break;
        case 2: //ｹﾞｰﾑガメン
            len = 39;
            backPaint();
            buf_gc.setColor(Color.red);
            buf_gc.drawString("Attack Enemy", 50, 20);
            buf_gc.drawString("Control By ↑ → ↓ ← key", 50, 40);

            if(!isSingle){
                if(isServer){ //server

                    ser.in();

                    inData = ser.line;

                    changeInData(5);

                    for (i=1; i<2; i++){
                        charaList[changedInData[4]].x = changedInData[0];
                        charaList[changedInData[4]].y = changedInData[1];
                        charaList[changedInData[4]].vec = changedInData[2];
                        charaList[changedInData[4]].hp = changedInData[3];
                    }

                    for (i = 0; i<4; i++){
                        outData[4*i+0] = charaList[i].x;
                        outData[4*i+1] = charaList[i].y;
                        outData[4*i+2] = charaList[i].vec;
                        outData[4*i+3] = charaList[i].hp;

                    }

                    changeOutData(16);
                    ser.out(changedOutData);

                }else{ //client
                    outData[0] = myChara.x;
                    outData[1] = myChara.y;
                    outData[2] = myChara.vec;
                    outData[3] = myChara.hp;
                    outData[4] = clientNum;

                    changeOutData(5);

                    cli.out(changedOutData);
                    cli.in();
                    inData = cli.line;

                    changeInData(16);

                    tmp=0;

                    for (i=0; i<4; i++){
                        if(i != clientNum){
                            charaList[tmp+1].x = changedInData[4*i];
                            charaList[tmp+1].y = changedInData[4*i+1];
                            charaList[tmp+1].vec = changedInData[4*i+2];
                            charaList[tmp+1].hp = changedInData[4*i+3];
                            tmp++;
                        }else{
                            charaList[0].x = changedInData[4*i];
                            charaList[0].y = changedInData[4*i+1];
                            //charaList[0].vec = changedInData[4*i+2];
                            charaList[0].hp = changedInData[4*i+3];
                            
                        }

                    }

                }
            }

            livingCharaCount=4;

            if(myChara.hp == 0){
                buf_gc.setColor(Color.red);
                buf_gc.drawString("Your Character Is Dead", 50, 60);
            }

            for (i = 0; i<4; i++){
                if(charaList[i].hp == 0){
                    livingCharaCount-=1;
                    continue;
                }

                if(!map.wallColissionCheck(charaList[i])){
                    charaList[i].move(buf_gc, imgW, imgH);
                //printAroundWall();
                }else{

                    if(isSingle){
                        if(i > 0){
                            charaList[i].turn();
                        }
                    }else{
                        if(i > 1){
                            charaList[i].turn();
                        }
                    }
                }

                if(i==0 && myChara.hp != 0){
                    buf_gc.setColor(Color.blue);
                    buf_gc.drawRect(charaList[i].x, charaList[i].y, 39, 39);

                }
                paintChara(charaList[i].num, charaList[i].vec, charaList[i].x, charaList[i].y);

                for(j=0; j<4; j++){
                    if(i==j) continue;
                    if(charaList[j].hp==0) continue;

                    charaList[i].collisionCheck(charaList[j]);
                }
            }

            if(livingCharaCount==1){
                if (myChara.hp == 1){
                    //win
                    mode = 6;

                }else{
                    //lose
                    mode = 7;
                }
            }

            //buf_gc.drawImage(charaImg, chara.x, chara.y, len+chara.x, len+chara.y, chara.xImage-chara.wImage, chara.yImage-chara.hImage, chara.xImage+chara.wImage, chara.yImage+chara.hImage, null);
            break;
        case 1:
            //キャラクター選択画面
            buf_gc.setColor(Color.black);
            buf_gc.drawString("Please Push Enter", 50, 20);
            buf_gc.drawString("Select Button by ↑ ↓ key", 50, 40);
            tmp = select%8;
            len = 99;

            for (i = 0; i<2; i++){
                for (j=0; j<4; j++){
                    paintChara(i*4+j, 3, 150*j+20, 150+150*i);
                    if (tmp == i*4+j){
                        buf_gc.setColor(Color.red);
                        buf_gc.drawLine(150*j+20, 150*(i+1)+99,150*j+119, 150*(i+1)+99);
                    }
                }
            }

            if (enter==true){ //シングルの時の処理
                if (isSingle){
                    myChara.num = tmp;
                    mode =2;
                    livingCharaCount=4;
                }else{ // マルチの時の処理
                    myChara.num = tmp;
                    mode = 3;
                    livingCharaCount=4;
                }
                
                enter = false;
            }
            break;
        case 3://マルチ専用画面
            // 0=>server, 1=>client

            buf_gc.setColor(Color.black);
            buf_gc.drawString("Please Push SPACE", 50, 20);
            buf_gc.drawString("Select Button by ↑ ↓ key", 50, 40);

            buf_gc.setColor(Color.red);

            if (select%2 == 0){
                buf_gc.drawLine(150, 360, 180, 360);
            }else{
                buf_gc.drawLine(550, 360, 580, 360);
            }

            buf_gc.setColor(Color.black);
            buf_gc.drawString("server", 150, 360);
            buf_gc.drawString("client", 550, 360);

            if(space == true){
                if (select %2 == 0){
                    isServer = true;
                    mode = 4;

                    try{
                        buf_gc.setColor(Color.white);
                        buf_gc.fillRect(0, 0, imgW, imgH);
                        myaddress = InetAddress.getLocalHost();
                        buf_gc.setColor(Color.black);
                        buf_gc.drawString("server:"+myaddress.getHostAddress(), 150, 360);
                    }catch (UnknownHostException e){
                        e.printStackTrace();
                    }
                }else{
                    buf_gc.setColor(Color.black);
                    buf_gc.drawString("Please Enter Server IP in Console", 50, 60);
                    isServer = false;
                    mode = 5;
                }
                space = false;
            }
            break;
        case 4://server用
            try{
                myaddress = InetAddress.getLocalHost();
                buf_gc.setColor(Color.black);
                buf_gc.drawString("server:"+myaddress.getHostAddress(), 150, 360);

                ser = new Server();

                ser.in();
                inData = ser.line;
                changeInData(1);
                charaList[ser.connectingClientCount].num = changedInData[0];

                for(i=0;i<4;i++){
                    outData[i] = charaList[i].num;
                }
                outData[4] = ser.connectingClientCount;
                changeOutData(5);

                ser.out(changedOutData);
                //ser.close();

                mode = 2;
            }catch (UnknownHostException e){
                e.printStackTrace();
            }
            break;
        case 5: //client

            cli = new Client();
            outData[0] = myChara.num;
            changeOutData(1);
            cli.out(changedOutData);
            cli.in();

            inData = cli.line;

            changeInData(5);
            clientNum = changedInData[4];

            tmp = 0;

            for (i=0;i<4;i++){
                if(clientNum == i){
                    continue;
                }else{
                    charaList[tmp+1].num = changedInData[i];
                    tmp++;
                }
            }

            //cli.close();

            mode = 2;
            break;
        case 6:
            buf_gc.setColor(Color.black);
            buf_gc.drawString("You Win", 30, 360);
            buf_gc.drawString("Please Push SPACE", 30, 380);
            if (space == true){
                mode = 0;
            }

            if(!isSingle){
                if(isServer){
                    ser.close();
                }else{
                    cli.close();
                }
            }
            break;
        case 7:
            buf_gc.setColor(Color.black);
            buf_gc.drawString("You Lose", 30, 360);
            buf_gc.drawString("Please Push SPACE", 30, 380);
            if (space == true){
                mode = 0;
            }

            if(!isSingle){
                if(isServer){
                    ser.close();
                }else{
                    cli.close();
                }
            }
            break;         
        }
        
        g.drawImage(buf, 0, 0, this);
        enter = false;
        space = false;
        //System.out.println(select);
    }

    public void changeInData(int num){
        String tmp = "";
        int count = 0;
        
        for(i=0; count<num; i++){
            if(inData.charAt(i) == '/'){
                changedInData[count]=Integer.valueOf(tmp).intValue();
                tmp="";
                count++;
            }else{
                tmp += inData.charAt(i);
            }
        }
    }

    public void changeOutData(int num){
        changedOutData = "";
        for (i=0; i<num; i++){
            changedOutData += Integer.valueOf(outData[i]).toString()+"/";
        }
    }

    public void paintChara(int num, int vec, int x, int y){
        buf_gc.drawImage(charaImg, x, y, len+x, len+y, charaNum[num][vec-1][0]-13+32*walkPtn[count], charaNum[num][vec-1][1]-23, charaNum[num][vec-1][0]+13+32*walkPtn[count], charaNum[num][vec-1][1]+23, null);
    }

    public Character setChara(Character chara){ //キャラの位置をセットする
        chara.set();
        if(map.map[chara.y/40][chara.x/40] == 1){
            chara.set();
        }

        return chara;
    }

    public void update(Graphics gc) {
        paint(gc);
    }
    
    public void keyTyped(KeyEvent ke) {
        int cd = ke.getKeyCode();
        switch (cd) {
        case KeyEvent.VK_SPACE:
            break;            
        }
    }

    public void keyReleased(KeyEvent ke) {}

    public void keyPressed(KeyEvent ke) {
        int cd = ke.getKeyCode();
        switch (cd) {
        case KeyEvent.VK_LEFT:
            myChara.vec = 4;
            break;

        case KeyEvent.VK_RIGHT:
            myChara.vec = 2;
            break;

        case KeyEvent.VK_UP:
            myChara.vec = 1;
            select+=1;
            break;

        case KeyEvent.VK_DOWN:
            myChara.vec = 3;
            select-=1;
            break;

        case KeyEvent.VK_SPACE:
            space = true;
            break;
        
        case KeyEvent.VK_ENTER:
            enter = true;
            break;
        }
        
    }
}
