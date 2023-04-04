
package com.noxfl.woodchipper.schema.tokopedia.category.productcard;

import java.util.LinkedHashMap;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "id",
    "url",
    "name",
    "goldmerchant",
    "official",
    "reputation",
    "clover",
    "location",
    "__typename"
})
@Generated("jsonschema2pojo")
public class Shop {

    @JsonProperty("id")
    private long id;
    @JsonProperty("url")
    private String url;
    @JsonProperty("name")
    private String name;
    @JsonProperty("goldmerchant")
    private boolean goldmerchant;
    @JsonProperty("official")
    private boolean official;
    @JsonProperty("reputation")
    private String reputation;
    @JsonProperty("clover")
    private String clover;
    @JsonProperty("location")
    private String location;
    @JsonProperty("__typename")
    private String typename;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     * 
     */
    public Shop() {
    }

    /**
     * 
     * @param goldmerchant
     * @param name
     * @param clover
     * @param official
     * @param reputation
     * @param location
     * @param id
     * @param url
     * @param typename
     */
    public Shop(long id, String url, String name, boolean goldmerchant, boolean official, String reputation, String clover, String location, String typename) {
        super();
        this.id = id;
        this.url = url;
        this.name = name;
        this.goldmerchant = goldmerchant;
        this.official = official;
        this.reputation = reputation;
        this.clover = clover;
        this.location = location;
        this.typename = typename;
    }

    @JsonProperty("id")
    public long getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(long id) {
        this.id = id;
    }

    @JsonProperty("url")
    public String getUrl() {
        return url;
    }

    @JsonProperty("url")
    public void setUrl(String url) {
        this.url = url;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("goldmerchant")
    public boolean isGoldmerchant() {
        return goldmerchant;
    }

    @JsonProperty("goldmerchant")
    public void setGoldmerchant(boolean goldmerchant) {
        this.goldmerchant = goldmerchant;
    }

    @JsonProperty("official")
    public boolean isOfficial() {
        return official;
    }

    @JsonProperty("official")
    public void setOfficial(boolean official) {
        this.official = official;
    }

    @JsonProperty("reputation")
    public String getReputation() {
        return reputation;
    }

    @JsonProperty("reputation")
    public void setReputation(String reputation) {
        this.reputation = reputation;
    }

    @JsonProperty("clover")
    public String getClover() {
        return clover;
    }

    @JsonProperty("clover")
    public void setClover(String clover) {
        this.clover = clover;
    }

    @JsonProperty("location")
    public String getLocation() {
        return location;
    }

    @JsonProperty("location")
    public void setLocation(String location) {
        this.location = location;
    }

    @JsonProperty("__typename")
    public String getTypename() {
        return typename;
    }

    @JsonProperty("__typename")
    public void setTypename(String typename) {
        this.typename = typename;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
