package distance_calculator_web_app.dao;

import distance_calculator_web_app.entities.DistanceEntity;
import distance_calculator_web_app.exceptions.NotObjsInDatabase;
import distance_calculator_web_app.wrappersAndAdapters.DistanceWrapper;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DistanceDaoImpl implements DistanceDao {

    @Autowired
    private SessionFactory sessionFactory;


    @Override
    public void saveDistanceToDB(DistanceWrapper wrapper) {
        Session session = sessionFactory.getCurrentSession();
        List<DistanceEntity> distanceList = wrapper.getDistanceList();
        for (DistanceEntity distance : distanceList) {
            session.saveOrUpdate(distance);
        }
    }

    //   можно поменять логику, сделать два списка и к каждому свой запрос (смотри ниже два запроса),
    //   затем объеденить в один
    @Override
    public DistanceEntity getDistanceList(String fromCity, String toCity) {
        Session session = sessionFactory.getCurrentSession();

        List<DistanceEntity> list =
                session.createQuery("from DistanceEntity where fromCity =: fromCity and toCity =: toCity",
                                       DistanceEntity.class)
                        .setParameter("fromCity", fromCity)
                        .setParameter("toCity", toCity)
                        .getResultList();

        if (list.isEmpty()) {
            list = session.createQuery("from DistanceEntity where fromCity =: fromCity and toCity =: toCity",
                                          DistanceEntity.class)
                    .setParameter("fromCity", toCity)
                    .setParameter("toCity", fromCity)
                    .getResultList();
        }

//        if(!list.stream().findFirst().isPresent()){
//            throw new NotObjsInDatabase("There is not a single 'distance' object in the database.");
//        }


        return list.stream()
                .findFirst()
                .orElse(
                        new DistanceEntity("fromCity_or_toCity_not_exist_in_database",
                                "first_you_need_to fill_the_database_with_these_values", 0));
    }


}
