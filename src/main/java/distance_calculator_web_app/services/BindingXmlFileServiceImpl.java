package distance_calculator_web_app.services;

import distance_calculator_web_app.dao.CityDao;
import distance_calculator_web_app.dao.DistanceDao;
import distance_calculator_web_app.exceptions.BadRequestException;
import distance_calculator_web_app.wrappersAndAdapters.CityAndDistanceWrapper;
import distance_calculator_web_app.wrappersAndAdapters.CityWrapper;
import distance_calculator_web_app.wrappersAndAdapters.DistanceWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;


@Service
public class BindingXmlFileServiceImpl implements BindingService{

    @Autowired
    private CityDao cityDao;
    @Autowired
    private DistanceDao distanceDao;


    //работает
    @Override
    @Transactional
    public void addObjsToDBFromXmlFile(MultipartFile file) {
        JAXBContext context = null;
        try {
            context = JAXBContext.newInstance(CityAndDistanceWrapper.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            CityAndDistanceWrapper unmarshal = (CityAndDistanceWrapper) unmarshaller.unmarshal(file.getInputStream());
            cityDao.saveCitiesToDB(unmarshal.getCitiesWrapper());
            distanceDao.saveDistanceToDB(unmarshal.getDistancesWrapper());
        } catch (Exception e) {
            throw new BadRequestException(
                    "The contents of the file do not meet the design requirements. " +
                            "Read the documentation with instructions for formatting the contents of the xml file.");
        }
    }

    //потом удалить
    @Override
    @Transactional
    public void addCityToDBFromXmlFile(MultipartFile file){
        JAXBContext context = null;
        try{
            context = JAXBContext.newInstance(CityWrapper.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            CityWrapper unmarshal = (CityWrapper) unmarshaller.unmarshal(file.getInputStream());
            cityDao.saveCitiesToDB(unmarshal);
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }

    //потом удалить
    @Override
    @Transactional
    public void addDistanceToDBFromXmlFile(MultipartFile file){
        JAXBContext context = null;
        try{
            context = JAXBContext.newInstance(DistanceWrapper.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            DistanceWrapper unmarshal = (DistanceWrapper) unmarshaller.unmarshal(file.getInputStream());
            distanceDao.saveDistanceToDB(unmarshal);
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }
}
