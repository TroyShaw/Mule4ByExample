package nz.co.troyshaw.mule;

import nz.co.troyshaw.mule.json.ValuePojo;

public class JsonProcessor {

	public String transform(String value) {
		return "";
	}
	
	public void validate(ValuePojo pojo) {
		if (pojo == null) throw new NullPointerException();
		
		if (pojo.getValue().equals("bad")) throw new RuntimeException("Value cannot be bad.");
	}
}
