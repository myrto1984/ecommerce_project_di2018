package services;

import dbEntities.Car;
import repositories.CarRepository;
import repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class CarServiceImpl implements CarService {

  @Autowired
  private CarRepository carRepository;


  @Override
  public synchronized Car addCar(Car car) {
    if (this.carRepository.fetchExistingCirculationNo(car.getCirculationNo()) > 0) {
      return null;
    }
    return this.carRepository.save(car);
  }


  @Override
  public Car updateCar(Car car) {
    return this.carRepository.save(car);
  }


  @Override
  public boolean deleteCar(Car car) {
    this.carRepository.delete(car);
    return  ( this.carRepository.fetchExistingCirculationNo(car.getCirculationNo()) > 0 );
  }


  @Override
  public Car getCar(String circulationNo) {
    return this.carRepository.findByCirculationNo(circulationNo);
  }

}
