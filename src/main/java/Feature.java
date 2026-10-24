import java.math.BigDecimal;

public class Feature {
    protected String title, type, id;
    protected BigDecimal mag, depth;
    protected BigDecimal[] coords = new BigDecimal[2];
    protected Long time;

    public Feature() {
        // PLACEHOLDER
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

    //-------------------Get Methods-------------------//
    public String getId(){return id;}
    public String getTitle(){return title;}
    public String getType(){return type;}
    public BigDecimal getMag(){return mag;}
    public BigDecimal getDepth(){return depth;}
    public BigDecimal[] getCoords(){return coords;}

    //add conversion options
    public Long getTime(){return time;}
    //-------------------------------------------------//
}
