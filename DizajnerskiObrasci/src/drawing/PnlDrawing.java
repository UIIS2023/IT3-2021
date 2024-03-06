package drawing;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JPanel;

import geometry.Point;

import geometry.Shape;


public class PnlDrawing extends JPanel {
	private ArrayList<Shape> shapes = new ArrayList<Shape>();
	boolean select=false;
	private Shape selected;

	/**
	 * Create the panel.
	 */
	
	public PnlDrawing() {
		setBackground(new Color(241, 245, 179));
		setBounds(100, 100, 470, 370);

	}
	
	public Shape getShape(int index) {
		return shapes.get(index);
	}
	
	public void setShape(int index, Shape shape) {
		shapes.set(index, shape);
	}
	public int getSelected() {
		for (int i = shapes.size()-1; i >= 0; i--) {
			if (shapes.get(i).isSelected()) {
				return i;
			}
		}
		return -1;
	}
	public void select(Point clickPoint) {
		
		for (int i = shapes.size() - 1; i >= 0; i--) {
			if (shapes.get(i).contains(clickPoint.getX(), clickPoint.getY()) && select!=true) {
				
				
				shapes.get(i).setSelected(true);
				repaint();
				if(shapes.get(i).isSelected() && select==true)
				{
					shapes.get(i).setSelected(false);
					repaint();
					select=false;
				}
				select=true;
				return;
			}
		}
		select=false;
		}


	public void deselect() {
		for(int i=shapes.size()-1;i>=0; i--)
		{
		 getShape(i).setSelected(false); //nasa metoda getShap, indeks i
		repaint();
		}
	}

	public void paint(Graphics g) {
		super.paint(g);
		Iterator<Shape> it = shapes.iterator();
		while (it.hasNext())
			it.next().draw(g);
	}
	// prolazi kroz oblike,
	// odlazi u shape i ostale klase i trazi u svakoj klasi metodu paint
	//
	

	public void add(Shape shape) {
		shapes.add(shape);
		repaint();
	}

	public boolean isEmpty() {
		return shapes.isEmpty();
	}

	
	public void setSelected(Shape selected) {
		this.selected = selected;
	}
	
	public void removeSelected() {
		shapes.removeIf(shape -> shape.isSelected());
		repaint();
	}
	//kreira se shape,,isptijue se da li je selektovan, ukoliko jeste selektovan uklanja se
	
	public ArrayList<Shape> getShapes() {
		return shapes;
	}



	public void setShapes(ArrayList<Shape> shapes) {
		this.shapes = shapes;
	}
	
	

	//repaint ugradjena poziva paint ponovo

}
