package tools;

import java.util.HashMap;
import java.util.Map;

public class Command {
		private String name;
		private String infos;
		private Map<String, CommandParameter> parameters;

	    public Command(String name,String infos) {
	        this.name = name;
	        this.infos = name +" : " +  infos;
	        this.parameters = new HashMap<>();
	    }

	    public void addParameter(String paramName, CommandParameter paramType) {
	        parameters.put(paramName, paramType);
	    }

	    public Map<String, CommandParameter> getParameters(){
	    	return this.parameters;
	    }
	    
	    public String getName() {
	    	return this.name;
	    }
	    
	    public String getInfos() {
	    	return this.infos;
	    }
	    
	    @Override
	    public String toString() {
	        StringBuilder stringBuilder = new StringBuilder();
	        stringBuilder.append("Command : ").append(name).append("\n");
	        stringBuilder.append("Parameters:\n");
	        for (Map.Entry<String, CommandParameter> entry : parameters.entrySet()) {
	            stringBuilder.append("\t").append(entry.getKey()).append(": ").append(entry.getValue().getValue()).append("\n");
	        }
	        return stringBuilder.toString();
	    }
	    
	    public Map<String, Object> toMap() {
		    Map<String, Object> commandMap = new HashMap<>();
		    commandMap.put("name", this.name);
		    for (Map.Entry<String, CommandParameter> entry : this.parameters.entrySet()) {
		        commandMap.put(entry.getKey(), entry.getValue().getValue());
		    }
		    return commandMap;
		}
		
	
}
