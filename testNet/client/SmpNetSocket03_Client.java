import java.net.Socket;
import java.net.ServerSocket;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.IOException;
 
public class SmpNetSocket03_Client {
    public static final int ECHO_PORT = 10007; // 通信に利用するポート番号
    public static void main(String args[]) {
	Socket socket = null;
	try {
	    // 1. ソケットのインスタンスを作成 (引数はホスト名とポート番号)
	    socket = new Socket(args[0], ECHO_PORT);
	    // 接続先のソケット情報を表示
	    System.out.println("接続しました" + socket.getRemoteSocketAddress());
	    // 2. Socket インスタンスから入出力ストリームを得る
	    //    ここではキーボード入力を受け取るための Reader 作成
	    BufferedReader in    = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	    PrintWriter    out   = new PrintWriter(socket.getOutputStream(), true);
	    BufferedReader keyIn = new BufferedReader(new InputStreamReader(System.in));

	    String input;
            // 空行が入力されるまで続ける
	    while ( (input = keyIn.readLine()).length() > 0 ) {
                // サーバーに入力した文字列を送る
		out.println(input); 
                // サーバーから送られてくる文字列を受け取り、表示
		String line = in.readLine(); 
		if (line != null) {
		    System.out.println(line);
		} else {
		    break;
		}
	    }
	} catch (IOException e) {
	    e.printStackTrace();
	} finally {
	    try {
		if (socket != null) {
		    socket.close();
		}
	    } catch (IOException e) {}
	    System.out.println("切断されました " + socket.getRemoteSocketAddress());
	}
    }
}