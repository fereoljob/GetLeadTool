package main;
import java.util.ArrayList;
import java.util.List;

import tools.Command;
import tools.CommandParameter;
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
