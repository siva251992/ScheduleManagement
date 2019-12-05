package org.com.schedulemanagement.mmScheduler;

import org.apache.commons.cli.*;

import com.ericsson.testing.model.LibCmppException;

public class CommandLines {
	
	public static void main(String args[]) throws InterruptedException {
		
   CommandLineParameter c=new CommandLineParameter();
		
			Options options = new Options();

	        Option dbClear = new Option("dbc", "dbclear", true, "Y/N for clearing the DB");
	        dbClear.setRequired(true);
	        options.addOption(dbClear);

	        Option materialId = new Option("mat", "materialID", true, "Part of a Material ID");
	        materialId.setRequired(true);
	        options.addOption(materialId);
	        
	        Option timReq= new Option("time","timeRequired",true,"TimeRequied for Items");
            timReq.setRequired(true);
            options.addOption(timReq);
            
            Option start=new Option("schStart","scheduleStart",true,"Start point of Schedule");
            start.setRequired(true);
            options.addOption(start);
            
            Option end=new Option("schEnd","scheduleEnd",true,"End point of Schedule");
            start.setRequired(true);
            options.addOption(end);
            
            Option item=new Option("item","itemCount",true,"No of Items to be Created");
            start.setRequired(true);
            options.addOption(item);
	        
	        

	        CommandLineParser parser = new DefaultParser();
	        HelpFormatter formatter = new HelpFormatter();
	        CommandLine cmd;
	        String dbclear="";
	        String materialID="";
	        String timeRequired="";
	        String scheduleStart="";
	        String scheduleEnd="";
	        String itemCount="";
	        try {
	            cmd = parser.parse(options, args);
	            dbclear = cmd.getOptionValue("dbclear");
	            materialID = cmd.getOptionValue("materialID");
	            timeRequired=cmd.getOptionValue("timeRequired");
	            scheduleStart=cmd.getOptionValue("scheduleStart");
	            scheduleEnd=cmd.getOptionValue("scheduleEnd");
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
	        c.setMaterialID(materialID);
	        c.setTimeRequired(timeRequired);
	        c.setScheduleStart(scheduleStart);
	        c.setScheduleEnd(scheduleEnd);
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
