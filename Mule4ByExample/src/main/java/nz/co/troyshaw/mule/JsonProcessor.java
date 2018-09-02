package nz.co.troyshaw.mule;

import nz.co.troyshaw.mule.json.JsonUtil;
import nz.co.troyshaw.mule.json.ValuePojo;

public class JsonProcessor {

	public String transform(String value) {
		ValuePojo valuePojo = JsonUtil.stringToPojo(value);
		
		validate(valuePojo);
		
		return JsonUtil.valuePojoToJsonString(valuePojo);
	}
	
	private void validate(ValuePojo pojo) {
		if (pojo.getValue().equals("bad")) throw new RuntimeException("Value cannot be bad.");
	}
}
