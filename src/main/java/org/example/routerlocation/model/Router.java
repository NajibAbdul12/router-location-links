package org.example.routerlocation.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class Router {
    private Integer id;

    private String name;

    @JsonProperty("location_id")
    private Integer locationId;

    @JsonProperty("router_links")
    private List<Integer> links;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Integer getLocationId() { return locationId; }
    public void setLocationId(Integer locationId) { this.locationId = locationId; }

    public List<Integer> getLinks() { return links; }
    public void setLinks(List<Integer> links) { this.links = links; }
}
