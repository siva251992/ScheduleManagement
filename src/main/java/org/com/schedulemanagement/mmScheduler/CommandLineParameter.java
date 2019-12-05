package org.com.schedulemanagement.mmScheduler;

public class CommandLineParameter {
	private String dbclear;
	private String materialID;
	private String asset1;
	private String asset2;
	private String asset1Tx;
	private String asset2Tx;
	private String asset1Profile;
	private String asset2Profile;
	private String mediaType;
	private String timeRequired;
	private String scheduleStart;
	private String scheduleEnd;
	private String noOfItems;
	
	
	public String getMediaType() {
		return mediaType;
	}
	public void setMediaType(String mediaType) {
		this.mediaType = mediaType;
	}
	public String getTimeRequired() {
		return timeRequired;
	}
	public void setTimeRequired(String timeRequired) {
		this.timeRequired = timeRequired;
	}
	
	public String getScheduleStart() {
		return scheduleStart;
	}
	public void setScheduleStart(String scheduleStart) {
		this.scheduleStart = scheduleStart;
	}
	public String getScheduleEnd() {
		return scheduleEnd;
	}
	public void setScheduleEnd(String scheduleEnd) {
		this.scheduleEnd = scheduleEnd;
	}
	public String getNoOfItems() {
		return noOfItems;
	}
	public void setNoOfItems(String noOfItems) {
		this.noOfItems = noOfItems;
	}
	public String getDbclear() {
		return dbclear;
	}
	public void setDbclear(String dbclear) {
		this.dbclear = dbclear;
	}
	public String getMaterialID() {
		return materialID;
	}
	public void setMaterialID(String materialID) {
		this.materialID = materialID;
	}
	public String getAsset1() {
		return asset1;
	}
	public void setAsset1(String asset1) {
		this.asset1 = asset1;
	}
	public String getAsset2() {
		return asset2;
	}
	public void setAsset2(String asset2) {
		this.asset2 = asset2;
	}
	public String getAsset1Tx() {
		return asset1Tx;
	}
	public void setAsset1Tx(String asset1Tx) {
		this.asset1Tx = asset1Tx;
	}
	public String getAsset2Tx() {
		return asset2Tx;
	}
	public void setAsset2Tx(String asset2Tx) {
		this.asset2Tx = asset2Tx;
	}
	public String getAsset1Profile() {
		return asset1Profile;
	}
	public void setAsset1Profile(String asset1Profile) {
		this.asset1Profile = asset1Profile;
	}
	public String getAsset2Profile() {
		return asset2Profile;
	}
	public void setAsset2Profile(String asset2Profile) {
		this.asset2Profile = asset2Profile;
	}
}
