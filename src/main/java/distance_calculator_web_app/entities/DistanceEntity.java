package distance_calculator_web_app.entities;

import lombok.Setter;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;


@Entity
@Setter
@XmlRootElement
@XmlType(propOrder = {"fromCity","toCity","distance"})
@Table(name = "distance_between_cities")
public class DistanceEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "fromCity")
    private String fromCity;

    @Column(name = "toCity")
    private String toCity;

    @Column(name = "distance")
    private double distance;

    public DistanceEntity() {}

    public DistanceEntity(String fromCity, String toCity, double distance) {
        this.fromCity = fromCity;
        this.toCity = toCity;
        this.distance = distance;
    }


    @XmlAttribute
    public int getId() {
        return id;
    }

    @XmlAttribute
    public String getFromCity() {
        return fromCity;
    }

    @XmlAttribute
    public String getToCity() {
        return toCity;
    }

    @XmlAttribute
    public double getDistance() {
        return distance;
    }

    public void setFromCity(String fromCity) {
        this.fromCity = fromCity;
    }

    @Override
    public String toString() {
        String result = String.format("%.3f",distance);
        return "Distance{" +
                "fromCity='" + fromCity + '\'' +
                ",toCity='" + toCity + '\'' +
                ",distance=" + result + "km" +
                '}'+" ";
    }
}
