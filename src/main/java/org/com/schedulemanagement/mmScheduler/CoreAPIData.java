package org.com.schedulemanagement.mmScheduler;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

import org.apache.log4j.Logger;
import com.ericsson.testing.model.LibCmppException;
import com.ericsson.testing.CmppClient;
import com.ericsson.testing.CmppClientFactory;
import com.ericsson.testing.CreateCMPPData;
import com.ericsson.testing.HALResource;

public class CoreAPIData {
	
     	static String canonicalPath;
		
		final static Logger log = Logger.getLogger(CoreAPIData.class);
		
		public static void main(String args[]) throws LibCmppException {
			
				//Get canonical path
				try {
					canonicalPath = new File(".").getCanonicalPath();
				} catch (IOException e) {
					e.printStackTrace();
				}
					
				//Initialise the client
				CmppClient cmppClient = (CmppClient) CmppClientFactory
						.newClient(canonicalPath + "/src/main/resources/envConfigs/worker.pfx", "nine", "pkcs12");
				
				HALResource cmpp = cmppClient.init("https://flagapi.malo.dev.aws.redbeemedia.com/v1");
				
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



