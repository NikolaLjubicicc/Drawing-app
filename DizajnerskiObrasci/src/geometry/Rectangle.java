package geometry;

import java.awt.Color;
import java.awt.Graphics;

public class Rectangle extends ColorShape {
	private Point upperLeft;
	private int width;
	private int height;
	
	public Rectangle()
	{
	}
	public Rectangle(Point upperLeft,int width,int height)
	{
		this.upperLeft=upperLeft;
		this.width=width;
		this.height=height;
	}
	public Rectangle(Point upperLeft,int width,int height,boolean selected)
	{
		this(upperLeft,width,height);
		this.selected=selected;
	}
	public Rectangle(Point upperLeft,int width,int height,boolean selected,Color color,Color innerColor)
	{
		this(upperLeft,width,height,selected);
		this.setColor(color);
		this.setInnerColor(innerColor);
	}
	public int area()
	{
		return this.width*this.height;
	}
	public int circumference()
	{
		return 2*(this.width+this.height);
	}
	public boolean contains(int x,int y)
	{
		return (upperLeft.getX() < x && upperLeft.getX()+width > x && upperLeft.getY() < y && upperLeft.getY() + height > y);
	}
	public boolean contains(Point p)
	{
		return this.contains(p.getX(),p.getY());
	}
	@Override
	public String toString()
	{
		return "Upper left point : "+upperLeft+" , width : "+width+" , height : "+height;
	}
	@Override
	public boolean equals(Object obj)
	{
		if(obj instanceof Rectangle)
		{
			Rectangle temp=(Rectangle) obj;
			if(upperLeft.equals(temp.upperLeft) && width==temp.width && height==temp.height)
			{
				return true;
			}
		}
		return false;
	}
	@Override
	public void moveTo(int x, int y) {
		upperLeft.moveTo(x, y);
		
	}
	@Override
	public void moveBy(int byX, int byY) {
		upperLeft.moveBy(byX, byY);
		
	}
	@Override
	public int compareTo(Object o) {
		if(o instanceof Rectangle)
		{
			return (int)(this.area()-((Rectangle) o).area());
		}
		return 0;
	}
	public Point getUpperLeft()
	{
		return upperLeft;
	}
	public void setUpperLeft(Point upperLeft)
	{
		this.upperLeft=upperLeft;
	}
	public int getWidth()
	{
		return width;
	}
	public void setWidth(int width)
	{
		this.width=width;
	}
	public int getHeight()
	{
		return height;
	}
	public void setHeight(int height)
	{
		this.height=height;
	}
	@Override
	public void Draw(Graphics g) {
		g.setColor(getColor());
		g.drawRect(upperLeft.getX(), upperLeft.getY(), width, height);
		this.fill(g);
		if(selected)
		{
			g.setColor(Color.BLUE);
			g.drawRect(upperLeft.getX()-2, upperLeft.getY()-2, 4, 4);
			g.drawRect(upperLeft.getX()+width-2, upperLeft.getY(), 4, 4);
			g.drawRect(upperLeft.getX()-2, upperLeft.getY()+height-2, 4, 4);
			g.drawRect(upperLeft.getX()+width-2, upperLeft.getY()+height-2, 4, 4);
			
		}
	}
	@Override
	public void fill(Graphics g) {
		g.setColor(getInnerColor());
		g.fillRect(this.upperLeft.getX() + 1, this.upperLeft.getY() + 1, this.width - 1, this.height - 1);
	}
	
	
	
}