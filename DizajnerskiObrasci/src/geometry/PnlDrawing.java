package geometry;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JPanel;


public class PnlDrawing extends JPanel{
	 ArrayList<Shape> shapes=new ArrayList<Shape>();
	 
	
	 private int i;
	 
	 public void paint(Graphics g) {
		 super.paint(g);
		 for(Shape s: this.shapes) {
			 s.Draw(g);
		 }
	 }
	 public void newShape(Shape s){
		 shapes.add(s);
		 repaint();
	 }
	 public Shape getShape(int i) {
			return shapes.get(i);
		}
	 public void setShape(int i, Shape s) {
			shapes.set(i, s);
		}
	 public void remove() {
			shapes.removeIf(shape -> shape.isselected());
			repaint();
		}
		
		public void deselect() {
			shapes.forEach(shape -> shape.setselected(false));
			repaint();
		}
		public void select(Point p) {
			for (i = shapes.size()-1; i >= 0; i--) {
				if (shapes.get(i).contains(p.getX(), p.getY())) {
					shapes.get(i).setselected(true);
					repaint();
					return;
				}
			}
		}
		public int getSelected() {
			for (i = shapes.size()-1; i >= 0; i--) {
				if (shapes.get(i).isselected()) {
					return i;
				}
			}
			return -1;
		}
		
		public boolean isEmpty() {
			return shapes.isEmpty();
		}
		
		public ArrayList<Shape> getShapes() {
			return shapes;
		}
}

