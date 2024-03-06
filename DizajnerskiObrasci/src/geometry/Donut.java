package geometry;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;

//eksplicitno navodimo da donut nasledjuje circle
public class Donut extends Circle {

	private int innerRadius;
    public Donut() {};
    public Donut(Point center) {
    	this.center=center;
    			};
    public Donut(Point center, int radius, int innerRadius) {
		super(center, radius);
		this.innerRadius = innerRadius;
	}
	
	
	public Donut(Point center, int radius, int innerRadius, Color eColor, Color iColor) { 
		
		this.center=center;
		this.radius=radius;
		this.innerRadius=innerRadius;
		seteColor(eColor);
		setiColor(iColor);
	}
	public Donut(Point center, int radius, int innerRadius, boolean selected) {
		//this.center = center;//jer je definisano kao protected u circle
	   //	setRadius(radius);//jer je private,iako se ne nalazi u Donut, odlazi u circle i tamo pronalazi metodu
		// is.Radius=radius ne moze jer je private
		this(center,radius,innerRadius);
		setSelected(selected);
	}

		
	public int compareTo(Object obj)
	{
		if(obj instanceof Donut)
		{
			Donut donutToCompare=(Donut)obj;
			return (int)(this.area()-donutToCompare.area());
		}
		return 0;
	}

	public int getInnerRadius() {
		return innerRadius;
	}                //donut je instanca kruga, moramo definisati, downkastikng

	public void setInnerRadius(int innerRadius) {
		this.innerRadius = innerRadius;
	};

	public boolean equals(Object obj) {
		if (obj instanceof Donut) {
			Donut pomocni = (Donut) obj;
			/*
			 * f (this.center.equals(pomocni.center) && this.getCenter()
			 * ==pomocni.getRadius()) { return true; } else { return false; } } else return
			 * false;
			 */
			if (super.equals(pomocni) && this.innerRadius == pomocni.innerRadius) {
				return true;
			} else
				return false;}
		else return false;
		}
	


	public void draw (Graphics g)
	{   

		Graphics2D g2d=(Graphics2D) g.create();
	    Ellipse2D outerCircle = new Ellipse2D.Double(center.getX() - radius, center.getY() - radius, radius * 2, radius * 2);
        Ellipse2D innerCircle = new Ellipse2D.Double(center.getX() - (innerRadius - 2), center.getY() - (innerRadius - 2), (innerRadius - 2) * 2, (innerRadius - 2) * 2);

        // Napravite oblasti iz oblika
        Area areaOuter = new Area(outerCircle);
        Area areaInner = new Area(innerCircle);

        // Izbuši rupu u spoljašnjem krugu (donut efekat)
        areaOuter.subtract(areaInner);

        // Bojenje ivice donuta
        g2d.setColor(eColor);
        g2d.draw(areaOuter);
        g2d.setColor(iColor);
        g2d.fill(areaOuter);
		
		if (isSelected()) {
			g.setColor(Color.BLUE);
			g.drawRect(center.getX() - 2, center.getY() - 2, 4, 4);
			g.drawRect(center.getX() - innerRadius - 2, center.getY() - 2, 4, 4);
			g.drawRect(center.getX() + innerRadius - 2, center.getY() - 2, 4, 4);
			g.drawRect(center.getX() - 2, center.getY() - innerRadius - 2, 4, 4);
			g.drawRect(center.getX() - 2, center.getY() + innerRadius - 2, 4, 4);
			g.setColor(Color.black);
			
		}    

}

	public boolean contains(int x, int y) {

		return super.contains(x,y) && center.distance(x,y) >= innerRadius;
	}

	public double area() {
		return super.area() - innerRadius * innerRadius * Math.PI;
	}

	public String toString() {
		return "Donut:" + "(Center:" + center.getX()+","+center.getY() + "), radius=" + radius + ", innerRadius:" + innerRadius+","+"EdgeColor:"+this.eColor+","+"InnerColor:"+this.iColor+")";
	}

	public Donut clone() {
		Donut d=new Donut(new Point());
		d.getCenter().setX(this.getCenter().getX());
		d.getCenter().setY(this.getCenter().getY());
		try {
			d.setRadius(this.getRadius());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		d.setInnerRadius(this.getInnerRadius());
		d.seteColor(this.geteColor());
		d.setiColor(this.getiColor());
		return d;
	}
	
}
