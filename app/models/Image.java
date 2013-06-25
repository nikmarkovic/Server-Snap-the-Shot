package models;

import javax.persistence.*;
import play.db.ebean.*;

/**
 *
 * @author nikola
 */
@Entity
public class Image extends Model {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String name;
    @Lob
    @Column(nullable = false)
    private byte[] imageData;
    public static Finder<Long, Image> find = new Finder<Long, Image>(Long.class, Image.class);

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

    public byte[] getImageData() {
        return imageData;
    }

    public void setImageData(byte[] imageData) {
        this.imageData = imageData;
    }
}
