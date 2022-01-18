class MapA extends Map{

    int[][] map = new int[18][18];

    int x = 50;
    int y = 50;
    int w = 30;
    int h = 30;

    int i, j;
    

    MapA () {


        int[][] mapA = {
        //  {0,0,0,0,5,0,0,0,0,1,0,0,0,0,5,0,0,0,0,1,0,0,0,0,5,0,0,0,0,1,0,0}
            {0,0,1,1,1,0,1,1,1,0,1,1,1,1,0,1,0,0},
            {0,0,1,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0},
            {1,0,1,0,1,1,1,0,1,0,1,0,1,1,1,1,1,1},
            {0,0,1,0,1,0,0,0,1,0,1,0,0,0,0,0,0,1},
            {1,0,1,0,1,0,1,1,1,0,1,1,0,1,1,1,0,1},
            {1,0,1,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0},
            {1,0,0,0,1,0,1,1,0,0,1,1,0,1,0,1,0,1},//10
            {1,1,1,1,1,0,1,0,0,0,0,1,0,1,0,0,0,1},
            {0,0,0,0,0,0,0,0,1,1,0,0,0,1,1,1,0,1},
            {1,0,1,1,1,0,0,0,1,1,0,0,0,0,0,0,0,0},
            {1,0,0,0,1,0,1,0,0,0,0,1,0,1,1,1,1,1},
            {1,0,1,0,1,0,1,1,0,0,1,1,0,1,0,0,0,1},
            {0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,1,0,1},
            {1,0,1,1,1,0,1,1,0,1,1,1,0,1,0,1,0,1},
            {1,0,0,0,0,0,0,1,0,1,0,0,0,1,0,1,0,0},
            {1,1,1,1,1,1,0,1,0,1,0,1,1,1,0,1,0,1},
            {0,0,0,0,0,0,0,1,0,0,0,0,0,1,0,0,0,0},//20
            {0,0,1,0,1,1,1,1,0,1,1,1,0,1,1,1,0,0}
        };

        map = mapA;

    }

    boolean wallColissionCheck(MovingObject m){
        int i;
        //System.out.println(m.vec);
        switch (m.vec) {
        case 1:
            //System.out.println(" [w] pressed");
            if (m.y <= 0) {
                return true;
            }
            for (i=0; i<40; i++){
                if (map[(m.y+m.h)/40-1][(m.x+i)/40] == 1) {
                    System.out.println("wall w");
                    return true;
                }
            }
            break;

        case 2:
            //System.out.println(" [d] pressed");
            if (m.x+m.w >= 719){
                return true;
            }
            for (i=0; i<40; i++){
                if(map[(m.y+i)/40][(m.x)/40+1] == 1) {
                    System.out.println("wall d");
                    return true;
                }
            }
            break;

        case 3:
            //System.out.println(" [s] pressed");
            if (m.y+m.h >= 719) {
                return true;
            }
            for (i=0; i<40; i++){
                if (map[(m.y)/40+1][(m.x+i)/40] == 1) {
                    System.out.println("wall s");
                    return true;
                }
            }
            break;

        case 4:
            //System.out.println(" [a] pressed");
            if (m.x <= 0){
                return true;
            }
            for (i=0; i<40; i++){
                if(map[(m.y+i)/40][(m.x+m.w)/40-1] == 1) {
                    System.out.println("wall a");
                    return true;
                }
            }
            break;
        }
        return false;
    }
}
