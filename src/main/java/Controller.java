import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;
import javax.swing.*;
import java.awt.*;

public class Controller {
    protected Boolean can_update = false;
    public void launch() throws Exception {
        // OBJECTS & VARS
        USGS_API_Wrapper uaw = new USGS_API_Wrapper();
        DataProcessor dp = new DataProcessor();
        ContentManager cm = new ContentManager();
        String raw_data;
        FeatureList fl = new FeatureList();
        cm.feature_list = fl;

        // UI
        JFrame main_frame = new JFrame("Live Earthquake Tracker");
        main_frame.setSize(760, 720);
        main_frame.setLocationRelativeTo(null);
        main_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel main_panel = new JPanel(new BorderLayout());

        //Navbar
        JPanel nav_bar = new JPanel();
        nav_bar.setBackground(new Color(21, 55, 13));
        nav_bar.setPreferredSize(new Dimension(1080,30));

        //MAIN CONTENT PANEL//
        JPanel content_panel = cm.getLatestContent();

        main_panel.add(nav_bar, BorderLayout.NORTH);
        main_panel.add(content_panel, BorderLayout.CENTER);
        main_frame.add(main_panel);
        main_frame.setVisible(true);

        fl.importData();
        can_update = true;
        JLabel last_updated = new JLabel();
        last_updated.setForeground(new Color(255, 242, 180));
        nav_bar.add(last_updated);

        while (can_update) {
            raw_data = uaw.getAllEarthquakes('h');
            dp.cleanData(raw_data, fl);
            main_panel.remove(content_panel);
            content_panel = cm.getLatestContent();
            nav_bar.remove(last_updated);
            last_updated.setText("Last Updated: " + getCurrentTime());
            nav_bar.add(last_updated);
            main_panel.add(content_panel, BorderLayout.CENTER);
            main_panel.revalidate();
            main_panel.repaint();
            fl.exportData();
            TimeUnit.MINUTES.sleep(1);
        }
    }

    private String getCurrentTime() {
        long unixTime = System.currentTimeMillis();
        Date date = new Date(unixTime);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
        return sdf.format(date);
    }
}
