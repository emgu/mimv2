package GUI;

import java.awt.Label;
import javax.swing.*;

import iohandling.IO;
import maps.MapHandler;

public class GuiHandler{
	JFrame map;
	JFrame player;
	
	public GuiHandler (){
		map = new JFrame("map");
		map.setVisible(true);
		map.setSize(600, 600);
		map.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JButton lab;
		
		for(int fieldId = 3; fieldId < MapHandler.getMapSize(0); fieldId++){
			lab = new JButton(MapHandler.getFieldInfo(0, fieldId, "name"));
			lab.setSize(150, 30);
			lab.setLocation(0, fieldId*30);
			map.add(lab);
			System.out.println(fieldId);
		}
		
	}
}

