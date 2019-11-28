package org.com.schedulemanagement.mmScheduler;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import org.com.scheduleproperties.Items;
import org.com.scheduleproperties.Schedule;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import junit.framework.Assert;


public class PostSchedule {
	
	public static void postRequest() throws InterruptedException, ParseException {
		@SuppressWarnings("resource")
		//**********ScannerClss- To set the values during RunTime*****************
		
		Scanner myObj = new Scanner(System.in);
		
		//**********DB Clear Step**************
		
		  System.out.println("Do You want to clear the DB?");
		  String dbClear=myObj.nextLine();
		   if(dbClear.equalsIgnoreCase("Yes")){
			   RequestSpecification request = RestAssured.with();
			   Response response = request.baseUri("http://ec2-52-50-126-1.eu-west-1.compute.amazonaws.com").get("/mongo-query");
			   System.out.println(response.asString());
		   			   
		   }
		   else {
			   System.out.println("DB is not Cleared");
		}
		   
		   int count=1;
		
		//*****************To set MaterialID,Schedule&Items Count***************
			
			
			System.out.println("Enter the materialId");
			String userName = myObj.nextLine();
			System.out.println("Enter the Start number of Schedules");
			int reqId1 = myObj.nextInt();
			System.out.println("Enter the End number of Schedules");
			int reqId2 = myObj.nextInt();
			System.out.println("Enter the number of Items");
			int matID=myObj.nextInt();
			
		//****************Object for DateConfiguration Class*****************	
			DateConfiguration d=new DateConfiguration();
			
			
			for (int i = reqId1; i <=reqId2; i++) {
	    		
				RequestSpecification request = RestAssured.with();
				Map<String, String> headers = new HashMap<String, String>();
			//************************Set Content-Type****************************	
				
				headers.put("Content-Type", "application/json");
				
				
			//************************Set Authorization****************************	
				
				
				headers.put("Authorization", "bearer eyJhbGciOiJSUzI1NiIsImtpZCI6Im1idGVzdC8yMDE3MDcyNiIsInR5cCI6IkpXVCJ9.eyJhdWQiOiJicm46c2VydmljZTpzaC1zY2hlZHVsZS1pbXBvcnQtc2VydmljZSIsImV4cCI6MTU5NDkwMDI1NCwiaWF0IjoxNTYzMzY0MjU0LCJpc3MiOiJtb2NrYmlyZCIsInN1YiI6ImJybjpudWM6Y2xpZW50OnN0YXJnYXRlOmZsYWcifQ.n8MXhz_ww2-BMKJZA9CuOS3VBq759Pu4P5dPz_QMa7kgoq-ZTF2htIBeu9KOUZwNgugVwUEK5gCxD5fdJhVU2fZYhyoRQ5CcBd8dRQLUE_OouVI_UGHekMpoPeQIpWMTBOc2Gl1Q6EtMHfAc0h-1Filw_N2nIkO7LIAZVmrHFLOWRa0tZaC0BeKYY0E0hEskZ7Wt1jBT2g4Mb6Icd9R_gW2u3__rFUWCgzt74NUK5WmthsdPmrz34vxAihrAyV2EX-aUbk_cnDLd_S_oZFJW6pSEqiv75xs5e0tMRSCOHCavQDld51xhxaHZ2Ub48J8uR3coWsc2Anvimb4O8bbyIQ");
								
				
			//***********************Set First-Level Property Values*************	
				
				String req_Id=""+i;
				Schedule s=new Schedule();
				s.setClient("new");
				s.setChannel("Cmore_Stars");
				s.setDateRequired(d.dateRequired());
				s.setRequestId(req_Id);
				s.setVersion("V001");
				s.setScheduleType("Primary");
				List<Items> l = new ArrayList<Items>();
				
				for (int j = 1; j <=matID; j++) {
			//***********************Set Item-Level Property Values*************	
					Items item1=new Items();
					String mat_Id="MAT-"+userName+"-"+i+"-"+j;
					item1.setMaterialID(mat_Id);
					item1.setContentType("Programme");
					item1.setTitle("test1");
					item1.setTimeRequired(d.timeRequired());
					item1.setDuration(100);
					item1.setMediaType("Video");
					item1.setMediaSource("File");
					
					l.add(item1);
									
				}
				s.setItems(l);
				Thread.sleep(500);
				
			//**********POST Request***********
				
				
				Response response = request.baseUri("https://ulbw5caxdh.execute-api.eu-west-1.amazonaws.com/flag/").headers(headers).body(s).post("/sch-imp/schedule");
				int code=202;
			//*********StatusCode Assertion*****
				
				
     			Assert.assertEquals(code, response.getStatusCode());
				System.out.println("Schedule "+count +" Created successfully ");
				count++;
	    	}
			System.out.println("Done");
	}
	
	
 
			
	public static void main(String[] args) throws InterruptedException, ParseException {
		
		postRequest();
		
		
		
	}
}

