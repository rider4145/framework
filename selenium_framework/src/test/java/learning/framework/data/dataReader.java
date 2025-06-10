package learning.framework.data;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class dataReader {

	// to read data from the json file
	public List<HashMap<String, String>> getJsonData(String path) throws IOException
	{
		// read json to string and we need to specify in what encoding format we are trying to write the string
		// standard encoding format (StandardCharsets.UTF_8) should be passed, so that the code will be depricated
		String json = FileUtils.readFileToString(new File(System.getProperty("user.dir") +"\\src\\test\\java\\learning\\framework\\data\\Purchaseorder.json"),
				StandardCharsets.UTF_8);			 
		
		
		// Another way of passing the file path instead of hasrdcoding the data
		String json1 = FileUtils.readFileToString(new File(path),StandardCharsets.UTF_8);			 
		
		
		//convert string to hashmap	-> get the new dependencies jackson datbind
		ObjectMapper mapper = new ObjectMapper();
		// reading the data from the hashmap object
		List<HashMap<String,String >>  data = mapper.readValue(json, new TypeReference<List<HashMap<String, String>>>(){
			
		});
		// data list will be having the values of 2 hashmap stored in the json file  {map,map1}
		return data;
	}
}
