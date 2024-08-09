package observer;


import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class Observable {
	
	private boolean modify;
	private boolean delete;
	private boolean undo;
	private boolean redo;
	private boolean toFront;
	private boolean bringToFront;
	private boolean toBack;
	private boolean bringToBack;
	private boolean drawing;
	private boolean selecting;
	
	private PropertyChangeSupport propertyChangeSupport;
	
	public Observable() {
		propertyChangeSupport = new PropertyChangeSupport(this);
	}
	
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		propertyChangeSupport.addPropertyChangeListener(listener);
	}

	public void removePropertyChangeListener(PropertyChangeListener listener) {
		propertyChangeSupport.removePropertyChangeListener(listener);
	}
	
	public void setModify(boolean modifyState) {
		propertyChangeSupport.firePropertyChange("btnModify", this.modify, modifyState);
		this.modify = modifyState;
	}
	public void setDelete(boolean deleteState) {
		propertyChangeSupport.firePropertyChange("btnDelete", this.delete, deleteState);
		this.delete = deleteState;
	}
	public void setUndo(boolean undoState) {
		propertyChangeSupport.firePropertyChange("btnUndo", this.undo, undoState);
		this.undo = undoState;
	}
	public void setRedo(boolean redoState) {
		propertyChangeSupport.firePropertyChange("btnRedo", this.redo, redoState);
		this.redo = redoState;
	}
	public void setToFront(boolean toFrontState) {
		propertyChangeSupport.firePropertyChange("btnToFront", this.toFront, toFrontState);
		this.toFront = toFrontState;
	}
	public void setBringToFront(boolean bringToFrontState) {
		propertyChangeSupport.firePropertyChange("btnBringToFront", this.bringToFront, bringToFrontState);
		this.bringToFront = bringToFrontState;
	}
	public void setToBack(boolean toBackState) {
		propertyChangeSupport.firePropertyChange("btnToBack", this.toBack, toBackState);
		this.toBack = toBackState;
	}
	public void setBringToBack(boolean bringToBackState) {
		propertyChangeSupport.firePropertyChange("btnBringToBack", this.bringToBack, bringToBackState);
		this.bringToBack = bringToBackState;
	}

	
	
	
	

}
