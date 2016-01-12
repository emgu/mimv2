package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.Border;

import iohandling.IO;

public class Window extends JFrame implements ActionListener{

	JButton bRoll;
	JLabel lRoll;
	
	public Window(){
		setSize(300, 200);
		setTitle("title");
		setLayout(null);
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	//	setVisible(true);
		
		bRoll = new JButton("Roll a dice");
		bRoll.setBounds(100, 50, 100, 20);
		add(bRoll);
		bRoll.addActionListener(this);
		
		lRoll = new JLabel("You rolled: ");
		lRoll.setBounds(50, 150, 100, 20);
		add(lRoll);
	}

	@Override
	public void actionPerformed(ActionEvent ae) {

		Object source = ae.getSource();
		
		if(source == bRoll){
			lRoll.setText("Wynik");
			IO.display("Wynik");
		}
		
		
	}
	
	
}
