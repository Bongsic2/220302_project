package multiFinalFrame;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.BufferedWriter;
import java.net.URI;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextArea;

import Info.MusicInfo;
import client.gameClient;
import client.gameUserList;
import client.scoreAll;
import main.R;
import view.MainFrame;
import view.MultiGameButtons;
import view.sounds.MusicBackGround;

public class JFRAME extends JFrame {
	private static String answer;
	private static BufferedWriter bw = gameClient.getBw();
	private static String readChatting;
	public static JTextArea chatChang;
	private Image SCI;
	private Graphics SCG; 
	private Image FinalBackGround = R.Gamebackground;
	private JLabel menuB = new JLabel(R.menuBarLabel);
	private JButton exit = new JButton(R.ExitBtn);
	private int mouseX, mouseY;
	// private JFrame fd = MultiGameButtons.useField();
	public static MusicBackGround introMusic = new MusicBackGround("../Resource/Sound/introMusic.mp3", true);
	public static List<String> SongSinger = new ArrayList<String>();
	public static List<String> userlist = new ArrayList<String>();
	public static String uri[] = new String[10];
	public static HashMap<String, Integer> uScore;
	public static List<String> set;
	private ArrayList<MusicInfo> list;
	
	// 유저 랭킹 4명 변수
	public static String rank1 = "";
	public static String rank2 = "";
	public static String rank3 = "";
	public static String rank4 = "";

	// 유저 랭킹 4명 라벨
	public static JLabel rankLabel1;
	public static JLabel rankLabel2;
	public static JLabel rankLabel3;
	public static JLabel rankLabel4;
	
	public JFRAME() {

	}

	public JFRAME(ArrayList<MusicInfo> listInfo) {
		this.list = listInfo;
		MultiGameButtons.setReadChatting("");
		introMusic.start();
		getList();
		getuserScore();
		getuserlist();
		initialize();
	}
	public void getuserScore() {
		uScore = scoreAll.userScore;
		set = new ArrayList<>(uScore.keySet());
		String[] str = new String[set.size()];

		set.sort((o1, o2) -> uScore.get(o2) - uScore.get(o1));

		for (int i = 0; i < str.length; i++) {
			str[i] = String.format("ID : %s - Score : %s", set.get(i), uScore.get(set.get(i)));
			if (!rank1.contains(str[0])) {
				rank1 = str[0];
			} else if (!rank2.contains(str[1])) {
				rank2 = str[1];
			} else if (!rank3.contains(str[2])) {
				rank3 = str[2];
			} else if (!rank4.contains(str[3])) {
				rank4 = str[3];
			}

		}
	}

	public void getuserlist() {
		for (int i = 0; i < gameUserList.gameUserName.size(); i++) {
			userlist.add(gameUserList.gameUserName.get(i));
		}
	}

	public void getList() {
		for (int i = 0; i < list.size(); i++) {
			SongSinger.add(list.get(i).getSong() + " - " + list.get(i).getSinger());
			uri[i] = list.get(i).getPath();
		}
	}

	public static void setReadChatting(String readChatting) { // 채팅창이 자꾸 위로 올라가서 사용
		JFRAME.readChatting = readChatting;
	}

	public static String getReadChatting() {
		return readChatting;
	}

	public static String getAnswer() {
		return answer;
	}

	private void initialize() {

		setUndecorated(true); // 기본메뉴바 숨기기
		setBackground(new Color(0, 0, 0, 0)); // paintcomponect 했을때 배경이 회색이 아니라 흰색으로 바뀜
		setSize(1280, 720);
		setResizable(false); // 사이즈 변경불가
		setLocationRelativeTo(null); // 화면 중앙에 뜸
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
		setLayout(null);

//		JButton chartBtn = new JButton("차트");
//		chartBtn.setBounds(600, 70, 111, 82);
//		getContentPane().add(chartBtn);

		// ----------------대기 버튼
		JButton roomBtn = new JButton(R.waitingRoomBtn);
		add(roomBtn);
		roomBtn.setBounds(800, 620, 207, 65);
		roomBtn.setBorderPainted(false);
		roomBtn.setContentAreaFilled(false);
		roomBtn.setFocusPainted(false);

		// ----------- 대기 방으로 돌아가는 버튼 ------------------
		roomBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				MusicBackGround buttonSound = new MusicBackGround("../Resource/Sound/ButtonSound.mp3", false);
				buttonSound.start();
				try {
					Thread.sleep(100);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				roomBtn.setIcon(R.waitingRoomBtnMouserOver);
				roomBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				roomBtn.setIcon(R.waitingRoomBtn);
				roomBtn.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		});

		// -------------- 종료 버튼
		JButton endBtn = new JButton(R.QuitBtn);
		add(endBtn);
		endBtn.setBounds(1050, 620, 215, 65);
		endBtn.setBorderPainted(false);
		endBtn.setContentAreaFilled(false);
		endBtn.setFocusPainted(false);

		// ------ 게임 종료 버튼 클릭 ------------------
		endBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				MusicBackGround buttonSound = new MusicBackGround("../Resource/Sound/ButtonSound.mp3", false);
				buttonSound.start();
				try {
					Thread.sleep(100);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
				// 클릭시 게임 싱글,멀티 고르는 창으로 이동
				// System.exit(0);
				// 서버로 raedcount초기화 명령 
				resetList();
				//gameClientReadMsg.serverSongRandom.clear();
				//gameClientReadMsg.interrupted();
				try {
					new ThreadExit();
				} catch (Exception e1) {
				
					e1.printStackTrace();
				}
				dispose();
				try {
					new MainFrame();
				} catch (UnknownHostException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}								
				//MainFrame.introMusic.start();
			}
			public void resetList() {
				introMusic.interrupt();
				list.clear();
				SongSinger.clear();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				endBtn.setIcon(R.QuitBtnMouseOver);
				endBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				endBtn.setIcon(R.QuitBtn);
				endBtn.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		});
		
		// ---------------- 1등
		JLabel iconRank1 = new JLabel();
		iconRank1.setIcon(new ImageIcon("../LineNo5/src/Resource/Images/ButtonGUI/1remove.png"));
		iconRank1.setBounds(50, 40, 50, 50);
		add(iconRank1);
		// ---------------- 유저 이름과 점수
		rankLabel1 = new JLabel(rank1);
		rankLabel1.setFont(new Font("굴림", Font.PLAIN, 20));
		rankLabel1.setForeground(Color.white);
		rankLabel1.setBounds(130, 40, 265, 50);
		add(rankLabel1);

		// ---------------- 2등

		JLabel iconRank2 = new JLabel();
		iconRank2.setIcon(new ImageIcon("../LineNo5/src/Resource/Images/ButtonGUI/2remove.png"));
		iconRank2.setBounds(50, 100, 50, 50);
		add(iconRank2);
		// ---------------- 유저 이름과 점수
		rankLabel2 = new JLabel(rank2);
		rankLabel2.setFont(new Font("굴림", Font.PLAIN, 20));
		rankLabel2.setForeground(Color.white);
		rankLabel2.setBounds(130, 100, 265, 50);
		add(rankLabel2);

		// ---------------- 3등

		JLabel iconRank3 = new JLabel();
		iconRank3.setIcon(new ImageIcon("../LineNo5/src/Resource/Images/ButtonGUI/3remove.png"));
		iconRank3.setBounds(50, 160, 50, 50);
		add(iconRank3);
		// ---------------- 유저 이름과 점수
		rankLabel3 = new JLabel(rank3);
		rankLabel3.setFont(new Font("굴림", Font.PLAIN, 20));
		rankLabel3.setForeground(Color.white);
		rankLabel3.setBounds(130, 160, 265, 50);
		add(rankLabel3);

		// ----------------4등 유저 이름과 점수
		rankLabel4 = new JLabel(rank4);
		rankLabel4.setFont(new Font("굴림", Font.PLAIN, 20));
		rankLabel4.setForeground(Color.white);
		rankLabel4.setBounds(130, 220, 265, 50);
		add(rankLabel4);

		// --- 결과 창에 USER 목록 나타내기 -------
		JList userList = new JList(userlist.toArray());
		userList.setFont(new Font("굴림", Font.PLAIN, 20));
		userList.setForeground(Color.white);
		userList.setOpaque(true);
		userList.setBackground(new Color(0, 0, 0, 100));
		userList.setBounds(850, 50, 400, 210);
		add(userList);

		// -----------게임 끝나고 노래 리스트 나오는 창-------------------
		JList jlist = new JList(SongSinger.toArray());
		jlist.setFont(new Font("굴림", Font.PLAIN, 20));
		jlist.setForeground(Color.white);
		jlist.setOpaque(true);
		jlist.setBackground(new Color(0, 0, 0, 100));

		jlist.setBounds(800, 310, 460, 300);

		jlist.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int index = jlist.locationToIndex(e.getPoint());
				if (e.getClickCount() == 2) {
					try {
						Desktop.getDesktop().browse(new URI(uri[index]));
					} catch (Exception e1) {

						e1.printStackTrace();

					}
				}
			}
		});
		add(jlist);
//ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ 메뉴바에 X 버튼 ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
		exit.setBounds(1245, 0, 30, 30);
		exit.setBorderPainted(false);
		exit.setContentAreaFilled(false);
		exit.setFocusPainted(false);
		exit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				exit.setIcon(R.ExitBtnMouseOver);
				exit.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				exit.setIcon(R.ExitBtn);
				exit.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				System.exit(0);
			}
		});
		add(exit);

		menuB.setBounds(0, 0, 1280, 30);
		menuB.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				mouseX = e.getX();
				mouseY = e.getY();
			}
		});
		menuB.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				int x = e.getXOnScreen();
				int y = e.getYOnScreen();
				setLocation(x - mouseX, y - mouseY); // 메뉴바 이동하게해줌
			}

		});
		exit.setCursor(new Cursor(Cursor.HAND_CURSOR));
		add(menuB);

// ------- CHAT 창 ----------------
		MultiGameButtons.chatArea();
		MultiGameButtons.useField();
		MultiGameButtons.Enter.setBounds(30, 630, 490, 40);
		// MultiGameButtons.display.set
		add(MultiGameButtons.Enter);
		MultiGameButtons.display.setBounds(30, 300, 490, 330);
		add(MultiGameButtons.display);
	}

	// 프레임에 이미지 넣는 코드
	public void paint(Graphics g) {
		SCI = createImage(1280, 700);
		SCG = SCI.getGraphics();
		screenDraw(SCG);
		g.drawImage(SCI, 0, 0, null);
	}

	public void screenDraw(Graphics g) {
		g.drawImage(FinalBackGround, 0, 0, null);
		paintComponents(g);
		this.repaint();
	}
	// 여기 까지

	public static void appendChat() {
		chatChang.append(readChatting + "\n");
	}

	public static void getMsg(String msg) {
		answer = msg;

		try {
			bw.write(msg + " " + "\n");
			bw.flush();
		} catch (Exception e) {
			if (bw != null) {
				try {
					bw.close();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new JFRAME().initialize();
	}
}