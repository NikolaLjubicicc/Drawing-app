package strategy;

import java.util.ArrayList;

import geometry.Shape;

public interface LoadingSavingDrawingStrategy {
	
		public ArrayList<Shape> load(String filePath);
		public void save(ArrayList<Shape> shapes, String filePath);

}
