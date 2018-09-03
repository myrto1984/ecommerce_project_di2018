package services;

import dbEntities.Car;
import dbEntities.DriverCar;
import dbEntities.User;

import java.util.List;

public interface DriverCarService {

  DriverCar addDriverCar(DriverCar driverCar);
  DriverCar updateDriverCar(DriverCar driverCar);
  boolean deleteDriverCar(DriverCar driverCar);
  List<Car> getCarsByDriver(String username);
  List<User> getDriversOfCar(String circulationNo);

}
