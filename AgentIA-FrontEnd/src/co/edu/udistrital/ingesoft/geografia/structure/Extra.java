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
"value",
"key"
})
public class Extra {

@JsonProperty("value")
private String value;
@JsonProperty("key")
private String key;
@JsonIgnore
private Map<String, Object> additionalProperties = new HashMap<String, Object>();

/**
* No args constructor for use in serialization
* 
*/
public Extra() {
}

/**
* 
* @param value
* @param key
*/
public Extra(String value, String key) {
this.value = value;
this.key = key;
}

/**
* 
* @return
* The value
*/
@JsonProperty("value")
public String getValue() {
return value;
}

/**
* 
* @param value
* The value
*/
@JsonProperty("value")
public void setValue(String value) {
this.value = value;
}

public Extra withValue(String value) {
this.value = value;
return this;
}

/**
* 
* @return
* The key
*/
@JsonProperty("key")
public String getKey() {
return key;
}

/**
* 
* @param key
* The key
*/
@JsonProperty("key")
public void setKey(String key) {
this.key = key;
}

public Extra withKey(String key) {
this.key = key;
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

public Extra withAdditionalProperty(String name, Object value) {
this.additionalProperties.put(name, value);
return this;
}

@Override
public int hashCode() {
return new HashCodeBuilder().append(value).append(key).append(additionalProperties).toHashCode();
}

@Override
public boolean equals(Object other) {
if (other == this) {
return true;
}
if ((other instanceof Extra) == false) {
return false;
}
Extra rhs = ((Extra) other);
return new EqualsBuilder().append(value, rhs.value).append(key, rhs.key).append(additionalProperties, rhs.additionalProperties).isEquals();
}

}