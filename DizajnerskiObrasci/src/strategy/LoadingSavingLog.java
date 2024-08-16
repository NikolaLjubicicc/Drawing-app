package strategy;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JOptionPane;

public class LoadingSavingLog implements LoadingSavingLogStrategy {

	@Override
	public String load(String filePath) {
		try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
			String str = "";
			String line;
            while ((line = reader.readLine()) != null) {
            	if (JOptionPane.showConfirmDialog(null, "Do you want to execute this command?", "Yes"
            			, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == 0) {
                
            	}
            	str = str + line+"\n";
            }
            return str;
			
		} catch (IOException e){
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void save(String save, String filePath) {
		try (FileWriter writer = new FileWriter(filePath)) {
            writer.write(save);
        } catch (IOException e) {
        	e.printStackTrace();
        }

	}

}
