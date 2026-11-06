import java.math.BigDecimal;
import java.util.*;
import java.io.*;
public class FeatureList extends LinkedList<Feature> {

    public void exportData() throws FileNotFoundException {
        try {
            Feature cur;
            PrintStream feature_log = new PrintStream("feature_log.txt");
            for (int i = size() - 1; i < size(); i--) {
                cur = get(i);
                if (i == 0)
                    feature_log.print(cur.getId() + "#" + cur.getTitle() + "#" + cur.getType() + "#" + cur.getMag() + "#" + cur.getDepth() + "#" + cur.getCoords()[0] + "," + cur.getCoords()[1] + "#" + cur.getTime());
                else
                    feature_log.println(cur.getId() + "#" + cur.getTitle() + "#" + cur.getType() + "#" + cur.getMag() + "#" + cur.getDepth() + "#" + cur.getCoords()[0] + "," + cur.getCoords()[1] + "#" + cur.getTime());
            }
            feature_log.close();
        }
        catch (IndexOutOfBoundsException e) {
            System.out.println("FeatureList is empty.");
        }
    }
    public void importData(){
        File feature_file = new File("feature_log.txt");
        try (Scanner Reader = new Scanner(feature_file)) {
            String[] feature_data, str_coords;
            String id, title, type;
            BigDecimal mag, depth;
            BigDecimal[] coords = new BigDecimal[2];
            Long time;
            Double double_mag;
            Double double_depth;
            Double[] double_coords = new Double[2];
            Feature cur_feat;

            while (Reader.hasNextLine()) {
                String cur = Reader.nextLine();
                feature_data = cur.split("#");

                id = feature_data[0];
                title = feature_data[1];
                type = feature_data[2];
                double_mag = new Double(feature_data[3]);
                mag = BigDecimal.valueOf(double_mag);
                double_depth = new Double(feature_data[4]);
                depth = BigDecimal.valueOf(double_depth);
                time = Long.valueOf(feature_data[6]);

                str_coords = feature_data[5].split(",");
                double_coords[0] = Double.valueOf(str_coords[0]);
                double_coords[1] = Double.valueOf(str_coords[1]);
                coords[0] = BigDecimal.valueOf(double_coords[0]);
                coords[1] = BigDecimal.valueOf(double_coords[1]);

                cur_feat = new Feature(id, title, type, mag, depth, coords, time);
                addFirst(cur_feat);
            }
        }
        catch (FileNotFoundException e) {
            System.out.println("No Feature log exists.");
        }
    }

}
