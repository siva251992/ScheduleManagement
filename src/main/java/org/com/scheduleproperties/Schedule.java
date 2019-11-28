package org.com.scheduleproperties;

import java.util.List;

public class Schedule {
	private String Client;
	private String client;
	private String CLIENT;
	private String ClIenT;
	 private String Channel;
	 private String RequestId;
	 private String Version;
	 private String DateRequired;
	 private String ScheduleType;
	 private List<Items> Items;
	
	 //Gettters & Setters
	 
	 public void setClient(String Client){
	        this.Client = Client;
	    }
	    public String getClient(){
	        return this.Client;
	    }
	    //Client in LoweCase
	    public void setclient(String client){
	        this.client = client;
	    }
	    public String getclient(){
	        return this.client;
	     //Client in UpperCase
	    }public void setCLIENT(String CLIENT){
	        this.CLIENT = CLIENT;
	    }
	    public String getCLIENT(){
	        return this.CLIENT;
	      //Client in CamelCase
	    }public void setClIenT(String ClIenT){
	        this.ClIenT = ClIenT;
	    }
	    public String getClIenT(){
	        return this.ClIenT;
	    }
	    public void setChannel(String Channel){
	        this.Channel = Channel;
	    }
	    public String getChannel(){
	        return this.Channel;
	    }
	    public void setRequestId(String RequestId){
	        this.RequestId = RequestId;
	    }
	    public String getRequestId(){
	        return this.RequestId;
	    }
	    public void setVersion(String Version){
	        this.Version = Version;
	    }
	    public String getVersion(){
	        return this.Version;
	    }
	    public void setDateRequired(String DateRequired){
	        this.DateRequired = DateRequired;
	    }
	    public String getDateRequired(){
	        return this.DateRequired;
	    }
	    public void setScheduleType(String ScheduleType){
	        this.ScheduleType = ScheduleType;
	    }
	    public String getScheduleType(){
	        return this.ScheduleType;
	    }
	    public void setItems(List<Items> Items){
	        this.Items = Items;
	    }
	    public List<Items> getItems(){
	        return this.Items;
	    }

}

