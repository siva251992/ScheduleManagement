package org.ericsson.nucleus.test.mmschedule.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang.builder.ToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "Channel", "Client", "ScheduleType", "RequestId", "Version", "DateRequired", "Items" })
public class Schedule {

	@JsonProperty("Channel")
	private String channel;
	@JsonProperty("Client")
	private String client;
	@JsonProperty("ScheduleType")
	private String scheduleType;
	@JsonProperty("RequestId")
	private String requestId;
	@JsonProperty("Version")
	private String version;
	@JsonProperty("DateRequired")
	private String dateRequired;
	@JsonProperty("Items")
	private List<Item> items = new ArrayList<Item>();
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("Channel")
	public String getChannel() {
		return channel;
	}

	@JsonProperty("Channel")
	public void setChannel(String channel) {
		this.channel = channel;
	}

	@JsonProperty("Client")
	public String getClient() {
		return client;
	}

	@JsonProperty("Client")
	public void setClient(String client) {
		this.client = client;
	}

	@JsonProperty("ScheduleType")
	public String getScheduleType() {
		return scheduleType;
	}

	@JsonProperty("ScheduleType")
	public void setScheduleType(String scheduleType) {
		this.scheduleType = scheduleType;
	}

	@JsonProperty("RequestId")
	public String getRequestId() {
		return requestId;
	}

	@JsonProperty("RequestId")
	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	@JsonProperty("Version")
	public String getVersion() {
		return version;
	}

	@JsonProperty("Version")
	public void setVersion(String version) {
		this.version = version;
	}

	@JsonProperty("DateRequired")
	public String getDateRequired() {
		return dateRequired;
	}

	@JsonProperty("DateRequired")
	public void setDateRequired(String dateRequired) {
		this.dateRequired = dateRequired;
	}

	@JsonProperty("Items")
	public List<Item> getItems() {
		return items;
	}

	@JsonProperty("Items")
	public void setItems(List<Item> items) {
		this.items = items;
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
		return new ToStringBuilder(this).append("channel", channel).append("client", client)
				.append("scheduleType", scheduleType).append("requestId", requestId).append("version", version)
				.append("dateRequired", dateRequired).append("items", items)
				.append("additionalProperties", additionalProperties).toString();
	}

}