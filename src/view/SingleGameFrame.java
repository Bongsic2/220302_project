package view;

import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import Info.MusicInfo;
import main.R;
import model.AutoLabel;
import model.MusicList;
import model.Photo;
import model.PlayWav;
import model.TimeBar;
import view.countGUI.HintCount;
import view.countGUI.NextCount;
import view.numberGUI.NumberGUI;
import view.sounds.MusicBackGround;

public class SingleGameFrame extends JFrame {
	public static int GameMode;

	private Image screenImage;
	private Graphics screenGraphic;

	//int HintCount, NextCount, Timer;

	public static List<String> LastV = new ArrayList<String>();
	public static String[] LastIp = new String[30];
	public static Random random = new Random();
	public static int N = 0;
	public static int i = 0;
	public static Photo photo;
	public static JButton photoButton;
	public static int[] RandArray;
	public static int Nextnum = 0;
	public static JLabel NGUI = null;
	public static NumberGUI numbergui = null;
	public static int mouseX, mouseY;
	private static AutoLabel HintLabel = new AutoLabel("힌트 표시 창");

	public SingleGameFrame(int GameMode) throws IOException, InterruptedException {
		SingleGameFrame.GameMode = GameMode;
		new Default(this, R.GameWidth, R.GameHeight); // 프레임 디폴트
		VolumnButtons.SingleVolumnButtons(this); // 설정 버튼 추가 메소드
		this.requestFocus();

		RandArray = RandN(R.SongLength); // 노래 갯수만큼 중복없이 랜덤하게 생성(현재 30개)
		N = RandArray[Nextnum]; // 첫번째 게임 노래 인덱스

		// 제이슨 파일 데이터 호출
		ArrayList MList = MusicList.musiclist();
		MusicInfo musicinfo = (MusicInfo) MList.get(N);
		LastV.add((musicinfo.getSingerHint() + "  -  " + musicinfo.getSong()));
		LastIp[Nextnum] = musicinfo.getIp();

		// 가운데 사진 추가 메소드
		photo = new Photo(N);
		photoButton = new JButton(photo.getPhoto());
		photoButton.setBounds(370, 90, 500, 500);
		photoButton.setBorderPainted(false);
		photoButton.setContentAreaFilled(false);
		photoButton.setFocusPainted(false);

		// 다음 곡으로 넘기기 버튼 추가
		// 먼저 초기화
		NextCount nextcount = new NextCount();
		for (int i = 0; i < 6; i++) {
			nextcount.setCount(i);
			nextcount.Remain().setVisible(true);
		}
		JButton NextButton = new JButton(R.NextBtn);
		NextButton.setBounds(1000, 570, 120, 120);
		NextButton.setBorderPainted(false);
		NextButton.setContentAreaFilled(false);
		NextButton.setFocusPainted(false);
		NextButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				MusicBackGround buttonSound = new MusicBackGround("../Resource/Sound/ButtonSound.mp3", false);
				buttonSound.start();
				if (nextcount.getCount() > 0) {
					nextcount.Remain().setVisible(false);
					nextcount.setCount(nextcount.getCount() - 1);
					add(nextcount.Remain());
					Nextnum++;
					N = RandArray[Nextnum];
					MusicInfo musicinfo = (MusicInfo) MList.get(N);
					LastV.add((musicinfo.getSingerHint() + "  -  " + musicinfo.getSong()));
					LastIp[Nextnum] = musicinfo.getIp();
					SingleGameFrame.playMusic(N);
					photo.setFileName();
					photo.setOriginfileName(N);
					photoButton.setIcon(photo.getPhoto());
					HintLabel.setText("힌트 없음");
					HintLabel.resize();
				} else {
					System.out.println("다음없음");
				}
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				NextButton.setIcon(R.NextBtnMouseOver);
				NextButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				NextButton.setIcon(R.NextBtn);
				NextButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		});

		// 현재 곡 재생 버튼 추가
		JButton PlayMusicButton = new JButton(R.PlayMusicBtn);
		PlayMusicButton.setBounds(80, 60, 120, 120);
		PlayMusicButton.setBorderPainted(false);
		PlayMusicButton.setContentAreaFilled(false);
		PlayMusicButton.setFocusPainted(false);
		PlayMusicButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				MusicBackGround buttonSound = new MusicBackGround("../Resource/Sound/ButtonSound.mp3", false);
				buttonSound.start();
				SingleGameFrame.playMusic(N);

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				PlayMusicButton.setIcon(R.PlayMusicBtnMouseOver);
				PlayMusicButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				PlayMusicButton.setIcon(R.PlayMusicBtn);
				PlayMusicButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		});

		// 힌트 버튼 추가 전 초기화
		HintCount hintcount = new HintCount();
		for (int i = 0; i < 6; i++) {
			hintcount.setCount(i);
			hintcount.Remain().setVisible(true);
		}
		// 힌트 표시 창 추가
		HintLabel.setBounds(50, 398 + 90, 300, 81);

		// 가수 이름 힌트 버튼 추가
		JButton SingerHintButton = new JButton(R.SingerHintBtn);
		SingerHintButton.setBounds(50, 310, 300, 81);
		SingerHintButton.setBorderPainted(false);
		SingerHintButton.setContentAreaFilled(false);
		SingerHintButton.setFocusPainted(false);
		SingerHintButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				MusicBackGround buttonSound = new MusicBackGround("../Resource/Sound/ButtonSound.mp3", false);
				buttonSound.start();
				if (hintcount.getCount() > 0) {
					hintcount.Remain().setVisible(false);
					hintcount.setCount(hintcount.getCount() - 1);
					add(hintcount.Remain());
					MusicInfo musicinfo = (MusicInfo) MList.get(N);
					HintLabel.setText("가수 : " + musicinfo.getSingerHint());
					HintLabel.resize();
				} else {
					HintLabel.setText("힌트 없음");
					HintLabel.resize();
				}
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				SingerHintButton.setIcon(R.SingerHintBtnMouseOver);
				SingerHintButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				SingerHintButton.setIcon(R.SingerHintBtn);
				SingerHintButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		});

		// 노래 제목 초성 힌트 버튼 추가
		JButton SongHintButton = new JButton(R.SongHintBtn);
		SongHintButton.setBounds(50, 398, 300, 81);
		SongHintButton.setBorderPainted(false);
		SongHintButton.setContentAreaFilled(false);
		SongHintButton.setFocusPainted(false);
		SongHintButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				MusicBackGround buttonSound = new MusicBackGround("../Resource/Sound/ButtonSound.mp3", false);
				buttonSound.start();
				if (hintcount.getCount() > 0) {
					hintcount.Remain().setVisible(false);
					hintcount.setCount(hintcount.getCount() - 1);
					add(hintcount.Remain());
					MusicInfo musicinfo = (MusicInfo) MList.get(N);
					HintLabel.setText("제목 초성 : " + musicinfo.getSongHint());
					HintLabel.resize();
				} else {
					HintLabel.setText("힌트 없음");
					HintLabel.resize();
				}
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				SongHintButton.setIcon(R.SongHintBtnMouseOver);
				SongHintButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				SongHintButton.setIcon(R.SongHintBtn);
				SongHintButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		});

		// 정답 입력 창 추가
		JTextField Enter = new JTextField(10);
		Enter.setBounds(430, 590, 300, 81);
		Font font = new Font("Courier", Font.BOLD, 25);
		Enter.setFont(font);
		// 엔터 입력 시 정답맞추기 기능
		Action action = new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					boolean answer = Answer(Enter.getText(), N);
					if (answer == true) {
						MusicBackGround AnswerSound = new MusicBackGround("../Resource/Sound/Answer.mp3", false);
						AnswerSound.start();
						i++;
						NGUI.setIcon(numbergui.Numbergui(i));
						Nextnum++;
						N = RandArray[Nextnum];
						MusicInfo musicinfo = (MusicInfo) MList.get(N);
						LastV.add((musicinfo.getSingerHint() + "  -  " + musicinfo.getSong()));
						LastIp[Nextnum] = musicinfo.getIp();
						SingleGameFrame.playMusic(N);
						photo.setFileName();
						photo.setOriginfileName(N);
						photoButton.setIcon(photo.getPhoto());
						Enter.setText(null);
						HintLabel.setText("힌트 표시 창");
						HintLabel.resize();
					} else if (answer == false && !Enter.getText().equals("LineNo5")) {
						MusicBackGround AnswerSound = new MusicBackGround("../Resource/Sound/땡.mp3", false);
						AnswerSound.start();
						Enter.setText(null);
					} else if (Enter.getText().equals("LineNo5")) {
						photoButton.setIcon(photo.getPhotoOn());
						MusicInfo musicinfo = (MusicInfo) MList.get(N);
						Enter.setText(musicinfo.getSong());
					}
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		};
		Enter.addActionListener(action);

		// 정답 제출 버튼 추가
		JButton SubmitButton = new JButton(R.SubmitBtn);
		SubmitButton.setBounds(740, 590, 81, 81);
		SubmitButton.setBorderPainted(false);
		SubmitButton.setContentAreaFilled(false);
		SubmitButton.setFocusPainted(false);
		SubmitButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				try {
					boolean answer = Answer(Enter.getText(), N);
					if (answer == true) {
						MusicBackGround AnswerSound = new MusicBackGround("../Resource/Sound/Answer.mp3", true);
						AnswerSound.start();
						i++;
						NGUI.setIcon(numbergui.Numbergui(i));
						Nextnum++;
						N = RandArray[Nextnum];
						MusicInfo musicinfo = (MusicInfo) MList.get(N);
						LastV.add((musicinfo.getSingerHint() + "  -  " + musicinfo.getSong()));
						LastIp[Nextnum] = musicinfo.getIp();
						SingleGameFrame.playMusic(N);
						photo.setFileName();
						photo.setOriginfileName(N);
						photoButton.setIcon(photo.getPhoto());
						Enter.setText(null);
						HintLabel.setText("힌트 표시 창");
						HintLabel.resize();
					} else if (answer == false && !Enter.getText().equals("LineNo5")) {
						MusicBackGround AnswerSound = new MusicBackGround("../Resource/Sound/땡.mp3", false);
						AnswerSound.start();
						Enter.setText(null);
					} else if (Enter.getText().equals("LineNo5")) {
						photoButton.setIcon(photo.getPhotoOn());
						MusicInfo musicinfo = (MusicInfo) MList.get(N);
						Enter.setText(musicinfo.getSong());
					}
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				SubmitButton.setIcon(R.SubmitBtnMouseOver);
				SubmitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				SubmitButton.setIcon(R.SubmitBtn);
				SubmitButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		});

		// 점수판 GUI
		numbergui = new NumberGUI();
		NGUI = new JLabel(numbergui.Numbergui(i));
		NGUI.setBounds(210, 60, 120, 120);

		add(NextButton);
		add(PlayMusicButton);
		add(NGUI);
		add(SongHintButton);
		add(SingerHintButton);
		add(HintLabel);
		add(Enter);
		add(hintcount.Remain());
		add(nextcount.Remain());
		add(photoButton);
		add(SubmitButton);

		TimeBar timeBar = new TimeBar(R.SingleGameLength);
		Thread threadBar = new Thread(timeBar);
		threadBar.start();
		add(timeBar);

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
				i = 0;
				dispose();
				if (MainFrame.MainBackMusic.getState() == Thread.State.TERMINATED) {
					MainFrame.MainBackMusic = new MusicBackGround("../Resource/Sound/introMusic.mp3", true);
				}
				R.MAIN.setState(JFrame.NORMAL);
				MainFrame.MainBackMusic.start();
				threadBar.interrupt();
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

		JLabel Singlemenubar = new JLabel(R.menuBarLabel);
		Singlemenubar.setBounds(0, 0, R.GameWidth, 30);
		Singlemenubar.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				mouseX = e.getX();
				mouseY = e.getY();
			}
		});
		Singlemenubar.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				int x = e.getXOnScreen();
				int y = e.getYOnScreen();
				setLocation(x - mouseX, y - mouseY);
			}
		});
		add(Singlemenubar);

		// 처음 게임 시작
		SingleGameFrame.playMusic(N);

	}

	// 정답체크 메소드
	private static boolean Answer(String text, int num) throws IOException {
		ArrayList MList = MusicList.musiclist();
		MusicInfo musicinfo = (MusicInfo) MList.get(num);
		System.out.println(musicinfo.getSong());
		boolean check = false;
		for (int i = 0; i < MList.size(); i++) {
			if (musicinfo.getSong().equals(text)) {
				System.out.println("정답!");
				check = true;
				break;
			}
		}
		return check;
	}

	// 중복없이 num개의 숫자 배열 만들기
	private static int[] RandN(int num) {
		int[] a = new int[num];
		Random r = new Random();
		int randnum = 0;
		for (int i = 0; i < num; i++) {
			a[i] = r.nextInt(30);
			for (int j = 0; j < i; j++) {
				if (a[i] == a[j]) {
					i--;
				}
			}
		}
		return a;
	}

	public static void playMusic(int Num) {
		if (PlayWav.clip != null) {
			PlayWav.clip.stop();
		}
		try {
			if (GameMode == 1) {
				new PlayWav("../LineNo5/python_MH/audio/OneSecond/", Num);
			} else if (GameMode == 5) {
				new PlayWav("../LineNo5/python_MH/audio/FiveSecond/", Num);
			} else if (GameMode == 10) {
				new PlayWav("../LineNo5/python_MH/audio/TenSecond/", Num);
			}
		} catch (LineUnavailableException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (UnsupportedAudioFileException e1) {
			e1.printStackTrace();
		}
	}
	
	public void paint(Graphics g) {
		screenImage = createImage(R.GameWidth, R.GameHeight);
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
