	package geometry;

import java.awt.Color;
import java.awt.Graphics;

public class Circle extends Shape {

	protected Point center; //protected da bi sve klase koje nasledjuju mogke da vide
	protected int radius;
	
	

	public Circle() {

	}
	public Circle(Point center) {
		this.center=center;
	}

	public Circle(Point center, int radius) {
		this.center = center;
		this.radius = radius;
	}

	public Circle(Point center, int radius, boolean selected) {
		this(center, radius);
		setSelected(selected);
	}

	public Circle(Point center, int radius, Color eColor, Color iColor) {
		this.center = center;
		this.radius = radius;
		seteColor(eColor);
		setiColor(iColor);
	}




	public boolean contains (int x, int y)
	{
		return center.distance(x,y)<=radius;
	}
	
	public boolean contains (Point clickPoint)//click point .getx, 
	{
		return center.distance(clickPoint.getX(), clickPoint.getY())<=2;
	}
	
	public void moveTo(int x,int y)
	{
		center.moveTo(x,y);
		
	}
	
	public void moveBy(int x,int y)
	{
		center.moveBy(x, y);
		
	}
	public int compareTo(Object obj)
	{
		if(obj instanceof Circle)
		{
			Circle circleToCompare=(Circle)obj;
			return (int)(this.area()-circleToCompare.area());
		}
		return 0;
	}

	public boolean equals(Object obj) {
		if (obj instanceof Circle) {
			Circle pomocni = (Circle) obj;
			if (this.center.equals(pomocni.center) && this.radius == pomocni.radius) {
				return true;
			} else {
				return false;
			}
		} else 
			return false;
		}
	
	public void fill(Graphics g) {
		g.setColor(getiColor());
		g.fillOval(this.center.getX() - this.radius + 1, this.center.getY() - this.radius + 1,
				this.radius*2 - 2, this.radius*2 - 2);
	}

	public void draw (Graphics g)
	{
		g.setColor(geteColor());
		g.drawOval(center.getX()-radius, center.getY()-radius, radius*2,radius*2);
		this.fill(g);
		if (isSelected()) {
			g.setColor(Color.BLUE);
			g.drawRect(center.getX() - 2, center.getY() - 2, 4, 4);
			g.drawRect(center.getX() - radius - 2, center.getY() - 2, 4, 4);
			g.drawRect(center.getX() + radius - 2, center.getY() - 2, 4, 4);
			g.drawRect(center.getX() - 2, center.getY() - radius - 2, 4, 4);
			g.drawRect(center.getX() - 2, center.getY() + radius - 2, 4, 4);
			
		}
	}
	
	

	public Point getCenter() {
		return this.center;
	}

	public void setCenter(Point center) {
		this.center = center;
	}

	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) throws Exception {
		if(radius<0)
		{
			throw new Exception("Mora biti veci od 0");
		}
		else this.radius = radius;
	}

	
	public double area() {
		return this.radius * this.radius * Math.PI;
	}

	public double circumference() {
		return 2 * this.radius * Math.PI;
	}
	
	public String toString() {
		// Center=(x,y), radius= radius
		return "Circle:" +"("+ "(Center:" + center.getX()+","+center.getY() + "), radius=" + radius+","+"EdgeColor:"+this.eColor+","+"InnerColor:"+ this.iColor+")";
	}

	public void setCircle(Point point, int radius) {
		this.center=point;
		this.radius=radius;
		// TODO Auto-generated method stub
		
	}


	public Circle clone() {
		Circle c=new Circle(new Point());
		c.getCenter().setX(this.getCenter().getX());
		c.getCenter().setY(this.getCenter().getY());
		try {
			c.setRadius(this.getRadius());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		c.seteColor(this.geteColor());
		c.setiColor(this.getiColor());
		return c;
	}

	
}
