package jdbc;
import java.net.*;
import java.io.*;

public class MyClient {
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		String host ="localhost";
		int port = 169;
		Socket s = new Socket(host,port);
		
		OutputStream u = s.getOutputStream();
		PrintWriter pw = new PrintWriter(u,true);
		
        pw.println("Hello, Server!");
        
		InputStream i = s.getInputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(i));
		
		String m = br.readLine();
        System.out.println("Received message: " + m);

		
		s.close();
	}

}
