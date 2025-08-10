package org.example.routerlocation.model;

import java.util.List;

public class DbResponse {
    private List<Location> locations;
    private List<Router> routers;

    public List<Location> getLocations() { return locations; }
    public void setLocations(List<Location> locations) { this.locations = locations; }

    public List<Router> getRouters() { return routers; }
    public void setRouters(List<Router> routers) { this.routers = routers; }
}
