package mvc;

import java.awt.Color;
import java.awt.Frame;
import java.awt.event.MouseEvent;

import javax.swing.JColorChooser;
import javax.swing.JOptionPane;

import drawing.CircleDlg;
import drawing.DonutDlg;
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

public class DrawingController {
	
	private DrawingModel model;
	private DrawingFrame frame;
	
	public DrawingController(DrawingModel model, DrawingFrame frame) {
		this.model = model;
		this.frame = frame;
	}
	
	private int drawing=1;
	private int selecting=0;
	private int activity=drawing;
	private Color outerColor=Color.WHITE,innerColor= Color.BLACK;
	boolean lineWaitingForEndPoint=false;
	private Point startPoint;
	Shape shapes;
	
	
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
	}
	public void setSelectingShapes() {
		frame.tglbtnDrawing.setSelected(false);
		activity=selecting;
		frame.btnModify.setEnabled(true);
		frame.btnDelete.setEnabled(true);
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
	}
	public int getSelected() {
		for (int i = model.getShapes().size() -1; i >= 0; i--) {
			if (model.getShapes().get(i).isselected()) {
				return i;
			}
		}
		return -1;
	}
	public void deselect() {
		model.getShapes().forEach(shape -> shape.setselected(false));
		frame.repaint();
	}
	
	public void MouseClicked(MouseEvent e) {
		Shape newShape = null;
		Point click=new Point(e.getX(),e.getY());
		deselect();
		if(activity==selecting) {
			try {
				for (int i = model.getShapes().size() -1; i >= 0; i--) {
					shapes = model.getShapes().get(i);
					if (shapes.contains(e.getX(), e.getY())) {
						model.getShapes().get(i).setselected(true);
						frame.repaint();
						return;
					}
				}
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
				model.add(newShape);
				frame.repaint();
			}
			return;
		}
		else if (frame.getTglbtnLine().isSelected()) {
			if(lineWaitingForEndPoint) {
				LineDlg ldlg = new LineDlg();
				Line l = new Line(startPoint,click);
				ldlg.setLine(l);
				ldlg.setColors(outerColor);
				ldlg.getBtnColor().setBackground(outerColor);
				ldlg.setVisible(true);
				if(ldlg.getLine()!= null) {
					newShape = ldlg.getLine();
					model.add(newShape);
					frame.repaint();
				}
				lineWaitingForEndPoint=false;
				return;
			}
			startPoint=click;
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
				model.add(newShape);
				frame.repaint();
			}
			return;
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
				model.add(newShape);
				frame.repaint();
			}
			return;
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
				model.add(newShape);
				frame.repaint();
			}
			return;
		}
			
		frame.repaint();
	}
	public void modify() {

		int i= getSelected();
		if(i==-1) {
			return;
		}
		Shape s=model.getShapes().get(i);
		if(s instanceof Point) {
			PointDlg pdlg=new PointDlg();
			pdlg.setPoint((Point)s);
			pdlg.setVisible(true);
			if(pdlg.getPoint()!=null) {
				model.getShapes().set(i, s);
			
			}
		}
		else if(s instanceof Line) {
			LineDlg ldlg=new LineDlg();
			ldlg.setLine((Line)s);
			ldlg.setVisible(true);
			if(ldlg.getLine() != null) {
				model.getShapes().set(i, ldlg.getLine());
			
			}}
		else if(s instanceof Rectangle) {
			RectangleDlg rdlg=new RectangleDlg();
			rdlg.setRectangle((Rectangle) s);
			rdlg.setVisible(true);
			if(rdlg.getRectangle()!=null) {
				model.getShapes().set(i, rdlg.getRectangle());
			
			}
		}
		else if(s instanceof Donut) {
			DonutDlg ddlg=new DonutDlg();
			ddlg.setDonut((Donut) s);
			ddlg.setVisible(true);
			if(ddlg.getDonut()!=null) {
				model.getShapes().set(i, ddlg.getDonut());
				
			}
		}
		else if(s instanceof Circle) {
			CircleDlg cdlg=new CircleDlg();
			cdlg.setCircle((Circle) s);
			cdlg.setVisible(true);
			if(cdlg.getCircle()!=null) {
				model.getShapes().set(i, cdlg.getCircle());
			}
		}
		
		frame.repaint();
		model.getShapes().get(i).setselected(true);
	}
	public void delete() {
		if (model.getShapes().isEmpty()) return;
		if (JOptionPane.showConfirmDialog(null, "Do you really want to delete the selected shape?", "Yes", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == 0)
			model.getShapes().removeIf(s -> s.isselected());
		
			frame.repaint();
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
	
}
