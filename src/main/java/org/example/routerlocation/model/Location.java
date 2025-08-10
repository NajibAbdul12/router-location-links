package org.example.routerlocation.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Location {
    private Integer id;
    private String name;

    // Add postcode field as JSON contains it
    private String postcode;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getPostcode() { return postcode; }
    public void setPostcode(String postcode) { this.postcode = postcode; }
}
