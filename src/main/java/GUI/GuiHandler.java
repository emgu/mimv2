package GUI;

import java.awt.Label;
import javax.swing.*;

import maps.MapHandler;

public class GuiHandler{
	JFrame map;
	public GuiHandler (){
		map = new JFrame("map");
		map.setVisible(true);
		map.setSize(600, 600);
		map.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel lab;
		
		for(int fieldId = 0; fieldId < MapHandler.getMapSize(1); fieldId++){
			lab = new JLabel(MapHandler.fieldName(1, fieldId));
			map.add(lab);
		}
	}
}
