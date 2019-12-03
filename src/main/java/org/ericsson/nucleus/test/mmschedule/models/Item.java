package org.ericsson.nucleus.test.mmschedule.models;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang.builder.ToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "ContentType", "MaterialID", "Duration", "MediaType", "TimeRequired", "MediaSource", "Title",
		"AdditionalInfo" })
public class Item {

	@JsonProperty("ContentType")
	private String contentType;
	@JsonProperty("MaterialID")
	private String materialID;
	@JsonProperty("Duration")
	private Integer duration;
	@JsonProperty("MediaType")
	private String mediaType;
	@JsonProperty("TimeRequired")
	private String timeRequired;
	@JsonProperty("MediaSource")
	private String mediaSource;
	@JsonProperty("Title")
	private String title;
	@JsonProperty("AdditionalInfo")
	private Object additionalInfo;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("ContentType")
	public String getContentType() {
		return contentType;
	}

	@JsonProperty("ContentType")
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	@JsonProperty("MaterialID")
	public String getMaterialID() {
		return materialID;
	}

	@JsonProperty("MaterialID")
	public void setMaterialID(String materialID) {
		this.materialID = materialID;
	}

	@JsonProperty("Duration")
	public Integer getDuration() {
		return duration;
	}

	@JsonProperty("Duration")
	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	@JsonProperty("MediaType")
	public String getMediaType() {
		return mediaType;
	}

	@JsonProperty("MediaType")
	public void setMediaType(String mediaType) {
		this.mediaType = mediaType;
	}

	@JsonProperty("TimeRequired")
	public String getTimeRequired() {
		return timeRequired;
	}

	@JsonProperty("TimeRequired")
	public void setTimeRequired(String timeRequired) {
		this.timeRequired = timeRequired;
	}

	@JsonProperty("MediaSource")
	public String getMediaSource() {
		return mediaSource;
	}

	@JsonProperty("MediaSource")
	public void setMediaSource(String mediaSource) {
		this.mediaSource = mediaSource;
	}

	@JsonProperty("Title")
	public String getTitle() {
		return title;
	}

	@JsonProperty("Title")
	public void setTitle(String title) {
		this.title = title;
	}

	@JsonProperty("AdditionalInfo")
	public Object getAdditionalInfo() {
		return additionalInfo;
	}

	@JsonProperty("AdditionalInfo")
	public void setAdditionalInfo(Object additionalInfo) {
		this.additionalInfo = additionalInfo;
	}

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("contentType", contentType).append("materialID", materialID)
				.append("duration", duration).append("mediaType", mediaType).append("timeRequired", timeRequired)
				.append("mediaSource", mediaSource).append("title", title).append("additionalInfo", additionalInfo)
				.append("additionalProperties", additionalProperties).toString();
	}

}