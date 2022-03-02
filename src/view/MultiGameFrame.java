package view;

import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import client.gameClient;
import main.R;
import model.PlayWav;
import view.sounds.MusicBackGround;

public class MultiGameFrame extends JFrame {
	private Image screenImage;
	private Graphics screenGraphic;
	public static int mouseX, mouseY;

	public MultiGameFrame() {
		new gameClient(); // 클라이언트 접속
		new Default(this, R.GameWidth, R.GameHeight); // 프레임 디폴트
		MultiGameButtons.AddMultiGameButtons(this); // 멀티 게임 버튼 추가 메소드
		//VolumnButtons.MultiVolumnButtons(this); // 설정 버튼 추가 메소드

		// 오른쪽 상단 종료 버튼 추가
		JButton exitButton = new JButton(R.ExitBtn);
		exitButton.setBounds(1160, 0, 30, 30);
		exitButton.setBorderPainted(false);
		exitButton.setContentAreaFilled(false);
		exitButton.setFocusPainted(false);
		exitButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				MusicBackGround buttonSound = new MusicBackGround("../Resource/Sound/ButtonSound.mp3", false);
				buttonSound.start();
				try {
					Thread.sleep(100);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
				if (PlayWav.clip != null) {
					PlayWav.clip.stop();
				}
				dispose();
				if (MainFrame.MainBackMusic.getState() == Thread.State.TERMINATED) {
					MainFrame.MainBackMusic = new MusicBackGround("../Resource/Sound/introMusic.mp3", true);
				}
				R.MAIN.setState(JFrame.NORMAL);
				MainFrame.MainBackMusic.start();
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
		add(exitButton);

		JLabel Multimenubar = new JLabel(R.menuBarLabel);
		Multimenubar.setBounds(0, 0, R.GameWidth, 30);
		Multimenubar.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				mouseX = e.getX();
				mouseY = e.getY();
			}
		});
		Multimenubar.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				int x = e.getXOnScreen();
				int y = e.getYOnScreen();
				setLocation(x - mouseX, y - mouseY);
			}
		});
		add(Multimenubar);
	}

	public static void playMusic() {
		try {
			new PlayWav("/python_MH/audio/OneSecond/",1);
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		}
	}
	
	public void paint(Graphics g) {
		screenImage = createImage(R.GameWidth, R.GameHeight+200);
		screenGraphic = screenImage.getGraphics();
		screenDraw(screenGraphic);
		g.drawImage(screenImage, 0, 0, null);
	}

	public void screenDraw(Graphics g) {
		g.drawImage(R.Gamebackground, 0, 0, null);
		paintComponents(g);
		this.repaint();
	}
}
