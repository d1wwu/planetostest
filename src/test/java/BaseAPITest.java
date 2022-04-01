import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import helpers.PlanetOSHelper;
import org.json.JSONObject;
import org.testng.annotations.Test;

import static core.TestManager.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;

public class BaseAPITest {

    @Test
    public void apiTest() {
        assertDataset("Title", "Global Ocean Biogeochemical Analysis and Forecast");

    }

    private void assertDataset(String field, String value) {
        HttpResponse<JsonNode> response = new PlanetOSHelper().getDataset();
        JSONObject json = response.getBody().getObject();
        assertThat("Response contains '" + field + "' field", json.toString(), containsString(field));
        assertThat("Field '" + field + "' contains value '" + value + "'", json.get(field).toString(), is(value));
    }
}
