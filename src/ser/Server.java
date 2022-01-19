import java.io.*;
import java.net.*;
import java.util.*;

class Server {
    int nPort;
    ServerSocket serverSocket;
    Socket skt;
    OutputStream os;
    DataOutputStream dos;
    InputStream is;
    DataInputStream ios;

    String line;

    Server() {
        try{
            nPort = Integer.parseInt("3000");
            if ( nPort < 1000 || nPort > 65535)
                throw new NumberFormatException();
            
            serverSocket = new ServerSocket(nPort);
            
            skt = serverSocket.accept();

            System.out.println(skt.getInetAddress().getHostName() + " から接続されました");

            os  = skt.getOutputStream();
            dos = new DataOutputStream(os);
            is = skt.getInputStream();
            ios = new DataInputStream(is);

        }catch(NumberFormatException  e){
            System.err.println("引数はポート番号です。1000〜65535までの数字を設定してください。");
        }catch(IndexOutOfBoundsException  e){
            System.err.println("引数はポート番号です。1000〜65535までの数字を設定してください。");
        }catch(IOException e){
            System.err.println("入出力エラーです\n" + e);
        }catch(Exception e){
            System.err.println(e);
        }
    }

    public void runFirst (String outData) {
        try{
            
            line = ios.readUTF(); // UTF: Unicode Transformation Format
            dos.writeUTF(outData);
            
        }catch(NumberFormatException  e){
            System.err.println("引数はポート番号です。1000〜65535までの数字を設定してください。");
        }catch(IndexOutOfBoundsException  e){
            System.err.println("引数はポート番号です。1000〜65535までの数字を設定してください。");
        }catch(IOException e){
            System.err.println("入出力エラーです\n" + e);
        }catch(Exception e){
            System.err.println(e);
        }
    }

    public void run (){
        try{
            String str1 = "こんにちは、あなたは 0 番目のお客様です\n";
            String str2 = "ただいまサーバーの時間は " + (new Date()).toString() + " です\n";
            dos.writeUTF(str1); // UTF: Unicode Transformation Format
            dos.writeUTF(str2);
            
        }catch(NumberFormatException  e){
            System.err.println("引数はポート番号です。1000〜65535までの数字を設定してください。");
        }catch(IndexOutOfBoundsException  e){
            System.err.println("引数はポート番号です。1000〜65535までの数字を設定してください。");
        }catch(IOException e){
            System.err.println("入出力エラーです\n" + e);
        }catch(Exception e){
            System.err.println(e);
        }
    }

    public void close(){
        try{
            os.close();
            is.close();
            skt.close();
        }catch(NumberFormatException  e){
            System.err.println("引数はポート番号です。1000〜65535までの数字を設定してください。");
        }catch(IndexOutOfBoundsException  e){
            System.err.println("引数はポート番号です。1000〜65535までの数字を設定してください。");
        }catch(IOException e){
            System.err.println("入出力エラーです\n" + e);
        }catch(Exception e){
            System.err.println(e);
        }
    }
    
}
