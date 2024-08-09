package geometry;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;


public class Donut extends Circle{
	private int innerRadius;
	
	public Donut()
	{
		
	}
	public Donut(Point center,int radius,int innerRadius)
	{
		super(center,radius);
		this.innerRadius=innerRadius;
	}
	public Donut(Point center,int radius,int innerRadius,boolean selected)
	{
		this(center,radius,innerRadius);
		this.selected=selected;
	}
	public Donut(Point center,int radius,int innerRadius,boolean selected,Color color,Color innerColor)
	{
		this(center,radius,innerRadius,selected);
		this.setColor(color);
		this.setInnerColor(innerColor);
	}
	@Override
	public boolean contains(int x,int y)
	{
		return super.contains(x,y) && center.distance(x, y)>=innerRadius;
	}
	@Override
	public boolean contains(Point p)
	{
		return this.contains(p.getX(),p.getY());
	}
	@Override
	public void Draw(Graphics g) {
		g.setColor(Color.BLACK);
		super.Draw(g);
		g.drawOval(center.getX()-innerRadius, center.getY()-innerRadius, 2*innerRadius, 2*innerRadius);
		this.fill(g);
		if(selected)
		{
			g.setColor(this.getColor());
			g.drawRect(center.getX()-2, center.getY()-2, 4, 4);
			g.drawRect(center.getX()-radius-2, center.getY()-2, 4, 4);
			g.drawRect(center.getX()+radius-2, center.getY()-2, 4, 4);
			g.drawRect(center.getX()-2, center.getY()-radius-2, 4, 4);
			g.drawRect(center.getX()-2, center.getY()+radius-2, 4, 4);
			g.drawRect(center.getX()-2, center.getY()-2, 4, 4); 
			g.drawRect(center.getX()-innerRadius-2, center.getY()-2, 4, 4);
			g.drawRect(center.getX()+innerRadius-2, center.getY()-2, 4, 4);
			g.drawRect(center.getX()-2, center.getY()-innerRadius-2, 4, 4);
			g.drawRect(center.getX()-2, center.getY()+innerRadius-2, 4, 4);
		}
	}
	public void fill(Graphics g) {
		Graphics2D gg = (Graphics2D) g.create();
		Ellipse2D ellipse = new Ellipse2D.Double(super.center.getX()-super.radius+1,super.center.getY()-super.radius+1,super.radius*2-2,super.radius*2-2);
		Ellipse2D ellipse2 = new Ellipse2D.Double(center.getX()-innerRadius, center.getY()-innerRadius, 2*innerRadius, 2*innerRadius);
		
		Area outerArea = new Area(ellipse);
        Area innerArea = new Area(ellipse2);
        outerArea.subtract(innerArea);
        
		gg.setColor(getInnerColor());
		gg.fill(outerArea);
		gg.dispose();
	}
	@Override
	public double area()
	{
		return super.area() - Math.pow(innerRadius,2)*Math.PI;
	}
	@Override
	public String toString()
	{
		return "Donut center : "+"Point [ X : "+center.getX()+" , Y : "+center.getY()+ "] , outerRadius : "+super.radius +  ", inner radius: "+innerRadius+
				" outerColor: "+getColor()+" , innerColor: "+getInnerColor();
	}
	@Override
	public boolean equals(Object obj)
	{
		if(obj instanceof Donut)
		{
			Donut temp=(Donut) obj;
			if(super.equals(new Circle(temp.center,temp.radius)) && innerRadius==temp.innerRadius);
			{
				return true;
			}
		}
		return false;
	}
	public int getInnerRadius()
	{
		return innerRadius;
	}
	public void setInnerRadius(int innerRadius)
	{
		this.innerRadius=innerRadius;
	}
}



