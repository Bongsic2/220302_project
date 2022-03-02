package model;

import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.sound.sampled.*;

import view.VolumnButtons;

// 랜덤으로 wav 파일 한곡 재생 클래스
public class PlayWav {
	public static boolean isplayed = false;
	public static Clip clip = null;
	public static long clipTime;
	public static FloatControl volume = null;
	public static int init = 0;
	String dir;
	// 경로를 인자로 받음
	public PlayWav(String dir, int N) throws LineUnavailableException, IOException, UnsupportedAudioFileException {
		String number = Integer.toString(N); // 랜덤 숫자 문자로 변환
		String filename = dir + number+".wav"; 
		File file = new File(filename); // 경로에 있는 파일 선택
		// 재생
		AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
		clip = AudioSystem.getClip();
		clip.open(audioStream);
		clipTime = clip.getMicrosecondPosition();
		volume = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
		if(init == 0) { //처음 소리 크기 지정
			volume.setValue(-10.0f);
		}
		switch (VolumnButtons.N) {
		case 0: {volume.setValue(-20.0f); break;}
		case 1: {volume.setValue(-18.0f); break;}
		case 2: {volume.setValue(-16.0f); break;}
		case 3: {volume.setValue(-14.0f); break;}
		case 4: {volume.setValue(-12.0f); break;}
		case 5: {volume.setValue(-10.0f); break;}
		case 6: {volume.setValue(-8.0f); break;}
		case 7: {volume.setValue(-6.0f); break;}
		case 8: {volume.setValue(-4.0f); break;}
		case 9: {volume.setValue(-2.0f); break;}
		case 10: {volume.setValue(0.0f); break;}
		}
		clip.start();
		init++;
		
	}
}
