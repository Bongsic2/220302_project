package main;

import java.awt.Image;
import java.net.InetAddress;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import client.GameInterface;

public class R {
	public static int SongLength = 30; // 노래 갯수
	public static int SingleGameLength = 10; // 싱글 플레이 시간
	//public static String HOST = "127.30.1.17"; // 임시 서버
	public static String HOST = new ServerIP().HOST;
	public static String user; // 임시 플레이어
	public static ArrayList<GameInterface> threadList = new ArrayList<GameInterface>();
	
	public static JFrame MAIN; // 클라이언트 메인 프레임
	public static JFrame GameMAIN; // 클라이언트 게임 프레임
	// 클라이언트 메인 배경화면
	public static Image MainBackground = new ImageIcon(Main.class.getResource("../Resource/Images/introBackground.jpg")).getImage();
	
	// 클라이언트 메인 화면 크기
	public static int ClientWidth = 1280;
	public static int ClientHeight = 720;
	
	// 클라이언트 게임 배경화면
	public static Image Gamebackground = new ImageIcon(Main.class.getResource("../Resource/Images/playBackground.jpg")).getImage();
	
	// 클라이언트 게임 화면 크기
	public static int GameWidth = 1200;
	public static int GameHeight = 700;
	
	// gif 아이콘
	public static ImageIcon PointIcon = new ImageIcon(Main.class.getResource("../Resource/Images/PointIcon.gif"));
	
	// 메뉴바 GUI
	public static ImageIcon ExitBtn = new ImageIcon(Main.class.getResource("../Resource/Images/MenuGUI/ExitButton.png"));
	public static ImageIcon ExitBtnMouseOver = new ImageIcon(Main.class.getResource("../Resource/Images/MenuGUI/ExitButtonMouseOver.png"));
	public static ImageIcon menuBarLabel = new ImageIcon(Main.class.getResource("../Resource/Images/MenuGUI/MenuBar.png"));
	
	// 클라이언트 메인 버튼 GUI
	public static ImageIcon MainPlayBtn = new ImageIcon(Main.class.getResource("../Resource/Images/ButtonGUI/PlayBtn.png"));
	public static ImageIcon MainPlayBtnMouseOver = new ImageIcon(Main.class.getResource("../Resource/Images/ButtonGUI/PlayBtnMouseOver.png"));
	public static ImageIcon MainQuitBtn = new ImageIcon(Main.class.getResource("../Resource/Images/ButtonGUI/QuitBtn.png"));
	public static ImageIcon MainQuitBtnMouseOver = new ImageIcon(Main.class.getResource("../Resource/Images/ButtonGUI/QuitBtnMouseOver.png"));
	public static ImageIcon MainSingle1Btn = new ImageIcon(Main.class.getResource("../Resource/Images/ButtonGUI/Single1Btn.png"));
	public static ImageIcon MainSingle1BtnMouseOver = new ImageIcon(Main.class.getResource("../Resource/Images/ButtonGUI/Single1BtnMouseOver.png"));
	public static ImageIcon MainSingle5Btn = new ImageIcon(Main.class.getResource("../Resource/Images/ButtonGUI/Single5Btn.png"));
	public static ImageIcon MainSingle5BtnMouseOver = new ImageIcon(Main.class.getResource("../Resource/Images/ButtonGUI/Single5BtnMouseOver.png"));
	public static ImageIcon MainSingle10Btn = new ImageIcon(Main.class.getResource("../Resource/Images/ButtonGUI/Single10Btn.png"));
	public static ImageIcon MainSingle10BtnMouseOver = new ImageIcon(Main.class.getResource("../Resource/Images/ButtonGUI/Single10BtnMouseOver.png"));
	public static ImageIcon MainMultiBtn = new ImageIcon(Main.class.getResource("../Resource/Images/ButtonGUI/MultiBtn.png"));
	public static ImageIcon MainMultiBtnMouseOver = new ImageIcon(Main.class.getResource("../Resource/Images/ButtonGUI/MultiBtnMouseOver.png"));
	public static ImageIcon MainMultiBtnOff = new ImageIcon(Main.class.getResource("../Resource/Images/ButtonGUI/MultiBtnOff.png"));
	public static ImageIcon MainUpdateBtn = new ImageIcon(Main.class.getResource("../Resource/Images/ButtonGUI/updateBtn.png"));
	public static ImageIcon MainUpdateBtnMouseOver = new ImageIcon(Main.class.getResource("../Resource/Images/ButtonGUI/updateBtnMouseOver.png"));
	public static ImageIcon MainUpdateBtnOff = new ImageIcon(Main.class.getResource("../Resource/Images/ButtonGUI/UpdateBtnOff.png"));
	
	// 클라이언트 게임 버튼 GUI
	public static ImageIcon NextBtn = new ImageIcon(Main.class.getResource("../Resource/Images/ButtonGUI/NextButton.png"));
	public static ImageIcon NextBtnMouseOver = new ImageIcon(Main.class.getResource("../Resource/Images/ButtonGUI/NextButtonMouseOver.png"));
	public static ImageIcon PlayMusicBtn = new ImageIcon(Main.class.getResource("../Resource/Images/ButtonGUI/PlayMusicBtn.png"));
	public static ImageIcon PlayMusicBtnMouseOver = new ImageIcon(
			Main.class.getResource("../Resource/Images/ButtonGUI/PlayMusicBtnMouseOver.png"));
	public static ImageIcon PlayMusicOff = new ImageIcon(Main.class.getResource("../Resource/Images/ButtonGUI/PlayMusicOff.png"));
	public static ImageIcon SingerHintBtn = new ImageIcon(Main.class.getResource("../Resource/Images/ButtonGUI/SingerHintBtn.png"));
	public static ImageIcon SingerHintBtnMouseOver = new ImageIcon(
			Main.class.getResource("../Resource/Images/ButtonGUI/SingerHintBtnMouseOver.png"));
	public static ImageIcon SongHintBtn = new ImageIcon(Main.class.getResource("../Resource/Images/ButtonGUI/SongHintBtn.png"));
	public static ImageIcon SongHintBtnMouseOver = new ImageIcon(
			Main.class.getResource("../Resource/Images/ButtonGUI/SongHintBtnMouseOver.png"));
	public static ImageIcon SubmitBtn = new ImageIcon(Main.class.getResource("../Resource/Images/ButtonGUI/SubmitBtn.png"));
	public static ImageIcon SubmitBtnMouseOver = new ImageIcon(Main.class.getResource("../Resource/Images/ButtonGUI/SubmitBtnMouseOver.png"));
	public static ImageIcon ReadyBtn = new ImageIcon(Main.class.getResource("../Resource/Images/ButtonGUI/Readybutton.png"));
	public static ImageIcon ReadyBtnMouseOver = new ImageIcon(Main.class.getResource("../Resource/Images/ButtonGUI/ReadybuttonMouseOver.png"));
	public static ImageIcon ReadyBtnOn = new ImageIcon(Main.class.getResource("../Resource/Images/ButtonGUI/ReadybuttonOn.png"));
	
	// 클라이언트 힌트, 넥스트 카운트 GUI
	public static ImageIcon RemainHint5 = new ImageIcon(Main.class.getResource("../Resource/Images/CountGUI/RemainHint5.png"));
	public static ImageIcon RemainHint4 = new ImageIcon(Main.class.getResource("../Resource/Images/CountGUI/RemainHint4.png"));
	public static ImageIcon RemainHint3 = new ImageIcon(Main.class.getResource("../Resource/Images/CountGUI/RemainHint3.png"));
	public static ImageIcon RemainHint2 = new ImageIcon(Main.class.getResource("../Resource/Images/CountGUI/RemainHint2.png"));
	public static ImageIcon RemainHint1 = new ImageIcon(Main.class.getResource("../Resource/Images/CountGUI/RemainHint1.png"));
	public static ImageIcon RemainHint0 = new ImageIcon(Main.class.getResource("../Resource/Images/CountGUI/RemainHint0.png"));
	
	public static ImageIcon RemainNext5 = new ImageIcon(Main.class.getResource("../Resource/Images/CountGUI/RemainNext5.png"));
	public static ImageIcon RemainNext4 = new ImageIcon(Main.class.getResource("../Resource/Images/CountGUI/RemainNext4.png"));
	public static ImageIcon RemainNext3 = new ImageIcon(Main.class.getResource("../Resource/Images/CountGUI/RemainNext3.png"));
	public static ImageIcon RemainNext2 = new ImageIcon(Main.class.getResource("../Resource/Images/CountGUI/RemainNext2.png"));
	public static ImageIcon RemainNext1 = new ImageIcon(Main.class.getResource("../Resource/Images/CountGUI/RemainNext1.png"));
	public static ImageIcon RemainNext0 = new ImageIcon(Main.class.getResource("../Resource/Images/CountGUI/RemainNext0.png"));
	
	// 클라이언트 설정 버튼 GUI
	public static ImageIcon SettingBtn = new ImageIcon(Main.class.getResource("../Resource/Images/ButtonGUI/SettingBtn.png"));
	public static ImageIcon SettingBtnMouseOver = new ImageIcon(
			Main.class.getResource("../Resource/Images/ButtonGUI/SettingBtnMouseOver.png"));
	public static ImageIcon BackGroundSoundLabel = new ImageIcon(Main.class.getResource("../Resource/Images/ButtonGUI/BackGroundSound.png"));
	public static ImageIcon HighBtn = new ImageIcon(Main.class.getResource("../Resource/Images/ButtonGUI/HighBtn.png"));
	public static ImageIcon HighBtnMouseOver = new ImageIcon(Main.class.getResource("../Resource/Images/ButtonGUI/HighBtnMouseOver.png"));
	public static ImageIcon LowBtn = new ImageIcon(Main.class.getResource("../Resource/Images/ButtonGUI/LowBtn.png"));
	public static ImageIcon LowBtnMouseOver = new ImageIcon(Main.class.getResource("../Resource/Images/ButtonGUI/LowBtnMouseOver.png"));
	
	// 클라이언트 싱글 결과 프레임 GUI
	public static ImageIcon ExitBtn2 = new ImageIcon(Main.class.getResource("../Resource/Images/FinalGUI/ExitBtn2.png"));
	public static ImageIcon ExitBtn2MouseOver = new ImageIcon(Main.class.getResource("../Resource/Images/FinalGUI/ExitBtn2MouseOver.png"));
	public static ImageIcon RetryBtn = new ImageIcon(Main.class.getResource("../Resource/Images/FinalGUI/RetryBtn.png"));
	public static ImageIcon RetryBtnMouseOver = new ImageIcon(Main.class.getResource("../Resource/Images/FinalGUI/RetryBtnMouseOver.png"));
	public static ImageIcon GoMainBtn = new ImageIcon(Main.class.getResource("../Resource/Images/FinalGUI/GoMainBtn.png"));
	public static ImageIcon GoMainBtnMouseOver = new ImageIcon(Main.class.getResource("../Resource/Images/FinalGUI/GoMainBtnMouseOver.png"));
	public static ImageIcon jumReal = new ImageIcon(Main.class.getResource("../Resource/Images/FinalGUI/jumReal.gif"));
	public static ImageIcon ok2 = new ImageIcon(Main.class.getResource("../Resource/Images/FinalGUI/ok2.png"));
	
	// 클라이언트 멀티 캐릭터 GUI
	public static ImageIcon character1 = new ImageIcon(Main.class.getResource("../Resource/Images/charactersGUI/character1.png"));
	public static ImageIcon character2 = new ImageIcon(Main.class.getResource("../Resource/Images/charactersGUI/character2.png"));
	public static ImageIcon character3 = new ImageIcon(Main.class.getResource("../Resource/Images/charactersGUI/character3.png"));
	public static ImageIcon character4 = new ImageIcon(Main.class.getResource("../Resource/Images/charactersGUI/character4.png"));
	
	// 클라이언트 멀티 결과 프레임 GUI
	public static ImageIcon QuitBtn = new ImageIcon(Main.class.getResource("../Resource/Images/ButtonGUI/QuitBtn1.png"));
	public static ImageIcon QuitBtnMouseOver = new ImageIcon(Main.class.getResource("../Resource/Images/ButtonGUI/QuitBtnMouseOver1.png"));
	public static ImageIcon waitingRoomBtn = new ImageIcon(Main.class.getResource("../Resource/Images/ButtonGUI/waitingBtn1.png"));
	public static ImageIcon waitingRoomBtnMouserOver = new ImageIcon(Main.class.getResource("../Resource/Images/ButtonGUI/waintRoomMouse11.png"));
}
