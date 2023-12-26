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

import domain.VirtualLeadDTO;
import tools.Command;
import tools.CommandParameter;
import tools.CustomScanner;
import tools.QueryHandler;
import tools.ScanType;


public class Main {

	public static void main(String[] args) throws Exception {

			 
			 //Commands creation
			 	Command findLeads = new Command("findLeads","");
			 	findLeads.addParameter("lowAnnualRevenue", new CommandParameter(ScanType.DOUBLE));
			 	findLeads.addParameter("highAnnualRevenue", new CommandParameter(ScanType.DOUBLE));
			 	findLeads.addParameter("state", new CommandParameter(ScanType.STRING));
			 	
			 	Command findLeadsByDate = new Command("findLeadsByDate", "(yyyy-MM-dd)");

			 	findLeadsByDate.addParameter("startDate", new CommandParameter(ScanType.STRING));
			 	findLeadsByDate.addParameter("endDate", new CommandParameter(ScanType.STRING));
			 	
			 	List<Command> commands = new ArrayList<Command>();
			 	
			 	commands.add(findLeads);
			 	commands.add(findLeadsByDate);
			 	
			 	QueryHandler qHandler= new QueryHandler(commands);
			 	qHandler.start();
				 	

			 		
		 } 

	

}
