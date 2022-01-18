import java.io.*;
import java.net.*;
import java.util.*;

class SmpNetSocket01_Client{
   public static void main(String args[]){
      try{
         // 引数からサーバー名の取得
         String strServer = args[0];

         // 引数からポート番号の取得
         int nPort = Integer.parseInt(args[1]);
         if( nPort < 1000 || nPort > 65535 )
            throw new NumberFormatException();
 
         // 1. サーバー名とポート番号を指定して Socket インスタンスを作成して接続する
         Socket skt = new Socket(strServer, nPort);

         // 2. Socket インスタンスからストリームを得る
               //    (InputStream は汎用的な抽象クラス)
         InputStream     is  = skt.getInputStream();
         // 3. 今回は、具体的に DataInputStream クラスを用いる
         DataInputStream ios = new DataInputStream(is);

         // 4. データ受信
         System.out.print(ios.readUTF());
         System.out.println(ios.readUTF());

         // 5. クローズ
         is.close();
         skt.close();
      }catch(NumberFormatException  e){
         System.err.println("引数はサーバー名とポート番号です。");
      }catch(IndexOutOfBoundsException  e){
         System.err.println("引数はサーバー名とポート番号です。");
      }catch(UnknownHostException e){
         System.err.println("サーバー(" + args[0] + ")が見つかりません");
      }catch(IOException e){
         System.err.println("入出力エラーです\n" + e);
      }catch(Exception e){
         System.err.println(e);
      }
   }
}
