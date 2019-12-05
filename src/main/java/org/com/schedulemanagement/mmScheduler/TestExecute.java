package org.com.schedulemanagement.mmScheduler;

import org.apache.commons.cli.*;

import com.ericsson.testing.model.LibCmppException;

public class TestExecute {
	
	public static void main(String args[]) throws InterruptedException {
		
   CommandLineParameter c=new CommandLineParameter();
		
			Options options = new Options();

	        Option input = new Option("dbc", "dbclear", true, "Yes/NO for clearing the DB");
	        input.setRequired(true);
	        options.addOption(input);

	        Option output = new Option("mat", "materialID", true, "Part of a Material ID");
	        output.setRequired(true);
	        options.addOption(output);
	        
	        Option Asset1 = new Option("asst1", "asset1", true, "No of Assets1");
	        output.setRequired(true);
	        options.addOption(Asset1);
	        
	        Option Asset2 = new Option("asst2", "asset2", true, "No of Assets2");
	        output.setRequired(true);
	        options.addOption(Asset2);
	        
	        Option Asset1Tx = new Option("asst1Tx", "asset1Tx", true, "Yes/No-Tx Status for Asset1");
	        output.setRequired(true);
	        options.addOption(Asset1Tx);
	        
	        Option Asset2Tx = new Option("asst2Tx", "asset2Tx", true, "Yes/No-Tx Status for Asset2");
	        output.setRequired(true);
	        options.addOption(Asset2Tx);
	        
	        Option Asset1Profile = new Option("asst1pf", "asset1Profile", true, "Profile for Asset1-AD/Video/Subtitles");
	        output.setRequired(true);
	        options.addOption(Asset1Profile);
	        
	        Option Asset2Profile = new Option("asst2pf", "asset2Profile", true, "Profile for Asset2-AD/Video/Subtitles");
	        output.setRequired(true);
	        options.addOption(Asset2Profile);
	        
	        
	        
	        Option medType= new Option("med","mediaType",true,"MediaType for Items-AD/Video/Subtitles");
            medType.setRequired(true);
            options.addOption(medType);
            
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
	        String asset1="";
	        String asset2="";
	        String asset1Tx="";
	        String asset2Tx="";
	        String asset1Profile="";
	        String asset2Profile="";
	        String mediaType="";
	        String timeRequired="";
	        String scheduleStart="";
	        String scheduleEnd="";
	        String itemCount="";
	        try {
	            cmd = parser.parse(options, args);
	            dbclear = cmd.getOptionValue("dbclear");
	            materialID = cmd.getOptionValue("materialID");
	            asset1= cmd.getOptionValue("asset1");
	            asset2= cmd.getOptionValue("asset2");
	            asset1Tx=cmd.getOptionValue("asset1Tx");
	            asset2Tx=cmd.getOptionValue("asset2Tx");
	            asset1Profile=cmd.getOptionValue("asset1Profile");
	            asset2Profile=cmd.getOptionValue("asset2Profile");
	            mediaType=cmd.getOptionValue("mediaType");
	            timeRequired=cmd.getOptionValue("timeRequired");
	            scheduleStart=cmd.getOptionValue("scheduleStart");
	            scheduleEnd=cmd.getOptionValue("scheduleEnd");
	            itemCount=cmd.getOptionValue("itemCount");
	        } catch (ParseException e) {
	            System.out.println(e.getMessage());
	            formatter.printHelp("mmScheduler", options);

	            System.exit(1);
	        }
	       // System.out.println(dbclear);
	      //  System.out.println(materialID);
	      //  System.out.println(asset1);
	        
	        c.setDbclear(dbclear);
	        c.setAsset1(asset1);
	        c.setAsset2(asset2);
	        c.setAsset1Tx(asset1Tx);
	        c.setAsset2Tx(asset2Tx);
	        c.setAsset1Profile(asset1Profile);
	        c.setAsset2Profile(asset2Profile);
	        c.setMaterialID(materialID);
	        c.setMediaType(mediaType);
	        c.setTimeRequired(timeRequired);
	        c.setScheduleStart(scheduleStart);
	        c.setScheduleEnd(scheduleEnd);
	        c.setNoOfItems(itemCount);

    	 CoreAPIData core= new CoreAPIData();
    	 PostSchedule p=new PostSchedule();
    	 
    	 //Method to create Core Data
    	 try {
			core.createAssets(c);
		} catch (LibCmppException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
    	 System.out.println("Core Data is created Successfully");
    	 Thread.sleep(500);
    	 //Method to Create ScheduleDate
    	 try {
			try {
				p.postRequest(c);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (java.text.ParseException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		
	}

}
