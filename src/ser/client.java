import java.io.*;
import java.net.*;
import java.util.*;

class Client {
   int nPort = 3000;
   String strServer = "localhost";
   Socket skt;
   InputStream is;
   DataInputStream ios;
   OutputStream os;
   DataOutputStream dos;
   String line;

   Client (){
      try{
         if( nPort < 1000 || nPort > 65535 )
            throw new NumberFormatException();

            skt = new Socket(strServer, nPort);
            is = skt.getInputStream();
            ios = new DataInputStream(is);
            os  = skt.getOutputStream();
            dos = new DataOutputStream(os);
         }catch(NumberFormatException  e){
            System.err.println("引数はサーバー名とポート番号です。");
         }catch(IndexOutOfBoundsException  e){
            System.err.println("引数はサーバー名とポート番号です。");
         }catch(UnknownHostException e){
            System.err.println("サーバーが見つかりません");
         }catch(IOException e){
            System.err.println("入出力エラーです\n" + e);
         }catch(Exception e){
            System.err.println(e);
         }
   }

   void runFirst(String outData){
      try{
         dos.writeUTF(outData);
         line = ios.readUTF();

      }catch(NumberFormatException  e){
         System.err.println("引数はサーバー名とポート番号です。");
      }catch(IndexOutOfBoundsException  e){
         System.err.println("引数はサーバー名とポート番号です。");
      }catch(UnknownHostException e){
         System.err.println("サーバーが見つかりません");
      }catch(IOException e){
         System.err.println("入出力エラーです\n" + e);
      }catch(Exception e){
         System.err.println(e);
      }
   }

   void run(){
      try{
         System.out.print(ios.readUTF());
         System.out.println(ios.readUTF());
      }catch(NumberFormatException  e){
         System.err.println("引数はサーバー名とポート番号です。");
      }catch(IndexOutOfBoundsException  e){
         System.err.println("引数はサーバー名とポート番号です。");
      }catch(UnknownHostException e){
         System.err.println("サーバーが見つかりません");
      }catch(IOException e){
         System.err.println("入出力エラーです\n" + e);
      }catch(Exception e){
         System.err.println(e);
      }
   }

   void close(){
      try{
         is.close();
         os.close();
         skt.close();
      }catch(NumberFormatException  e){
         System.err.println("引数はサーバー名とポート番号です。");
      }catch(IndexOutOfBoundsException  e){
         System.err.println("引数はサーバー名とポート番号です。");
      }catch(UnknownHostException e){
         System.err.println("サーバーが見つかりません");
      }catch(IOException e){
         System.err.println("入出力エラーです\n" + e);
      }catch(Exception e){
         System.err.println(e);
      }
   }
}