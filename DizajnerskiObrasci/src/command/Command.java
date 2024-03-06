package command;

import java.util.List;
import java.util.Stack;

public interface Command {
	
	public void execute();
	public void unexecute();
	public String getName();
	public String getNameForUndo();
	public String getNameForRedo();
	
}
