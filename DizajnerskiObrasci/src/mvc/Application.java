package mvc;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;


public class Application {

	public static void main(String[] args) {
		System.out.println("Dobrodošli na vežbe iz predmeta Dizajnerski obrasci.");
	
		
		DrawingFrame frame=new DrawingFrame();
		DrawingModel model=new DrawingModel();
		frame.getView().setModel(model);
		DrawingController controller = new DrawingController(model, frame);
		frame.setController(controller);
		frame.setSize(1400,600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		
		
	

	}

}
