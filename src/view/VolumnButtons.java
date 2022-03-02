package view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import main.Main;
import main.R;
import model.PlayWav;
import music.MusicPlayer;
import view.sounds.MusicBackGround;

public class VolumnButtons {
	public static int N = 5;
	public static void SingleVolumnButtons(JFrame jFrame) {
		JButton HighButton = new JButton(R.HighBtn);
		JButton LowButton = new JButton(R.LowBtn);
		JButton SettingButton = new JButton(R.SettingBtn);

		JPanel Setting = new JPanel();
		Setting.setBounds(880, 100, 280, 200);
		Setting.setOpaque(true);
		Setting.setBackground(new Color(255, 255, 255, 170));
		Setting.setVisible(false);
		
		
		JLabel number = new JLabel(Integer.toString(N));
		number.setFont(new Font("Serif",Font.BOLD,30));
		number.setForeground(Color.white);
		
		HighButton.setBorderPainted(false);
		HighButton.setContentAreaFilled(false);
		HighButton.setFocusPainted(false);
		HighButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				MusicBackGround buttonSound = new MusicBackGround("../Resource/Sound/ButtonSound.mp3", false);
				buttonSound.start();
				if(N < 10 && N >= 0) {
					Long nowFrame = PlayWav.clip.getMicrosecondPosition();
					PlayWav.clip.stop();
					PlayWav.clip.setMicrosecondPosition(PlayWav.clipTime);
					PlayWav.volume.setValue(PlayWav.volume.getValue() + 2.0f);
					N++;
					number.setText(Integer.toString(N));
					PlayWav.clip.setMicrosecondPosition(nowFrame); 
					PlayWav.clip.start();
				}
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				HighButton.setIcon(R.HighBtnMouseOver);
				HighButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				HighButton.setIcon(R.HighBtn);
				HighButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		});

		LowButton.setBorderPainted(false);
		LowButton.setContentAreaFilled(false);
		LowButton.setFocusPainted(false);
		LowButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				MusicBackGround buttonSound = new MusicBackGround("../Resource/Sound/ButtonSound.mp3", false);
				buttonSound.start();
				if(N <= 10 && N > 0) {
					Long nowFrame = PlayWav.clip.getMicrosecondPosition();
					PlayWav.clip.stop();
					PlayWav.clip.setMicrosecondPosition(PlayWav.clipTime);
					PlayWav.volume.setValue(PlayWav.volume.getValue() - 2.0f);
					N--;
					number.setText(Integer.toString(N));
					PlayWav.clip.setMicrosecondPosition(nowFrame);
					PlayWav.clip.start();
				}
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				LowButton.setIcon(R.LowBtnMouseOver);
				LowButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				LowButton.setIcon(R.LowBtn);
				LowButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		});

		JLabel BackGroundSoundLabel = new JLabel(R.BackGroundSoundLabel);
		BackGroundSoundLabel.setBounds(980, 100, 250, 100);
		Setting.add(BackGroundSoundLabel);
		Setting.add(LowButton);
		Setting.add(number);
		Setting.add(HighButton);
		

		SettingButton.setBounds(1120, 35, 55, 55);
		SettingButton.setBorderPainted(false);
		SettingButton.setContentAreaFilled(false);
		SettingButton.setFocusPainted(false);
		SettingButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				MusicBackGround buttonSound = new MusicBackGround("../Resource/Sound/ButtonSound.mp3", false);
				buttonSound.start();
				if (Setting.isVisible() == false) {
					Setting.setVisible(true);
				} else if (Setting.isVisible() == true) {
					Setting.setVisible(false);
				}
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				SettingButton.setIcon(R.SettingBtnMouseOver);
				SettingButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				SettingButton.setIcon(R.SettingBtn);
				SettingButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		});
		jFrame.add(SettingButton);
		jFrame.add(Setting);
	}
}
