package distance_calculator_web_app.services;

import distance_calculator_web_app.dao.CityDao;
import distance_calculator_web_app.entities.CityEntity;
import distance_calculator_web_app.wrappersAndAdapters.CityWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CityServiceImpl implements CityService {

    @Autowired
    private CityDao cityDao;

    @Override
    @Transactional
    public List<CityEntity> getAllCity() {
        return cityDao.getAllCity();
    }

    @Override
    @Transactional
    public void saveCitiesFromList(CityWrapper wrapper) {
        cityDao.saveCitiesToDB(wrapper);
    }


}
