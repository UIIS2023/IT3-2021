package mvc;

import java.awt.Graphics;
import java.util.ArrayList;

import geometry.Point;
import geometry.Shape;

public class DrawingModel extends Shape {
	
	ArrayList<Shape> shapes=new ArrayList<Shape>();

	
		public void add(Shape shape) {
			shapes.add(shape);
		}
			
		public ArrayList<Shape> getShapes (){
			return shapes;
		}
		public void remove(Shape shape){
			shapes.remove(shape);
		}
		
		public Shape getShape(int index) {
			return shapes.get(index);

}

		@Override
		public void moveTo(int x, int y) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void moveBy(int x, int y) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public int compareTo(Object obj) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public boolean contains(int x, int y) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public void draw(Graphics g) {
			// TODO Auto-generated method stub
			
		}

		
		
		

}
