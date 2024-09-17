
package com.cydeo.dto;

import java.util.LinkedHashMap;
import java.util.Map;
import javax.annotation.Generated;

import com.cydeo.dto.Height;
import com.cydeo.dto.Weight;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "weight",
    "height",
    "id",
    "name",
    "bred_for",
    "breed_group",
    "life_span",
    "temperament",
    "origin",
    "reference_image_id",
    "country_code",
    "description",
    "history"
})
@Generated("jsonschema2pojo")
public class DogDTO {

    @JsonProperty("weight")
    private Weight weight;
    @JsonProperty("height")
    private Height height;
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("bred_for")
    private String bredFor;
    @JsonProperty("breed_group")
    private String breedGroup;
    @JsonProperty("life_span")
    private String lifeSpan;
    @JsonProperty("temperament")
    private String temperament;
    @JsonProperty("origin")
    private String origin;
    @JsonProperty("reference_image_id")
    private String referenceImageId;
    @JsonProperty("country_code")
    private String countryCode;
    @JsonProperty("description")
    private String description;
    @JsonProperty("history")
    private String history;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("weight")
    public Weight getWeight() {
        return weight;
    }

    @JsonProperty("weight")
    public void setWeight(Weight weight) {
        this.weight = weight;
    }

    @JsonProperty("height")
    public Height getHeight() {
        return height;
    }

    @JsonProperty("height")
    public void setHeight(Height height) {
        this.height = height;
    }

    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Integer id) {
        this.id = id;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("bred_for")
    public String getBredFor() {
        return bredFor;
    }

    @JsonProperty("bred_for")
    public void setBredFor(String bredFor) {
        this.bredFor = bredFor;
    }

    @JsonProperty("breed_group")
    public String getBreedGroup() {
        return breedGroup;
    }

    @JsonProperty("breed_group")
    public void setBreedGroup(String breedGroup) {
        this.breedGroup = breedGroup;
    }

    @JsonProperty("life_span")
    public String getLifeSpan() {
        return lifeSpan;
    }

    @JsonProperty("life_span")
    public void setLifeSpan(String lifeSpan) {
        this.lifeSpan = lifeSpan;
    }

    @JsonProperty("temperament")
    public String getTemperament() {
        return temperament;
    }

    @JsonProperty("temperament")
    public void setTemperament(String temperament) {
        this.temperament = temperament;
    }

    @JsonProperty("origin")
    public String getOrigin() {
        return origin;
    }

    @JsonProperty("origin")
    public void setOrigin(String origin) {
        this.origin = origin;
    }

    @JsonProperty("reference_image_id")
    public String getReferenceImageId() {
        return referenceImageId;
    }

    @JsonProperty("reference_image_id")
    public void setReferenceImageId(String referenceImageId) {
        this.referenceImageId = referenceImageId;
    }

    @JsonProperty("country_code")
    public String getCountryCode() {
        return countryCode;
    }

    @JsonProperty("country_code")
    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    @JsonProperty("description")
    public void setDescription(String description) {
        this.description = description;
    }

    @JsonProperty("history")
    public String getHistory() {
        return history;
    }

    @JsonProperty("history")
    public void setHistory(String history) {
        this.history = history;
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
