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

    private final double RAD = 6_371.302;

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

                double distanceBetweenCities = getDistanceFromLatLonInKm(
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

    //расчет расстояния
    public double getDistanceFromLatLonInKm(double lat1, double lon1, double lat2, double lon2) {

        double dLat = deg2rad(lat2 - lat1);  // перевод дег в рад
        double dLon = deg2rad(lon2 - lon1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) *
                        Math.sin(dLon / 2) * Math.sin(dLon / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        double scale = Math.pow(10, 3);
        return Math.ceil((RAD * c) * scale) / scale ; // расстояние в км
    }

    public double deg2rad(double deg) {
        return deg * (Math.PI / 180);
    }


}
