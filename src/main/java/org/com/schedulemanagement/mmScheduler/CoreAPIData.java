package org.com.schedulemanagement.mmScheduler;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

import org.apache.log4j.Logger;
import com.ericsson.testing.model.LibCmppException;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


import com.ericsson.testing.CmppClient;
import com.ericsson.testing.CmppClientFactory;
import com.ericsson.testing.CreateCMPPData;
import com.ericsson.testing.HALResource;

public class CoreAPIData {
	
     	static String canonicalPath;
		
		final static Logger log = Logger.getLogger(CoreAPIData.class);
		
		public  void createAssets(CommandLineParameter c) throws LibCmppException, InterruptedException {
			//Core DB Clear
			
			if(c.getDbclear().equalsIgnoreCase("yes")) {
			RequestSpecification request = RestAssured.with();
			Response response = request.baseUri("http://ec2-52-50-126-1.eu-west-1.compute.amazonaws.com").get("/cleardb?q=coreGolf");
			System.out.println(response.asString());
			System.out.println("Core DB Cleared successfully");
			}
			else {
				System.out.println("Core DB is not cleared");
			}
				//Get canonical path
				try {
					canonicalPath = new File(".").getCanonicalPath();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
										      
				
				//Initialise the client
				 
				//Assets(1-5000) with Ready for Tx State
				for (int i = 1; i <=5000; i++) {
				CmppClient cmppClient = (CmppClient) CmppClientFactory
						.newClient(canonicalPath + "/src/main/resources/envConfigs/worker.pfx", "nine", "pkcs12");
				
				HALResource cmpp = cmppClient.init("https://golfapi.malo.dev.aws.redbeemedia.com/v1");
				
				CreateCMPPData cmppdata = new CreateCMPPData();
				
				
					
				String mat=""+i;
				//create asset
				HALResource asset = cmppdata.createAsset(cmpp, "SPECIAL-UUID-TEST-ASSETGROUP-A", "TestAsset"+i, "MAT-"+mat,  
						"BONNIER", 123456789, "Ready for Tx", "program");
				String asset1 = asset.uri()+1;
				log.info("Created Asset: " + asset.uri());
				
				Thread.sleep(10);
				//create assetfile for above asset
				HALResource assetfile = cmppdata.createAssetFile(asset, "Assetfile-Test", "Assetfile-Test", "Assetfile-Test-checksum", 
						 "SPECIAL-UUID-TEST-FILETYPE MXF", 123456789, 123456789,  "mxf_.123", "10:00:00:00", "Passed" );
				log.info("Created Assetfile: " + assetfile.uri());
				
				
				//create filelocation for above assetfile
				HALResource filelocation = cmppdata.createAssetFileLocation(assetfile, "Assetfilelocation-Test-checksum", 123456789,
						 "morph://PU8_NU_STG_AD1/", LocalDateTime.now());
				log.info("Created AssetfileLocation: " + filelocation.uri());
				cmppClient = null;
				}
				
				
				//Assets(5001-10000) with Not Ready for Tx State
				
				for (int i = 5001; i <=10000; i++) {
					CmppClient cmppClient = (CmppClient) CmppClientFactory
							.newClient(canonicalPath + "/src/main/resources/envConfigs/worker.pfx", "nine", "pkcs12");
					
					HALResource cmpp = cmppClient.init("https://golfapi.malo.dev.aws.redbeemedia.com/v1");
					
					CreateCMPPData cmppdata = new CreateCMPPData();
					
					
					
					String mat=""+i;
					//create asset
					HALResource asset = cmppdata.createAsset(cmpp, "SPECIAL-UUID-TEST-ASSETGROUP-A", "TestAsset"+i, "MAT-"+mat,  
							"BONNIER", 123456789, "Not Ready for Tx", "program");
					String asset1 = asset.uri()+1;
					log.info("Created Asset: " + asset.uri());
					
					Thread.sleep(10);
					//create assetfile for above asset
					HALResource assetfile = cmppdata.createAssetFile(asset, "Assetfile-Test", "Assetfile-Test", "Assetfile-Test-checksum", 
							 "SPECIAL-UUID-TEST-FILETYPE MXF", 123456789, 123456789,  "mxf_.123", "10:00:00:00", "Passed" );
					log.info("Created Assetfile: " + assetfile.uri());
					
					
					//create filelocation for above assetfile
					HALResource filelocation = cmppdata.createAssetFileLocation(assetfile, "Assetfilelocation-Test-checksum", 123456789,
							 "morph://PU8_NU_STG_AD1/", LocalDateTime.now());
					log.info("Created AssetfileLocation: " + filelocation.uri());
					cmppClient = null;
					}


		}
		
		
		
	}



