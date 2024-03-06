package mvc;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JPanel;
import geometry.Point;
import geometry.Shape;

public class DrawingView extends JPanel {
	
	DrawingModel model=new DrawingModel();
	
	boolean select=false;


	public DrawingView() {
		setBackground(new Color(192, 192, 192));
		//setBounds(100, 100, 10, 10);
	}
	 
		public void paint(Graphics g) {
		Iterator<Shape> it = model.getShapes().iterator();
		while(it.hasNext()) {
			it.next().draw(g);
		}
	}
	
	public DrawingModel getModel() {
		return model;
	}

	public void setModel(DrawingModel model) {
		this.model = model;
	}
	
}
