package nz.co.troyshaw.mule.json;

import com.google.gson.Gson;

public class JsonUtil {

	public static ValuePojo stringToPojo(String input) {
		Gson gson = new Gson();
		
		return gson.fromJson(input, ValuePojo.class);
	}
	
	public static String valuePojoToJsonString(ValuePojo valuePojo) {
		Gson gson = new Gson();
		
		return gson.toJson(valuePojo);
	}
}
