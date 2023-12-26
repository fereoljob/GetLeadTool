package tools;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import domain.VirtualLeadDTO;

public class QueryHandler {

	private List<Command> commands;
	private boolean proceed;
	
	public QueryHandler(List<Command> commands) {
		super();
		this.commands = commands;
		this.proceed = false;
	}

	
	public void start() {
		this.proceed = true;
		while(proceed) {
	 		System.out.println("Select a command:\n");
	 		System.out.println("Press 0 to quit");
	 		int choice = -1;
		 	for(int i =0; i<commands.size();i++) {
		 		System.out.println(i+1 + ": "+commands.get(i).getInfos());
		 	}
		 		CustomScanner scanner = new CustomScanner();
		 		
		 		choice = (int) scanner.scanType(ScanType.INT);
		 		if(choice == 0) {
			 		this.proceed = false;
			 	}
		 		else if(choice<0 || choice >commands.size()) {
		 			System.out.println("Commands not in the list. Please select a valid command.");
		 		}
		 		else {
		 			Command currentCommand = commands.get(choice-1);
		 			System.out.println("Please setUp the commands parameters :");
		 			for(Map.Entry<String, CommandParameter> entry : currentCommand.getParameters().entrySet()) {
		 				System.out.println(entry.getKey());
		 			entry.getValue().setValue(scanner.scanType(entry.getValue().getType()));	
		 			}
		 			restQuery(currentCommand);
		 		}
		 	}
		 	
		 	
		 	
		 	
		 	
		 	
		}
		
	
	
	private void restQuery(Command currentCommand) {
		
		try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
			HttpPost httpPost = new HttpPost("http://localhost:8080/myTest/commandGate");
 			httpPost.setHeader("Content-Type", "application/json");

 			ObjectMapper objectMapper = new ObjectMapper();
 	        String json = objectMapper.writeValueAsString(currentCommand.toMap());
 			
 			
	            
 	        StringEntity requestEntity = new StringEntity(json);
 	        httpPost.setEntity(requestEntity);
 	       try (CloseableHttpResponse response = httpClient.execute(httpPost)) {
 	    	    HttpEntity responseEntity = response.getEntity();
 	    	    
 	    	    if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {

 	    	         objectMapper = new ObjectMapper();
 	    	        List<VirtualLeadDTO> leadDTOs = objectMapper.readValue(responseEntity.getContent(), new TypeReference<List<VirtualLeadDTO>>() {});
 	    	        if(leadDTOs == null) {
 	    	        	System.out.println("No Result found");
 	    	        }
 	    	        else {
 	    	        	for (VirtualLeadDTO dto : leadDTOs) {
 	 	    	            System.out.println(dto.toString());
 	 	    	        }
 	    	        }
 	    	        
 	    	    } else {

 	    	        System.out.println("Response from the server: " + response.getStatusLine().getReasonPhrase());
 	    	    }
 	    	} catch (Exception e) {
 	    	    e.printStackTrace();
 	    	}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		 
		 
		 
	}
	
	
	
	
	
}
