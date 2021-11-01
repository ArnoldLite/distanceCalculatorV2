package distance_calculator_web_app.services;

import org.springframework.web.multipart.MultipartFile;

public interface BindingService {
    void addObjsToDBFromXmlFile(MultipartFile file);

    void addCityToDBFromXmlFile(MultipartFile file);

    void addDistanceToDBFromXmlFile(MultipartFile file);
}
