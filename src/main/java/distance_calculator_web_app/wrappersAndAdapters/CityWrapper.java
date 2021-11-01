package distance_calculator_web_app.wrappersAndAdapters;

import distance_calculator_web_app.entities.CityEntity;
import lombok.Setter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * Класс CityWrapper действует как класс, охватывающий более одного объекта City.
 * Единственная цель этого — обеспечить легкое преобразование объектов типа List<City>
 * в формат XML и обратно.
 */

@Setter
@XmlRootElement(name = "citiesList")
public class CityWrapper implements Serializable {

    private List<CityEntity> citiesList = new ArrayList<>();

    public CityWrapper() {}

    public CityWrapper(List<CityEntity> citiesList) {
        this.citiesList = citiesList;
    }

    @XmlElement(name = "city")
    public List<CityEntity> getCitiesList() {return citiesList;}


}
