package model;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JLabel;

import main.R;
import view.SingleFinalFrame;

public class TimeBar extends JLabel implements Runnable {
	int width = 450, height = 50, gow = 450;
	int x = 400, y = 40;
	Color color = new Color(255, 0, 0);
	int second;

	public int getGow() {
		return gow;
	}

	public TimeBar(int second) {
		setBackground(color);
		setOpaque(true);
		setBounds(x, y, width, height);

		this.second = second;
	}

	@Override
	public void run() {
		try {
			while (!Thread.currentThread().isInterrupted()){
				Thread.sleep(1000 / (width / second));		
				if (getWidth() > 0) {
					gow -= 1;
					setBounds(x, y, gow, height);
				} else {
					break;
				}
			}
		} catch (InterruptedException e) {
			System.out.println("나가~~~");
            return;
        } finally {
        	if (gow == 0 && !Thread.currentThread().isInterrupted()) {
    			PlayWav.clip.stop();
    			R.GameMAIN.dispose();
    			new SingleFinalFrame(); 
    		}
        }
	}	
}

