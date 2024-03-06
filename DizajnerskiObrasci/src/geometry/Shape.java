package geometry;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;

public abstract class Shape implements Moveable, Comparable,Serializable  {

	private boolean selected;
	protected Color eColor;
	protected Color iColor;

	public Shape() {
	};

	public Shape(boolean selected) {
		this.selected = selected;

	}

	public abstract boolean contains(int x, int y);// ne pravi se telo metode, jer imamo vise oblika

	public abstract void draw(Graphics g);// svaka podredjena klasa mora implementirati drugu

	public boolean isSelected()

	{
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	public Color geteColor() {
		return eColor;
	}

	public void seteColor(Color eColor) {
		this.eColor = eColor;
	}

	public Color getiColor() {
		return iColor;
	}

	public void setiColor(Color iColor) {
		this.iColor = iColor;
	}
	 @Override
	    public Shape clone() {
	        try {
	            return (Shape) super.clone();
	        } catch (CloneNotSupportedException e) {
	            e.printStackTrace();
	            return null;
	        }
	 }
}
