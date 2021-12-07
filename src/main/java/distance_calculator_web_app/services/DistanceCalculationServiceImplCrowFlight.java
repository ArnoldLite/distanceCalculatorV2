package distance_calculator_web_app.services;


import distance_calculator_web_app.dao.CityDao;
import distance_calculator_web_app.dao.DistanceDao;
import distance_calculator_web_app.entities.CityEntity;
import distance_calculator_web_app.entities.DistanceEntity;
import distance_calculator_web_app.wrappersAndAdapters.CityWrapperForCalculation;
import distance_calculator_web_app.wrappersAndAdapters.DistanceWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class DistanceCalculationServiceImplCrowFlight implements DistanceCalculationService {

    @Autowired
    private CityDao cityDao;

    @Autowired
    private DistanceDao distanceDao;

    @Override
    @Transactional
    public DistanceWrapper distanceCalculation(CityWrapperForCalculation wrapper) {

        List<CityEntity>bigList = new ArrayList<>();

        bigList.addAll(wrapper.getFromCitiesWrapper().getCitiesList());
        bigList.addAll(wrapper.getToCitiesWrapper().getCitiesList());

        for(CityEntity cityEntity : bigList){
            if(!cityEntity.equals(cityDao.getCity(cityEntity))){
                cityDao.saveCityToDB(cityEntity);
            }
        }

        List<DistanceEntity> distanceList = new ArrayList<>();

        for (int a = 0; a < wrapper.getFromCitiesWrapper().getCitiesList().size(); a++) {
            for (int b = 0; b < wrapper.getToCitiesWrapper().getCitiesList().size(); b++) {

                double distanceBetweenCities = CalculateDistance.getDistanceFromLatLonInKm(
                        wrapper.getFromCitiesWrapper().getCitiesList().get(a).getLatitude(),
                        wrapper.getFromCitiesWrapper().getCitiesList().get(a).getLongitude(),
                        wrapper.getToCitiesWrapper().getCitiesList().get(b).getLatitude(),
                        wrapper.getToCitiesWrapper().getCitiesList().get(b).getLongitude());

                distanceList.add(new DistanceEntity(wrapper.getFromCitiesWrapper().getCitiesList().get(a).getName(),
                        wrapper.getToCitiesWrapper().getCitiesList().get(b).getName(),
                        distanceBetweenCities));
            }
        }

        DistanceWrapper distanceWrapper = new DistanceWrapper();
        distanceWrapper.setDistanceList(distanceList);

        return distanceWrapper;
    }
}
