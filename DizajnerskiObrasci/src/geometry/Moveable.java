package geometry;

public interface Moveable {
	
	public abstract void moveTo(int x,int y);
	void moveBy(int x,int y); //public abstract podrazumevano
	int compareTo(Object  obj);

}
