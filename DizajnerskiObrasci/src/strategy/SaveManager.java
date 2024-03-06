package strategy;

import java.io.File;

public class SaveManager {

	private Save saveLog,savePaint,save;
	
	
	public SaveManager(Save saveLog,Save savePaint) {
		this.saveLog=saveLog;
		this.savePaint=savePaint;
		
	}
	public SaveManager(Save save) {
		this.save=save;
		
	}

	public void save() {
		// TODO Auto-generated method stub
		saveLog.save();
		savePaint.save();
		
	}
	
	
public void load() {
	saveLog.load();
	savePaint.load();
}
}
