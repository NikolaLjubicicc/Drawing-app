package adapter;

import java.awt.Color;
import java.awt.Graphics;

import geometry.Circle;
import geometry.ColorShape;
import geometry.Point;
import hexagon.Hexagon;

public class HexagonAdapter extends ColorShape{
	
	private Hexagon hexagon;
	
	public HexagonAdapter() {
		
	}
	
	public HexagonAdapter (Point center, int radius) {
		this.hexagon = new Hexagon(center.getX(), center.getY(), radius);
	}
	
	public HexagonAdapter(Point center, int radius, boolean selected,Color outerColor, Color innerColor) {
		this.hexagon = new Hexagon(center.getX(), center.getY(), radius);
		hexagon.setBorderColor(outerColor);
		hexagon.setAreaColor(innerColor);
		hexagon.setSelected(selected);
	}
	
	@Override
	public int compareTo(Object o) {
		if(o instanceof HexagonAdapter)
		{
			HexagonAdapter hexagonAdapter = (HexagonAdapter)o;
			return hexagon.getR() - hexagonAdapter.hexagon.getR();
		}
		return 0;

	}
	@Override
	public void moveTo(int x, int y) {
		hexagon.setX(x);
		hexagon.setY(y);
		
	}
	@Override
	public void moveBy(int byX, int byY) {
		hexagon.setX(hexagon.getX() + byX);
		hexagon.setY(hexagon.getY() + byY);
		
	}
	@Override
	public void fill(Graphics g) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public boolean contains(int x, int y) {
		return hexagon.doesContain(x, y);
	}
	@Override
	public void Draw(Graphics g) {
		hexagon.paint(g);
		
	}
	public String toString() {
		return "HexagonAdapter center: Point [X : " + hexagon.getX() + ", Y : " + hexagon.getY() + "], radius : " + 
				hexagon.getR() + ", outerColor: " + hexagon.getBorderColor() +
				", innerColor: " + hexagon.getAreaColor();
	}
	
	public int getX() {
		return hexagon.getX();
	}
	public int getY() {
		return hexagon.getY();
	}
	public void setX(int x) {
		hexagon.setX(x);
	}
	public void setY(int y) {
		hexagon.setY(y);
	}
	public int getR() {
		return hexagon.getR();
	}
	public void setR(int r) {
		hexagon.setR(r);
	}
	
	@Override
	public void setselected(boolean selected) {
		hexagon.setSelected(selected);
	}
	
	@Override
	public boolean isselected() {
		return hexagon.isSelected();
	}

	@Override
	public void setColor(Color color) {
		hexagon.setBorderColor(color);
	}
	
	@Override
	public Color getColor() {
		return hexagon.getBorderColor();
	}
	
	@Override
	public Color getInnerColor() {
		return hexagon.getAreaColor();
	}
	
	@Override
	public void setInnerColor(Color color) {
		hexagon.setAreaColor(color);
	}
	

	
}
