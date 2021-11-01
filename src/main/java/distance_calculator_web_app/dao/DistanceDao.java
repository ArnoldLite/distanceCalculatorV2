package distance_calculator_web_app.dao;

import distance_calculator_web_app.entities.DistanceEntity;
import distance_calculator_web_app.wrappersAndAdapters.DistanceWrapper;

public interface DistanceDao {

    public void saveDistanceToDB(DistanceWrapper wrapper);

    public DistanceEntity getDistanceList(String fromCity, String toCity);
}
