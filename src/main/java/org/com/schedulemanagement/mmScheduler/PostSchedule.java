package org.com.schedulemanagement.mmScheduler;

import static io.restassured.RestAssured.given;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.ericsson.nucleus.test.mmschedule.models.Item;
import org.ericsson.nucleus.test.mmschedule.models.Schedule;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import junit.framework.Assert;


public class PostSchedule {

	public static void postRequest(CommandLineParameter param) throws ParseException, InterruptedException {
		
		//*********DateConfiguration object*******
		DateConfiguration d=new DateConfiguration();
		
		
		//*********DB Clear*********
		
		if(param.getDbclear().equalsIgnoreCase("yes")){
			   RequestSpecification request = RestAssured.with();
			   Response response = request.baseUri("http://ec2-52-50-126-1.eu-west-1.compute.amazonaws.com").get("/mongo-query");
			   System.out.println(response.asString());
			  
		   			   
		   }
		   else {
			   System.out.println("DB is not Cleared");
		}
		
		
		//********Set MediaType****************
		String mediaType=param.getMediaType();
		if(mediaType.equalsIgnoreCase("Video")) {
			mediaType="Video";
		}
		else if(mediaType.equalsIgnoreCase("Subtitles")) {
			mediaType="Subtitles";
		}
		else if (mediaType.equalsIgnoreCase("AD")) {
			mediaType="AD";
		}
		else {
			System.out.println("Invalid MediaType-Enter AD/Video/Subtitles ");
			
			System.exit(0);
		}
		
		//**********Set TimeRequired************
		String timeRequired = param.getTimeRequired();
		if (timeRequired.equalsIgnoreCase("current")) {
			try {
				timeRequired=d.timeRequired();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
			}				
		}
		else if (timeRequired.equalsIgnoreCase("past")) {
			try {
				timeRequired=d.pasttimeRequired();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
			}
		}
		else {
			System.out.println("Invalid Value- Enter past/current");
			System.exit(0);
			
		}
		
		//*********Set Schedule & Item's count
		
		int reqId1 = Integer.parseInt(param.getScheduleStart());
		//System.out.println("Enter the End number of Schedules");
		int reqId2 = Integer.parseInt(param.getScheduleEnd());
		//System.out.println("Enter the number of Items");
		int matID=Integer.parseInt(param.getNoOfItems());
		
		int count=1;
		int mat=1;
		for (int i=reqId1;i<=reqId2; i++) {
			
			Map<String, String> headers = new HashMap<String, String>();
			//************************Set Content-Type****************************	
			
			headers.put("Content-Type", "application/json");
			
			
		//************************Set Authorization****************************	
			
			
			headers.put("Authorization", "bearer eyJhbGciOiJSUzI1NiIsImtpZCI6Im1idGVzdC8yMDE3MDcyNiIsInR5cCI6IkpXVCJ9.eyJhdWQiOiJicm46c2VydmljZTpzaC1zY2hlZHVsZS1pbXBvcnQtc2VydmljZSIsImV4cCI6MTU5NDkwMDI1NCwiaWF0IjoxNTYzMzY0MjU0LCJpc3MiOiJtb2NrYmlyZCIsInN1YiI6ImJybjpudWM6Y2xpZW50OnN0YXJnYXRlOmZsYWcifQ.n8MXhz_ww2-BMKJZA9CuOS3VBq759Pu4P5dPz_QMa7kgoq-ZTF2htIBeu9KOUZwNgugVwUEK5gCxD5fdJhVU2fZYhyoRQ5CcBd8dRQLUE_OouVI_UGHekMpoPeQIpWMTBOc2Gl1Q6EtMHfAc0h-1Filw_N2nIkO7LIAZVmrHFLOWRa0tZaC0BeKYY0E0hEskZ7Wt1jBT2g4Mb6Icd9R_gW2u3__rFUWCgzt74NUK5WmthsdPmrz34vxAihrAyV2EX-aUbk_cnDLd_S_oZFJW6pSEqiv75xs5e0tMRSCOHCavQDld51xhxaHZ2Ub48J8uR3coWsc2Anvimb4O8bbyIQ");
							
			String req_Id=""+i;
			Schedule s=new Schedule();
			s.setClient("Bonnier");
			s.setChannel("Cmore_Stars");
			s.setDateRequired(d.dateRequired());
			s.setRequestId(req_Id);
			s.setVersion("V001");
			s.setScheduleType("Primary");
			List<Item> l = new ArrayList<Item>();
			
			for (int j =1; j <=matID; j++) {
				Item item1=new Item();
				String mat_Id="MAT-"+param.getMaterialID()+"-"+mat;
				item1.setMaterialID(mat_Id);
				item1.setContentType("Programme");
				item1.setTitle("test1");
				item1.setTimeRequired(timeRequired);
				item1.setDuration(100);
				item1.setMediaType("Video");
				item1.setMediaSource("File");
				
				l.add(item1);
				mat++;
			}
			
			s.setItems(l);
			Thread.sleep(500);
			
			//**********POST Request***********
				Response postResponse = 
						given()
						.baseUri("https://ulbw5caxdh.execute-api.eu-west-1.amazonaws.com/flag/")
						.headers(headers)
						.body(s).log().all().when()
   					    .post("/sch-imp/schedule");

						
				int code=202;
			//*********StatusCode Assertion*****
				
				
     			Assert.assertEquals(code, postResponse.getStatusCode());
     			System.out.println("Schedule "+count +" Created successfully ");
				count++;
		}
	 
	   System.out.println("Done");

	}
	
}
