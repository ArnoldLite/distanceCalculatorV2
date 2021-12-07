package distance_calculator_web_app.services;

public class CalculateDistance {

    private static final double RAD = 6_371.302;

    //расчет расстояния
    public static double getDistanceFromLatLonInKm(double lat1, double lon1, double lat2, double lon2) {

        double dLat = deg2rad(lat2 - lat1);  // перевод дег в рад
        double dLon = deg2rad(lon2 - lon1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) *
                        Math.sin(dLon / 2) * Math.sin(dLon / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        double scale = Math.pow(10, 3);
        return Math.ceil((RAD * c) * scale) / scale ; // расстояние в км
    }

    private static double deg2rad(double deg) {
        return deg * (Math.PI / 180);
    }
}
