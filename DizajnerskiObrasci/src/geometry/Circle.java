package geometry;
import java.awt.Color;
import java.awt.Graphics;

public class Circle extends ColorShape {
	protected Point center;
	protected int radius;
	
	public Circle()
	{
	}
	public Circle(Point center,int radius)
	{
		this.center=center;
		this.radius=radius;
	}
	public Circle(Point center,int radius,boolean selected)
	{
		this(center,radius);
		this.selected=selected;
	}
	public Circle(Point center,int radius,boolean selected,Color color,Color innerColor)
	{
		this(center,radius,selected);
		this.setColor(color);
		this.setInnerColor(innerColor);
	}
	public double area()
	{
		return Math.pow(this.radius,2)*Math.PI;
	}
	public double circumference()
	{
		return 2*this.radius*Math.PI;
	}
	public boolean contains(int x,int y)
	{
		return center.distance(x, y) <=radius;
	}
	public boolean contains(Point p)
	{
		return this.contains(p.getX(),p.getY());
	}
	@Override
	public String toString()
	{
		return "Circle center : "+"Point [ X : "+center.getX()+" , Y : "+center.getY()+ "] , radius : "+radius + " outerColor: "+getColor()+" , innerColor: "+getInnerColor();
	}
	@Override
	public boolean equals(Object obj)
	{
		if(obj instanceof Circle)
		{
			Circle temp=(Circle) obj;
			if(center.equals(temp.center) && radius==temp.radius)
			{
				return true;
			}
		}
		return false;
	}

	public void moveTo(int x, int y) {
		center.moveTo(x, y);
		
	}
	
	public void moveBy(int byX, int byY) {
		center.moveBy(byX, byY);
		
	}
	@Override 
	public int compareTo(Object o) {
		if(o instanceof Circle)
		{
			return (int)(this.area()-((Circle) o).area());
		}
		return 0;
	}
	@Override
	public void fill(Graphics g) {
		g.setColor(getInnerColor());
		g.fillOval(this.center.getX() - this.radius + 1, this.center.getY() - this.radius + 1,
				this.radius*2 - 2, this.radius*2 - 2);
		
	}
	public Point getCenter()
	{
		return center;
	}
	public void setCenter(Point center)
	{
		this.center=center;
	}
	public int getRadius()
	{
		return radius;
	}
	public void setRadius (int radius) throws NumberFormatException
	{
		if(radius<0)
		{
			throw new NumberFormatException("Radius cannot be less than 0");
		}
		this.radius=radius;
	}
	@Override
	public void Draw(Graphics g) {
		g.setColor(this.getColor());
		g.drawOval(center.getX()-radius, center.getY()-radius, 2*radius, 2*radius);
		this.fill(g);
		if(selected)
		{
			g.setColor(Color.BLUE);
			g.drawRect(center.getX()-2, center.getY()-2, 4, 4);
			g.drawRect(center.getX()-radius-2, center.getY()-2, 4, 4);
			g.drawRect(center.getX()+radius-2, center.getY()-2, 4, 4);
			g.drawRect(center.getX()-2, center.getY()-radius-2, 4, 4);
			g.drawRect(center.getX()-2, center.getY()+radius-2, 4, 4);
		}
	}
	
	public Circle clone(Circle circle) {
		this.getCenter().setX(circle.getCenter().getX());
		this.getCenter().setY(circle.getCenter().getY());
		this.setRadius(circle.getRadius());
		this.setColor(circle.getColor());
		this.setInnerColor(circle.getInnerColor());
		return this;
	}
	
}
