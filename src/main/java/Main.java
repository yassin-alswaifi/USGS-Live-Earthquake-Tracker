
public class Main {
    public static void main(String[] args) throws Exception {

        USGS_API_Wrapper uaw = new USGS_API_Wrapper();
        DataProcessor dp = new DataProcessor();
        System.out.println("Retrieving data...");
        String raw_data = uaw.getAllEarthquakes('h');
        System.out.println("Processing data...");
        dp.cleanData(raw_data);

//        EXAMPLE
//        Feature feat = new Feature();
//        FeatureList<Feature> fl = new FeatureList<Feature>();
//        fl.add(feat);

    }
}
