import org.example.routerlocation.RouterService.ApiResponse;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.example.routerlocation.RouterService;


public class RouterServiceTest {

    @Test
    public void testFetchApiData() {
        ApiResponse response = null;
        try {
            response = RouterService.fetchApiData();
        } catch (Exception e) {
            fail("fetchApiData() threw an exception: " + e.getMessage());
        }
        assertNotNull(response, "API response should not be null");
        assertFalse(response.getRouters().isEmpty(), "Routers list should not be empty");
        assertFalse(response.getLocations().isEmpty(), "Locations list should not be empty");
    }

    @Test
    public void testRouterListContents() {
        ApiResponse response = null;
        try {
            response = RouterService.fetchApiData();
        } catch (Exception e) {
            fail("fetchApiData() threw an exception: " + e.getMessage());
        }
        assertNotNull(response.getRouters());
        assertTrue(response.getRouters().stream().allMatch(r -> r.getId() != null), "All routers should have an ID");
        assertTrue(response.getRouters().stream().allMatch(r -> r.getName() != null), "All routers should have a name");
    }

    @Test
    public void testLocationListContents() {
        ApiResponse response = null;
        try {
            response = RouterService.fetchApiData();
        } catch (Exception e) {
            fail("fetchApiData() threw an exception: " + e.getMessage());
        }
        assertNotNull(response.getLocations());
        assertTrue(response.getLocations().stream().allMatch(l -> l.getId() != null), "All locations should have an ID");
        assertTrue(response.getLocations().stream().allMatch(l -> l.getName() != null), "All locations should have a name");
    }
}
