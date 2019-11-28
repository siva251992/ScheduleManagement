package org.com.scheduleproperties;

public class Items {
	
	private String MaterialID;
    private String Title;
    private String TimeRequired;
    private int Duration;
    private String MediaType;
    private String MediaSource;
    private String ContentType;
    private AdditionalInfo AdditionalInfo;
    
    public void setMaterialID(String MaterialID){
        this.MaterialID = MaterialID;
    }
    public String getMaterialID(){
        return this.MaterialID;
    }
    public void setTitle(String Title){
        this.Title = Title;
    }
    public String getTitle(){
        return this.Title;
    }
    public void setTimeRequired(String TimeRequired){
        this.TimeRequired = TimeRequired;
    }
    public String getTimeRequired(){
        return this.TimeRequired;
    }
    public void setDuration(int Duration){
        this.Duration = Duration;
    }
    public int getDuration(){
        return this.Duration;
    }
    public void setMediaType(String MediaType){
        this.MediaType = MediaType;
    }
    public String getMediaType(){
        return this.MediaType;
    }
    public void setMediaSource(String MediaSource){
        this.MediaSource = MediaSource;
    }
    public String getMediaSource(){
        return this.MediaSource;
    }
    public void setContentType(String ContentType){
        this.ContentType = ContentType;
    }
    public String getContentType(){
        return this.ContentType;
    }
    public void setAdditionalInfo(AdditionalInfo AdditionalInfo){
        this.AdditionalInfo = AdditionalInfo;
    }
    public AdditionalInfo getAdditionalInfo(){
        return this.AdditionalInfo;
    }
}
    