package distance_calculator_web_app.wrappersAndAdapters;

import lombok.Setter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * Обертка для двух объектов оберток CityWrapper и DistanceWrapper
 */

@Setter
@XmlRootElement(name = "list")
public class CityAndDistanceWrapper implements Serializable {

    private CityWrapper citiesWrapper;
    private DistanceWrapper distancesWrapper;

    public CityAndDistanceWrapper() {}

    public CityAndDistanceWrapper(CityWrapper cityWrapper, DistanceWrapper distanceWrapper) {
        this.citiesWrapper = cityWrapper;
        this.distancesWrapper = distanceWrapper;
    }

    @XmlElement(name = "citiesList")
    public CityWrapper getCitiesWrapper() {
        return citiesWrapper;
    }
    @XmlElement(name = "distanceList")
    public DistanceWrapper getDistancesWrapper() {
        return distancesWrapper;
    }

}


