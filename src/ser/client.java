import java.io.*;
import java.net.*;
import java.util.*;

class Client {
   int nPort = 3000;
   String strServer="localhost";
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

            Scanner scan = new Scanner(System.in);
            System.out.println("Plsese Enter Server IP address");
            strServer = scan.next();

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

         System.out.println("送信:"+outData);
         System.out.println("受信:"+line);

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