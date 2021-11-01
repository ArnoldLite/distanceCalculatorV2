package distance_calculator_web_app.dao;

import distance_calculator_web_app.entities.CityEntity;
import distance_calculator_web_app.wrappersAndAdapters.CityWrapper;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CityDaoImpl implements CityDao {

    @Autowired
    private SessionFactory sessionFactory;


    //получаем список всех городов в базе данных
    @Override
    public List<CityEntity> getAllCity() {
        Session session = sessionFactory.getCurrentSession();

        List<CityEntity> allCities = session.createQuery("from CityEntity")
                .getResultList();

        return allCities;
    }


    //сохраняем список в дб
    @Override
    public void saveCitiesToDB(CityWrapper cityWrapper) {
        Session session = sessionFactory.getCurrentSession();
        List<CityEntity> cityList = cityWrapper.getCitiesList();
        for (CityEntity city : cityList) {
            if(!city.equals(getCity(city))){
                session.save(city);
            }
        }
    }


    @Override
    public void saveCityToDB(CityEntity city) {
        Session session = sessionFactory.getCurrentSession();
        session.save(city);
    }


    @Override
    public CityEntity getCity(CityEntity cityEntity) {

        Session session = sessionFactory.getCurrentSession();

        List<CityEntity> list = session
                .createQuery("from CityEntity where name=:name")
                .setParameter("name", cityEntity.getName())
                .getResultList();

        CityEntity cityEntityInDB = list.stream()
                .findFirst()
                .orElse(null);

        return cityEntityInDB;
    }


}
