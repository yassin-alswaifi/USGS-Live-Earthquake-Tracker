import java.math.BigDecimal;
import java.text.*;
import java.util.*;

public class Feature {
    protected String title, type, id;
    protected BigDecimal mag, depth;
    protected BigDecimal[] coords;
    protected Long time;

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
    public Long getTime(){return time;}
    public String getTime_sdf() {
        Date date = new Date(time);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
        return sdf.format(date);
    }
    //-------------------------------------------------//
}
