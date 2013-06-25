package models;

import java.util.Date;
import javax.persistence.*;
import play.db.ebean.*;

/**
 *
 * @author nikola
 */
@Entity
public class Log extends Model {

    public enum EntityType {

        city, sight, image;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date logTime;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private EntityType entityType;
    @Column(nullable = false)
    private Long entityId;
    public static Finder<Long, Log> find = new Finder<Long, Log>(Long.class, Log.class);

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getLogTime() {
        return logTime;
    }

    public void setLogTime(Date logTime) {
        this.logTime = logTime;
    }

    public EntityType getEntityType() {
        return entityType;
    }

    public void setEntityType(EntityType entityType) {
        this.entityType = entityType;
    }

    public Long getEntityId() {
        return entityId;
    }

    public void setEntityId(Long entityId) {
        this.entityId = entityId;
    }
}
