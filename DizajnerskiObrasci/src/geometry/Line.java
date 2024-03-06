 package geometry;

import java.awt.Color;
import java.awt.Graphics;

public class Line extends Shape implements Cloneable{

	private Point startPoint;
	private Point endPoint;
	
	
	public Line() {
	}

	public Line(Point startPoint, Point endPoint) {
		this.startPoint = startPoint;
		this.endPoint = endPoint;
	}

	public Line(Point startPoint, Point endPoint, boolean selected) {
		//this(startPoint, endPoint); ovo moze zbog prethodne metode
		//this.selected = selected;
		//setSelected(selected);
		super(selected); //poziv konstruktora iz nadredjene klase-shape
		this.startPoint=startPoint;
		this.endPoint=endPoint;
	}
	public Line(Point startPoint, Point endPoint, Color eColor) {
		
		this.startPoint=startPoint;
		this.endPoint=endPoint;
		seteColor(eColor);
	}

	public void moveTo(int x,int y)
	{
		startPoint.moveTo(x,y);
		endPoint.moveTo(x, y);
	}
	
	public void moveBy(int x,int y)
	{
		startPoint.moveBy(x, y);
		endPoint.moveBy(x, y);
	}
	
	public int compareTo(Object obj)
	{
		if(obj instanceof Line)
		{
			Line lineToCompare=(Line)obj;
			return (int)(this.length()-lineToCompare.length());
		}
		return 0;
	}
	
	public boolean equals(Object obj) {
		if (obj instanceof Line) {
			Line pomocna = (Line) obj;//downcasting
			if (this.startPoint.equals(pomocna.startPoint) && this.endPoint.equals(pomocna.endPoint))
				return true;
			else
				return false;
		} else
			return false;
	}
	
	public boolean contains (int x, int y)
	{
		return this.startPoint.distance(x,y)+this.endPoint.distance(x,y)-length()<=2;
	}

	public double length() {
		return this.startPoint.distance(this.endPoint.getX(), this.endPoint.getY());

	}
	
	public void draw (Graphics g)
	{      g.setColor(geteColor());
		g.drawLine(startPoint.getX(),startPoint.getY(),endPoint.getX(),endPoint.getY());
		//da su x,y protected , ne moye/komnpozicija...
		if(isSelected()) {
			g.setColor(Color.BLUE);
			g.drawRect(startPoint.getX()-2, startPoint.getY()-2, 4, 4);
			g.drawRect(endPoint.getX()-2, endPoint.getY()-2, 4, 4);
			g.setColor(Color.BLACK);
		}
	}

	public void setStartPoint(Point startPoint)

	{
		this.startPoint = startPoint;
	}

	public Point getStartPoint() {
		return this.startPoint;
	}

	public Point getEndPoint() {
		return endPoint;
	}

	public void setEndPoint(Point endPoint) {
		this.endPoint = endPoint;
	} //sa this.endPoint je obelezje klase, a endPoint je prosledjeni parametar

//za shallow
/*public Line clone() {
	try {
		return (Line) super.clone();
	} catch (CloneNotSupportedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return null;
	}
}*/

public Line clone() {
	Line line=new Line(new Point(),new Point());
	line.getStartPoint().setX(this.getStartPoint().getX());
	line.getStartPoint().setY(this.getStartPoint().getY());
	line.getEndPoint().setX(this.getEndPoint().getX());
	line.getEndPoint().setY(this.getEndPoint().getY());
	line.seteColor(this.geteColor());
	return line;
	
}
	

	public String toString() {
		return "Line:" +"(("+startPoint.getX()+","+startPoint.getY() +"),(" + endPoint.getX()+","+endPoint.getY()+")"+"color:"+ this.eColor+")";
	}

	

	
}
