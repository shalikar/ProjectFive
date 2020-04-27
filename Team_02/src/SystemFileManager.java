import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Kunal Sharma
 * @created 02-18-2020
 * @version 1.0
 * @author abhinaw sarang
 * @modified 03-21-2020
 * @version 2.0
 */
public class SystemFileManager {

	public void restoreShape(String pathName) {
		try {
			Gson gson = new Gson();
			FileReader file = new FileReader(pathName);
	        BufferedReader bufferreader = new BufferedReader(file);
	        RightPanelDataProcessor rpDataProcessor = NewTab.mapRightPanels.get(tabids).panelMouseListener.dataProcessor;
            String line;
            int lineNumber = 1;
            while ((line = bufferreader.readLine()) != null) {
                if (lineNumber == 1) {
                	List<Dot> temp = new ArrayList<Dot>();
                    temp = gson.fromJson(line, new TypeToken<List<Dot>>(){}.getType());
                    dataObject.setDotList(temp);
                }
                if (lineNumber == 2) {
                	List<Line> temp = new ArrayList<Line>();
                    temp = gson.fromJson(line, new TypeToken<List<Line>>(){}.getType());
                    dataObject.setLineList(temp);
                }
                if (lineNumber == 3) {
                	List<Dot> temp = new ArrayList<Dot>();
                    temp = gson.fromJson(line, new TypeToken<List<Dot>>(){}.getType());
                    dataObject.setBarCenterList(temp);
                }
                if (lineNumber == 4) {
                	JsonObject convertedObject = gson.fromJson(line, JsonObject.class);
                	for (String each : convertedObject.keySet()) {
                		ClickedShape.shapeName = each;
                		JsonObject allIcons = convertedObject.get(each).getAsJsonObject();
                		for (String x : allIcons.keySet()) {
                			dataObject.addNewIcon(Integer.valueOf(x),allIcons.get(x).getAsInt());
                		}
                	}

                }
                lineNumber++;
            }
            dataObject.customNotify();
		} catch (Exception ex) {
			System.out.println("Trouble loading file" + ex);
		}
	}

	public void saveShape(String pathName) {
		String splitPattern = "\\.";
		String fileLoc = pathName.split(splitPattern)[0];
		
		
		for (Integer tabids : NewTab.mapRightPanels.keySet()) {
			
			String finalLoc = fileLoc + String.valueOf(tabids) + ".dat";
			
			try {
				RightPanelDataProcessor rpDataProcessor = NewTab.mapRightPanels.get(tabids).panelMouseListener.dataProcessor;
				Gson gson = new Gson();
				JsonObject object = new JsonObject();
				for (String key: rpDataProcessor.getIconMap().keySet()) {
					JsonObject temp = new JsonObject();
					for (Icon icon: rpDataProcessor.getIconMap().get(key)) {
						temp.addProperty(Integer.toString(icon.getCenterX()),icon.getCenterY());
					}
					object.add(key, temp);
				}
				
				String jsonDotList = gson.toJson(rpDataProcessor.getDotList());
				String jsonLineList = gson.toJson(rpDataProcessor.getLineList());
				String jsonBarList = gson.toJson(rpDataProcessor.getBarCenterList());
				String jsonIconList = gson.toJson(object);
				FileWriter file = new FileWriter(finalLoc, true);
	            file.write(jsonDotList);
	            file.write("\n");
	            file.write(jsonLineList);
	            file.write("\n");
	            file.write(jsonBarList);
	            file.write("\n");
	            file.write(jsonIconList);
	            file.flush();
	            file.close();

			} catch (Exception ex) {
				System.out.println("Trouble saving file" +  ex.getMessage());
			}
		}
	}

}
