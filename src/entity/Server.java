package entity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;

public class Server {
	String path ;
	int number;
	char ch;
	
	public void runServer() {
		
		byte[] bytes = new byte[2048];
		
		
		System.out.println("Server started");
		try {
			
			ServerSocket serverSocket = new ServerSocket(10000);
			Socket socket = serverSocket.accept();
			InputStream in = socket.getInputStream();
			in.read(bytes);
			String msg = new String(bytes);
			parseMessage(msg);
			
			String strFile = readFromFile(path);
			String form = stringSolution(strFile, number, ch);
		
			OutputStream out = socket.getOutputStream();
			out.write(form.getBytes());
			
					
			
			System.out.println(form);
			System.out.println(path +", " + number + " ,"+ ch);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private String readFromFile(String path) {
		String strTmp = "";
		try {
			char[] buffer = new char[2048];
			BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
			while((bufferedReader.read(buffer))!=-1) {
				strTmp += new String(buffer);
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			
		}
		return strTmp;
		
	}
	int count =1 ;
	public String stringSolution(String str,int numberChar, char newChar) {
		String tmp ="";
		String[] arrString = str.split("\\s*(\\s|,|!|\\.)\\s*");
		for (int i = 0; i < arrString.length; i++) {
			if(arrString[i].length() >numberChar-1) {
				char[] chararr = arrString[i].trim().toCharArray();
				for (int j = 0; j < chararr.length; j++) {
					if(count == numberChar) {
						chararr[j] = newChar;
						arrString[i]= new String(chararr);
						count = 1;
					}else {count++;}	
				}
				count =1;
			}tmp+=arrString[i]+" ";
		}
		return tmp;
	}
	
	public void parseMessage(String msg) {
		String[] arr = msg.split("\\s+");
		System.out.println(Arrays.toString(arr));
		number=Integer.valueOf(arr[0]);
		ch = arr[1].charAt(0);
		path = arr[2].trim();
	}

	@Override
	public String toString() {
		return "Server [path=" + path + ", number=" + number + ", ch=" + ch + "]";
	}
	
}
