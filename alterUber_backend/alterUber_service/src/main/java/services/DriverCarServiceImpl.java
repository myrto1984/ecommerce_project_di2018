package services;

import dbEntities.Car;
import dbEntities.DriverCar;
import dbEntities.User;
import repositories.CarRepository;
import repositories.DriverCarRepository;
import repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DriverCarServiceImpl implements DriverCarService {

  @Autowired
  private DriverCarRepository driverCarRepository;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private CarRepository carRepository;

  @Override
  public DriverCar addDriverCar(DriverCar driverCar) {
    if (this.driverCarRepository.fetchCountExistingDriverAndCar(driverCar.getDriver(), driverCar.getCar()) > 0) {
      return null;
    }
    return this.driverCarRepository.save(driverCar);
  }


  @Override
  public DriverCar updateDriverCar(DriverCar driverCar) {
    return this.driverCarRepository.save(driverCar);
  }


  @Override
  public boolean deleteDriverCar(DriverCar driverCar) {
    this.driverCarRepository.delete(driverCar);

    boolean isCarUsed = (this.driverCarRepository.findAllByCar(driverCar.getCar()).size() > 0);
    if ( !isCarUsed ) {
      Car car = this.carRepository.findByCirculationNo(driverCar.getCar().getCirculationNo());
      car.setDeleted(1);
      this.carRepository.save(car);
    }

    return !this.driverCarRepository.findById(driverCar.getId()).isPresent();
  }


  @Override
  public List<Car> getCarsByDriver(String username) {
    User driver = this.userRepository.findByUsername(username);
    List<Car> cars = new ArrayList<>();
    this.driverCarRepository.findAllByDriver(driver).forEach( dc -> cars.add(dc.getCar()) );

    return cars;
  }


  @Override
  public List<User> getDriversOfCar(String circulationNo) {
    Car car = this.carRepository.findByCirculationNo(circulationNo);
    List<User> drivers = new ArrayList<>();
    this.driverCarRepository.findAllByCar(car).forEach( dc -> drivers.add(dc.getDriver()) );

    return drivers;
  }

}
