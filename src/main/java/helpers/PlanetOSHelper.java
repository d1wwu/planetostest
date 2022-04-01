package helpers;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import core.TestManager;
import org.testng.Reporter;

public class PlanetOSHelper {

    private final TestManager manager;

    public PlanetOSHelper() {
        manager = TestManager.getActualInstance();
    }

    public HttpResponse<JsonNode> getDataset() {
        String url = "http://api.planetos.com/v1/datasets/copernicus_goba_global_weekly?apikey=" + manager.getApikey();
        HttpResponse<JsonNode> response;
        try {
            response = Unirest
                    .get(url)
                    .header("Content-Type", "application/json")
                    .asJson();
        } catch (UnirestException e) {
            Reporter.log(e.getMessage());
            throw new Error(e.getMessage());
        }
        return response;
    }
}
