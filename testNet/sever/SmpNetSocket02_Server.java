import java.io.*;
import java.net.*;
import java.util.*;

class SmpNetSocket02_Server{
    public static void main(String args[]){
        try{
            // ポート番号の取得（実行ファイルのオプション入力から）
	    // parseInt は引数の文字列を10進数整数型に型変換する
            int nPort = Integer.parseInt(args[0]);
            if( nPort<1000 || nPort>65535 )
                throw new NumberFormatException();
            // 1. サーバーソケット (ServerSocket クラス) のインスタンス作成
            ServerSocket sskt = new ServerSocket(nPort);
            // i は接続しているクライアントの数
            for(int i=1; true; i++){
                // 2. Socket インスタンスを新たに作成し、
                //    クライアントからの接続に待機する (accept メソッド)
                //    ここでプログラムは一旦停止
                Socket skt = sskt.accept();

                // 3. クライアントからの接続があれば Socket クラスの戻値として
                //    Socket クラスのインスタンスが返ってくる。新しいクライアント
                //    インスタンスに対し新しいスレッドを作り、開始 (start) する
                (new ConnectionThread(skt)).start();
            }
        }catch(NumberFormatException  e){
            System.err.println("引数はポート番号です。1000〜65535までの数字を指定");
        }catch(IndexOutOfBoundsException  e){
            System.err.println("引数はポート番号です。1000〜65535までの数字を指定");
        }catch(IOException e){
            System.err.println("入出力エラーです\n" + e);
        }catch(Exception e){
            System.err.println(e);
        }
    }
}

// 接続されたクライアントに対し、スレッドのインスタンスが１つ作成される
class ConnectionThread extends Thread{
    private Socket skt;
    ConnectionThread(Socket skt){
        this.skt = skt;
    }
    public void run(){
        try{
            System.out.println(skt.getInetAddress().getHostName()+"から接続されました");
            // 4. ストリーム作成。getOutputStream の戻値は OutputStream クラス
            OutputStream os = skt.getOutputStream();
            // 5. ここでは DataOutputStream を用いる
            DataOutputStream dos = new DataOutputStream(os);
            // 送信する文字列を作成し
            String str = "サーバーの時刻は " + (new Date()).toString() + "です \n";
            // 出力ストリームにデータ送信
            dos.writeUTF(str);
            // 出力ストリームとソケットをクローズ
            os.close();
            skt.close();
        }catch(IOException e){
            System.err.println("入出力エラーです\n" + e);
        }catch(Exception e){
            System.err.println(e);
        }
    }
}