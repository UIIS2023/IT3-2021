package geometry;

import java.awt.Color;
import java.awt.Graphics;

import hexagon.Hexagon;

public class HexagonAdapter extends Shape {

	
	protected Point center=new Point(); 
	protected int radius;
	private Hexagon hexagon;
	 
	
	
	public HexagonAdapter() {
		hexagon=new Hexagon(0,0,0);
		
	}
	public HexagonAdapter(Hexagon hexagon) {
		this.hexagon=hexagon;	
	}
	
	public HexagonAdapter(Hexagon hexagon,Point center) {
		this.hexagon=hexagon;
		hexagon.setX(center.getX());
		hexagon.setY(center.getY());
	}
	
	public HexagonAdapter(Hexagon hexagon,Color eColor,Color iColor) {
		this.hexagon=hexagon;
		hexagon.setBorderColor(eColor);
		hexagon.setAreaColor(iColor);
	}

	public HexagonAdapter(Point center, int radius) {
		this();
		hexagon.setX(center.getX());
		hexagon.setY(center.getY());
		hexagon.setR(radius);
	
	}
	public HexagonAdapter(Point center, int radius,Color eColor, Color iColor) {
		this(center,radius);
		seteColor(eColor);
		setiColor(iColor);
	}
	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		
		hexagon.setSelected(isSelected());
		hexagon.paint(g);
		
		
		}
	
	public boolean contains (int x, int y)
	{
		return hexagon.doesContain(x, y);
	}
	
	
	public void moveTo(int x,int y)
	{
		center.moveTo(x,y);
		
	}
	
	public void moveBy(int x,int y)
	{
		center.moveBy(x, y);
		
	}

	//return "Circle:" +"("+ "(Center:" + center.getX()+","+center.getY() + "), radius=" + radius+","+"EdgeColor:"+this.eColor+","+"InnerColor:"+ this.iColor+")";
	public String toString() {
		
		return "Hexagon:" +"("+"(Center:" + hexagon.getX()+"," + hexagon.getY()+"), radius=" + hexagon.getR()+ ","+"EdgeColor:"+hexagon.getBorderColor()+","+"InnerColor:"+hexagon.getAreaColor()+")";
	}

	public void setHexagonAdapter(Point point, int radius) {
		this.center=point;
		this.radius=radius;
		// TODO Auto-generated method stub
		
	}
	
	
	public double area() {
		return this.radius * this.radius * Math.PI;
	}
	
	public int compareTo(Object obj)
	{
		if(obj instanceof HexagonAdapter)
		{
			HexagonAdapter hexagonToCompare=(HexagonAdapter)obj;
			return (int)(this.area()-hexagonToCompare.area());
		}
		return 0;
	}

	public boolean equals(Object obj) {
		if (obj instanceof HexagonAdapter) {
			HexagonAdapter pomocni = (HexagonAdapter) obj;
			if (this.center.equals(pomocni.center) && this.radius == pomocni.radius) {
				return true;
			} else {
				return false;
			}
		} else 
			return false;
		}
	

	public void setCenter(Point center) {
		this.hexagon.setX(center.getX()); 
		this.hexagon.setY(center.getY()); 
		//this.center=center;
	}

	public int getRadius() {
		return hexagon.getR();
	}

	public void setRadius(int radius) throws Exception {
		if(radius<0)
		{
			throw new Exception("Mora biti veci od 0");
		}
		else 
			
			this.hexagon.setR(radius);

	}
	public Color geteColor() {
        return hexagon.getBorderColor();
    }

    public void seteColor(Color eColor) {
        hexagon.setBorderColor(eColor);
    }

    public Color getiColor() {
        return hexagon.getAreaColor();
    }

    public void setiColor(Color iColor) {
        hexagon.setAreaColor(iColor);
    }
	
    public HexagonAdapter clone() {
    	Hexagon hex=new Hexagon(0,0,0);
    	HexagonAdapter hexagon=new HexagonAdapter(hex);
    	hexagon.setX(this.getX());
    	hexagon.setY(this.getY());
    	try {
			hexagon.setRadius(this.getRadius());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	hexagon.seteColor(this.geteColor());
    	hexagon.setiColor(this.getiColor());
    	return hexagon;
    }
	public Hexagon getHexagon() {
		return hexagon;
	}
	public void setHexagon(Hexagon hexagon) {
		this.hexagon = hexagon;
	}
    
	public void setX(int x) {
		this.hexagon.setX(x);
	}
	public void setY(int y) {
		this.hexagon.setY(y);
	}
    
    
  public int getX() {
	  return hexagon.getX();
  }
  
  public int getY() {
	  return hexagon.getY();
  }
  
  
  

	
}
