package distance_calculator_web_app.wrappersAndAdapters;

import lombok.Setter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;


/**
 * Обертка для двух объектов обертки CityWrapper
 */

@Setter
@XmlRootElement(name = "list")
public class CityWrapperForCalculation implements Serializable {

    private CityWrapper toCitiesWrapper;
    private CityWrapper fromCitiesWrapper;

    public CityWrapperForCalculation() {}

    public CityWrapperForCalculation(CityWrapper cityWrapper1, CityWrapper cityWrapper2) {
        this.fromCitiesWrapper = cityWrapper1;
        this.toCitiesWrapper = cityWrapper2;
    }

    @XmlElement(name = "citiesListFrom")
    public CityWrapper getFromCitiesWrapper() {return fromCitiesWrapper;}
    @XmlElement(name = "citiesListTo")
    public CityWrapper getToCitiesWrapper() {return toCitiesWrapper;}
}
