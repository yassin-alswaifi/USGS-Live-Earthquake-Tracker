import java.util.concurrent.TimeUnit;

public class DevTools {
    protected Boolean can_update = false;
    public void testRun() throws Exception {
        USGS_API_Wrapper uaw = new USGS_API_Wrapper();
        DataProcessor dp = new DataProcessor();
        System.out.println("Retrieving data...");
        String raw_data;
        System.out.println("Processing data...");
        FeatureList fl = new FeatureList();

        fl.importData();
        can_update = true;

        while (can_update) {
            raw_data = uaw.getAllEarthquakes('h');
            dp.cleanData(raw_data, fl);
            fl.exportData();
            TimeUnit.MINUTES.sleep(1);
        }


    }
}
