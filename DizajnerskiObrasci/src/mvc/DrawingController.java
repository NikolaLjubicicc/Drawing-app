package mvc;

import command.*;
import java.awt.Color;
import java.awt.Frame;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import adapter.HexagonAdapter;
import drawing.CircleDlg;
import drawing.DonutDlg;
import drawing.HexagonDlg;
import drawing.LineDlg;
import drawing.PointDlg;
import drawing.RectangleDlg;
import geometry.Circle;
import geometry.Donut;
import geometry.Line;
import geometry.PnlDrawing;
import geometry.Point;
import geometry.Rectangle;
import geometry.Shape;
import observer.Observable;
import observer.UpdateButtons;
import strategy.LoadingSavingLog;
import strategy.LoadingSavingLogStrategy;

public class DrawingController {
	
	private DrawingModel model;
	private DrawingFrame frame;
	
	
	private int drawing=1;
	private int selecting=0;
	private int activity=drawing;
	private Color outerColor=Color.WHITE,innerColor= Color.BLACK;
	boolean lineWaitingForEndPoint=true;
	private Point startPoint;
	Shape shapes;
	private ArrayList<Command> undoList = new ArrayList<Command>();
	private ArrayList<Command> redoList = new ArrayList<Command>();
	private Observable observableButtons = new Observable();
	private UpdateButtons updateButtons;
	private LoadingSavingLogStrategy loadingSavingLog;
	
	public DrawingController(DrawingModel model, DrawingFrame frame) {
		this.model = model;
		this.frame = frame;
		this.updateButtons = new UpdateButtons(frame);
		this.observableButtons.addPropertyChangeListener(updateButtons);
		this.loadingSavingLog = new LoadingSavingLog(this);
	}
	
	



	private void execute(Command command) {
		
		command.execute();
		undoList.add(command);
		redoList.clear();
		frame.repaint();
		updateButton();
		frame.logTextArea.append(command.toString() + '\n');
	}
	
	public void undo() {
		int index = undoList.size()-1;
		if(index < 0) {
			JOptionPane.showMessageDialog(frame, "There is nothing to undo", "error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		Command command = undoList.get(index);
		command.unexecute();
		undoList.remove(index);
		redoList.add(command);
		frame.repaint();
		
		frame.logTextArea.append("Undo -> "+command.toString() + '\n');
		updateButton();
	}
	
	public void redo() {
		int index = redoList.size()-1;
		if(index < 0) {
			JOptionPane.showMessageDialog(frame, "There is nothing to redo", "error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		Command command = redoList.get(index);
		command.execute();
		redoList.remove(index);
		undoList.add(command);
		frame.repaint();
		updateButton();
		frame.logTextArea.append("Redo -> "+command.toString() + '\n');
	}
	
	public void setDrawing() {
		frame.tglbtnSelecting.setSelected(false);
		activity=drawing;
		deselect();
		frame.btnModify.setEnabled(false);
		frame.btnDelete.setEnabled(false);
		frame.tglbtnPoint.setEnabled(true);
		frame.tglbtnPoint.setSelected(false);
		frame.tglbtnLine.setEnabled(true);
		frame.tglbtnRectangle.setEnabled(true);
		frame.tglbtnCircle.setEnabled(true);
		frame.tglbtnDonut.setEnabled(true);
		frame.tglbtnHexagon.setEnabled(true);
		frame.btnInnerColor.setSelected(false);
		frame.btnOuterColor.setSelected(false);
	}
	public void setSelectingShapes() {
		frame.tglbtnDrawing.setSelected(false);
		activity=selecting;
		
		frame.btnModify.setEnabled(false);
		frame.btnDelete.setEnabled(false);
		frame.tglbtnPoint.setEnabled(false);
		frame.tglbtnLine.setEnabled(false);
		frame.tglbtnRectangle.setEnabled(false);
		frame.tglbtnCircle.setEnabled(false);
		frame.tglbtnDonut.setEnabled(false);
		frame.tglbtnHexagon.setEnabled(false);
		
		frame.tglbtnPoint.setSelected(false);
		frame.tglbtnLine.setSelected(false);
		frame.tglbtnRectangle.setSelected(false);
		frame.tglbtnCircle.setSelected(false);
		frame.tglbtnDonut.setSelected(false);
		frame.tglbtnHexagon.setSelected(false);
		frame.btnInnerColor.setSelected(false);
		frame.btnOuterColor.setSelected(false);
	}
	public void btnPointClicked() {
		frame.getTglbtnLine().setSelected(false);
		frame.getTglbtnRectangle().setSelected(false);
		frame.getTglbtnCircle().setSelected(false);
		frame.getTglbtnDonut().setSelected(false);
		frame.getTglbtnHexagon().setSelected(false);
	}
	public void btnLineClicked(){
		frame.getTglbtnPoint().setSelected(false);
		frame.getTglbtnRectangle().setSelected(false);
		frame.getTglbtnCircle().setSelected(false);
		frame.getTglbtnDonut().setSelected(false);
		frame.getTglbtnHexagon().setSelected(false);
	}
	public void btnRectangleClicked() {
		frame.getTglbtnPoint().setSelected(false);
		frame.getTglbtnLine().setSelected(false);
		frame.getTglbtnCircle().setSelected(false);
		frame.getTglbtnDonut().setSelected(false);
		frame.getTglbtnHexagon().setSelected(false);
	}
	public void btnCircleClicked() {
		frame.getTglbtnPoint().setSelected(false);
		frame.getTglbtnLine().setSelected(false);
		frame.getTglbtnRectangle().setSelected(false);
		frame.getTglbtnDonut().setSelected(false);
		frame.getTglbtnHexagon().setSelected(false);
	}
	public void btnDonutClicked() {
		frame.getTglbtnPoint().setSelected(false);
		frame.getTglbtnLine().setSelected(false);
		frame.getTglbtnRectangle().setSelected(false);
		frame.getTglbtnCircle().setSelected(false);
		frame.getTglbtnHexagon().setSelected(false);
	}
	public void btnHexagonClicked() {
		frame.getTglbtnPoint().setSelected(false);
		frame.getTglbtnLine().setSelected(false);
		frame.getTglbtnRectangle().setSelected(false);
		frame.getTglbtnCircle().setSelected(false);
		frame.getTglbtnDonut().setSelected(false);
	}
	public Shape getSelected() {
		Shape shape=null;
		for (int i = model.getShapes().size() -1; i >= 0; i--) {
			if (model.getShapes().get(i).isselected()) {
				shape = model.getShapes().get(i);
				return shape;
			}
		}
		return shape;
	}
	public void deselect() {
		DeselectShapeCmd command = null;
		for(Shape shape : model.getShapes()) {
			if(shape.isselected() == true) {
			 command = new DeselectShapeCmd(shape, model);
			 execute(command);
			}
			
		}
	}
	public void deselectOne(Shape shape) {
		DeselectShapeCmd command = new DeselectShapeCmd(shape, model);;
		execute(command);
		
	}
	
	public void MouseClicked(MouseEvent e) {
		Shape newShape = null;
		Point click=new Point(e.getX(),e.getY());
		
		if(activity==selecting) {
			try {
				for (int i = model.getShapes().size() -1; i >= 0; i--) {
					shapes = model.getShapes().get(i);
					if (shapes.contains(e.getX(), e.getY())) {
						if(shapes.isselected() == true) {
							deselectOne(shapes);
							return;
						 }

						SelectShapeCmd command = new SelectShapeCmd(shapes, model);
						execute(command);
						return;
					}
					
					
				}
				deselect();

			}
			catch(Exception exc) {

				JOptionPane.showMessageDialog(frame.getPnlDrawing(), "Please select an object!");
				
			}
		}
		if(frame.getTglbtnPoint().isSelected()) {
			PointDlg pdlg=new PointDlg();
			pdlg.setPoint(click);
			pdlg.setColors(outerColor);
			pdlg.getBtnColor().setBackground(outerColor);
			pdlg.setVisible(true);
			if(pdlg.getPoint()!=null) {
				newShape = pdlg.getPoint();
				
			}

		}
		else if (frame.getTglbtnLine().isSelected()) {
			if(lineWaitingForEndPoint) {
				startPoint=click;
				lineWaitingForEndPoint=false;
				return;
			}
	
			LineDlg ldlg = new LineDlg();
			Line l = new Line(startPoint,click);
			ldlg.setLine(l);
			ldlg.setColors(outerColor);
			ldlg.getBtnColor().setBackground(outerColor);
			ldlg.setVisible(true);
			if(ldlg.getLine()!= null) {
				newShape = ldlg.getLine();
			}
			lineWaitingForEndPoint=true;

	
		}
		else if(frame.getTglbtnRectangle().isSelected()) {
			RectangleDlg rdlg=new RectangleDlg();
			rdlg.setPoint(click);
			rdlg.setColors(outerColor, innerColor);
			rdlg.getBtnInnerColor().setBackground(innerColor);
			rdlg.getBtnOuterColor().setBackground(outerColor);
			rdlg.setVisible(true);
			if(rdlg.getRectangle()!=null) {
				newShape = rdlg.getRectangle();

			}

		}
		else if(frame.getTglbtnCircle().isSelected()) {
			CircleDlg cdlg=new CircleDlg();
			cdlg.setPoint(click);
			cdlg.setColors(outerColor, innerColor);
			cdlg.getBtnInnerColor().setBackground(innerColor);
			cdlg.getBtnOuterColor().setBackground(outerColor);
			cdlg.setVisible(true);
			if(cdlg.getCircle()!=null) {
				newShape = cdlg.getCircle();

			}

		}
		else if(frame.getTglbtnDonut().isSelected()){
			DonutDlg ddlg=new DonutDlg();
			ddlg.setPoint(click);
			ddlg.setColors(outerColor, innerColor);
			ddlg.getBtnInnerColor().setBackground(innerColor);
			ddlg.getBtnOuterColor().setBackground(outerColor);
			ddlg.setVisible(true);
			if(ddlg.getDonut()!=null) {
				newShape = ddlg.getDonut();

			}

		}
		else if (frame.getTglbtnHexagon().isSelected()) {
			HexagonDlg hdlg = new HexagonDlg();
			hdlg.setPoint(click);
			hdlg.setColors(outerColor, innerColor);
			hdlg.getBtnInnerColor().setBackground(innerColor);
			hdlg.getBtnOuterColor().setBackground(outerColor);
			hdlg.setVisible(true);
			if(hdlg.getHexagon()!=null) {
				newShape = hdlg.getHexagon();

			}
		}
		
		if(newShape != null) {
			AddShapeCmd addShape = new AddShapeCmd(newShape,model);
			execute(addShape);
		}
			
		frame.repaint();
	}
	public void modify() {

		int i= model.getIndex(getSelected());
		if(i==-1) {
			return;
		}
		Shape s=model.getShapes().get(i);
		Command command = null;
		if(s instanceof Point) {
			PointDlg pdlg=new PointDlg();
			pdlg.setPoint((Point)s);
			pdlg.setVisible(true);
			if(pdlg.getPoint()!=null) {
				command = new UpdatePointCmd((Point)s, pdlg.getPoint());
				execute(command);
			
			}
		}
		else if(s instanceof Line) {
			LineDlg ldlg=new LineDlg();
			ldlg.setLine((Line)s);
			ldlg.setVisible(true);
			if(ldlg.getLine() != null) {
				command = new UpdateLineCmd((Line)s, ldlg.getLine());
				execute(command);
			
			}}
		else if(s instanceof Rectangle) {
			RectangleDlg rdlg=new RectangleDlg();
			rdlg.setRectangle((Rectangle) s);
			rdlg.setVisible(true);
			if(rdlg.getRectangle()!=null) {
				command = new UpdateRectangleCmd((Rectangle)s, rdlg.getRectangle());
				execute(command);
			}
		}
		else if(s instanceof Donut) {
			DonutDlg ddlg=new DonutDlg();
			ddlg.setDonut((Donut) s);
			ddlg.setVisible(true);
			if(ddlg.getDonut()!=null) {
				command = new UpdateDonutCmd((Donut)s, ddlg.getDonut());
				execute(command);
				
				
			}
		}
		else if(s instanceof Circle) {
			CircleDlg cdlg=new CircleDlg();
			cdlg.setCircle((Circle) s);
			cdlg.setVisible(true);
			if(cdlg.getCircle()!=null) {
				command = new UpdateCircleCmd((Circle)s, cdlg.getCircle());
				execute(command);
			}
		}
		else if (s instanceof HexagonAdapter) {
			HexagonDlg hdlg = new HexagonDlg();
			hdlg.setHexagon((HexagonAdapter) s);
			hdlg.setVisible(true);
			if(hdlg.getHexagon() != null) {
				command = new UpdateHexagonCmd((HexagonAdapter)s, hdlg.getHexagon());
				execute(command);
			}
		}
	
		frame.repaint();
		model.getShapes().get(i).setselected(true);
	}
	public void delete() {
		if (model.getShapes().isEmpty()) return;
		else if (getSelected() == null ) return;
		if (JOptionPane.showConfirmDialog(null, "Do you really want to delete the selected shape?", "Yes", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == 0) {
			Iterator<Shape> iterator = model.getShapes().iterator();
			ArrayList<Shape> selected = new ArrayList<Shape>();
			RemoveShapeCmd command=null;
	        while (iterator.hasNext()) {
	            Shape shape = iterator.next();
	            if(shape.isselected() == true) {
	            	selected.add(shape);
	            	
	            }
	        }

	        Iterator<Shape> iterator2 = selected.iterator();
	        while (iterator2.hasNext()) {
	        	command = new RemoveShapeCmd(model.getShapes().get(model.getIndex(iterator2.next())),model);
				execute(command);
	        }
			
		}
		

	}
	
	
	public void setInnerColor() {
		innerColor = JColorChooser.showDialog(null, "Choose the inner color", innerColor);
		frame.getInnerColor().setBackground(innerColor);
		if (innerColor == null) innerColor = Color.BLACK;
	}
	public void setOuterColor() {
		outerColor = JColorChooser.showDialog(null, "Choose the outer color", outerColor);
		frame.btnOuterColor.setBackground(outerColor);
		if (outerColor == null) outerColor = Color.BLACK;
		
	}
	public void bringToFront() {
		BringToFrontCmd command = new BringToFrontCmd(getSelected(), model);
		execute(command);
	}
	public void bringToBack() {
		BringToBackCmd command = new BringToBackCmd(getSelected(), model);
		execute(command);
	}
	public void toFront() {
		ToFrontCmd command = new ToFrontCmd(getSelected(), model);
		execute(command);
	}
	public void toBack() {
		ToBackCmd command = new ToBackCmd(getSelected(), model);
		execute(command);
	}
	
	public void updateButton() {
		int numberOfSelectedShapes=0;
		for (int i = model.getShapes().size() -1; i >= 0; i--) {
			if (model.getShapes().get(i).isselected()) {
				numberOfSelectedShapes++;
				}
			}
		if(numberOfSelectedShapes > 0) {
			observableButtons.setDelete(true);
			if(numberOfSelectedShapes == 1) {
				observableButtons.setModify(true);
				if(model.getShapes().indexOf(getSelected()) != 0) {
					observableButtons.setBringToBack(true);
					observableButtons.setToBack(true);
				} else {
					observableButtons.setBringToBack(false);
					observableButtons.setToBack(false);
				}
				if(model.getShapes().indexOf(getSelected()) != model.getShapes().size()-1)
				{
					observableButtons.setBringToFront(true);
					observableButtons.setToFront(true);
				}
				else {
					observableButtons.setBringToFront(false);
					observableButtons.setToFront(false);
				}
			} else if(numberOfSelectedShapes > 1) {
				observableButtons.setModify(false);
				observableButtons.setBringToFront(false);
				observableButtons.setToFront(false);
				observableButtons.setBringToBack(false);
				observableButtons.setToBack(false);
			}
		} else {
			observableButtons.setDelete(false);
			observableButtons.setModify(false);
			observableButtons.setBringToFront(false);
			observableButtons.setToFront(false);
			observableButtons.setBringToBack(false);
			observableButtons.setToBack(false);
		}
		
		if(!undoList.isEmpty()) {
			observableButtons.setUndo(true);
		} else {
			observableButtons.setUndo(false);
		}
		if(!redoList.isEmpty()) {
			observableButtons.setRedo(true);
		} else {
			observableButtons.setRedo(false);
		}
		
	}
	public void loadLog() {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Open");
		int userChoice = fileChooser.showOpenDialog(frame);
		fileChooser.setVisible(true);
		if (userChoice == JFileChooser.APPROVE_OPTION) {
			String filePath = fileChooser.getSelectedFile().getAbsolutePath();
			model.getShapes().clear();
			undoList.clear();
			redoList.clear();
			frame.getLogTextArea().setText("");
			frame.getLogTextArea().setText((String) loadingSavingLog.load(filePath));
		}
	}
	
	public void saveLog() {
		JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Save as");
        int userChoice = fileChooser.showSaveDialog(frame);
        if (userChoice == JFileChooser.APPROVE_OPTION) {
            String filePath = fileChooser.getSelectedFile().getAbsolutePath();
            loadingSavingLog.save(frame.getLogTextArea().getText(), filePath+".txt");
        } 
	}
	
	public void executeLogCmd(String str) {
		if(str.startsWith("AddShapeCmd")) {
			AddShapeCmd cmd = new AddShapeCmd(getParsedShape(str), model);
			execute(cmd);
		} else if (str.startsWith("RemoveShapeCmd")) {
			RemoveShapeCmd cmd = new RemoveShapeCmd(getParsedShape(str),model);
			execute(cmd);
		}else if (str.startsWith("SelectChapeCmd")) {
			SelectShapeCmd cmd = new SelectShapeCmd(getParsedShape(str),model);
			execute(cmd);
		}
		else if (str.startsWith("UpdateLineCmd")) {
			String[] temps = str.split("newState: ");
			String oldShape = temps[0];
			String newShape = temps[1];
			UpdateLineCmd cmd = new UpdateLineCmd((Line)getParsedShape(oldShape),(Line)getParsedShape(newShape));
			execute(cmd);
		}
	}
	public Shape getParsedShape(String str) {
		Shape shape;
		String[] strings = str.split(": ");

		 if (str.contains("Donut")) {
			 String[] outerColorStr = strings[6].split(",");
			 String[] innerColorStr = strings[7].split(",");
			 int x = Integer.parseInt(strings[2].split(" , ")[0]);
			 int y = Integer.parseInt(strings[3].split("] , ")[0]);
			 int outerRadius = Integer.parseInt(strings[4].split(" , ")[0]);
			 int innerRadius = Integer.parseInt(strings[5].split(" , ")[0]);
			 int rO = Integer.parseInt(outerColorStr[0].split("r=")[1]);
			 int gO = Integer.parseInt(outerColorStr[1].split("=")[1]);
			 String temp = outerColorStr[2].split("=")[1];
			 int bO = Integer.parseInt(temp.split("]")[0]);
			 int rI = Integer.parseInt(innerColorStr[0].split("r=")[1]);
			 int gI = Integer.parseInt(innerColorStr[1].split("=")[1]);
			 temp = innerColorStr[2].split("=")[1];
			 int bI = Integer.parseInt(temp.split("]")[0]);
			 shape = new Donut(new Point(x,y),outerRadius,innerRadius,false,new Color(rO,gO,bO),new Color(rI,gI,bI));
			 return shape;
				
		 }else if(str.contains("Circle")) {
			String[] outerColorStr = strings[5].split(",");
			String[] innerColorStr = strings[6].split(",");
			int x = Integer.parseInt(strings[2].split(" , ")[0]);
			int y = Integer.parseInt(strings[3].split("] , ")[0]);
			int radius = Integer.parseInt(strings[4].split(" , ")[0]);
			int rO = Integer.parseInt(outerColorStr[0].split("r=")[1]);
			int gO = Integer.parseInt(outerColorStr[1].split("=")[1]);
			String temp = outerColorStr[2].split("=")[1];
			int bO = Integer.parseInt(temp.split("]")[0]);
			int rI = Integer.parseInt(innerColorStr[0].split("r=")[1]);
			int gI = Integer.parseInt(innerColorStr[1].split("=")[1]);
			temp = innerColorStr[2].split("=")[1];
			int bI = Integer.parseInt(temp.split("]")[0]);
			shape = new Circle(new Point(x,y),radius,false,new Color(rO,gO,bO),new Color(rI,gI,bI));
			return shape;
			
		} else if (str.contains("Line")) {
			String[] colorStr = strings[7].split(",");
			int xS = Integer.parseInt(strings[2].split(" , ")[0]);
			int yS = Integer.parseInt(strings[3].split("] , ")[0]);
			int xE = Integer.parseInt(strings[5].split(" , ")[0]);
			int yE = Integer.parseInt(strings[6].split("] , ")[0]);
			int r = Integer.parseInt(colorStr[0].split("r=")[1]);
			int g = Integer.parseInt(colorStr[1].split("=")[1]);
			String temp = colorStr[2].split("=")[1];
			int b = Integer.parseInt(temp.split("]")[0]);
			shape = new Line(new Point(xS,yS),new Point(xE,yE),false,new Color(r,g,b));
			return shape;

		} else if (str.contains("Rectangle")) {
			String[] outerColorStr = strings[6].split(",");
			String[] innerColorStr = strings[7].split(",");
			int x = Integer.parseInt(strings[2].split(" , ")[0]);
			int y = Integer.parseInt(strings[3].split("] , ")[0]);
			int width = Integer.parseInt(strings[4].split(" , ")[0]);
			int height = Integer.parseInt(strings[5].split(" , ")[0]);
			int rO = Integer.parseInt(outerColorStr[0].split("r=")[1]);
			int gO = Integer.parseInt(outerColorStr[1].split("=")[1]);
			String temp = outerColorStr[2].split("=")[1];
			int bO = Integer.parseInt(temp.split("]")[0]);
			int rI = Integer.parseInt(innerColorStr[0].split("r=")[1]);
			int gI = Integer.parseInt(innerColorStr[1].split("=")[1]);
			temp = innerColorStr[2].split("=")[1];
			int bI = Integer.parseInt(temp.split("]")[0]);
			shape = new Rectangle(new Point(x,y),width,height,false,new Color(rO,gO,bO),new Color(rI,gI,bI));
			return shape;
		} else if (str.contains("HexagonAdapter")) {
			String[] outerColorStr = strings[5].split(",");
			String[] innerColorStr = strings[6].split(",");
			int x = Integer.parseInt(strings[2].split(" , ")[0]);
			int y = Integer.parseInt(strings[3].split("] , ")[0]);
			int radius = Integer.parseInt(strings[4].split(" , ")[0]);
			int rO = Integer.parseInt(outerColorStr[0].split("r=")[1]);
			int gO = Integer.parseInt(outerColorStr[1].split("=")[1]);
			String temp = outerColorStr[2].split("=")[1];
			int bO = Integer.parseInt(temp.split("]")[0]);
			int rI = Integer.parseInt(innerColorStr[0].split("r=")[1]);
			int gI = Integer.parseInt(innerColorStr[1].split("=")[1]);
			temp = innerColorStr[2].split("=")[1];
			int bI = Integer.parseInt(temp.split("]")[0]);
			shape = new HexagonAdapter(new Point(x,y),radius,false,new Color(rO,gO,bO),new Color(rI,gI,bI));
			return shape;
			
		} else if(str.contains("Point")) {
			String[] colorStr = strings[3].split(",");
			int x = Integer.parseInt(strings[1].split(" , ")[0]);
			int y = Integer.parseInt(strings[2].split("] , ")[0]);
			int r = Integer.parseInt(colorStr[0].split("r=")[1]);
			int g = Integer.parseInt(colorStr[1].split("=")[1]);
			String temp = colorStr[2].split("=")[1];
			int b = Integer.parseInt(temp.split("]")[0]);
			shape = new Point(x,y,false,new Color(r,g,b));
			return shape;
			
		}
		
		return null;
	}
	
}
