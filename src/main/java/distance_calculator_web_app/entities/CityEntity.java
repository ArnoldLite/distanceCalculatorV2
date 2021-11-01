package distance_calculator_web_app.entities;

import lombok.Setter;

import javax.persistence.*;
import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.Objects;

@Setter
@Entity
@XmlRootElement
@XmlType(propOrder = {"id","name","latitude","longitude"})
@Table(name = "cities")
public class CityEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "latitude")
    private float latitude;

    @Column(name = "longitude")
    private float longitude;


    public CityEntity() {}

    public CityEntity(String name, float latitude, float longitude) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }


    @XmlAttribute(name = "id")
    public int getId() {
        return id;
    }

    @XmlAttribute
    public String getName() {
        return name;
    }

    @XmlAttribute
    public float getLatitude() { return latitude; }

    @XmlAttribute
    public float getLongitude() { return longitude; }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CityEntity)) return false;
        CityEntity that = (CityEntity) o;
        return  Float.compare(that.getLatitude(), getLatitude()) == 0 &&
                Float.compare(that.getLongitude(), getLongitude()) == 0 &&
                getName().equals(that.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getLatitude(), getLongitude());
    }

    @Override
    public String toString() {
        return "CityEntity{" +
                "name='" + name + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}
