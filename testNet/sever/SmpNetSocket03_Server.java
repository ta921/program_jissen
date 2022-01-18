import java.net.Socket;
import java.net.ServerSocket;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.IOException;
 
public class SmpNetSocket03_Server {
    public static final int ECHO_PORT = 10007; // 通信に使用するポート
    public static void main(String args[]) {
	ServerSocket sskt = null;      // クラスオブジェクトの宣言
	Socket skt = null;             // クラスオブジェクトの宣言
	try {
	    // 1. ポート番号を指定してサーバー用 ServerSocket のインスタンス作成
	    sskt = new ServerSocket(ECHO_PORT); 
	    System.out.println("EchoServerが起動しました(port=" + sskt.getLocalPort() + ")");
	    // 2. クライアントからの接続待ち開始（accept メソッド) 
	    //    クライアントからの接続があるまでここで一時停止
	    skt = sskt.accept();

            // 3. クライアントからアクセスがあると accept メソッドの戻値として
            //     Socket クラスのオブジェクトが返ってくる
	    System.out.println("接続されました " + skt.getRemoteSocketAddress() );
            // 4. 出来た Socket クラスのインスタンスを使って入出力ストリームを得る
	    BufferedReader in = new BufferedReader(new InputStreamReader(skt.getInputStream()));
	    PrintWriter out = new PrintWriter(skt.getOutputStream(), true);
	    String line;
            // 6. 入出力ストリームを使って通信開始
	    while ( (line = in.readLine()) != null ) {
		System.out.println("受信: " + line);
		out.println(line);
		System.out.println("送信: " + line);
	    }
	} catch (IOException e) {
	    e.printStackTrace();
	} finally {
	    try {
		if (skt != null) {
		    skt.close();
		}
	    } catch (IOException e) {}
	    try {
		if (sskt != null) {
		    sskt.close();
		}
	    } catch (IOException e) {}
	}
    }
 
}