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

    int connectingClientCount;

    Server() {
        try{
            nPort = Integer.parseInt("3000");
            if ( nPort < 1000 || nPort > 65535)
                throw new NumberFormatException();
            
            serverSocket = new ServerSocket(nPort);
            
            skt = serverSocket.accept();

            connectingClientCount++;

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
            System.out.println("送信:"+outData);
            System.out.println("受信:"+line);
            
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

    public void out (String outData){
        try{
            dos.writeUTF(outData); // UTF: Unicode Transformation Format
            System.out.println("送信:"+outData);
            
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

    public void in (){
        try{
            line = ios.readUTF();
            System.out.println("受信:"+line);
            
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
