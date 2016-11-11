/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udistrital.ingesoft.geografia.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import co.edu.udistrital.ingesoft.geografia.structure.GeographyDataset;
import co.edu.udistrital.ingesoft.geografia.structure.Resource;
import co.edu.udistrital.ingesoft.geografia.structure.Tag;

/**
 *
 * @author Normalito
 */
public class GeographyMapper {
    public static List<GeographyDataset> map(String jsonInput){
        return getGeographyDatasetObject(jsonInput);
    }
    
    private static List<GeographyDataset> getGeographyDatasetObject(String jsonInput){
        List<GeographyDataset> listGeographyDataset= new ArrayList<>();
        try {
            // "I want to iterate though the objects in the array..."
            JSONObject outerObject = new JSONObject(jsonInput);
            JSONObject innerObject = outerObject.getJSONObject("result");

            int contador = innerObject.getInt("count");
            System.out.println("El conteo es:"+contador);

            JSONArray jsonArray = innerObject.getJSONArray("results");
            for (int i = 0, size = jsonArray.length(); i < size; i++)
            {
                JSONObject objectInArray = jsonArray.getJSONObject(i);
                listGeographyDataset.add(buildGeographyObject(objectInArray));
            }
            return listGeographyDataset;
        } catch (JSONException ex) {
            Logger.getLogger(GeographyMapper.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    private static GeographyDataset buildGeographyObject(JSONObject geographyJSONObject){
        try {
            //Map the JSON Attribues to variables
            boolean isOpen= geographyJSONObject.isNull("isopen") ? null : geographyJSONObject.getBoolean("isopen");
            boolean isPrivate= geographyJSONObject.isNull("private") ? null : geographyJSONObject.getBoolean("private");
            String notes= geographyJSONObject.isNull("notes") ? null : geographyJSONObject.getString("notes");
            int num_resources= geographyJSONObject.isNull("num_resources") ? null : geographyJSONObject.getInt("num_resources");
            String owner_org= geographyJSONObject.isNull("owner_org") ? null : geographyJSONObject.getString("owner_org");
            String type= geographyJSONObject.isNull("type") ? null : geographyJSONObject.getString("type");
            String title= geographyJSONObject.isNull("title") ? null : geographyJSONObject.getString("title");
            String maintainer= geographyJSONObject.isNull("maintainer") ? null : geographyJSONObject.getString("maintainer");
            String revision_timestamp= geographyJSONObject.isNull("revision_timestamp") ? null : geographyJSONObject.getString("revision_timestamp");
            String author_email= geographyJSONObject.isNull("author_email") ? null : geographyJSONObject.getString("author_email");
            int num_tags= geographyJSONObject.isNull("num_tags") ? null : geographyJSONObject.getInt("num_tags");
            String id= geographyJSONObject.isNull("id") ? null : geographyJSONObject.getString("id");
            String state= geographyJSONObject.isNull("state") ? null : geographyJSONObject.getString("state");
            String revision_id= geographyJSONObject.isNull("revision_id") ? null : geographyJSONObject.getString("revision_id");
            String creator_user_id= geographyJSONObject.isNull("creator_user_id") ? null : geographyJSONObject.getString("creator_user_id");
            String metadata_modified= geographyJSONObject.isNull("metadata_modified") ? null : geographyJSONObject.getString("metadata_modified");
            String author= geographyJSONObject.isNull("author") ? null : geographyJSONObject.getString("author");
            String metadata_created= geographyJSONObject.isNull("metadata_created") ? null : geographyJSONObject.getString("metadata_created");


            List<Resource> resources=getResourcesList(geographyJSONObject.getJSONArray("resources"));

            String version= geographyJSONObject.isNull("version") ? null : geographyJSONObject.getString("version");
            String url= geographyJSONObject.isNull("url") ? null : geographyJSONObject.getString("url");

            List<Tag> tag=getTagList(geographyJSONObject.getJSONArray("tags"));

            String license_id= geographyJSONObject.isNull("license_id") ? null : geographyJSONObject.getString("license_id");
            String organization= geographyJSONObject.isNull("organization") ? null : geographyJSONObject.getString("organization");
            String name= geographyJSONObject.isNull("name") ? null : geographyJSONObject.getString("name");
            String license_title= geographyJSONObject.isNull("license_title") ? null : geographyJSONObject.getString("license_title");
            String maintainer_email= geographyJSONObject.isNull("maintainer_email") ? null : geographyJSONObject.getString("maintainer_email");

            return new GeographyDataset(isOpen, isPrivate, notes, num_resources, null, owner_org, type, title, maintainer, revision_timestamp, author_email, num_tags, id, state, null, revision_id, creator_user_id, null, metadata_modified, author, null, metadata_created, resources, null, version, url, tag, license_id, organization, name, license_title, maintainer_email);
        } catch (JSONException ex) {
            Logger.getLogger(GeographyMapper.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    private static List<Resource> getResourcesList(JSONArray jsonResources){
        List<Resource> listResources = new ArrayList<>();
        for (int i = 0, arraySize = jsonResources.length(); i < arraySize; i++)
        {
            try {
                JSONObject resourceObject = jsonResources.getJSONObject(i);
                String resource_group_id = resourceObject.isNull("resource_group_id") ? null : resourceObject.getString("resource_group_id");
                String description = resourceObject.isNull("description") ? null : resourceObject.getString("description");
                String cache_last_updated = resourceObject.isNull("cache_last_updated") ? null : resourceObject.getString("cache_last_updated");
                String resource_locator_protocol = resourceObject.isNull("resource_locator_protocol") ? null : resourceObject.getString("resource_locator_protocol");
                String md_resource = resourceObject.isNull("md_resource") ? null : resourceObject.getString("md_resource");
                String revision_timestamp = resourceObject.isNull("revision_timestamp") ? null : resourceObject.getString("revision_timestamp");
                String id = resourceObject.isNull("id") ? null : resourceObject.getString("id");
                String state = resourceObject.isNull("state") ? null : resourceObject.getString("state");
                String last_modified = resourceObject.isNull("last_modified") ? null : resourceObject.getString("last_modified");
                String mimetype_inner = resourceObject.isNull("mimetype_inner") ? null : resourceObject.getString("mimetype_inner");
                String revision_id = resourceObject.isNull("revision_id") ? null : resourceObject.getString("revision_id");
                String url_type = resourceObject.isNull("url_type") ? null : resourceObject.getString("url_type");
                String created = resourceObject.isNull("created") ? null : resourceObject.getString("created");
                String format = resourceObject.isNull("format") ? null : resourceObject.getString("format");
                String resource_type = resourceObject.isNull("resource_type") ? null : resourceObject.getString("resource_type");
                String url = resourceObject.isNull("url") ? null : resourceObject.getString("url");
                String resource_locator_function = resourceObject.isNull("resource_locator_function") ? null : resourceObject.getString("resource_locator_function");
                String size = resourceObject.isNull("size") ? null : resourceObject.getString("size");
                String cache_url = resourceObject.isNull("cache_url") ? null : resourceObject.getString("cache_url");
                String webstore_last_updated = resourceObject.isNull("webstore_last_updated") ? null : resourceObject.getString("webstore_last_updated");
                String webstore_url = resourceObject.isNull("webstore_url") ? null : resourceObject.getString("webstore_url");
                String name = resourceObject.isNull("name") ? null : resourceObject.getString("name");
                String mimetype = resourceObject.isNull("mimetype") ? null : resourceObject.getString("mimetype");
                int position = resourceObject.isNull("position") ? null : resourceObject.getInt("position");
                String hash = resourceObject.isNull("position") ? null : resourceObject.getString("hash");
                Resource resource = new Resource(resource_group_id, description, cache_last_updated, resource_locator_protocol, md_resource, revision_timestamp, id, state, last_modified, mimetype_inner, revision_id, null, url_type, created, format, resource_type, url, resource_locator_function, size, cache_url, webstore_last_updated, webstore_url, name, mimetype, position, hash);
                listResources.add(resource);
            } catch (JSONException ex) {
                Logger.getLogger(GeographyMapper.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            }
        }
        return listResources;
    }
        
    private static List<Tag> getTagList(JSONArray jsonTag){
        List<Tag> listTag = new ArrayList<>();
        for (int i = 0, arraySize = jsonTag.length(); i < arraySize; i++)
        {
            try {
                JSONObject resourceObject = jsonTag.getJSONObject(i);
                String vocabulary_id = resourceObject.isNull("vocabulary_id") ? null : resourceObject.getString("vocabulary_id");
                String name = resourceObject.isNull("name") ? null : resourceObject.getString("name");
                String revision_timestamp = resourceObject.isNull("revision_timestamp") ? null : resourceObject.getString("revision_timestamp");
                String state = resourceObject.isNull("state") ? null : resourceObject.getString("state");
                String id = resourceObject.isNull("id") ? null : resourceObject.getString("id");
                String display_name = resourceObject.isNull("display_name") ? null : resourceObject.getString("display_name");

                Tag tag= new Tag(vocabulary_id, name, revision_timestamp, state, id, display_name);
                listTag.add(tag);
            } catch (JSONException ex) {
                Logger.getLogger(GeographyMapper.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            }
        }
        return listTag;
    }
}
