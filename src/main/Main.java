package main;

import java.net.InetAddress;
import java.net.UnknownHostException;
import view.MainFrame;

public class Main {
	public static void main(String[] args) throws UnknownHostException {
		// 추후 메인 화면을 컨트롤하기 위해 클라이언트를 변수로 받아 실행
		R.MAIN = new MainFrame(); 
	}

}
