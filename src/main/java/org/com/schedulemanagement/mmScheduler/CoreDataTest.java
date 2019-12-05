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

public class CoreDataTest {
	
     	static String canonicalPath;
		
		final static Logger log = Logger.getLogger(CoreDataTest.class);
		
		public  void createAssets(CommandLineParameter c) throws LibCmppException {
			//Core DB Clear
			
			if(c.getDbclear().equalsIgnoreCase("yes")) {
			RequestSpecification request = RestAssured.with();
			Response response = request.baseUri("http://ec2-52-50-126-1.eu-west-1.compute.amazonaws.com").get("/cleardb?q=coreGolf");
			System.out.println(response.asString());
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
				
				//Set AssetCount
				int assetCount1 = Integer.parseInt(c.getAsset1());
				int assetCount2 = Integer.parseInt(c.getAsset2());
				
				//set txStatus for Asset1
			      String asset1Tx = c.getAsset1Tx();
			      if(asset1Tx.equalsIgnoreCase("Yes")) {
			    	  asset1Tx="Ready for Tx";
			      }
			      else {
			    	  asset1Tx="Not Ready for Tx";
			      }
			      
			      //set txStatus for Asset2
			      String asset2Tx = c.getAsset2Tx();
			      if(asset2Tx.equalsIgnoreCase("Yes")) {
			    	  asset2Tx="Ready for Tx";
			      }
			      else {
			    	  asset2Tx="Not Ready for Tx";
			      }
			      
			      //set profile for Asset1
			      String asset1Profile = c.getAsset1Profile();
			      if (asset1Profile.equalsIgnoreCase("AD")) {
			    	  asset1Profile="ad_bwav";
				}
			      else if (asset1Profile.equalsIgnoreCase("Video")) {
			    	  asset1Profile="mxf_.123";
				} else if(asset1Profile.equalsIgnoreCase("Subtitles")){
					asset1Profile="subs_stl";
				}
				else {
					System.out.println("Invalid Asset1 Profile- Enter AD/Video/Subtitles");
				}
			      
			      //Set profile for Asset2
			      String asset2Profile = c.getAsset2Profile();
			      if (asset2Profile.equalsIgnoreCase("AD")) {
			    	  asset2Profile="ad_bwav";
				}
			      else if (asset2Profile.equalsIgnoreCase("Video")) {
			    	  asset2Profile="mxf_.123";
				} else if(asset2Profile.equalsIgnoreCase("Subtitles")){
					asset2Profile="subs_stl";
				}
				else {
					System.out.println("Invalid Asset2 Profile- Enter AD/Video/Subtitles");
				}
			      
				
				//Initialise the client
				for (int i = 1; i <=10; i++) {
				CmppClient cmppClient = (CmppClient) CmppClientFactory
						.newClient(canonicalPath + "/src/main/resources/envConfigs/worker.pfx", "nine", "pkcs12");
				
				HALResource cmpp = cmppClient.init("https://golfapi.malo.dev.aws.redbeemedia.com/v1");
				
				CreateCMPPData cmppdata = new CreateCMPPData();
				
				
					
				String mat=""+i;
				//create asset
				HALResource asset = cmppdata.createAsset(cmpp, "SPECIAL-UUID-TEST-ASSETGROUP-A", "Jag's Test", "MAT-"+c.getMaterialID()+"-"+mat,  
						"BONNIER", 123456789, "Ready for Tx", "program");
				String asset1 = asset.uri()+1;
				log.info("Created Asset: " + asset.uri());
				
				
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
				for (int i = 11; i <=20; i++) {
					CmppClient cmppClient = (CmppClient) CmppClientFactory
							.newClient(canonicalPath + "/src/main/resources/envConfigs/worker.pfx", "nine", "pkcs12");
					
					HALResource cmpp = cmppClient.init("https://golfapi.malo.dev.aws.redbeemedia.com/v1");
					
					CreateCMPPData cmppdata = new CreateCMPPData();
					
					
					
					String mat=""+i;
					//create asset
					HALResource asset = cmppdata.createAsset(cmpp, "SPECIAL-UUID-TEST-ASSETGROUP-A", "Jag's Test", "MAT-"+c.getMaterialID()+"-"+mat,  
							"BONNIER", 123456789, "Not Ready for Tx", "program");
					String asset1 = asset.uri()+1;
					log.info("Created Asset: " + asset.uri());
					
					
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
		
		public static void main(String args[]) throws LibCmppException {
			//Core Db Clear
			
				//Get canonical path
				try {
					canonicalPath = new File(".").getCanonicalPath();
				} catch (IOException e) {
					e.printStackTrace();
				}
					
				//Initialise the client
				CmppClient cmppClient = (CmppClient) CmppClientFactory
						.newClient(canonicalPath + "/src/main/resources/envConfigs/worker.pfx", "nine", "pkcs12");
				
				HALResource cmpp = cmppClient.init("https://golfapi.malo.dev.aws.redbeemedia.com/v1");
				
				CreateCMPPData cmppdata = new CreateCMPPData();
				
				
				//create asset
				HALResource asset = cmppdata.createAsset(cmpp, "SPECIAL-UUID-TEST-ASSETGROUP-A", "Jag's Test", "MY-MaterialID",  
						"TEST", 123456789, "Ready for Tx", "program");
				log.info("Created Asset: " + asset.uri());
				
				
				//create assetfile for above asset
				HALResource assetfile = cmppdata.createAssetFile(asset, "Assetfile-Test", "Assetfile-Test", "Assetfile-Test-checksum", 
						 "SPECIAL-UUID-TEST-FILETYPE MXF", 123456789, 123456789,  "test-profile", "10:00:00:00", "Passed" );
				log.info("Created Assetfile: " + assetfile.uri());
				
				
				//create filelocation for above assetfile
				HALResource filelocation = cmppdata.createAssetFileLocation(assetfile, "Assetfilelocation-Test-checksum", 123456789,
						 "file://test/test.text", LocalDateTime.now());
				log.info("Created AssetfileLocation: " + filelocation.uri());
				
				
				cmppClient = null;
						
		}
		
	}



