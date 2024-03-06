package geometry;

import java.awt.Color;
import java.awt.Graphics;

public class Rectangle extends Shape {

	private Point upperLeftPoint;
	private int width;
	private int height;
	
	public Rectangle() {
	}

	public Rectangle(Point upperLeftPoint) {
		this.upperLeftPoint=upperLeftPoint;
	}
	public Rectangle(Point upperLeftPoint, int width, int height) {
		this.upperLeftPoint = upperLeftPoint;
		this.width = width;
		this.height = height;
	}

	public Rectangle(Point upperLeftPoint, int width, int height, boolean selected) {

		this(upperLeftPoint, width, height);//ovo mora biti prva naredba
		setSelected(selected);
		
	}
	public Rectangle(Point upperLeftPoint, int width, int height, Color eColor, Color iColor) {

		this.upperLeftPoint = upperLeftPoint;
		this.width = width;
		this.height = height;
		seteColor(eColor);
		setiColor(iColor);
		
	}
	
	
	public void moveTo(int x,int y)
	{
		upperLeftPoint.moveTo(x,y);
		
	}
	
	public void moveBy(int x,int y)
	{
		upperLeftPoint.moveBy(x, y);
		
	}

	public int compareTo(Object obj)
	{
		if(obj instanceof Rectangle)
		{
			Rectangle rectangleToCompare=(Rectangle)obj;
			return (int)(this.area()-rectangleToCompare.area());
		}
		return 0;
	}

	public boolean contains (int x, int y)
	{
		return upperLeftPoint.getX()<=x && upperLeftPoint.getX()+width>=x && 
				upperLeftPoint.getY()<=y && upperLeftPoint.getY()+height>=y;
	}
	
public boolean contains (Point clickPoint)
{
	return upperLeftPoint.getX()<=clickPoint.getX() && upperLeftPoint.getX()+width>=clickPoint.getX() && 
			upperLeftPoint.getY()<=clickPoint.getY() && upperLeftPoint.getY()+height>=clickPoint.getY();
}
	
	public boolean equals(Object obj) {
		if (obj instanceof Rectangle) {
			Rectangle pomocna = (Rectangle) obj;
			if (this.upperLeftPoint.equals(pomocna.upperLeftPoint) && this.width == pomocna.width
					&& this.height == pomocna.height)
				return true;
			else
				return false;
		} else
			return false;
	}
	
	public void fill(Graphics g) {
		g.setColor(getiColor());
		g.fillRect(this.upperLeftPoint.getX() + 1, this.upperLeftPoint.getY() + 1, this.width - 1, this.height - 1);
	}
	public void draw (Graphics g)
	
	{      g.setColor(geteColor());
	      g.drawRect(upperLeftPoint.getX(),upperLeftPoint.getY(),width,height);
	      this.fill(g);
	
		if(isSelected()) {
			g.setColor(Color.blue);
			g.drawRect(upperLeftPoint.getX() - 2, upperLeftPoint.getY() - 2, 4, 4);
			g.drawRect(upperLeftPoint.getX() + width - 2, upperLeftPoint.getY() - 2, 4, 4);
			g.drawRect(upperLeftPoint.getX() - 2, upperLeftPoint.getY() + height - 2, 4, 4);
			g.drawRect(upperLeftPoint.getX() + width  - 2, upperLeftPoint.getY() + height - 2, 4, 4);
			g.setColor(Color.black);
		}
	}
	public Point getUpperLeftPoint() {
		return this.upperLeftPoint;

	}

	public void setUpperLeftPoint(Point upperLeftPoint) {
		this.upperLeftPoint = upperLeftPoint;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	
	public int area() {
		return this.width * this.height;
	}

	public int circumference() {
		return 2 * (this.width + this.height);
	}
	
	public String toString() {
		return "Rectangle:("+ "Upper left point:"+ upperLeftPoint.getX()+","+upperLeftPoint.getY()+", width ="+ width +",height = "+height+","+"EdgeColor:"+ this.eColor+","+"InnerColor:"+this.iColor+")";
	}
	
	public Rectangle clone() {
		Rectangle r=new Rectangle(new Point());
		r.getUpperLeftPoint().setX(this.getUpperLeftPoint().getX());
		r.getUpperLeftPoint().setY(this.getUpperLeftPoint().getY());
		r.setWidth(this.getWidth());
		r.setHeight(this.getHeight());
		r.seteColor(this.geteColor());
		r.setiColor(this.getiColor());
		
		return r;
	}

}
