package view.countGUI;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import main.Main;
import main.R;

public class NextCount {

	public static JLabel RemainNext5Label = new JLabel(R.RemainNext5);
	public static JLabel RemainNext4Label = new JLabel(R.RemainNext4);
	public static JLabel RemainNext3Label = new JLabel(R.RemainNext3);
	public static JLabel RemainNext2Label = new JLabel(R.RemainNext2);
	public static JLabel RemainNext1Label = new JLabel(R.RemainNext1);
	public static JLabel RemainNext0Label = new JLabel(R.RemainNext0);
	int count;
	
	public NextCount() {
		this.count = 5;
	}
	
	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}



	public JLabel Remain() {
		RemainNext5Label.setBounds(880, 480, 300, 86);
		RemainNext4Label.setBounds(880, 480, 300, 86);
		RemainNext3Label.setBounds(880, 480, 300, 86);
		RemainNext2Label.setBounds(880, 480, 300, 86);
		RemainNext1Label.setBounds(880, 480, 300, 86);
		RemainNext0Label.setBounds(880, 480, 300, 86);
		JLabel remain = null;
		switch (count) {
		case 5: {remain = RemainNext5Label; break;}
		case 4: {remain = RemainNext4Label; break;}
		case 3: {remain = RemainNext3Label; break;}
		case 2: {remain = RemainNext2Label; break;}
		case 1: {remain = RemainNext1Label; break;}
		case 0: {remain = RemainNext0Label; break;}
		}
		return remain;
	}


}
