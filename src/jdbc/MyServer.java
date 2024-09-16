package jdbc;
import java.net.*;
import java.io.*;

public class MyServer {
	public static void main(String[] args)  {
		// TODO Auto-generated method stub
		int port=169;
		try (ServerSocket ss = new ServerSocket(port)) {
			System.out.println("Server is listening on port " + port);
			Socket s = ss.accept();
			System.out.println("Client Connected");
			
			InputStream i = s.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(i));
			
			String m = br.readLine();
            System.out.println("Received message: " + m);
            
    		OutputStream u = s.getOutputStream();
    		PrintWriter pw = new PrintWriter(u,true);
    		
            pw.println("Hello, Client!");
			
			s.close();
		} catch (IOException e) {
            System.out.println("Client exception: " + e.getMessage());
            e.printStackTrace();
		}
	}

}
