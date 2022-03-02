package view;

import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import client.gameClient;
import main.R;
import model.CountDown;
import view.MenuBar.MainMenuBar;
import view.sounds.MusicBackGround;

public class MainFrame extends JFrame {
	private Image screenImage;
	private Graphics screenGraphic;
	
	public static MusicBackGround MainBackMusic = new MusicBackGround("../Resource/Sound/introMusic.mp3", true);

	public MainFrame() throws UnknownHostException {
		//new gameClient(); // 클라이언트 접속 
		new Default(this, R.ClientWidth, R.ClientHeight); // 클라이언트 프레임 디폴트 설정
		new MainMenuBar(this); // 클라이언트 상단 메뉴바 추가 메소드
		// 클라이언트 배경음악 재생
		MainBackMusic.start();

		JButton playButton = new JButton(R.MainPlayBtn);
		JButton QuitButton = new JButton(R.MainQuitBtn);
		JButton Single1Button = new JButton(R.MainSingle1Btn);
		JButton Single5Button = new JButton(R.MainSingle5Btn);
		JButton Single10Button = new JButton(R.MainSingle10Btn);
		JButton MultiButton = new JButton(R.MainMultiBtn);

		JLabel MultiButtonOff = new JLabel(R.MainMultiBtnOff);
		MultiButtonOff.setBounds(650, 504, 400, 204);

		JLabel UpdateButtonOff = new JLabel(R.MainUpdateBtnOff);
		UpdateButtonOff.setBounds(1100, 30, 120, 120);
		add(UpdateButtonOff);

		JButton UpdateButton = new JButton(R.MainUpdateBtn);
		UpdateButton.setBounds(1100, 30, 120, 120);
		UpdateButton.setBorderPainted(false);
		UpdateButton.setContentAreaFilled(false);
		UpdateButton.setFocusPainted(false);
		UpdateButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				MusicBackGround buttonSound = new MusicBackGround("../Resource/Sound/ButtonSound.mp3", false);
				buttonSound.start();
				new gameClient();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				UpdateButton.setIcon(R.MainUpdateBtn);
				UpdateButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				UpdateButton.setIcon(R.MainUpdateBtnMouseOver);
				UpdateButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
		});
		add(UpdateButton);

		// 인터넷 연결 상태 체크
		boolean isConnected = R.HOST.equals(InetAddress.getLocalHost().getHostAddress());
		if (isConnected) { // 연결이 되있으면
			UpdateButtonOff.setVisible(false);
			UpdateButton.setVisible(true);
			MultiButtonOff.setVisible(false);
			MultiButton.setVisible(true);
		} else { // 연결이 안되어있으면
			UpdateButtonOff.setVisible(true);
			UpdateButton.setVisible(false);
			MultiButtonOff.setVisible(true);
			MultiButton.setVisible(false);
		}

		playButton.setBounds(240, 500, 400, 204);
		playButton.setBorderPainted(false);
		playButton.setContentAreaFilled(false);
		playButton.setFocusPainted(false);
		playButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				MusicBackGround buttonSound = new MusicBackGround("../Resource/Sound/ButtonSound.mp3", false);
				buttonSound.start();
				playButton.setVisible(false);
				QuitButton.setVisible(false);
				add(Single1Button);
				add(Single5Button);
				add(Single10Button);
				add(MultiButton);
				add(MultiButtonOff);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				playButton.setIcon(R.MainPlayBtnMouseOver);
				playButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				playButton.setIcon(R.MainPlayBtn);
				playButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		});
		add(playButton);

		QuitButton.setBounds(660, 500, 400, 204);
		QuitButton.setBorderPainted(false);
		QuitButton.setContentAreaFilled(false);
		QuitButton.setFocusPainted(false);
		QuitButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				MusicBackGround buttonSound = new MusicBackGround("../Resource/Sound/ButtonSound.mp3", false);
				buttonSound.start();
				try {
					// 일정 시간 지난 후 시스템 종료
					Thread.sleep(100);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
				System.exit(0);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				QuitButton.setIcon(R.MainQuitBtnMouseOver);
				QuitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				QuitButton.setIcon(R.MainQuitBtn);
				QuitButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		});
		add(QuitButton);

		Single1Button.setBounds(200, 250, 400, 204);
		Single1Button.setBorderPainted(false);
		Single1Button.setContentAreaFilled(false);
		Single1Button.setFocusPainted(false);
		Single1Button.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				// 버튼음
				MusicBackGround buttonSound = new MusicBackGround("../Resource/Sound/ButtonSound.mp3", false);
				buttonSound.start();
				// 스타트음
				MusicBackGround StartMusic = new MusicBackGround("../Resource/Sound/StartMusic.mp3", false);
				StartMusic.start();

				// 3초 후, 싱글 1초로 음악맞추기 게임 시작
				MainFrame.MainBackMusic.close();
				CountDown com = new CountDown(3, 1, R.MAIN);
				Thread thcom = new Thread(com);
				thcom.start();
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				Single1Button.setIcon(R.MainSingle1BtnMouseOver);
				Single1Button.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				Single1Button.setIcon(R.MainSingle1Btn);
				Single1Button.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		});

		Single5Button.setBounds(650, 250, 400, 204);
		Single5Button.setBorderPainted(false);
		Single5Button.setContentAreaFilled(false);
		Single5Button.setFocusPainted(false);
		Single5Button.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				// 버튼음
				MusicBackGround buttonSound = new MusicBackGround("../Resource/Sound/ButtonSound.mp3", false);
				buttonSound.start();
				// 스타트음
				MusicBackGround StartMusic = new MusicBackGround("../Resource/Sound/StartMusic.mp3", false);
				StartMusic.start();
				// 3초 후, 싱글 5초로 음악맞추기 게임 시작
				MainFrame.MainBackMusic.close();
				CountDown com = new CountDown(3, 5, R.MAIN);
				Thread thcom = new Thread(com);
				thcom.start();
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				Single5Button.setIcon(R.MainSingle5BtnMouseOver);
				Single5Button.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				Single5Button.setIcon(R.MainSingle5Btn);
				Single5Button.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		});

		Single10Button.setBounds(200, 504, 400, 204);
		Single10Button.setBorderPainted(false);
		Single10Button.setContentAreaFilled(false);
		Single10Button.setFocusPainted(false);
		Single10Button.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				// 버튼음
				MusicBackGround buttonSound = new MusicBackGround("../Resource/Sound/ButtonSound.mp3", false);
				buttonSound.start();
				// 스타트음
				MusicBackGround StartMusic = new MusicBackGround("../Resource/Sound/StartMusic.mp3", false);
				StartMusic.start();
				// 3초 후, 싱글 10초로 음악맞추기 게임 시작
				MainFrame.MainBackMusic.close();
				CountDown com = new CountDown(3, 10, R.MAIN);
				Thread thcom = new Thread(com);
				thcom.start();
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				Single10Button.setIcon(R.MainSingle10BtnMouseOver);
				Single10Button.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				Single10Button.setIcon(R.MainSingle10Btn);
				Single10Button.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		});

		MultiButton.setBounds(650, 504, 400, 204);
		MultiButton.setBorderPainted(false);
		MultiButton.setContentAreaFilled(false);
		MultiButton.setFocusPainted(false);
		MultiButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				// 버튼음
				MusicBackGround buttonSound = new MusicBackGround("../Resource/Sound/ButtonSound.mp3", false);
				buttonSound.start();
				// 스타트음
				MusicBackGround StartMusic = new MusicBackGround("../Resource/Sound/StartMusic.mp3", false);
				StartMusic.start();
				// 3초 후, 멀티 음악맞추기 게임 시작
				MainFrame.MainBackMusic.close();
				CountDown com = new CountDown(3, 111, R.MAIN);
				Thread thcom = new Thread(com);
				thcom.start();
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				MultiButton.setIcon(R.MainMultiBtnMouseOver);
				MultiButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				MultiButton.setIcon(R.MainMultiBtn);
				MultiButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		});

	}

	// JFrame에 오브젝트 추가 메소드
	public void paint(Graphics g) {
		screenImage = createImage(R.ClientWidth, R.ClientHeight);
		screenGraphic = screenImage.getGraphics();
		screenDraw(screenGraphic);
		g.drawImage(screenImage, 0, 0, null);
	}

	public void screenDraw(Graphics g) {
		g.drawImage(R.MainBackground, 0, 0, null);
		paintComponents(g);
		this.repaint();
	}

}
