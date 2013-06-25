package models;

import java.util.Date;
import javax.persistence.*;
import play.db.ebean.*;

/**
 *
 * @author nikola
 */
@Entity
public class City extends Model {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String name;
    public static Finder<Long, City> find = new Finder<Long, City>(Long.class, City.class);

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

    @Override
    public void save() {
        super.save();
        Log log = new Log();
        log.setLogTime(new Date());
        log.setEntityType(Log.EntityType.city);
        log.setEntityId(getId());
        log.save();
    }
}
