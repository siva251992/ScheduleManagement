package org.com.schedulemanagement.mmScheduler;

import static io.restassured.RestAssured.given;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.ericsson.nucleus.test.mmschedule.models.Item;
import org.ericsson.nucleus.test.mmschedule.models.Schedule;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import junit.framework.Assert;

public class ScheduleData {

	private Map<String, String> headers;
	private Schedule schedule;
	private List<Item> schedItems;
	private Item item;
	private String mmSchedUrl;
	
	final static Logger log = Logger.getLogger(ScheduleData.class);

	// method to create schedules
	public void createSchedule(CommandLineParameter param) throws InterruptedException, ParseException {

		// initialise Headers
		this.headers = new HashMap<String, String>();
		headers.put("Content-Type", "application/json");
		headers.put("Authorization",
				"bearer eyJhbGciOiJSUzI1NiIsImtpZCI6Im1idGVzdC8yMDE3MDcyNiIsInR5cCI6IkpXVCJ9.eyJhdWQiOiJicm46c2VydmljZTpzaC1zY2hlZHVsZS1pbXBvcnQtc2VydmljZSIsImV4cCI6MTU5NDkwMDI1NCwiaWF0IjoxNTYzMzY0MjU0LCJpc3MiOiJtb2NrYmlyZCIsInN1YiI6ImJybjpudWM6Y2xpZW50OnN0YXJnYXRlOmZsYWcifQ.n8MXhz_ww2-BMKJZA9CuOS3VBq759Pu4P5dPz_QMa7kgoq-ZTF2htIBeu9KOUZwNgugVwUEK5gCxD5fdJhVU2fZYhyoRQ5CcBd8dRQLUE_OouVI_UGHekMpoPeQIpWMTBOc2Gl1Q6EtMHfAc0h-1Filw_N2nIkO7LIAZVmrHFLOWRa0tZaC0BeKYY0E0hEskZ7Wt1jBT2g4Mb6Icd9R_gW2u3__rFUWCgzt74NUK5WmthsdPmrz34vxAihrAyV2EX-aUbk_cnDLd_S_oZFJW6pSEqiv75xs5e0tMRSCOHCavQDld51xhxaHZ2Ub48J8uR3coWsc2Anvimb4O8bbyIQ");

		// URL
		this.mmSchedUrl = "https://ulbw5caxdh.execute-api.eu-west-1.amazonaws.com/flag/";

		// *********DateConfiguration object*******
		DateConfiguration d = new DateConfiguration();

		// *********DB Clear*********
		if (param.getDbclear().equalsIgnoreCase("yes")) {
			RequestSpecification request = RestAssured.with();
			Response response = request.baseUri("http://ec2-52-50-126-1.eu-west-1.compute.amazonaws.com")
					.get("/mongo-query");
			log.info("Mongo" + response.asString());
		} else {
			log.info("DB is not Cleared");
		}
		// *********Set Schedule & Item's count

		int noOfScheds = Integer.parseInt(param.getScheduleEnd());
		int noOfItems = Integer.parseInt(param.getNoOfItems());
		int i, j;

		for (i = 0; i < noOfScheds; i++) {

			this.schedule = new Schedule();
			schedule.setClient("Bonnier");
			schedule.setChannel("Cmore_Stars");
			schedule.setDateRequired(d.dateRequired());
			schedule.setRequestId((i + 1) + "");
			schedule.setVersion("V001");
			schedule.setScheduleType("Primary");
			this.schedItems = new ArrayList<Item>();

			for (j = 1; j <= noOfItems; j++) {
				item = new Item();
				item.setMaterialID("MAT-" + ((noOfScheds*i)+j));
				item.setContentType("Programme");
				item.setTitle("test1");
				item.setTimeRequired(d.timeRequired());
				item.setDuration(100);
				item.setMediaType("Video");
				item.setMediaSource("File");

				schedItems.add(item);
				item=null;
			}

			schedule.setItems(schedItems);

			// **********POST Request***********
			Response postResponse = given().baseUri(mmSchedUrl).headers(headers).body(schedule).when()
					.post("/sch-imp/schedule");

			// *********StatusCode Assertion*****
			Assert.assertEquals(202, postResponse.getStatusCode());
			log.info("Schedule " + (i+1) + " Created in " + postResponse.getTime() + "ms");
			
			//reset the lists
			schedule=null;
			schedItems.clear();
			item=null;
			
		}
	}
}
