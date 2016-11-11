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
"resource_group_id",
"description",
"cache_last_updated",
"resource_locator_protocol",
"md_resource",
"revision_timestamp",
"id",
"state",
"last_modified",
"mimetype_inner",
"revision_id",
"tracking_summary",
"url_type",
"created",
"format",
"resource_type",
"url",
"resource_locator_function",
"size",
"cache_url",
"webstore_last_updated",
"webstore_url",
"name",
"mimetype",
"position",
"hash"
})
public class Resource {

@JsonProperty("resource_group_id")
private String resourceGroupId;
@JsonProperty("description")
private String description;
@JsonProperty("cache_last_updated")
private Object cacheLastUpdated;
@JsonProperty("resource_locator_protocol")
private String resourceLocatorProtocol;
@JsonProperty("md_resource")
private String mdResource;
@JsonProperty("revision_timestamp")
private String revisionTimestamp;
@JsonProperty("id")
private String id;
@JsonProperty("state")
private String state;
@JsonProperty("last_modified")
private Object lastModified;
@JsonProperty("mimetype_inner")
private Object mimetypeInner;
@JsonProperty("revision_id")
private String revisionId;
@JsonProperty("tracking_summary")
private TrackingSummary_ trackingSummary;
@JsonProperty("url_type")
private Object urlType;
@JsonProperty("created")
private String created;
@JsonProperty("format")
private String format;
@JsonProperty("resource_type")
private Object resourceType;
@JsonProperty("url")
private String url;
@JsonProperty("resource_locator_function")
private String resourceLocatorFunction;
@JsonProperty("size")
private Object size;
@JsonProperty("cache_url")
private Object cacheUrl;
@JsonProperty("webstore_last_updated")
private Object webstoreLastUpdated;
@JsonProperty("webstore_url")
private Object webstoreUrl;
@JsonProperty("name")
private String name;
@JsonProperty("mimetype")
private Object mimetype;
@JsonProperty("position")
private Integer position;
@JsonProperty("hash")
private String hash;
@JsonIgnore
private Map<String, Object> additionalProperties = new HashMap<String, Object>();

/**
* No args constructor for use in serialization
* 
*/
public Resource() {
}

/**
* 
* @param position
* @param lastModified
* @param hash
* @param state
* @param cacheUrl
* @param format
* @param resourceLocatorProtocol
* @param url
* @param urlType
* @param revisionTimestamp
* @param revisionId
* @param size
* @param resourceType
* @param id
* @param mimetypeInner
* @param cacheLastUpdated
* @param mimetype
* @param trackingSummary
* @param created
* @param description
* @param mdResource
* @param name
* @param webstoreUrl
* @param resourceLocatorFunction
* @param resourceGroupId
* @param webstoreLastUpdated
*/
public Resource(String resourceGroupId, String description, Object cacheLastUpdated, String resourceLocatorProtocol, String mdResource, String revisionTimestamp, String id, String state, Object lastModified, Object mimetypeInner, String revisionId, TrackingSummary_ trackingSummary, Object urlType, String created, String format, Object resourceType, String url, String resourceLocatorFunction, Object size, Object cacheUrl, Object webstoreLastUpdated, Object webstoreUrl, String name, Object mimetype, Integer position, String hash) {
this.resourceGroupId = resourceGroupId;
this.description = description;
this.cacheLastUpdated = cacheLastUpdated;
this.resourceLocatorProtocol = resourceLocatorProtocol;
this.mdResource = mdResource;
this.revisionTimestamp = revisionTimestamp;
this.id = id;
this.state = state;
this.lastModified = lastModified;
this.mimetypeInner = mimetypeInner;
this.revisionId = revisionId;
this.trackingSummary = trackingSummary;
this.urlType = urlType;
this.created = created;
this.format = format;
this.resourceType = resourceType;
this.url = url;
this.resourceLocatorFunction = resourceLocatorFunction;
this.size = size;
this.cacheUrl = cacheUrl;
this.webstoreLastUpdated = webstoreLastUpdated;
this.webstoreUrl = webstoreUrl;
this.name = name;
this.mimetype = mimetype;
this.position = position;
this.hash = hash;
}

/**
* 
* @return
* The resourceGroupId
*/
@JsonProperty("resource_group_id")
public String getResourceGroupId() {
return resourceGroupId;
}

/**
* 
* @param resourceGroupId
* The resource_group_id
*/
@JsonProperty("resource_group_id")
public void setResourceGroupId(String resourceGroupId) {
this.resourceGroupId = resourceGroupId;
}

public Resource withResourceGroupId(String resourceGroupId) {
this.resourceGroupId = resourceGroupId;
return this;
}

/**
* 
* @return
* The description
*/
@JsonProperty("description")
public String getDescription() {
return description;
}

/**
* 
* @param description
* The description
*/
@JsonProperty("description")
public void setDescription(String description) {
this.description = description;
}

public Resource withDescription(String description) {
this.description = description;
return this;
}

/**
* 
* @return
* The cacheLastUpdated
*/
@JsonProperty("cache_last_updated")
public Object getCacheLastUpdated() {
return cacheLastUpdated;
}

/**
* 
* @param cacheLastUpdated
* The cache_last_updated
*/
@JsonProperty("cache_last_updated")
public void setCacheLastUpdated(Object cacheLastUpdated) {
this.cacheLastUpdated = cacheLastUpdated;
}

public Resource withCacheLastUpdated(Object cacheLastUpdated) {
this.cacheLastUpdated = cacheLastUpdated;
return this;
}

/**
* 
* @return
* The resourceLocatorProtocol
*/
@JsonProperty("resource_locator_protocol")
public String getResourceLocatorProtocol() {
return resourceLocatorProtocol;
}

/**
* 
* @param resourceLocatorProtocol
* The resource_locator_protocol
*/
@JsonProperty("resource_locator_protocol")
public void setResourceLocatorProtocol(String resourceLocatorProtocol) {
this.resourceLocatorProtocol = resourceLocatorProtocol;
}

public Resource withResourceLocatorProtocol(String resourceLocatorProtocol) {
this.resourceLocatorProtocol = resourceLocatorProtocol;
return this;
}

/**
* 
* @return
* The mdResource
*/
@JsonProperty("md_resource")
public String getMdResource() {
return mdResource;
}

/**
* 
* @param mdResource
* The md_resource
*/
@JsonProperty("md_resource")
public void setMdResource(String mdResource) {
this.mdResource = mdResource;
}

public Resource withMdResource(String mdResource) {
this.mdResource = mdResource;
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

public Resource withRevisionTimestamp(String revisionTimestamp) {
this.revisionTimestamp = revisionTimestamp;
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

public Resource withId(String id) {
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

public Resource withState(String state) {
this.state = state;
return this;
}

/**
* 
* @return
* The lastModified
*/
@JsonProperty("last_modified")
public Object getLastModified() {
return lastModified;
}

/**
* 
* @param lastModified
* The last_modified
*/
@JsonProperty("last_modified")
public void setLastModified(Object lastModified) {
this.lastModified = lastModified;
}

public Resource withLastModified(Object lastModified) {
this.lastModified = lastModified;
return this;
}

/**
* 
* @return
* The mimetypeInner
*/
@JsonProperty("mimetype_inner")
public Object getMimetypeInner() {
return mimetypeInner;
}

/**
* 
* @param mimetypeInner
* The mimetype_inner
*/
@JsonProperty("mimetype_inner")
public void setMimetypeInner(Object mimetypeInner) {
this.mimetypeInner = mimetypeInner;
}

public Resource withMimetypeInner(Object mimetypeInner) {
this.mimetypeInner = mimetypeInner;
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

public Resource withRevisionId(String revisionId) {
this.revisionId = revisionId;
return this;
}

/**
* 
* @return
* The trackingSummary
*/
@JsonProperty("tracking_summary")
public TrackingSummary_ getTrackingSummary() {
return trackingSummary;
}

/**
* 
* @param trackingSummary
* The tracking_summary
*/
@JsonProperty("tracking_summary")
public void setTrackingSummary(TrackingSummary_ trackingSummary) {
this.trackingSummary = trackingSummary;
}

public Resource withTrackingSummary(TrackingSummary_ trackingSummary) {
this.trackingSummary = trackingSummary;
return this;
}

/**
* 
* @return
* The urlType
*/
@JsonProperty("url_type")
public Object getUrlType() {
return urlType;
}

/**
* 
* @param urlType
* The url_type
*/
@JsonProperty("url_type")
public void setUrlType(Object urlType) {
this.urlType = urlType;
}

public Resource withUrlType(Object urlType) {
this.urlType = urlType;
return this;
}

/**
* 
* @return
* The created
*/
@JsonProperty("created")
public String getCreated() {
return created;
}

/**
* 
* @param created
* The created
*/
@JsonProperty("created")
public void setCreated(String created) {
this.created = created;
}

public Resource withCreated(String created) {
this.created = created;
return this;
}

/**
* 
* @return
* The format
*/
@JsonProperty("format")
public String getFormat() {
return format;
}

/**
* 
* @param format
* The format
*/
@JsonProperty("format")
public void setFormat(String format) {
this.format = format;
}

public Resource withFormat(String format) {
this.format = format;
return this;
}

/**
* 
* @return
* The resourceType
*/
@JsonProperty("resource_type")
public Object getResourceType() {
return resourceType;
}

/**
* 
* @param resourceType
* The resource_type
*/
@JsonProperty("resource_type")
public void setResourceType(Object resourceType) {
this.resourceType = resourceType;
}

public Resource withResourceType(Object resourceType) {
this.resourceType = resourceType;
return this;
}

/**
* 
* @return
* The url
*/
@JsonProperty("url")
public String getUrl() {
return url;
}

/**
* 
* @param url
* The url
*/
@JsonProperty("url")
public void setUrl(String url) {
this.url = url;
}

public Resource withUrl(String url) {
this.url = url;
return this;
}

/**
* 
* @return
* The resourceLocatorFunction
*/
@JsonProperty("resource_locator_function")
public String getResourceLocatorFunction() {
return resourceLocatorFunction;
}

/**
* 
* @param resourceLocatorFunction
* The resource_locator_function
*/
@JsonProperty("resource_locator_function")
public void setResourceLocatorFunction(String resourceLocatorFunction) {
this.resourceLocatorFunction = resourceLocatorFunction;
}

public Resource withResourceLocatorFunction(String resourceLocatorFunction) {
this.resourceLocatorFunction = resourceLocatorFunction;
return this;
}

/**
* 
* @return
* The size
*/
@JsonProperty("size")
public Object getSize() {
return size;
}

/**
* 
* @param size
* The size
*/
@JsonProperty("size")
public void setSize(Object size) {
this.size = size;
}

public Resource withSize(Object size) {
this.size = size;
return this;
}

/**
* 
* @return
* The cacheUrl
*/
@JsonProperty("cache_url")
public Object getCacheUrl() {
return cacheUrl;
}

/**
* 
* @param cacheUrl
* The cache_url
*/
@JsonProperty("cache_url")
public void setCacheUrl(Object cacheUrl) {
this.cacheUrl = cacheUrl;
}

public Resource withCacheUrl(Object cacheUrl) {
this.cacheUrl = cacheUrl;
return this;
}

/**
* 
* @return
* The webstoreLastUpdated
*/
@JsonProperty("webstore_last_updated")
public Object getWebstoreLastUpdated() {
return webstoreLastUpdated;
}

/**
* 
* @param webstoreLastUpdated
* The webstore_last_updated
*/
@JsonProperty("webstore_last_updated")
public void setWebstoreLastUpdated(Object webstoreLastUpdated) {
this.webstoreLastUpdated = webstoreLastUpdated;
}

public Resource withWebstoreLastUpdated(Object webstoreLastUpdated) {
this.webstoreLastUpdated = webstoreLastUpdated;
return this;
}

/**
* 
* @return
* The webstoreUrl
*/
@JsonProperty("webstore_url")
public Object getWebstoreUrl() {
return webstoreUrl;
}

/**
* 
* @param webstoreUrl
* The webstore_url
*/
@JsonProperty("webstore_url")
public void setWebstoreUrl(Object webstoreUrl) {
this.webstoreUrl = webstoreUrl;
}

public Resource withWebstoreUrl(Object webstoreUrl) {
this.webstoreUrl = webstoreUrl;
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

public Resource withName(String name) {
this.name = name;
return this;
}

/**
* 
* @return
* The mimetype
*/
@JsonProperty("mimetype")
public Object getMimetype() {
return mimetype;
}

/**
* 
* @param mimetype
* The mimetype
*/
@JsonProperty("mimetype")
public void setMimetype(Object mimetype) {
this.mimetype = mimetype;
}

public Resource withMimetype(Object mimetype) {
this.mimetype = mimetype;
return this;
}

/**
* 
* @return
* The position
*/
@JsonProperty("position")
public Integer getPosition() {
return position;
}

/**
* 
* @param position
* The position
*/
@JsonProperty("position")
public void setPosition(Integer position) {
this.position = position;
}

public Resource withPosition(Integer position) {
this.position = position;
return this;
}

/**
* 
* @return
* The hash
*/
@JsonProperty("hash")
public String getHash() {
return hash;
}

/**
* 
* @param hash
* The hash
*/
@JsonProperty("hash")
public void setHash(String hash) {
this.hash = hash;
}

public Resource withHash(String hash) {
this.hash = hash;
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

public Resource withAdditionalProperty(String name, Object value) {
this.additionalProperties.put(name, value);
return this;
}

@Override
public int hashCode() {
return new HashCodeBuilder().append(resourceGroupId).append(description).append(cacheLastUpdated).append(resourceLocatorProtocol).append(mdResource).append(revisionTimestamp).append(id).append(state).append(lastModified).append(mimetypeInner).append(revisionId).append(trackingSummary).append(urlType).append(created).append(format).append(resourceType).append(url).append(resourceLocatorFunction).append(size).append(cacheUrl).append(webstoreLastUpdated).append(webstoreUrl).append(name).append(mimetype).append(position).append(hash).append(additionalProperties).toHashCode();
}

@Override
public boolean equals(Object other) {
if (other == this) {
return true;
}
if ((other instanceof Resource) == false) {
return false;
}
Resource rhs = ((Resource) other);
return new EqualsBuilder().append(resourceGroupId, rhs.resourceGroupId).append(description, rhs.description).append(cacheLastUpdated, rhs.cacheLastUpdated).append(resourceLocatorProtocol, rhs.resourceLocatorProtocol).append(mdResource, rhs.mdResource).append(revisionTimestamp, rhs.revisionTimestamp).append(id, rhs.id).append(state, rhs.state).append(lastModified, rhs.lastModified).append(mimetypeInner, rhs.mimetypeInner).append(revisionId, rhs.revisionId).append(trackingSummary, rhs.trackingSummary).append(urlType, rhs.urlType).append(created, rhs.created).append(format, rhs.format).append(resourceType, rhs.resourceType).append(url, rhs.url).append(resourceLocatorFunction, rhs.resourceLocatorFunction).append(size, rhs.size).append(cacheUrl, rhs.cacheUrl).append(webstoreLastUpdated, rhs.webstoreLastUpdated).append(webstoreUrl, rhs.webstoreUrl).append(name, rhs.name).append(mimetype, rhs.mimetype).append(position, rhs.position).append(hash, rhs.hash).append(additionalProperties, rhs.additionalProperties).isEquals();
}

}
