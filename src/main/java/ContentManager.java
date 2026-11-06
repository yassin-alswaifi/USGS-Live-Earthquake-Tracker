import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.Queue;

public class ContentManager extends JFrame{
    protected LinkedList<Feature> last_six_features = new LinkedList<>();
    public FeatureList feature_list;

    private void populateQueue() {
        int cur = 0;
        while (cur < feature_list.size() && cur < 6) {
            if (last_six_features.size() < 6) {
                last_six_features.add(feature_list.get(cur));
            }
            else {
                last_six_features.removeFirst();
                last_six_features.add(feature_list.get(cur));
            }
            cur++;
        }
    }

    public JPanel getLatestContent() {
        JPanel content_panel = new JPanel();
        content_panel.setBackground(new Color(255, 242, 180));
        populateQueue();

        for (int i = 0; i < last_six_features.size(); i++) {
            // Feature Panel
            JPanel feature_panel = new JPanel();
            feature_panel.setLayout(new BoxLayout(feature_panel, BoxLayout.Y_AXIS));
            feature_panel.setBackground(new Color(229, 205, 130));
            feature_panel.setPreferredSize(new Dimension(720, 102));

            Feature cur = last_six_features.get(i);

            // Title
            JLabel title = new JLabel(" " + cur.getTitle());
            title.setForeground(new Color(21, 55, 13));
            title.setFont(new Font("Arial", Font.BOLD, 24));
            feature_panel.add(title);

            // Time
            JLabel time = new JLabel(" Time: " + cur.getTime_sdf());
            time.setForeground(new Color(21, 55, 13));
            time.setFont(new Font("Arial", Font.PLAIN, 18));
            feature_panel.add(time);

            // Lat & Long
            JLabel coord = new JLabel(" Latitude & Longitude: " + (cur.getCoords()[0]) + ", " + (cur.getCoords()[1]));
            coord.setForeground(new Color(21, 55, 13));
            coord.setFont(new Font("Arial", Font.PLAIN, 18));
            feature_panel.add(coord);

            // Depth
            JLabel depth = new JLabel(" Depth: " + cur.getDepth().setScale(2, BigDecimal.ROUND_HALF_UP) + " km");
            depth.setForeground(new Color(21, 55, 13));
            depth.setFont(new Font("Arial", Font.PLAIN, 18));
            feature_panel.add(depth);

            content_panel.add(feature_panel);
        }

        return content_panel;
    }

}
