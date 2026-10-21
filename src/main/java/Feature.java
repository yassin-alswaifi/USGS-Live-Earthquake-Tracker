import java.math.BigDecimal;

public class Feature {
    protected String title, type, id;
    protected BigDecimal mag, depth;
    protected BigDecimal[] coords = new BigDecimal[2];
    protected Long time;

    public Feature() {

    }
    public Feature(String id, String title, String type, BigDecimal mag, BigDecimal depth, BigDecimal[] coords, Long time) {
        this.id = id;
        this.title = title;
        this.type = type;
        this.mag = mag;
        this.coords = coords;
        this.depth = depth;
        this.time = time;
    }

    //To Do: Implement various info-fetch methods.
}
