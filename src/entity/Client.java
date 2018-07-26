package entity;

import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {
	private Scanner scanner;
	private String task;
	private String path;

	public void runClient() {
		Socket socket = null;
		InputStream in =null;
		OutputStream out = null;
		byte[] bytArr = new byte[2048];
		try {
			socket = new Socket("localhost",10000);
			askUser();
			
			String sendMes= task+" "+path;
			out = socket.getOutputStream();
			out.write(sendMes.getBytes());
			
			in = socket.getInputStream();
			in.read(bytArr);
			String tmp = new String(bytArr);
			
			FileOutputStream outFile = new FileOutputStream("C:\\MyFiles\\Programming\\TrainingFiles\\FromClient.txt");
			outFile.write(tmp.getBytes());
			
			System.out.println("Stringa  "+tmp);
			System.out.println(toString());
			
			
			
		} catch (IOException e) {
		
			e.printStackTrace();
		}
	}
	
	public void askUser() {
		scanner = new Scanner(System.in);
		System.out.println("Hello User");
		System.out.println("Enter the symbol number and symbol");
		task = scanner.nextLine();
		System.out.println("Enter path to file");
		path = scanner.next();
		
	}

	@Override
	public String toString() {
		return "Client ["+" task=" + task + ", path=" + path + "]";
	} 
	
}
