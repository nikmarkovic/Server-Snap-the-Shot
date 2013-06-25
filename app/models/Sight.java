package models;

import java.util.Date;
import javax.persistence.*;
import org.codehaus.jackson.annotate.JsonIgnore;
import play.db.ebean.*;

/**
 *
 * @author nikola
 */
@Entity
public class Sight extends Model {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Lob
    @Column
    private String description;
    @Column(nullable = false)
    private double latitude;
    @Column(nullable = false)
    private double longitude;
    @ManyToOne
    private City city;
    @JsonIgnore
    @OneToOne
    private Image image;
    public static Finder<Long, Sight> find = new Finder<Long, Sight>(Long.class, Sight.class);

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    @Override
    public void save() {
        super.save();
        Log log = new Log();
        log.setLogTime(new Date());
        log.setEntityType(Log.EntityType.sight);
        log.setEntityId(getId());
        log.save();
    }
}
