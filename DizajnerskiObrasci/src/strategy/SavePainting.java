package strategy;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import geometry.Shape;

public class SavePainting implements Save {
	private ArrayList<Shape> shapes;
	

	public SavePainting(ArrayList<Shape> shapes) {
		this.shapes = shapes;
	}



	public void save() {
	    JFileChooser chooser = new JFileChooser();
	    chooser.setDialogTitle("Save drawing");
        int result = chooser.showSaveDialog(null);

        if (result != JFileChooser.APPROVE_OPTION) {
            return;
        }

        File selectedFile = chooser.getSelectedFile();

        if (!selectedFile.getName().toLowerCase().endsWith(".bin")) {
            selectedFile = new File(selectedFile.getAbsolutePath() + ".bin");
        }

        // Ako fajl već postoji
        if (selectedFile.exists()) {
            result = JOptionPane.showConfirmDialog(null, "The file already exists. Do you want to overwrite it?", "File Exists", JOptionPane.YES_NO_OPTION);
            if (result != JOptionPane.YES_OPTION) {
                // Ako ne želimo da prepisujemo fajl
                return;
            }
        }

        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(selectedFile))) {
            // Čuvanje liste oblika
            objectOutputStream.writeObject(shapes);

            JOptionPane.showMessageDialog(null, "Drawing saved successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e1) {
            e1.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error saving drawing: " + e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void load() {
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Load drawing");
        int result = chooser.showOpenDialog(null);

        if (result != JFileChooser.APPROVE_OPTION) {
            return;
        }

        File selectedFile = chooser.getSelectedFile();

        if (!selectedFile.getName().toLowerCase().endsWith(".bin")) {
            JOptionPane.showMessageDialog(null, "Invalid file format. Please select a .bin file.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(selectedFile))) {
            // Učitavanje liste oblika
            ArrayList<Shape> loadedShapes = (ArrayList<Shape>) objectInputStream.readObject();
            
            // Čišćenje trenutne liste i dodavanje učitanih oblika
            shapes.clear();
            shapes.addAll(loadedShapes);

            JOptionPane.showMessageDialog(null, "Drawing load successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error loading drawing: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

}
