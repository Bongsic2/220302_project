package multiFinalFrame;

import java.awt.Container;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JList;
import javax.swing.JLabel;

public class JFrameChang extends JFrame {
	JButton EndBtn;
	Container c = getContentPane();
	private JTextField textField;

	public JFrameChang() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(1200, 720);
		setVisible(true);
		c.setLayout(null);
		
//		JButton waitBtn = new JButton("waitBtn");
//		waitBtn.setBounds(704, 608, 207, 65);
//		getContentPane().add(waitBtn);
//		
//		JButton endBtn = new JButton("end");
//		endBtn.setBounds(943, 608, 215, 65);
//		getContentPane().add(endBtn);
		
//		JList songList = new JList();
//		songList.setBounds(704, 258, 454, 338);
//		getContentPane().add(songList);
//		
		JButton chartBtn = new JButton("차트");
		chartBtn.setBounds(522, 10, 111, 82);
		getContentPane().add(chartBtn);
		
		JList userList = new JList();
		userList.setBounds(704, 26, 454, 213);
		getContentPane().add(userList);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(130, 10, 266, 48);
		getContentPane().add(lblNewLabel);
		
		JLabel label2 = new JLabel("New label");
		label2.setBounds(130, 83, 265, 48);
		getContentPane().add(label2);
		
		JLabel label3 = new JLabel("New label");
		label3.setBounds(130, 152, 265, 48);
		getContentPane().add(label3);
		
		JLabel label4 = new JLabel("New label");
		label4.setBounds(130, 210, 266, 48);
		getContentPane().add(label4);
	}
}
