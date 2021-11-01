package distance_calculator_web_app.services;

import distance_calculator_web_app.entities.CityEntity;
import distance_calculator_web_app.wrappersAndAdapters.CityWrapper;

import java.util.List;

public interface CityService {


    public List<CityEntity> getAllCity();

    public void saveCitiesFromList(CityWrapper wrapper);
}
