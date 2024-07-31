package mvc;

import java.awt.Color;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;

import drawing.CircleDlg;
import drawing.DonutDlg;
import drawing.LineDlg;
import drawing.PointDlg;
import drawing.RectangleDlg;
import geometry.Line;
import geometry.PnlDrawing;
import geometry.Point;

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
	
	
	public void MouseClicked(MouseEvent e) {
		Point click=new Point(e.getX(),e.getY());
		frame.getPnlDrawing().deselect();
		if(activity==selecting) {
			try {
				frame.getPnlDrawing().select(click);
			    frame.getLblInfo().setText(frame.getPnlDrawing().getShape(frame.getPnlDrawing().getSelected()).toString());
				return;
			}
			catch(Exception exc) {
				frame.getLblInfo().setText("");
				JOptionPane.showMessageDialog(frame.getPnlDrawing(), "Please select an object!");
				
			}
		}
		if(frame.getTglbtnPoint().isSelected()) {
			PointDlg pdlg=new PointDlg();
			pdlg.setPoint(click);
			pdlg.setColors(outerColor);
			pdlg.setVisible(true);
			if(pdlg.getPoint()!=null) {
				frame.getPnlDrawing().newShape(pdlg.getPoint());
			}
			return;
		}
		else if (frame.getTglbtnLine().isSelected()) {
			if(lineWaitingForEndPoint) {
				LineDlg ldlg = new LineDlg();
				Line l = new Line(startPoint,click);
				ldlg.setLine(l);
				ldlg.setColors(outerColor);
				ldlg.setVisible(true);
				if(ldlg.getLine()!= null) {
					frame.getPnlDrawing().newShape(ldlg.getLine());
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
			rdlg.setVisible(true);
			if(rdlg.getRectangle()!=null) {
				frame.getPnlDrawing().newShape(rdlg.getRectangle());
			}
			return;
		}
		else if(frame.getTglbtnCircle().isSelected()) {
			CircleDlg cdlg=new CircleDlg();
			cdlg.setPoint(click);
			cdlg.setColors(outerColor, innerColor);
			cdlg.setVisible(true);
			if(cdlg.getCircle()!=null) {
				frame.getPnlDrawing().newShape(cdlg.getCircle());
			}
			return;
		}
		else if(frame.getTglbtnDonut().isSelected()){
			DonutDlg ddlg=new DonutDlg();
			ddlg.setPoint(click);
			ddlg.setColors(outerColor, innerColor);
			ddlg.setVisible(true);
			if(ddlg.getDonut()!=null) {
				frame.getPnlDrawing().newShape(ddlg.getDonut());
			}
			return;
		}
	}
}
