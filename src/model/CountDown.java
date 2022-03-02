package model;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import main.Main;
import main.R;
import view.MultiGameFrame;
import view.SingleGameFrame;

public class CountDown extends JLabel implements Runnable {
	JLabel CountDownLabel = new JLabel("카운트 다운 시작 준비...");
	JLabel label;
	int sc, n;

	public CountDown(int sc, int n, JFrame jFrame) {
		CountDownLabel.setBounds(310, 100, 660, 160);
		CountDownLabel.setHorizontalAlignment(JLabel.CENTER);
		CountDownLabel.setOpaque(true);
		CountDownLabel.setBackground(new Color(0,0,0,235));
		CountDownLabel.setForeground(Color.WHITE);
		CountDownLabel.setFont(new Font("Serif",Font.BOLD,60));
		jFrame.add(CountDownLabel);
		this.sc = sc;
		this.n = n;
		
		// GIF 추가
		label = new JLabel(R.PointIcon);
		label.setBounds(310-160, 100, 160, 160);
		jFrame.add(label);
	}

	@Override
	public void run() {
		while (true) {
			try {
				if (sc != 3) {
					Thread.sleep(1000); // 1초
				 }
			 }catch (Exception e) {
				 e.printStackTrace();
			   }
			
			if (sc > 0) {
				CountDownLabel.setText(sc + "초후에 시작합니다.\n준비하세요");
				sc--;
			} else {
				try {
					if(n==1) {
						R.GameMAIN = new SingleGameFrame(1);
					} else if(n==5) {
						R.GameMAIN = new SingleGameFrame(5);
					} else if(n==10) {
						R.GameMAIN = new SingleGameFrame(10);
					} else {
						R.GameMAIN = new MultiGameFrame();
					}
					R.MAIN.setState(JFrame.ICONIFIED);
					
				} catch (IOException e1) {
					e1.printStackTrace();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				CountDownLabel.setVisible(false);
				label.setVisible(false);
				return;
			  }
        }
	
	}
}
