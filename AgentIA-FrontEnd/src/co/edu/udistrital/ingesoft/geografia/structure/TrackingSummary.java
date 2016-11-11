package co.edu.udistrital.ingesoft.geografia.structure;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
"total",
"recent"
})
public class TrackingSummary {

@JsonProperty("total")
private Integer total;
@JsonProperty("recent")
private Integer recent;
@JsonIgnore
private Map<String, Object> additionalProperties = new HashMap<String, Object>();

/**
* No args constructor for use in serialization
* 
*/
public TrackingSummary() {
}

/**
* 
* @param total
* @param recent
*/
public TrackingSummary(Integer total, Integer recent) {
this.total = total;
this.recent = recent;
}

/**
* 
* @return
* The total
*/
@JsonProperty("total")
public Integer getTotal() {
return total;
}

/**
* 
* @param total
* The total
*/
@JsonProperty("total")
public void setTotal(Integer total) {
this.total = total;
}

public TrackingSummary withTotal(Integer total) {
this.total = total;
return this;
}

/**
* 
* @return
* The recent
*/
@JsonProperty("recent")
public Integer getRecent() {
return recent;
}

/**
* 
* @param recent
* The recent
*/
@JsonProperty("recent")
public void setRecent(Integer recent) {
this.recent = recent;
}

public TrackingSummary withRecent(Integer recent) {
this.recent = recent;
return this;
}

@Override
public String toString() {
return ToStringBuilder.reflectionToString(this);
}

@JsonAnyGetter
public Map<String, Object> getAdditionalProperties() {
return this.additionalProperties;
}

@JsonAnySetter
public void setAdditionalProperty(String name, Object value) {
this.additionalProperties.put(name, value);
}

public TrackingSummary withAdditionalProperty(String name, Object value) {
this.additionalProperties.put(name, value);
return this;
}

@Override
public int hashCode() {
return new HashCodeBuilder().append(total).append(recent).append(additionalProperties).toHashCode();
}

@Override
public boolean equals(Object other) {
if (other == this) {
return true;
}
if ((other instanceof TrackingSummary) == false) {
return false;
}
TrackingSummary rhs = ((TrackingSummary) other);
return new EqualsBuilder().append(total, rhs.total).append(recent, rhs.recent).append(additionalProperties, rhs.additionalProperties).isEquals();
}

}
