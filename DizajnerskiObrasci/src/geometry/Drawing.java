package geometry;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Drawing extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static void main(String args[]) {
		JFrame frame = new JFrame("Drawing");
		frame.setSize(800, 600);
		JPanel drawing = new Drawing();// kreiramo panel koji je instanca klase drawing
		frame.getContentPane().add(drawing);
		frame.setVisible(true);

	}

	public void paint(Graphics g)
	{
		Point p=new Point(10,15);
		p.draw(g);
		g.setColor(Color.red);
		Line l=new Line(new Point (20,30),new Point (40,50));
		l.draw(g);
		g.setColor(Color.BLACK);
		Donut d=new Donut(new Point(60,70),50,30,true);
		d.draw(g);
		Circle c=new Circle(new Point(15,15), 10);
		c.draw(g);
		Rectangle r=new Rectangle(new Point(20,20), 15, 20);
	
	
	ArrayList<Shape> shapes=new ArrayList<Shape>();
	
	shapes.add(p);
	shapes.add(l);
	shapes.add(c);
	shapes.add(d);
	shapes.add(r);
	Iterator<Shape> it=shapes.iterator();

	//Exception
			try {
				c.setRadius(-10);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

			it = shapes.iterator();
			while (it.hasNext()) {
				Shape sh = it.next();
				sh.moveBy(10, 0);
				sh.setSelected(true);
				sh.draw(g);
			}
	
   while (it.hasNext())
   { Shape sh=it.next();
	sh.moveBy(10,0);
	System.out.println(sh);
	}
   
	shapes.get(3).draw(g);//iscrtavanje 4.elementa
	shapes.get(shapes.size()-1).draw(g);//poslednji element
	shapes.remove(1);//pomeranje za 1 mesto
	shapes.get(1).draw(g);
	shapes.add(3,l);
	
     //Collections.sort(shapes)
   
   
	//Exception
	try {
		c.setRadius(-10);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	 //	System.out.println(e.getMessage());
	}
	
	
	
 HashMap<String,Shape> hmShapes=new HashMap<String,Shape>();
 hmShapes.put("point",p);
 hmShapes.put("line",l);
 System.out.println(hmShapes.get("point"));
 
}
	}

