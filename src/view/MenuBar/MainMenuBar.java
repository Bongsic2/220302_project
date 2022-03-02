package view.MenuBar;

import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import main.R;
import view.sounds.MusicBackGround;

public class MainMenuBar {
	public static int mouseX, mouseY;
	
	public MainMenuBar(JFrame jframe) {
		JButton exitButton = new JButton(R.ExitBtn);
		exitButton.setBounds(R.ClientWidth-35, 0, 30, 30);
		exitButton.setBorderPainted(false);
		exitButton.setContentAreaFilled(false);
		exitButton.setFocusPainted(false);
		exitButton.addMouseListener(new MouseAdapter() {
			@Override //버튼 클릭 후 클릭음이 들려야하기 때문에 어느정도 시간간격을 두고 프로그램 종료
			public void mousePressed(MouseEvent e) {
				MusicBackGround buttonSound = new MusicBackGround("../Resource/Sound/ButtonSound.mp3", false);
				buttonSound.start();
				try {
					Thread.sleep(100);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
				System.exit(0);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				exitButton.setIcon(R.ExitBtnMouseOver);
				exitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				exitButton.setIcon(R.ExitBtn);
				exitButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		});
		jframe.add(exitButton);

		
		// 메뉴바 드래그시 프레임 이동
		JLabel menuBar = new JLabel(R.menuBarLabel);
		menuBar.setBounds(0, 0, R.ClientWidth, 30);
		menuBar.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				mouseX = e.getX();
				mouseY = e.getY();
			}
		});
		menuBar.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				int x = e.getXOnScreen();
				int y = e.getYOnScreen();
				jframe.setLocation(x - mouseX, y - mouseY);
			}
		});
		jframe.add(menuBar);
	}
}
