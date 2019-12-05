package org.com.schedulemanagement.mmScheduler;

import java.io.File;
import java.time.LocalDateTime;

import org.apache.log4j.Logger;
import com.ericsson.testing.model.LibCmppException;

import static io.restassured.RestAssured.given;

import com.ericsson.testing.CmppClient;
import com.ericsson.testing.CmppClientFactory;
import com.ericsson.testing.CreateCMPPData;
import com.ericsson.testing.HALResource;

public class CoreAPIData {

	final static Logger log = Logger.getLogger(CoreAPIData.class);
	String canonicalPath;
	
	public void createAssets(CommandLineParameter param) throws LibCmppException, InterruptedException {

		// Golf Core DB Clear
		if (param.getDbclear().equalsIgnoreCase("yes")) {
			given().baseUri("http://ec2-52-50-126-1.eu-west-1.compute.amazonaws.com").get("/cleardb?q=coreGolf").then()
					.statusCode(200);
			log.info("Golf core DB cleared successfully");
		} else {
			log.info("Golf core DB not cleared.");
		}

		// Get canonical path for locating the certificate
		try {
			canonicalPath = new File(".").getCanonicalPath();
		} catch (Exception e) {
			log.info("Could not get canonical path.");
		}

		// Initialise the client
		CmppClient cmppClient = (CmppClient) CmppClientFactory
				.newClient(canonicalPath + "/worker.pfx", "nine", "pkcs12");

		// Command line params
		int noOfScheds = Integer.parseInt(param.getScheduleEnd());
		int noOfItems = Integer.parseInt(param.getNoOfItems());
		int i, totalRecords;

		totalRecords = noOfScheds * noOfItems;
		HALResource cmpp = cmppClient.init("https://golfapi.malo.dev.aws.redbeemedia.com/v1");
		
		CreateCMPPData cmppdata;
		HALResource asset, assetfile, filelocation;

		// Create Data
		for (i = 1; i <= totalRecords; i++) {

			cmppdata = new CreateCMPPData();

			if (i % 2 == 0) {
				// create asset with Ready for Tx status
				asset = cmppdata.createAsset(cmpp, "SPECIAL-UUID-TEST-ASSETGROUP-A", "TestAsset" + i, "MAT-" + i,
						"BONNIER", 123456789, "Ready for Tx", "program");
				log.info("Created Asset: " + asset.uri());
			} else {
				// create asset with Not Ready for Txstatus
				asset = cmppdata.createAsset(cmpp, "SPECIAL-UUID-TEST-ASSETGROUP-A", "TestAsset" + i, "MAT-" + i,
						"BONNIER", 123456789, "Not Ready for Tx", "program");
				log.info("Created Asset: " + asset.uri());
			}

			// create assetfile for above asset
			assetfile = cmppdata.createAssetFile(asset, "Assetfile-Test", "Assetfile-Test", "Assetfile-Test-checksum",
					"SPECIAL-UUID-TEST-FILETYPE MXF", 123456789, 123456789, "mxf_.123", "10:00:00:00", "Passed");
			log.info("Created Assetfile: " + assetfile.uri());

			// create filelocation for above assetfile
			filelocation = cmppdata.createAssetFileLocation(assetfile, "Assetfilelocation-Test-checksum", 123456789,
					"morph://PU8_NU_STG_AD1/", LocalDateTime.now());
			log.info("Created AssetfileLocation: " + filelocation.uri());
		}
		
		cmppClient = null;
	}

}
