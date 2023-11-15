package geometry;

import java.awt.Color;
import java.awt.Graphics;

public abstract class Shape implements Comparable, Moveable{
	protected boolean selected;
	protected Color color;
	public Shape()
	{
		
	}
	public Shape(Color color)
	{
		this.color=color;
	}
	public Shape(Color color,boolean selected)
	{
		this(color);
		this.selected=selected;
	}
	public abstract boolean contains(int x,int y);
	public abstract void Draw(Graphics g);
	
	
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	public boolean isselected()
	{
		return selected;
	}
	public void setselected(boolean selected)
	{
		this.selected=selected;
	}
}