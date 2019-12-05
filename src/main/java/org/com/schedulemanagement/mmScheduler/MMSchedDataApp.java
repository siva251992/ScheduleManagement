package org.com.schedulemanagement.mmScheduler;

import org.apache.commons.cli.*;
import org.apache.log4j.Logger;

public class MMSchedDataApp {
	
	final static Logger log = Logger.getLogger(MMSchedDataApp.class);

	public static void main(String args[]){

		CommandLineParameter c = new CommandLineParameter();

		Options options = new Options();

		Option dbClear = new Option("dbc", "dbclear", true, "yes/no for clearing the DB");
		dbClear.setRequired(true);
		options.addOption(dbClear);

		Option schedule = new Option("sch", "scheduleCount", true, "No of Schedules to be created");
		schedule.setRequired(true);
		options.addOption(schedule);

		Option item = new Option("item", "itemCount", true, "No of Items to be Created");
		item.setRequired(true);
		options.addOption(item);

		CommandLineParser parser = new DefaultParser();
		HelpFormatter formatter = new HelpFormatter();
		CommandLine cmd;
		String dbclear = "";
		String scheduleCount = "";
		String itemCount = "";
		try {
			cmd = parser.parse(options, args);
			dbclear = cmd.getOptionValue("dbclear");
			scheduleCount = cmd.getOptionValue("scheduleCount");
			itemCount = cmd.getOptionValue("itemCount");

		} catch (ParseException e) {
			log.info(e.getMessage());
			formatter.printHelp("mmScheduler", options);

			System.exit(1);
		}

		// Set the CommandLine Values to CommandLineParameters
		c.setDbclear(dbclear);
		c.setScheduleEnd(scheduleCount);
		c.setNoOfItems(itemCount);

		
		//create CoreData=========================================================

		CoreAPIData core = new CoreAPIData();
		try {
			core.createAssets(c);
		} catch (Exception e) {
			log.info(e.getMessage());
		}

		log.info("Core data creation completed.");

		
		
		// Create Schedule Data=======================================================
		ScheduleData sh = new ScheduleData();
		try {
			sh.createSchedule(c);
		} catch (Exception e) {
			log.info(e.getMessage());
		}
		log.info("Schedule data creation completed.");
	}

}
