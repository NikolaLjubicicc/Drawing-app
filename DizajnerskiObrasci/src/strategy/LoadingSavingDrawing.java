package strategy;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import geometry.Shape;

public class LoadingSavingDrawing implements LoadingSavingDrawingStrategy{

	@Override
	public ArrayList<Shape> load(String filePath) {
		try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(filePath))) {
			return (ArrayList<Shape>) inputStream.readObject();
	    } catch (IOException | ClassNotFoundException e) {
	        e.printStackTrace();
	        return null;
	    }
	}

	@Override
	public void save(ArrayList<Shape> shapes, String filePath) {
		try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(filePath))) {
            outputStream.writeObject(shapes);
        } catch (IOException e) {
            e.printStackTrace();
        }
		
	}
	
}
