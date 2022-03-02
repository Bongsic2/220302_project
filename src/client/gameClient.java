package client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import main.R;
import server.Test;

public class gameClient {
	private static Socket socket;
	private static BufferedWriter bw;
	private static BufferedReader br;
	
	public static BufferedWriter getBw() {
		return bw;
	}
	public static BufferedReader getBr() {
		return br;
	}
	public static void setBr(BufferedReader br) {
		gameClient.br = br;
	}
	public static Socket getSocket() {
		return socket;
	}
 
	//private String serverIp = "192.168.100.115";
	private String serverIp = R.HOST;
	Scanner scan = new Scanner(System.in);
	
	public gameClient() { 
		try { 
			// 클라이언트 실행시 서버와 접속.			
			socket = new Socket(serverIp, 8888);
			System.out.println("접속 되었습니다");
			
			//서버와 통신할 br bw 설정
			br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			
			String userId = "김씨";
			
			bw.write(userId+"\n");
			bw.flush();
			
			//메세지를 받는다
			R.threadList.add(new gameClientReadMsg(br));
			//new gameClientReadMsg(br);
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// 소켓 접속이 끊길 시 close()를 해줘서 예외 차단
			if(socket != null)
				try {
					scan.close();
					br.close();
					bw.close();
					socket.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		}
	}

}
