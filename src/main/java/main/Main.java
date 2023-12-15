package main;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import tools.Command;
import tools.CommandParameter;
import tools.CustomScanner;
import tools.ScanType;
import tools.VirtualLeadDTO;


public class Main {

	public static void main(String[] args) throws Exception {
		 try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
			 
			 //Commands creation
			 	Command findLeads = new Command("findLeads");
			 	findLeads.addParameter("lowAnnualRevenue", new CommandParameter(ScanType.DOUBLE));
			 	findLeads.addParameter("highAnnualRevenue", new CommandParameter(ScanType.DOUBLE));
			 	findLeads.addParameter("state", new CommandParameter(ScanType.STRING));
			 	
			 	List<Command> commands = new ArrayList<Command>();
			 	
			 	commands.add(findLeads);
			 	
			 	//User Interaction
			 	boolean proceed = true;
			 	while(proceed) {
			 		System.out.println("Select a command:\n");
			 		System.out.println("Press 0 to quit");
			 		int choice = -1;
				 	for(int i =0; i<commands.size();i++) {
				 		System.out.println(i+1 + ": "+commands.get(i).getName());
				 		CustomScanner scanner = new CustomScanner();
				 		
				 		choice = (int) scanner.scanType(ScanType.INT);
				 		if(choice == 0) {
					 		proceed = false;
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
				 			System.out.println(currentCommand.toString());
				 			
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


				 	    	        for (VirtualLeadDTO dto : leadDTOs) {
				 	    	            System.out.println(dto.toString());
				 	    	        }
				 	    	    } else {

				 	    	        System.out.println("Response from the server: " + response.getStatusLine().getReasonPhrase());
				 	    	    }
				 	    	} catch (Exception e) {
				 	    	    e.printStackTrace();
				 	    	}
				 		}
				 	}
				 	

			 		
			 		}
			 	}
		 } 

	

}
