package strategy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import command.Command;

public class SaveLog implements Save {

	private DefaultListModel<String> dlm;
	
	
	public SaveLog(DefaultListModel<String> dlm) {
		this.dlm = dlm;
	}

	public void save() {
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Save txt");
        int i = chooser.showSaveDialog(chooser);
        if (i != JFileChooser.APPROVE_OPTION) return;

        File selectedFile = chooser.getSelectedFile();

        // ako postoji
        if (selectedFile.exists()) {
            int result = JOptionPane.showConfirmDialog(null, "The file already exists. Do you want to overwrite it?", "File Exists", JOptionPane.YES_NO_OPTION);
            if (result != JOptionPane.YES_OPTION) {
                // ako ne zelimo da overwrite odradimo
                return;
            }
        }

        if (!selectedFile.getName().toLowerCase().endsWith(".txt")) {
            selectedFile = new File(selectedFile.getAbsolutePath() + ".txt");
        }
        try (BufferedWriter bf = new BufferedWriter(new FileWriter(selectedFile))) {
            for (int index = 0; index < dlm.size(); index++) {
                bf.write(dlm.get(index) + "\n");
            }
            JOptionPane.showMessageDialog(null, "Log saved successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e1) {
            e1.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error saving log: " + e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void load() {
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Load txt");
        int result = chooser.showOpenDialog(chooser);

        if (result != JFileChooser.APPROVE_OPTION) {
            return;
        }

        File selectedFile = chooser.getSelectedFile();
        if (!selectedFile.getName().toLowerCase().endsWith(".txt")) {
            JOptionPane.showMessageDialog(null, "Invalid file format. Please select a .txt file.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(selectedFile))) {
            dlm.clear();
            String line;
            while ((line = reader.readLine()) != null) {
                dlm.addElement(line);
            }
            JOptionPane.showMessageDialog(null, "Log load successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error loading log: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    
	}
	

		
    

	

}
