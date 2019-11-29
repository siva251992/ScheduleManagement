package org.com.schedulemanagement.mmScheduler;

import org.apache.commons.cli.*;

public class CommandLines {
	
	public static void main(String args[]) {
		
			Options options = new Options();

	        Option input = new Option("dbc", "dbclear", true, "Y/N for clearing the DB");
	        input.setRequired(true);
	        options.addOption(input);

	        Option output = new Option("mat", "materialID", true, "Part of a Material ID");
	        output.setRequired(true);
	        options.addOption(output);

	        CommandLineParser parser = new DefaultParser();
	        HelpFormatter formatter = new HelpFormatter();
	        CommandLine cmd;
	        String dbclear="";
	        String materialID="";
	        try {
	            cmd = parser.parse(options, args);
	            dbclear = cmd.getOptionValue("dbclear");
	            materialID = cmd.getOptionValue("materialID");
	        } catch (ParseException e) {
	            System.out.println(e.getMessage());
	            formatter.printHelp("mmScheduler", options);

	            System.exit(1);
	        }
	        System.out.println(dbclear);
	        System.out.println(materialID);

    	 
		
	}

}
