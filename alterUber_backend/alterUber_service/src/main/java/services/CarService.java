package services;

import dbEntities.Car;

public interface CarService {

  Car addCar(Car car);
  Car updateCar(Car car);
  boolean deleteCar(Car car);
  Car getCar(String circulationNo);

}
