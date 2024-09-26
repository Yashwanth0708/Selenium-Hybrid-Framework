package yashwanthgunapatiqa.globalcomponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtils {
	
	Properties prop;
	
    // Method to read properties from the configuration file
    public void readPropFile() throws IOException {
        prop = new Properties();
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "\\src\\main\\resources\\BaseFile.properties");
        prop.load(fis);
    }

	public List<HashMap<String,String>> getJsonData(String dataFileToUse) throws IOException {
		readPropFile();
		String relativePath = prop.getProperty(dataFileToUse);
		//Read Json file content and convert to String 
		String jsonContent = FileUtils.readFileToString(new File(System.getProperty("user.dir") + "\\" + relativePath),StandardCharsets.UTF_8);
		
		//Convert String into hashmap
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String,String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String,String>>>() {
		});
		
		return data;
	}

}
