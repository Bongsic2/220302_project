package main;

import java.net.InetAddress;

public class ServerIP {
	public String HOST;
	public ServerIP() {     
		try {           
			InetAddress ip = InetAddress.getLocalHost();
			HOST = ip.getHostAddress();
		}      
		catch (Exception e) {    
			System.out.println(e);     
		}   
	}
}
