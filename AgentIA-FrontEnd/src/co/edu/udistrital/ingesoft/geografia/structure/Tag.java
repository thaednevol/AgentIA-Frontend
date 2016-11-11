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
"vocabulary_id",
"name",
"revision_timestamp",
"state",
"id",
"display_name"
})
public class Tag {

@JsonProperty("vocabulary_id")
private Object vocabularyId;
@JsonProperty("name")
private String name;
@JsonProperty("revision_timestamp")
private String revisionTimestamp;
@JsonProperty("state")
private String state;
@JsonProperty("id")
private String id;
@JsonProperty("display_name")
private String displayName;
@JsonIgnore
private Map<String, Object> additionalProperties = new HashMap<String, Object>();

/**
* No args constructor for use in serialization
* 
*/
public Tag() {
}

/**
* 
* @param id
* @param vocabularyId
* @param name
* @param state
* @param displayName
* @param revisionTimestamp
*/
public Tag(Object vocabularyId, String name, String revisionTimestamp, String state, String id, String displayName) {
this.vocabularyId = vocabularyId;
this.name = name;
this.revisionTimestamp = revisionTimestamp;
this.state = state;
this.id = id;
this.displayName = displayName;
}

/**
* 
* @return
* The vocabularyId
*/
@JsonProperty("vocabulary_id")
public Object getVocabularyId() {
return vocabularyId;
}

/**
* 
* @param vocabularyId
* The vocabulary_id
*/
@JsonProperty("vocabulary_id")
public void setVocabularyId(Object vocabularyId) {
this.vocabularyId = vocabularyId;
}

public Tag withVocabularyId(Object vocabularyId) {
this.vocabularyId = vocabularyId;
return this;
}

/**
* 
* @return
* The name
*/
@JsonProperty("name")
public String getName() {
return name;
}

/**
* 
* @param name
* The name
*/
@JsonProperty("name")
public void setName(String name) {
this.name = name;
}

public Tag withName(String name) {
this.name = name;
return this;
}

/**
* 
* @return
* The revisionTimestamp
*/
@JsonProperty("revision_timestamp")
public String getRevisionTimestamp() {
return revisionTimestamp;
}

/**
* 
* @param revisionTimestamp
* The revision_timestamp
*/
@JsonProperty("revision_timestamp")
public void setRevisionTimestamp(String revisionTimestamp) {
this.revisionTimestamp = revisionTimestamp;
}

public Tag withRevisionTimestamp(String revisionTimestamp) {
this.revisionTimestamp = revisionTimestamp;
return this;
}

/**
* 
* @return
* The state
*/
@JsonProperty("state")
public String getState() {
return state;
}

/**
* 
* @param state
* The state
*/
@JsonProperty("state")
public void setState(String state) {
this.state = state;
}

public Tag withState(String state) {
this.state = state;
return this;
}

/**
* 
* @return
* The id
*/
@JsonProperty("id")
public String getId() {
return id;
}

/**
* 
* @param id
* The id
*/
@JsonProperty("id")
public void setId(String id) {
this.id = id;
}

public Tag withId(String id) {
this.id = id;
return this;
}

/**
* 
* @return
* The displayName
*/
@JsonProperty("display_name")
public String getDisplayName() {
return displayName;
}

/**
* 
* @param displayName
* The display_name
*/
@JsonProperty("display_name")
public void setDisplayName(String displayName) {
this.displayName = displayName;
}

public Tag withDisplayName(String displayName) {
this.displayName = displayName;
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

public Tag withAdditionalProperty(String name, Object value) {
this.additionalProperties.put(name, value);
return this;
}

@Override
public int hashCode() {
return new HashCodeBuilder().append(vocabularyId).append(name).append(revisionTimestamp).append(state).append(id).append(displayName).append(additionalProperties).toHashCode();
}

@Override
public boolean equals(Object other) {
if (other == this) {
return true;
}
if ((other instanceof Tag) == false) {
return false;
}
Tag rhs = ((Tag) other);
return new EqualsBuilder().append(vocabularyId, rhs.vocabularyId).append(name, rhs.name).append(revisionTimestamp, rhs.revisionTimestamp).append(state, rhs.state).append(id, rhs.id).append(displayName, rhs.displayName).append(additionalProperties, rhs.additionalProperties).isEquals();
}

}