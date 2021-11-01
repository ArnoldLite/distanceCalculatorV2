package distance_calculator_web_app.services;

import distance_calculator_web_app.dao.DistanceDao;
import distance_calculator_web_app.entities.DistanceEntity;
import distance_calculator_web_app.wrappersAndAdapters.CityWrapper;
import distance_calculator_web_app.wrappersAndAdapters.CityWrapperForCalculation;
import distance_calculator_web_app.wrappersAndAdapters.DistanceWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class DistanceCalculationServiceImplMatrix implements DistanceCalculationService{

    @Autowired
    private DistanceDao distanceDao;

    @Override
    @Transactional
    public DistanceWrapper distanceCalculation(CityWrapperForCalculation cityWrapperForCalculation) {

        CityWrapper fromCity = cityWrapperForCalculation.getFromCitiesWrapper();
        CityWrapper toCity = cityWrapperForCalculation.getToCitiesWrapper();

        List<DistanceEntity> distanceList = new ArrayList<>();

        for(int a = 0; a < fromCity.getCitiesList().size(); a++){
            for(int b = 0; b < toCity.getCitiesList().size(); b++){
                distanceList.add(
                        distanceDao.getDistanceList(
                                fromCity.getCitiesList().get(a).getName(),
                                toCity.getCitiesList().get(b).getName()));
            }
        }
        DistanceWrapper distanceWrapper = new DistanceWrapper();
        distanceWrapper.setDistanceList(distanceList);


        return distanceWrapper;
    }
}
