import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) throws Exception {
        JFrame main_frame = new JFrame("Live Earthquake Tracker");
        main_frame.setSize(1080, 720);
        main_frame.setLocationRelativeTo(null);
        main_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel main_panel = new JPanel(new BorderLayout());
        JPanel nav_bar = new JPanel();
        nav_bar.setBackground(Color.darkGray);
        nav_bar.setPreferredSize(new Dimension(1920,30));

        //MAIN CONTENT PANEL (WILL BE MOVED LATER)//
        JPanel content_panel = new JPanel(new BorderLayout());
        content_panel.setBackground(Color.lightGray);

        //Events
        JPanel event_log = new JPanel(new GridLayout(5,1, 10, 10)); //Switch to BoxLayout
        event_log.setBackground(Color.white);
        event_log.setPreferredSize(new Dimension(320,1050));

        JPanel feature_panel = new JPanel();
        feature_panel.setBackground(Color.gray);
        feature_panel.setPreferredSize(new Dimension(280,20));
        event_log.add(feature_panel);

        JScrollPane event_scroll = new JScrollPane(event_log);
        event_scroll.getVerticalScrollBar().setUnitIncrement(8);
        content_panel.add(event_scroll, BorderLayout.WEST);

        ////////////////////////////////////////////

        main_panel.add(nav_bar, BorderLayout.NORTH);
        main_panel.add(content_panel, BorderLayout.CENTER);
        main_frame.add(main_panel);
        main_frame.setVisible(true);

        DevTools dev = new DevTools();
        dev.testRun();
    }
}
