import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
public class USGS_API_Wrapper {
    private static final String BASE_URL = "https://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/";

    public String getAllEarthquakes(Character timeframe) throws Exception {
        String targetFile = null;

        switch (timeframe) {
            case 'h':
                targetFile = "all_hour.geojson";
                break;

            case 'd':
                targetFile = "all_day.geojson";
                break;

            case 'w':
                targetFile = "all_week.geojson";
                break;

            case 'm':
                targetFile = "all_month.geojson";
                break;

        }

        String endpoint = BASE_URL + targetFile;
        return makeRequest(endpoint);
    }

    private String makeRequest(String endpoint) {
        HttpURLConnection connection = null;
        BufferedReader reader = null;

        try {
            URL url = new URL(endpoint);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(10000);
            connection.setReadTimeout(10000);

            int responseCode = connection.getResponseCode();
            if (responseCode != 200) {
                return null;
            }

            reader = new BufferedReader(
                    new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8)
            );

            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }

            System.out.println("Successfully retrieved data.");

            return response.toString();

        } catch (Exception e) {
            return null;
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
                if (connection != null) {
                    connection.disconnect();
                }
            } catch (Exception e) {

            }
        }
    }
}
