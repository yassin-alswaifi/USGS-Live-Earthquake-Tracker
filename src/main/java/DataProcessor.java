import org.json.*;
import java.util.*;
import java.math.BigDecimal;
import java.io.*;

public class DataProcessor {

    private Boolean idExists(String id) {
        Boolean found = false;

        File id_file = new File("id_log.txt");
        try (Scanner myReader = new Scanner(id_file)) {
            while (myReader.hasNextLine()) {
                String cur = myReader.nextLine();
                if (id.equals(cur)) {
                    System.out.println("ID found.");
                    found = true;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("No file found, ID is unique.");
        }
        return found;
    }
    public void cleanData(String raw_data, FeatureList f_list) throws FileNotFoundException { // Could take the FeatureList as a parameter too and just populate it here instead? Also split this method into smaller ones when done
        //Unfiltered
        JSONObject base_geoJSON = new JSONObject(raw_data);

        //Filtered
        JSONArray raw_features = base_geoJSON.getJSONArray("features");

        String id, title, type;
        BigDecimal mag, depth;
        BigDecimal[] coords = new BigDecimal[2];
        Long time;
        int data_len = raw_features.length();
        ArrayList<Feature> new_results = new ArrayList<>();
        PrintStream id_list = new PrintStream(
                new FileOutputStream("id_log.txt", true)
        );

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

            boolean found = idExists(id);
            if (!found) {
                Feature new_feature = new Feature(id, title, type, mag, depth, coords, time);
                id_list.println(id);
                new_results.add(i, new_feature);

                //----------Test Output----------//
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
                //-------------------------------//
            }
        }
        id_list.close();

        for (int i = 0; i < new_results.size(); i++) {
            f_list.add(new_results.get(i));
        }
    }
}
