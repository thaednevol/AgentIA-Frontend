package co.edu.udistrital.ingesoft.geografia.structure;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
"isopen",
"private",
"notes",
"num_resources",
"extras",
"owner_org",
"type",
"title",
"maintainer",
"revision_timestamp",
"author_email",
"num_tags",
"id",
"state",
"relationships_as_object",
"revision_id",
"creator_user_id",
"tracking_summary",
"metadata_modified",
"author",
"relationships_as_subject",
"metadata_created",
"resources",
"groups",
"version",
"url",
"tags",
"license_id",
"organization",
"name",
"license_title",
"maintainer_email"
})
public class GeographyDataset {

@JsonProperty("isopen")
private Boolean isopen;
@JsonProperty("private")
private Boolean _private;
@JsonProperty("notes")
private String notes;
@JsonProperty("num_resources")
private Integer numResources;
@JsonProperty("extras")
private List<Extra> extras = new ArrayList<Extra>();
@JsonProperty("owner_org")
private Object ownerOrg;
@JsonProperty("type")
private String type;
@JsonProperty("title")
private String title;
@JsonProperty("maintainer")
private Object maintainer;
@JsonProperty("revision_timestamp")
private String revisionTimestamp;
@JsonProperty("author_email")
private Object authorEmail;
@JsonProperty("num_tags")
private Integer numTags;
@JsonProperty("id")
private String id;
@JsonProperty("state")
private String state;
@JsonProperty("relationships_as_object")
private List<Object> relationshipsAsObject = new ArrayList<Object>();
@JsonProperty("revision_id")
private String revisionId;
@JsonProperty("creator_user_id")
private String creatorUserId;
@JsonProperty("tracking_summary")
private TrackingSummary trackingSummary;
@JsonProperty("metadata_modified")
private String metadataModified;
@JsonProperty("author")
private Object author;
@JsonProperty("relationships_as_subject")
private List<Object> relationshipsAsSubject = new ArrayList<Object>();
@JsonProperty("metadata_created")
private String metadataCreated;
@JsonProperty("resources")
private List<Resource> resources = new ArrayList<Resource>();
@JsonProperty("groups")
private List<Object> groups = new ArrayList<Object>();
@JsonProperty("version")
private Object version;
@JsonProperty("url")
private Object url;
@JsonProperty("tags")
private List<Tag> tags = new ArrayList<Tag>();
@JsonProperty("license_id")
private Object licenseId;
@JsonProperty("organization")
private Object organization;
@JsonProperty("name")
private String name;
@JsonProperty("license_title")
private Object licenseTitle;
@JsonProperty("maintainer_email")
private Object maintainerEmail;
@JsonIgnore
private Map<String, Object> additionalProperties = new HashMap<String, Object>();

/**
* No args constructor for use in serialization
* 
*/
public GeographyDataset() {
}

/**
* 
* @param licenseId
* @param resources
* @param metadataCreated
* @param numResources
* @param state
* @param ownerOrg
* @param type
* @param numTags
* @param revisionTimestamp
* @param revisionId
* @param version
* @param id
* @param maintainerEmail
* @param author
* @param organization
* @param title
* @param creatorUserId
* @param trackingSummary
* @param _private
* @param name
* @param groups
* @param metadataModified
* @param tags
* @param isopen
* @param authorEmail
* @param relationshipsAsSubject
* @param url
* @param maintainer
* @param extras
* @param licenseTitle
* @param notes
* @param relationshipsAsObject
*/
public GeographyDataset(Boolean isopen, Boolean _private, String notes, Integer numResources, List<Extra> extras, Object ownerOrg, String type, String title, Object maintainer, String revisionTimestamp, Object authorEmail, Integer numTags, String id, String state, List<Object> relationshipsAsObject, String revisionId, String creatorUserId, TrackingSummary trackingSummary, String metadataModified, Object author, List<Object> relationshipsAsSubject, String metadataCreated, List<Resource> resources, List<Object> groups, Object version, Object url, List<Tag> tags, Object licenseId, Object organization, String name, Object licenseTitle, Object maintainerEmail) {
this.isopen = isopen;
this._private = _private;
this.notes = notes;
this.numResources = numResources;
this.extras = extras;
this.ownerOrg = ownerOrg;
this.type = type;
this.title = title;
this.maintainer = maintainer;
this.revisionTimestamp = revisionTimestamp;
this.authorEmail = authorEmail;
this.numTags = numTags;
this.id = id;
this.state = state;
this.relationshipsAsObject = relationshipsAsObject;
this.revisionId = revisionId;
this.creatorUserId = creatorUserId;
this.trackingSummary = trackingSummary;
this.metadataModified = metadataModified;
this.author = author;
this.relationshipsAsSubject = relationshipsAsSubject;
this.metadataCreated = metadataCreated;
this.resources = resources;
this.groups = groups;
this.version = version;
this.url = url;
this.tags = tags;
this.licenseId = licenseId;
this.organization = organization;
this.name = name;
this.licenseTitle = licenseTitle;
this.maintainerEmail = maintainerEmail;
}

/**
* 
* @return
* The isopen
*/
@JsonProperty("isopen")
public Boolean getIsopen() {
return isopen;
}

/**
* 
* @param isopen
* The isopen
*/
@JsonProperty("isopen")
public void setIsopen(Boolean isopen) {
this.isopen = isopen;
}

public GeographyDataset withIsopen(Boolean isopen) {
this.isopen = isopen;
return this;
}

/**
* 
* @return
* The _private
*/
@JsonProperty("private")
public Boolean getPrivate() {
return _private;
}

/**
* 
* @param _private
* The private
*/
@JsonProperty("private")
public void setPrivate(Boolean _private) {
this._private = _private;
}

public GeographyDataset withPrivate(Boolean _private) {
this._private = _private;
return this;
}

/**
* 
* @return
* The notes
*/
@JsonProperty("notes")
public String getNotes() {
return notes;
}

/**
* 
* @param notes
* The notes
*/
@JsonProperty("notes")
public void setNotes(String notes) {
this.notes = notes;
}

public GeographyDataset withNotes(String notes) {
this.notes = notes;
return this;
}

/**
* 
* @return
* The numResources
*/
@JsonProperty("num_resources")
public Integer getNumResources() {
return numResources;
}

/**
* 
* @param numResources
* The num_resources
*/
@JsonProperty("num_resources")
public void setNumResources(Integer numResources) {
this.numResources = numResources;
}

public GeographyDataset withNumResources(Integer numResources) {
this.numResources = numResources;
return this;
}

/**
* 
* @return
* The extras
*/
@JsonProperty("extras")
public List<Extra> getExtras() {
return extras;
}

/**
* 
* @param extras
* The extras
*/
@JsonProperty("extras")
public void setExtras(List<Extra> extras) {
this.extras = extras;
}

public GeographyDataset withExtras(List<Extra> extras) {
this.extras = extras;
return this;
}

/**
* 
* @return
* The ownerOrg
*/
@JsonProperty("owner_org")
public Object getOwnerOrg() {
return ownerOrg;
}

/**
* 
* @param ownerOrg
* The owner_org
*/
@JsonProperty("owner_org")
public void setOwnerOrg(Object ownerOrg) {
this.ownerOrg = ownerOrg;
}

public GeographyDataset withOwnerOrg(Object ownerOrg) {
this.ownerOrg = ownerOrg;
return this;
}

/**
* 
* @return
* The type
*/
@JsonProperty("type")
public String getType() {
return type;
}

/**
* 
* @param type
* The type
*/
@JsonProperty("type")
public void setType(String type) {
this.type = type;
}

public GeographyDataset withType(String type) {
this.type = type;
return this;
}

/**
* 
* @return
* The title
*/
@JsonProperty("title")
public String getTitle() {
return title;
}

/**
* 
* @param title
* The title
*/
@JsonProperty("title")
public void setTitle(String title) {
this.title = title;
}

public GeographyDataset withTitle(String title) {
this.title = title;
return this;
}

/**
* 
* @return
* The maintainer
*/
@JsonProperty("maintainer")
public Object getMaintainer() {
return maintainer;
}

/**
* 
* @param maintainer
* The maintainer
*/
@JsonProperty("maintainer")
public void setMaintainer(Object maintainer) {
this.maintainer = maintainer;
}

public GeographyDataset withMaintainer(Object maintainer) {
this.maintainer = maintainer;
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

public GeographyDataset withRevisionTimestamp(String revisionTimestamp) {
this.revisionTimestamp = revisionTimestamp;
return this;
}

/**
* 
* @return
* The authorEmail
*/
@JsonProperty("author_email")
public Object getAuthorEmail() {
return authorEmail;
}

/**
* 
* @param authorEmail
* The author_email
*/
@JsonProperty("author_email")
public void setAuthorEmail(Object authorEmail) {
this.authorEmail = authorEmail;
}

public GeographyDataset withAuthorEmail(Object authorEmail) {
this.authorEmail = authorEmail;
return this;
}

/**
* 
* @return
* The numTags
*/
@JsonProperty("num_tags")
public Integer getNumTags() {
return numTags;
}

/**
* 
* @param numTags
* The num_tags
*/
@JsonProperty("num_tags")
public void setNumTags(Integer numTags) {
this.numTags = numTags;
}

public GeographyDataset withNumTags(Integer numTags) {
this.numTags = numTags;
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

public GeographyDataset withId(String id) {
this.id = id;
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

public GeographyDataset withState(String state) {
this.state = state;
return this;
}

/**
* 
* @return
* The relationshipsAsObject
*/
@JsonProperty("relationships_as_object")
public List<Object> getRelationshipsAsObject() {
return relationshipsAsObject;
}

/**
* 
* @param relationshipsAsObject
* The relationships_as_object
*/
@JsonProperty("relationships_as_object")
public void setRelationshipsAsObject(List<Object> relationshipsAsObject) {
this.relationshipsAsObject = relationshipsAsObject;
}

public GeographyDataset withRelationshipsAsObject(List<Object> relationshipsAsObject) {
this.relationshipsAsObject = relationshipsAsObject;
return this;
}

/**
* 
* @return
* The revisionId
*/
@JsonProperty("revision_id")
public String getRevisionId() {
return revisionId;
}

/**
* 
* @param revisionId
* The revision_id
*/
@JsonProperty("revision_id")
public void setRevisionId(String revisionId) {
this.revisionId = revisionId;
}

public GeographyDataset withRevisionId(String revisionId) {
this.revisionId = revisionId;
return this;
}

/**
* 
* @return
* The creatorUserId
*/
@JsonProperty("creator_user_id")
public String getCreatorUserId() {
return creatorUserId;
}

/**
* 
* @param creatorUserId
* The creator_user_id
*/
@JsonProperty("creator_user_id")
public void setCreatorUserId(String creatorUserId) {
this.creatorUserId = creatorUserId;
}

public GeographyDataset withCreatorUserId(String creatorUserId) {
this.creatorUserId = creatorUserId;
return this;
}

/**
* 
* @return
* The trackingSummary
*/
@JsonProperty("tracking_summary")
public TrackingSummary getTrackingSummary() {
return trackingSummary;
}

/**
* 
* @param trackingSummary
* The tracking_summary
*/
@JsonProperty("tracking_summary")
public void setTrackingSummary(TrackingSummary trackingSummary) {
this.trackingSummary = trackingSummary;
}

public GeographyDataset withTrackingSummary(TrackingSummary trackingSummary) {
this.trackingSummary = trackingSummary;
return this;
}

/**
* 
* @return
* The metadataModified
*/
@JsonProperty("metadata_modified")
public String getMetadataModified() {
return metadataModified;
}

/**
* 
* @param metadataModified
* The metadata_modified
*/
@JsonProperty("metadata_modified")
public void setMetadataModified(String metadataModified) {
this.metadataModified = metadataModified;
}

public GeographyDataset withMetadataModified(String metadataModified) {
this.metadataModified = metadataModified;
return this;
}

/**
* 
* @return
* The author
*/
@JsonProperty("author")
public Object getAuthor() {
return author;
}

/**
* 
* @param author
* The author
*/
@JsonProperty("author")
public void setAuthor(Object author) {
this.author = author;
}

public GeographyDataset withAuthor(Object author) {
this.author = author;
return this;
}

/**
* 
* @return
* The relationshipsAsSubject
*/
@JsonProperty("relationships_as_subject")
public List<Object> getRelationshipsAsSubject() {
return relationshipsAsSubject;
}

/**
* 
* @param relationshipsAsSubject
* The relationships_as_subject
*/
@JsonProperty("relationships_as_subject")
public void setRelationshipsAsSubject(List<Object> relationshipsAsSubject) {
this.relationshipsAsSubject = relationshipsAsSubject;
}

public GeographyDataset withRelationshipsAsSubject(List<Object> relationshipsAsSubject) {
this.relationshipsAsSubject = relationshipsAsSubject;
return this;
}

/**
* 
* @return
* The metadataCreated
*/
@JsonProperty("metadata_created")
public String getMetadataCreated() {
return metadataCreated;
}

/**
* 
* @param metadataCreated
* The metadata_created
*/
@JsonProperty("metadata_created")
public void setMetadataCreated(String metadataCreated) {
this.metadataCreated = metadataCreated;
}

public GeographyDataset withMetadataCreated(String metadataCreated) {
this.metadataCreated = metadataCreated;
return this;
}

/**
* 
* @return
* The resources
*/
@JsonProperty("resources")
public List<Resource> getResources() {
return resources;
}

/**
* 
* @param resources
* The resources
*/
@JsonProperty("resources")
public void setResources(List<Resource> resources) {
this.resources = resources;
}

public GeographyDataset withResources(List<Resource> resources) {
this.resources = resources;
return this;
}

/**
* 
* @return
* The groups
*/
@JsonProperty("groups")
public List<Object> getGroups() {
return groups;
}

/**
* 
* @param groups
* The groups
*/
@JsonProperty("groups")
public void setGroups(List<Object> groups) {
this.groups = groups;
}

public GeographyDataset withGroups(List<Object> groups) {
this.groups = groups;
return this;
}

/**
* 
* @return
* The version
*/
@JsonProperty("version")
public Object getVersion() {
return version;
}

/**
* 
* @param version
* The version
*/
@JsonProperty("version")
public void setVersion(Object version) {
this.version = version;
}

public GeographyDataset withVersion(Object version) {
this.version = version;
return this;
}

/**
* 
* @return
* The url
*/
@JsonProperty("url")
public Object getUrl() {
return url;
}

/**
* 
* @param url
* The url
*/
@JsonProperty("url")
public void setUrl(Object url) {
this.url = url;
}

public GeographyDataset withUrl(Object url) {
this.url = url;
return this;
}

/**
* 
* @return
* The tags
*/
@JsonProperty("tags")
public List<Tag> getTags() {
return tags;
}

/**
* 
* @param tags
* The tags
*/
@JsonProperty("tags")
public void setTags(List<Tag> tags) {
this.tags = tags;
}

public GeographyDataset withTags(List<Tag> tags) {
this.tags = tags;
return this;
}

/**
* 
* @return
* The licenseId
*/
@JsonProperty("license_id")
public Object getLicenseId() {
return licenseId;
}

/**
* 
* @param licenseId
* The license_id
*/
@JsonProperty("license_id")
public void setLicenseId(Object licenseId) {
this.licenseId = licenseId;
}

public GeographyDataset withLicenseId(Object licenseId) {
this.licenseId = licenseId;
return this;
}

/**
* 
* @return
* The organization
*/
@JsonProperty("organization")
public Object getOrganization() {
return organization;
}

/**
* 
* @param organization
* The organization
*/
@JsonProperty("organization")
public void setOrganization(Object organization) {
this.organization = organization;
}

public GeographyDataset withOrganization(Object organization) {
this.organization = organization;
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

public GeographyDataset withName(String name) {
this.name = name;
return this;
}

/**
* 
* @return
* The licenseTitle
*/
@JsonProperty("license_title")
public Object getLicenseTitle() {
return licenseTitle;
}

/**
* 
* @param licenseTitle
* The license_title
*/
@JsonProperty("license_title")
public void setLicenseTitle(Object licenseTitle) {
this.licenseTitle = licenseTitle;
}

public GeographyDataset withLicenseTitle(Object licenseTitle) {
this.licenseTitle = licenseTitle;
return this;
}

/**
* 
* @return
* The maintainerEmail
*/
@JsonProperty("maintainer_email")
public Object getMaintainerEmail() {
return maintainerEmail;
}

/**
* 
* @param maintainerEmail
* The maintainer_email
*/
@JsonProperty("maintainer_email")
public void setMaintainerEmail(Object maintainerEmail) {
this.maintainerEmail = maintainerEmail;
}

public GeographyDataset withMaintainerEmail(Object maintainerEmail) {
this.maintainerEmail = maintainerEmail;
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

public GeographyDataset withAdditionalProperty(String name, Object value) {
this.additionalProperties.put(name, value);
return this;
}

@Override
public int hashCode() {
return new HashCodeBuilder().append(isopen).append(_private).append(notes).append(numResources).append(extras).append(ownerOrg).append(type).append(title).append(maintainer).append(revisionTimestamp).append(authorEmail).append(numTags).append(id).append(state).append(relationshipsAsObject).append(revisionId).append(creatorUserId).append(trackingSummary).append(metadataModified).append(author).append(relationshipsAsSubject).append(metadataCreated).append(resources).append(groups).append(version).append(url).append(tags).append(licenseId).append(organization).append(name).append(licenseTitle).append(maintainerEmail).append(additionalProperties).toHashCode();
}

@Override
public boolean equals(Object other) {
if (other == this) {
return true;
}
if ((other instanceof GeographyDataset) == false) {
return false;
}
GeographyDataset rhs = ((GeographyDataset) other);
return new EqualsBuilder().append(isopen, rhs.isopen).append(_private, rhs._private).append(notes, rhs.notes).append(numResources, rhs.numResources).append(extras, rhs.extras).append(ownerOrg, rhs.ownerOrg).append(type, rhs.type).append(title, rhs.title).append(maintainer, rhs.maintainer).append(revisionTimestamp, rhs.revisionTimestamp).append(authorEmail, rhs.authorEmail).append(numTags, rhs.numTags).append(id, rhs.id).append(state, rhs.state).append(relationshipsAsObject, rhs.relationshipsAsObject).append(revisionId, rhs.revisionId).append(creatorUserId, rhs.creatorUserId).append(trackingSummary, rhs.trackingSummary).append(metadataModified, rhs.metadataModified).append(author, rhs.author).append(relationshipsAsSubject, rhs.relationshipsAsSubject).append(metadataCreated, rhs.metadataCreated).append(resources, rhs.resources).append(groups, rhs.groups).append(version, rhs.version).append(url, rhs.url).append(tags, rhs.tags).append(licenseId, rhs.licenseId).append(organization, rhs.organization).append(name, rhs.name).append(licenseTitle, rhs.licenseTitle).append(maintainerEmail, rhs.maintainerEmail).append(additionalProperties, rhs.additionalProperties).isEquals();
}

}