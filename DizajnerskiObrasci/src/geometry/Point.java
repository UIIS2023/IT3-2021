package geometry;

import java.awt.Color;
import java.awt.Graphics;

public class Point extends Shape {

	private int  x;
	private int  y;
	
	
	
	
	public Point clone() {
		Point p=new Point();
		p.setX(this.getX());
		p.setY(this.getY());
		p.seteColor(this.geteColor());
		return p;
	}
	public void draw (Graphics g)
	{    g.setColor(geteColor());
		g.drawLine(x-2, y, x+2, y);
		g.drawLine(x,y-2,x,y+2);
		if(isSelected()) {
			g.setColor(Color.BLUE);
			g.drawRect(x-2, y-2, 4, 4);
			g.setColor(Color.BLACK);
		}
	}
	public int compareTo(Object obj)
	{
		if(obj instanceof Point)
		{
			Point pointToCompare=(Point)obj;
			return (int)(this.distance(0, 0)-pointToCompare.distance(0, 0));
		}
		return 0;
	}
	
	
	public void moveTo(int x,int y)
	{
		setX(x);
		this.y=y;
	}
	
	public void moveBy(int x,int y)
	{
		setX(this.x+x);
		this.y+=y;
	}
	
	public double distance (int xPoint2, int yPoint2)
	{
		double dx=this.x-xPoint2;
		double dy=this.y-yPoint2;
		double d=Math.sqrt(dx*dx+dy*dy);
		return d;
		}
	
	
	public boolean contains (int x, int y)
	{
		return this.distance(x,y)<=2;
	}
	
	public int  getX()
	{
		return this.x;
	}
	
	public  void setX(int x)
	{
		this.x=x;
	}
	
	public void setY(int y)
	{
		this.y=y;
	}




	public int getY() {
		return y;
	}

	

	//vezba 4

	public Point () {};
	public Point (int x, int y)
	{
		this.x=x;
		this.y=y;    //potpis metode(ime, redosled i broj tipova podataka) ne smeju biti isti, overloading-preklapanje naziva
		//vise metpda koje se isto nazivaju
	}
	public Point (int x, int y, boolean selected) 
	{//this.x=x;
	//this.y=y; 
		this(x,y); //iz prethodne metode, zna se sta poziva zbog tipa i broja parametara
	setSelected (selected);	
	//this.selected=selected;
	
	};
	public Point (int x, int y, Color eColor)
	{
		this.x=x;
		this.y=y;
		this.eColor=eColor;
		//seteColor(eColor);
	}


	
	/*objct je roditeljska klasa, sve ostale nasledjuju 
	 * njene osobine 
	 * r1=r2    prenosi se i vrednost i referenca
	 * r1==r2 porede se reference, i imace iste vrednosti,uvek true
	 * ako imamo new onda su dva objekta, dve raylicite refenrece
	 * equals dal su jednake vrednosti, metoda instance
	 * overwriting predefinisanje metode/isti potpis metode, a razlicita implementacija
	 * dok kod overloadinga imamo razlicite parametre
	 */
	
	

	//equals je metoda instance i poziva se nad objektom
	public boolean equals(Object obj) {
		if (obj instanceof Point) {
			Point pomocna = (Point) obj;
			/*downcast objekat pretvaramo u tacku, konvertovanje //poredjenje po vrednosti*/
			if (this.x == pomocna.x && this.y == pomocna.y)//ne mora da stoji this
				return true;
			else
				return false;
		} else
			return false;
	}




	public String toString() {
		return "Point:"+ "(" + x + "," + y + ")" + "Color:"+ this.eColor;
		//return x; ne moze int u string, konkatenacija
		//redefinisanje ili overwriting metode string
	}
	
	
	
}
