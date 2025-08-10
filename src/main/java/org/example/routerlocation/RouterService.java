package org.example.routerlocation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.example.routerlocation.model.Location;
import org.example.routerlocation.model.Router;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import java.util.*;
import java.util.stream.Collectors;

public class RouterService {

    private static final String API_URL = "https://my-json-server.typicode.com/marcuzh/router_location_test_api/db";

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class ApiResponse {
        public List<Location> locations;
        public List<Router> routers;

        public List<Router> getRouters() {
            return routers;
        }

        public List<Location> getLocations() {
            return locations;
        }

        // setters if needed
        public void setRouters(List<Router> routers) {
            this.routers = routers;
        }

        public void setLocations(List<Location> locations) {
            this.locations = locations;
        }
    }

    public static void main(String[] args) throws Exception {
        ApiResponse data = fetchApiData();

        Map<Integer, String> locationIdToName = data.locations.stream()
                .collect(Collectors.toMap(Location::getId, Location::getName));

        Map<Integer, Integer> routerIdToLocationId = data.routers.stream()
                .collect(Collectors.toMap(Router::getId, Router::getLocationId));

        Set<String> locationConnections = new HashSet<>();

        for (Router router : data.routers) {
            Integer routerLocId = router.getLocationId();

            if (router.getLinks() == null) continue;

            for (Integer linkedRouterId : router.getLinks()) {
                Integer linkedLocId = routerIdToLocationId.get(linkedRouterId);
                if (linkedLocId == null) continue;

                if (routerLocId.equals(linkedLocId)) continue;

                String locA = locationIdToName.get(routerLocId);
                String locB = locationIdToName.get(linkedLocId);

                if (locA == null || locB == null) continue;

                String connection = locA.compareTo(locB) < 0
                        ? locA + " <-> " + locB
                        : locB + " <-> " + locA;

                locationConnections.add(connection);
            }
        }

        locationConnections.forEach(System.out::println);
    }

    public static ApiResponse fetchApiData() throws Exception {
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(API_URL))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 200) {
            throw new RuntimeException("Failed to fetch API data: HTTP " + response.statusCode());
        }

        String responseBody = response.body();

        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(responseBody, ApiResponse.class);
    }
}
