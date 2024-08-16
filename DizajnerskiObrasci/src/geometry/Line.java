package geometry;

import java.awt.Color;
import java.awt.Graphics;

public class Line extends Shape{

	private Point startPoint=new Point();
	private Point endPoint=new Point();
	
	public Line()
	{
	}
	public Line(Point startPoint,Point endPoint)
	{
		this.startPoint=startPoint;
		this.endPoint=endPoint;
	}
	public Line(Point startPoint,Point endPoint,boolean selected)
	{
		this(startPoint,endPoint); 
		this.selected=selected;
	}
	public Line(Point startPoint,Point endPoint,boolean selected,Color color)
	{
		this(startPoint,endPoint,selected);
		this.setColor(color);;
	}
	public double length()
	{
		return startPoint.distance(endPoint.getX(), endPoint.getY());
	}
	public boolean contains(int x,int y)
	{
		return (startPoint.distance(x, y)+endPoint.distance(x, y))-length()<=2;
	}
	@Override
	public String toString()
	{
		return "Line StartPoint : " + "Point [ X : "+startPoint.getX()+" , Y : "+startPoint.getY()
				+ "] , EndPoint : "+"Point [ X : "+endPoint.getX()+" , Y : "+endPoint.getY() +"] , color: "+getColor();
	}
	@Override
	public boolean equals(Object obj)
	{
		if(obj instanceof Line)
		{
			Line temp=(Line) obj;
			return startPoint.equals(temp.startPoint) && endPoint.equals(temp.endPoint);
		}
		return false;
	}
	@Override
	public void moveTo(int x, int y) {

		
	}
	@Override
	public void moveBy(int byX, int byY) {
		startPoint.moveBy(byX, byY);
		endPoint.moveBy(byX, byY);
		
	}
	@Override
	public int compareTo(Object o) {
		if(o instanceof Line)
		{
			return (int)(this.length()-((Line) o).length());
		}
		return 0;
	}
	public Point getStartPoint()
	{
		return startPoint;
	}
	public void setStartPoint(Point startPoint)
	{
		this.startPoint=startPoint;
	}
	public Point getEndPoint()
	{
		return endPoint;
	}
	public void setEndPoint(Point endPoint)
	{
		this.endPoint=endPoint;
	}
	@Override
	public void Draw(Graphics g) {
		g.setColor(this.getColor());
		g.drawLine(startPoint.getX(), startPoint.getY(), endPoint.getX(), endPoint.getY());
		if(selected)
		{
			g.drawRect(getStartPoint().getX()-2, getStartPoint().getY()-2, 4, 4);
			g.drawRect(getEndPoint().getX()-2, getEndPoint().getY()-2, 4, 4);
		}
		
	}
	public Line clone(Line line) {
		this.getStartPoint().setX(line.getStartPoint().getX());
		this.getStartPoint().setY(line.getStartPoint().getY());
		this.getEndPoint().setX(line.getEndPoint().getX());
		this.getEndPoint().setY(line.getEndPoint().getY());
		this.setColor(line.getColor());
		return this;
	}

	
}