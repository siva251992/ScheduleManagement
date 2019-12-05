package org.com.schedulemanagement.mmScheduler;

import org.apache.commons.cli.*;

import com.ericsson.testing.model.LibCmppException;

public class CommandLines {
	
	public static void main(String args[]) throws InterruptedException, java.text.ParseException {
		
   CommandLineParameter c=new CommandLineParameter();
		
			Options options = new Options();

	        Option dbClear = new Option("dbc", "dbclear", true, "Y/N for clearing the DB");
	        dbClear.setRequired(true);
	        options.addOption(dbClear);

	                    
            Option schedule=new Option("sch","scheduleCount",true,"No of Schedules to be created");
            schedule.setRequired(true);
            options.addOption(schedule);
            
            Option item=new Option("item","itemCount",true,"No of Items to be Created");
            item.setRequired(true);
            options.addOption(item);
	        
	        

	        CommandLineParser parser = new DefaultParser();
	        HelpFormatter formatter = new HelpFormatter();
	        CommandLine cmd;
	        String dbclear="";
	    	String scheduleCount="";
	        String itemCount="";
	        try {
	            cmd = parser.parse(options, args);
	            dbclear = cmd.getOptionValue("dbclear");
	            scheduleCount=cmd.getOptionValue("scheduleCount");
	            itemCount=cmd.getOptionValue("itemCount");
	            
	        } catch (ParseException e) {
	            System.out.println(e.getMessage());
	            formatter.printHelp("mmScheduler", options);

	            System.exit(1);
	        }
	        //System.out.println(dbclear);
	       // System.out.println(materialID);
	       
	        //Set the CommandLine Values to CommandLineParameters
	        c.setDbclear(dbclear);
	        c.setScheduleEnd(scheduleCount);
	        c.setNoOfItems(itemCount);
	        
	        
	      //Method to create Core Data

    	 CoreAPIData core= new CoreAPIData();
    	 try {
			core.createAssets(c);
		} catch (LibCmppException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
    	 
    	 System.out.println("Core Data is created Successfully");
    	 Thread.sleep(500);
    	 
    	 //Method to Create ScheduleDate
    	 ScheduleData sh=new ScheduleData();
    	 
    	 try {
			sh.createSchedule(c);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
    	 System.out.println("Schedule Data is created Successfully");
	}

}
