package testNet.sever;
import java.io.*;
import java.net.*;
import java.util.*;

class SmpNetSocket01_Server{
   public static void main(String args[]){
      try{
         // ポート番号の取得
         int nPort = Integer.parseInt(args[0]);
         if( nPort < 1000 || nPort > 65535 )
            throw new NumberFormatException();
         // 1. ソケット (ServerSocket クラス) のインスタンス作成
         ServerSocket serverSocket = new ServerSocket(nPort);
         for( int i = 1; true; i++ ){
            // 2. 接続を待機する (accept メソッド、一時停止)
            Socket skt = serverSocket.accept();

            // 3. クライアントからのアクセスがあると
                    //    Socket クラスのオブジェクトにクライアント情報が入り、
                    //    プログラムの進行を再開する
            System.out.println(skt.getInetAddress().getHostName() + " から接続されました");

            // 4. Socket クラスからIOストリームのインスタンスを得る
                    //    getOutputStream の戻値は OutputStream クラス（汎用的な抽象クラス）
            OutputStream     os  = skt.getOutputStream();
            // 5. 今回は具体的に DataOutputStream クラスを使う
            DataOutputStream dos = new DataOutputStream(os);

            // 6. クライアントにデータ送信
            String str1 = "こんにちは、あなたは " + i + " 番目のお客様です\n";
            String str2 = "ただいまサーバーの時間は " + (new Date()).toString() + " です\n";
            dos.writeUTF(str1); // UTF: Unicode Transformation Format
            dos.writeUTF(str2);

            // 7. データを送信したら一旦全てクローズ
            os.close();
            skt.close();
         }
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