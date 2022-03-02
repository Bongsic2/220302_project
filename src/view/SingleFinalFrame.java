package view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.plaf.basic.BasicScrollBarUI;

import main.R;
import model.CountDown;
import model.PlayWav;
import view.sounds.MusicBackGround;

public class SingleFinalFrame extends JFrame {

	private Image SCI;
	private Graphics SCG;
	
	private JLabel ScoreBackGround = new JLabel(R.jumReal);
	private JLabel ScoreLabel = new JLabel(R.ok2);
	private JLabel PlayerName = new JLabel(R.user);
	public static JLabel FinalCountDown = new JLabel("카운트 다운 시작 준비...");
	
	int index;
	JLabel PlayList;
	JButton finalExit, Retry, GoMain;
	JList MusicList;
	
	public static int mouseX, mouseY;
	
	public SingleFinalFrame() {
		// 배경 음악
		MusicBackGround BackGroundMusic = new MusicBackGround("../Resource/Sound/FinalBackGroundMusic.mp3", true);
		BackGroundMusic.start(); // 배경음악 재생
		new Default(this, R.GameWidth, R.GameHeight+20); // 프레임 디폴트
		
		// 메뉴바
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
				if(PlayWav.clip != null) {
					PlayWav.clip.stop();
				}
				BackGroundMusic.close();
				dispose();
				SingleGameFrame.LastV.clear();
				SingleGameFrame.numbergui.setN(0);
				SingleGameFrame.i = 0;
				SingleGameFrame.GameMode = 0;
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

		JLabel SingleFianlmenubar = new JLabel(R.menuBarLabel);
		SingleFianlmenubar.setBounds(0, 0, R.GameWidth, 30);
		SingleFianlmenubar.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				mouseX = e.getX();
				mouseY = e.getY();
			}
		});
		SingleFianlmenubar.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				int x = e.getXOnScreen();
				int y = e.getYOnScreen();
				setLocation(x - mouseX, y - mouseY);
			}
		});
		add(SingleFianlmenubar);
		
		
		FinalCountDown.setBounds(310, 100, 660, 160);
		FinalCountDown.setHorizontalAlignment(JLabel.CENTER);
		FinalCountDown.setOpaque(true);
		FinalCountDown.setBackground(new Color(0, 0, 0, 250));
		FinalCountDown.setForeground(Color.WHITE);
		FinalCountDown.setFont(new Font("Serif", Font.BOLD, 60));
		FinalCountDown.setVisible(false);
		add(FinalCountDown);

		JLabel Score = SingleGameFrame.NGUI;
		Score.setBounds(330, 315, SingleGameFrame.NGUI.getWidth(), SingleGameFrame.NGUI.getHeight());
		add(Score);

		ScoreLabel.setBounds(90, 165, 600, 100);
		add(ScoreLabel);

		PlayerName.setBounds(330, 500, 100, 50);
		PlayerName.setFont(new Font("굴림", Font.BOLD, 30));
		PlayerName.setForeground(Color.white);
		add(PlayerName);

		ScoreBackGround.setBounds(80, 200, 600, 400);
		ScoreBackGround.setBorder(null);
		ScoreBackGround.setOpaque(true);
		ScoreBackGround.setBackground(new Color(0, 0, 0, 100));
		add(ScoreBackGround);

		
		finalExit = new JButton(R.ExitBtn2);
		finalExit.setBounds(80, 600, 200, 104);
		finalExit.setBorderPainted(false);
		finalExit.setContentAreaFilled(false);
		finalExit.setFocusPainted(false);
		finalExit.addMouseListener(new MouseAdapter() {
			@Override
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
				finalExit.setIcon(R.ExitBtn2MouseOver);
				finalExit.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				finalExit.setIcon(R.ExitBtn2);
				finalExit.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		});
		add(finalExit);

		Retry = new JButton(R.RetryBtn);
		Retry.setBounds(80+210, 600, 200, 104);
		Retry.setBorderPainted(false);
		Retry.setContentAreaFilled(false);
		Retry.setFocusPainted(false);
		Retry.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				MusicBackGround buttonSound = new MusicBackGround("../Resource/Sound/ButtonSound.mp3", false);
				buttonSound.start();
				// 스타트음
				MusicBackGround StartMusic = new MusicBackGround("../Resource/Sound/StartMusic.mp3", false);
				StartMusic.start();
				BackGroundMusic.close();
				dispose();
				R.MAIN.setState(JFrame.NORMAL);
				SingleGameFrame.LastV.clear();
				SingleGameFrame.numbergui.setN(0);
				SingleGameFrame.i = 0;
				CountDown com = new CountDown(3, SingleGameFrame.GameMode, R.MAIN);
				Thread thcom = new Thread(com);
				thcom.start();
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				Retry.setIcon(R.RetryBtnMouseOver);
				Retry.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				Retry.setIcon(R.RetryBtn);
				Retry.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		});
		add(Retry);

		GoMain = new JButton(R.GoMainBtn);
		GoMain.setBounds(80+210+210, 600, 200, 104);
		GoMain.setBorderPainted(false);
		GoMain.setContentAreaFilled(false);
		GoMain.setFocusPainted(false);
		GoMain.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				MusicBackGround buttonSound = new MusicBackGround("../Resource/Sound/ButtonSound.mp3", false);
				buttonSound.start();
				BackGroundMusic.close();
				dispose();
				R.MAIN.setState(JFrame.NORMAL);
				SingleGameFrame.LastV.clear();
				SingleGameFrame.numbergui.setN(0);
				SingleGameFrame.i = 0;
				SingleGameFrame.GameMode = 0;
				if (MainFrame.MainBackMusic.getState() == Thread.State.TERMINATED) {
					MainFrame.MainBackMusic = new MusicBackGround("../Resource/Sound/introMusic.mp3", true);
				}
				MainFrame.MainBackMusic.start();
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				GoMain.setIcon(R.GoMainBtnMouseOver);
				GoMain.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				GoMain.setIcon(R.GoMainBtn);
				GoMain.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		});
		add(GoMain);

		PlayList = new JLabel("Play List");
		PlayList.setBounds(700, 200, 300, 50);
		PlayList.setFont(new Font("굴림", Font.BOLD, 30));
		PlayList.setForeground(Color.white);
		add(PlayList);

		MusicList = new JList(SingleGameFrame.LastV.toArray());
		MusicList.setFont(new Font("굴림", Font.PLAIN, 20));
		MusicList.setForeground(Color.white);
		MusicList.setOpaque(true);
		MusicList.setBackground(new Color(0, 0, 0, 100));
		JScrollPane JS = new JScrollPane(MusicList);
		JS.setBounds(700, 250, 500, 350);
		JS.setOpaque(true);
		JS.setBackground(new Color(0, 0, 0, 100));
		JS.getHorizontalScrollBar().setOpaque(true);
		JS.getHorizontalScrollBar().setBackground(new Color(0, 0, 0, 100));
		JS.setBorder(null);
		JS.getHorizontalScrollBar().setUI(new BasicScrollBarUI() {
			@Override // 스크롤 휠 색상
			protected void configureScrollBarColors() {
				this.thumbColor = new Color(255, 255, 255, 100);
			}

			@Override // 스크롤 버튼 색상
			protected void paintTrack(Graphics g, JComponent c, Rectangle trackBounds) {
				c.setBackground(new Color(255, 255, 255, 100));
			}
		});

		add(JS);

		MusicList.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				index = MusicList.locationToIndex(evt.getPoint());
				MusicList = (JList) evt.getSource();
				if (evt.getClickCount() == 2) {
					String url = SingleGameFrame.LastIp[index];
					try {
						Desktop.getDesktop().browse(new URI(url));
					} catch (IOException e) {
						e.printStackTrace();
					} catch (URISyntaxException e) {
						e.printStackTrace();
					}
				}
			}
		});

	}

	public void paint(Graphics g) {
		SCI = createImage(R.GameWidth, R.GameHeight+20);
		SCG = SCI.getGraphics();
		screenDraw(SCG);
		g.drawImage(SCI, 0, 0, null);

	}

	public void screenDraw(Graphics g) {
		g.drawImage(R.Gamebackground, 0, 0, null);
		paintComponents(g);
		this.repaint();
	}

}