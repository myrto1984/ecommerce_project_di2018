-- -----------------------------------------------------
-- Table `alterUber_DB`.`roles`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS roles ( role VARCHAR(10) NOT NULL,PRIMARY KEY (role));


-- -----------------------------------------------------
-- Insert values to `alterUber_DB`.`roles`
-- -----------------------------------------------------
INSERT INTO roles (role) VALUES ('passenger');
INSERT INTO roles (role) VALUES ('driver');
INSERT INTO roles (role) VALUES ('admin');


-- -----------------------------------------------------
-- Table `alterUber_DB`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS uber_users (
  username VARCHAR(20) NOT NULL,
  password VARCHAR(20) NOT NULL,
  firstname VARCHAR(100) NULL DEFAULT NULL,
  lastname VARCHAR(100) NULL DEFAULT NULL,
  email VARCHAR(50) NOT NULL,
  telephone VARCHAR(14) NULL DEFAULT NULL,
  photo TEXT NULL DEFAULT NULL,
  role VARCHAR(10) NOT NULL,
  registrationDate TIMESTAMP(0) NULL DEFAULT CURRENT_TIMESTAMP,
  deletedAccount SMALLINT CHECK (deletedAccount > 0) NULL DEFAULT 0,
  PRIMARY KEY (username),

  CONSTRAINT fk_user_role
    FOREIGN KEY (role)
    REFERENCES roles (role)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
;

CREATE INDEX fk_user_role_idx ON uber_users (role ASC);


-- -----------------------------------------------------
-- Table `alterUber_DB`.`user_address`
-- -----------------------------------------------------
CREATE SEQUENCE user_address_seq;

CREATE TABLE IF NOT EXISTS user_address (
  id INT CHECK (id > 0) NOT NULL DEFAULT NEXTVAL ('user_address_seq'),
  username VARCHAR(20) NOT NULL,
  addressTitle VARCHAR(20) NOT NULL,
  streetAndNumber VARCHAR(255) NOT NULL,
  municipality VARCHAR(255) NOT NULL,
  district VARCHAR(255) NOT NULL,
  postalCode VARCHAR(5) NOT NULL,
  gmapMarker VARCHAR(100) NOT NULL,
  PRIMARY KEY (id),

  CONSTRAINT fk_user_address_username
    FOREIGN KEY (username)
    REFERENCES uber_users (username)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
;

CREATE INDEX fk_user_address_username_idx ON user_address (username ASC);


-- -----------------------------------------------------
-- Table `alterUber_DB`.`user_credit_card`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS user_credit_card (
  cardNo VARCHAR(20) NOT NULL,
  username VARCHAR(20) NOT NULL,
  cardType VARCHAR(20) NOT NULL,
  nameOnCard VARCHAR(100) NOT NULL,
  expiresOn TIMESTAMP(0) NOT NULL,
  securityCode VARCHAR(3) NOT NULL,
  PRIMARY KEY (cardNo)
 ,
  CONSTRAINT fk_user_credit_card_username
    FOREIGN KEY (username)
    REFERENCES uber_users (username)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
;

CREATE INDEX fk_user_credit_card_username_idx ON user_credit_card (username ASC);


-- -----------------------------------------------------
-- Table `alterUber_DB`.`car`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS car (
  circulationNo VARCHAR(10) NOT NULL,
  maxPassengers SMALLINT CHECK (maxPassengers > 0) NOT NULL DEFAULT 4,
  maxLoad DOUBLE PRECISION NOT NULL,
  manufacturer VARCHAR(45) NOT NULL,
  model VARCHAR(255) NOT NULL,
  colour VARCHAR(45) NOT NULL,
  productionYear INT CHECK (productionYear > 0) NOT NULL,
  registrationDate TIMESTAMP(0) NULL DEFAULT CURRENT_TIMESTAMP,
  deleted SMALLINT CHECK (deleted > 0) NULL DEFAULT 0,
  PRIMARY KEY (circulationNo))
;


-- -----------------------------------------------------
-- Table `alterUber_DB`.`driver_car`
-- -----------------------------------------------------
CREATE SEQUENCE driver_car_seq;

CREATE TABLE IF NOT EXISTS driver_car (
  id INT CHECK (id > 0) NOT NULL DEFAULT NEXTVAL ('driver_car_seq'),
  driver VARCHAR(20) NOT NULL,
  carCircNo VARCHAR(10) NOT NULL,
  deleted SMALLINT CHECK (deleted > 0) NULL DEFAULT 0,
  PRIMARY KEY (id)
 ,
  CONSTRAINT fk_driver_car_driver
    FOREIGN KEY (driver)
    REFERENCES uber_users (username)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_driver_car_car
    FOREIGN KEY (carCircNo)
    REFERENCES car (circulationNo)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
;

CREATE INDEX fk_driver_car_driver_idx ON driver_car (driver ASC);
CREATE INDEX fk_driver_car_car_idx ON driver_car (carCircNo ASC);


-- -----------------------------------------------------
-- Table `alterUber_DB`.`trip`
-- -----------------------------------------------------
CREATE SEQUENCE trip_seq;

CREATE TABLE IF NOT EXISTS trip (
  id INT CHECK (id > 0) NOT NULL DEFAULT NEXTVAL ('trip_seq'),
  passenger VARCHAR(20) NOT NULL,
  startPoint VARCHAR(100) NOT NULL,
  destination VARCHAR(100) NOT NULL,
  noOfStops SMALLINT CHECK (noOfStops > 0) NULL DEFAULT 0,
  requestDate TIMESTAMP(0) NULL DEFAULT CURRENT_TIMESTAMP,
  noOfPassengers SMALLINT CHECK (noOfPassengers > 0) NULL DEFAULT 1,
  luggageCat VARCHAR(20) NOT NULL,
  responseDate TIMESTAMP(0) NULL DEFAULT NULL,
  driverCar INT CHECK (driverCar > 0) NULL DEFAULT NULL,
  tripStartTime TIMESTAMP(0) NULL DEFAULT NULL,
  tripEndTime TIMESTAMP(0) NULL DEFAULT NULL,
  passengerRatingOfDriver SMALLINT CHECK (passengerRatingOfDriver > 0) NULL DEFAULT NULL,
  passengerRatingOfCar SMALLINT CHECK (passengerRatingOfCar > 0) NULL DEFAULT NULL,
  passengerComments TEXT NULL DEFAULT NULL,
  driverRatingOfPassenger SMALLINT CHECK (driverRatingOfPassenger > 0) NULL DEFAULT NULL,
  driverComments TEXT NULL DEFAULT NULL,
  cancelledByPassenger SMALLINT CHECK (cancelledByPassenger > 0) NULL DEFAULT 0,
  cancelledByDriver SMALLINT CHECK (cancelledByDriver > 0) NULL DEFAULT 0,
  PRIMARY KEY (id)
 ,
  CONSTRAINT fk_trip_passenger
    FOREIGN KEY (passenger)
    REFERENCES uber_users (username)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_trip_driverCar
    FOREIGN KEY (driverCar)
    REFERENCES driver_car (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
;

CREATE INDEX fk_trip_passenger_idx ON trip (passenger ASC);
CREATE INDEX fk_trip_driverCar_idx ON trip (driverCar ASC);


-- -----------------------------------------------------
-- Table `alterUber_DB`.`invoice`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS invoice (
  invoiceId VARCHAR(45) NOT NULL,
  tripId INT CHECK (tripId > 0) NOT NULL,
  dateIssued TIMESTAMP(0) NULL DEFAULT CURRENT_TIMESTAMP,
  chargePerKm DOUBLE PRECISION NOT NULL,
  vat DOUBLE PRECISION NOT NULL,
  distanceTravelled DOUBLE PRECISION NOT NULL,
  totalCharge DOUBLE PRECISION NOT NULL,
  paidInCash SMALLINT CHECK (paidInCash > 0) NULL DEFAULT 1,
  PRIMARY KEY (invoiceId)
 ,
  CONSTRAINT fk_invoice_tripId
    FOREIGN KEY (tripId)
    REFERENCES trip (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
;

CREATE INDEX fk_invoice_tripId_idx ON invoice (tripId ASC);


-- -----------------------------------------------------
-- Table `alterUber_DB`.`credit_card_in_invoice`
-- -----------------------------------------------------
CREATE SEQUENCE credit_card_in_invoice_seq;

CREATE TABLE IF NOT EXISTS credit_card_in_invoice (
  id INT CHECK (id > 0) NOT NULL DEFAULT NEXTVAL ('credit_card_in_invoice_seq'),
  invoiceId VARCHAR(45) NOT NULL,
  cardNo VARCHAR(20) NOT NULL,
  cardType VARCHAR(20) NOT NULL,
  nameOnCard VARCHAR(100) NOT NULL,
  PRIMARY KEY (id)
 ,
  CONSTRAINT fk_credit_card_in_invoice_invoice
    FOREIGN KEY (invoiceId)
    REFERENCES invoice (invoiceId)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
;

CREATE INDEX fk_credit_card_in_invoice_invoice_idx ON credit_card_in_invoice (invoiceId ASC);


/* SET SQL_MODE=@OLD_SQL_MODE; */
/* SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS; */
/* SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS; */

