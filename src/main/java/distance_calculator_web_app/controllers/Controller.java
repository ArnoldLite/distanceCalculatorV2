package distance_calculator_web_app.controllers;

import distance_calculator_web_app.entities.CityEntity;
import distance_calculator_web_app.exceptions.BadRequestException;
import distance_calculator_web_app.exceptions.NotObjsInDatabase;
import distance_calculator_web_app.services.BindingService;
import distance_calculator_web_app.services.DistanceCalculationService;
import distance_calculator_web_app.wrappersAndAdapters.CityWrapper;
import distance_calculator_web_app.services.CityService;
import distance_calculator_web_app.wrappersAndAdapters.CityWrapperForCalculation;
import distance_calculator_web_app.wrappersAndAdapters.DistanceWrapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class Controller {

    @Autowired
    private CityService cityService;
    @Autowired
    private BindingService bindingService;
    @Autowired
    @Qualifier(value = "distanceCalculationServiceImplCrowFlight")
    private DistanceCalculationService distanceCalculation1;
    @Autowired
    @Qualifier(value = "distanceCalculationServiceImplMatrix")
    private DistanceCalculationService distanceCalculation2;


    //   1 endpoint, получаем полный список городов из бд в xml списке
    //   (можно переделать, чтобы возвращал List<CityEntity>)
    @GetMapping(path = "cities")
    public CityWrapper getAllCities() {

        List<CityEntity> allCities = cityService.getAllCity();

        if (allCities.isEmpty()) {
            throw new NotObjsInDatabase("There is not a single 'city' object in the database.");
        }

        return new CityWrapper(allCities);
    }




    //   2.1 endpoint сохраняем список городов (в body запроса) с координатами
    //   в формате xml  (но не файл!)  (метод без проверок на исключения!!!)  (потом удалить)
    @PostMapping(path = "upload")
    public String upload(@RequestBody CityWrapper cityWrapper) {
        cityService.saveCitiesFromList(cityWrapper);
        return "Cities saved in DB";
    }

    //   2.2 endpoint с загрузкой xml файла в таблицу городов с координатами (работает)
    @PostMapping(path = "upload/file", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void fileUpload(@RequestParam(value = "uploadFile") MultipartFile uploadFile) {

        // проверка на то, что файл имеет xml формат
        if (!uploadFile.getOriginalFilename().endsWith(".xml")) {
            throw new BadRequestException("The file has a non-xml format");
        }

        //  проверка на то, что файл не пустой
        if (uploadFile.isEmpty()) {
            throw new BadRequestException("File is empty");
        }

        bindingService.addObjsToDBFromXmlFile(uploadFile);
    }




    //   3.1 endpoint расчет дистанции по сфере с помощью координат (работает - погрешность связана с радиусом Земли)
    @PostMapping(path = "calculate/crowflight")
    public DistanceWrapper crowFlightDistanceCalculation(@RequestBody CityWrapperForCalculation cityWrapperForCalculation) {
        return distanceCalculation1.distanceCalculation(cityWrapperForCalculation);
    }

    //   3.2 endpoint расчет дистанции по матрице дистанций
    @PostMapping(path = "calculate/distancematrix")
    public DistanceWrapper distanceMatrixCalculation(@RequestBody CityWrapperForCalculation cityWrapperForCalculation) {
        return distanceCalculation2.distanceCalculation(cityWrapperForCalculation);
    }

}
