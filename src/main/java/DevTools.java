import java.util.ArrayList;

public class DevTools {
    public void testRun() throws Exception {
        USGS_API_Wrapper uaw = new USGS_API_Wrapper();
        DataProcessor dp = new DataProcessor();
        System.out.println("Retrieving data...");
        String raw_data = uaw.getAllEarthquakes('h');
        System.out.println("Processing data...");

        FeatureList<Feature> fl = new FeatureList<Feature>();
        dp.cleanData(raw_data, fl);
        fl.exportData();
    }
}
