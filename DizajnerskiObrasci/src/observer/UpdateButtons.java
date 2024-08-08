package observer;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import mvc.DrawingFrame;

public class UpdateButtons implements PropertyChangeListener{

	DrawingFrame frame;
	public UpdateButtons(DrawingFrame frame) {
		this.frame = frame;
	}
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if(evt.getPropertyName().equals("btnModify")) frame.getBtnModify().setEnabled((boolean) evt.getNewValue());
		if(evt.getPropertyName().equals("btnDelete")) frame.getBtnDelete().setEnabled((boolean) evt.getNewValue());
		if(evt.getPropertyName().equals("btnUndo")) frame.getBtnUndo().setEnabled((boolean) evt.getNewValue());
		if(evt.getPropertyName().equals("btnRedo")) frame.getBtnRedo().setEnabled((boolean) evt.getNewValue());
		if(evt.getPropertyName().equals("btnToFront")) frame.getBtnToFront().setEnabled((boolean) evt.getNewValue());
		if(evt.getPropertyName().equals("btnToBack")) frame.getBtnToBack().setEnabled((boolean) evt.getNewValue());
		if(evt.getPropertyName().equals("btnBringToBack")) frame.getBtnBringToBack().setEnabled((boolean) evt.getNewValue());
		if(evt.getPropertyName().equals("btnBringToFront")) frame.getBtnBringToFront().setEnabled((boolean) evt.getNewValue());
		if(evt.getPropertyName().equals("tglbtnDrawing")) frame.getTglbtnDrawing().setEnabled((boolean) evt.getNewValue());
		if(evt.getPropertyName().equals("tglbtnSelecting")) frame.getTglbtnSelecting().setEnabled((boolean) evt.getNewValue());
		
		
		
	}

}
