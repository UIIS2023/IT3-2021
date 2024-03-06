package observer;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JToggleButton;

public class Button  {

	private PropertyChangeSupport propertyChangeSupport;

	public Button() {
		this.propertyChangeSupport=new PropertyChangeSupport(this);
	}
	
	public void addListener(PropertyChangeListener listener) {
		propertyChangeSupport.addPropertyChangeListener(listener);
	}
	
public void removeListener(PropertyChangeListener listener) {
		propertyChangeSupport.removePropertyChangeListener(listener);
	}

public void buttonVisibilityChanged() {
	propertyChangeSupport.firePropertyChange("buttonVisibilityChanged", null, null);
}
}