package distance_calculator_web_app.wrappersAndAdapters;

import distance_calculator_web_app.entities.DistanceEntity;
import lombok.Setter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * Класс DistanceWrapper действует как класс, охватывающий более одного объекта Distance.
 */

@Setter
@XmlRootElement(name = "distanceList")
public class DistanceWrapper implements Serializable {

    private List<DistanceEntity>distanceList = new ArrayList<>();

    public DistanceWrapper() {}

    public DistanceWrapper(List<DistanceEntity> distanceList) {this.distanceList = distanceList;}

    @XmlElement(name = "distance")
    public List<DistanceEntity> getDistanceList() {return distanceList;}
}
