package geometry;

import java.awt.Color;
import java.awt.Graphics;

public abstract class ColorShape extends Shape{
	private Color innerColor;

	public abstract void fill(Graphics g);

	public Color getInnerColor() {
		return innerColor;
	}

	public void setInnerColor(Color innerColor) {
		this.innerColor = innerColor;
	}
}
