package alterUber.services;

import alterUber.dbEntities.Car;
import alterUber.dbEntities.DriverCar;
import alterUber.dbEntities.User;

import java.util.List;

public interface DriverCarService {

  DriverCar addDriverCar(DriverCar driverCar);
  DriverCar updateDriverCar(DriverCar driverCar);
  boolean deleteDriverCar(DriverCar driverCar);
  List<Car> getCarsByDriver(String username);
  List<User> getDriversOfCar(String circulationNo);

}
