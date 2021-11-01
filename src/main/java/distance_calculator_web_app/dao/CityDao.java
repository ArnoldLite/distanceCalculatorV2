package distance_calculator_web_app.dao;

import distance_calculator_web_app.entities.CityEntity;
import distance_calculator_web_app.wrappersAndAdapters.CityWrapper;

import java.util.List;

public interface CityDao {

    public List<CityEntity> getAllCity();

    public void saveCitiesToDB(CityWrapper wrapper);

    public void saveCityToDB(CityEntity city);

    public CityEntity getCity(CityEntity city);
}
