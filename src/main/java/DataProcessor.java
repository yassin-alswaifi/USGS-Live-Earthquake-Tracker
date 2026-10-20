import org.json.*;

import java.math.BigDecimal;
import java.util.List;

public class DataProcessor {
    public void cleanData(String raw_data) {
        //Unfiltered
        JSONObject base_geoJSON = new JSONObject(raw_data);

        //Filtered
        JSONArray raw_features = base_geoJSON.getJSONArray("features");

        String id, title, type;
        BigDecimal mag, depth;
        BigDecimal[] coords = new BigDecimal[2];
        Long time;
        int data_len = raw_features.length();

        System.out.println("Found " + data_len + " record(s).");

        for (int i = 0; i < data_len; i++) {
            id = raw_features.getJSONObject(i).getString("id");
            title = raw_features.getJSONObject(i).getJSONObject("properties").getString("title");
            type = raw_features.getJSONObject(i).getJSONObject("properties").getString("type");
            mag = raw_features.getJSONObject(i).getJSONObject("properties").getBigDecimal("mag");
            coords[0] = raw_features.getJSONObject(i).getJSONObject("geometry").getJSONArray("coordinates").getBigDecimal(1);
            coords[1] = raw_features.getJSONObject(i).getJSONObject("geometry").getJSONArray("coordinates").getBigDecimal(0);
            depth = raw_features.getJSONObject(i).getJSONObject("geometry").getJSONArray("coordinates").getBigDecimal(2);
            time = raw_features.getJSONObject(i).getJSONObject("properties").getLong("time");

            //Test Output
            System.out.println("RECORD " + i);
            System.out.println("---------------");
            System.out.println("id: " + id);
            System.out.println("Title: " + title);
            System.out.println("Type: " + type);
            System.out.println("Magnitude: " + mag);
            System.out.println("Coordinates: [" + coords[0] + ", " + coords[1] + "]");
            System.out.println("Depth: " + depth + " km");
            System.out.println("Time: " + time);
            System.out.println("\n");
        }

    }
}
