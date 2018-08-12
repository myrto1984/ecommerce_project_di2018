-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema alterUber_DB
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema alterUber_DB
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `alterUber_DB` DEFAULT CHARACTER SET utf8 ;
USE `alterUber_DB` ;

-- -----------------------------------------------------
-- Table `alterUber_DB`.`roles`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `alterUber_DB`.`roles` (
  `role` VARCHAR(10) NOT NULL,
  PRIMARY KEY (`role`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Insert values to `alterUber_DB`.`roles`
-- -----------------------------------------------------
INSERT INTO `alterUber_DB`.`roles` (`role`) VALUES ('passenger');
INSERT INTO `alterUber_DB`.`roles` (`role`) VALUES ('driver');
INSERT INTO `alterUber_DB`.`roles` (`role`) VALUES ('admin');


-- -----------------------------------------------------
-- Table `alterUber_DB`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `alterUber_DB`.`user` (
  `username` VARCHAR(20) NOT NULL,
  `password` VARCHAR(20) NOT NULL,
  `firstname` VARCHAR(100) NULL,
  `lastname` VARCHAR(100) NULL,
  `email` VARCHAR(50) NOT NULL,
  `telephone` VARCHAR(14) NULL,
  `photo` TEXT NULL,
  `role` VARCHAR(10) NULL,
  `registrationDate` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
  `deletedAccount` TINYINT(1) UNSIGNED NULL DEFAULT '0',
  PRIMARY KEY (`username`),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC),
  INDEX `fk_user_role_idx` (`role` ASC),
  CONSTRAINT `fk_user_role`
    FOREIGN KEY (`role`)
    REFERENCES `alterUber_DB`.`roles` (`role`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `alterUber_DB`.`user_address`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `alterUber_DB`.`user_address` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(20) NOT NULL,
  `addressTitle` VARCHAR(20) NOT NULL,
  `street_and_number` VARCHAR(255) NOT NULL,
  `municipality` VARCHAR(255) NOT NULL,
  `district` VARCHAR(255) NOT NULL,
  `postalCode` VARCHAR(5) NOT NULL,
  `gmapMarker` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `unique_usrn_addr` (`addressTitle` ASC, `username` ASC),
  INDEX `fk_user_address_username_idx` (`username` ASC),
  CONSTRAINT `fk_user_address_username`
    FOREIGN KEY (`username`)
    REFERENCES `alterUber_DB`.`user` (`username`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `alterUber_DB`.`user_credit_card`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `alterUber_DB`.`user_credit_card` (
  `cardNo` VARCHAR(20) NOT NULL,
  `username` VARCHAR(20) NOT NULL,
  `cardType` VARCHAR(20) NOT NULL,
  `nameOnCard` VARCHAR(100) NOT NULL,
  `securityCode` VARCHAR(3) NOT NULL,
  PRIMARY KEY (`cardNo`),
  INDEX `fk_user_credit_card_username_idx` (`username` ASC),
  CONSTRAINT `fk_user_credit_card_username`
    FOREIGN KEY (`username`)
    REFERENCES `alterUber_DB`.`user` (`username`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `alterUber_DB`.`car`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `alterUber_DB`.`car` (
  `circulationNo` VARCHAR(10) NOT NULL,
  `maxPassengers` TINYINT(2) UNSIGNED NOT NULL,
  `maxLoad` FLOAT NOT NULL,
  `manufacturer` VARCHAR(45) NOT NULL,
  `model` VARCHAR(255) NOT NULL,
  `colour` VARCHAR(45) NOT NULL,
  `productionYear` INT(4) UNSIGNED NOT NULL,
  `registrationDate` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
  `deleted` TINYINT(1) UNSIGNED NULL DEFAULT '0',
  PRIMARY KEY (`circulationNo`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `alterUber_DB`.`driver_car`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `alterUber_DB`.`driver_car` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `driver` VARCHAR(20) NOT NULL,
  `carCircNo` VARCHAR(10) NOT NULL,
  `deleted` TINYINT(1) UNSIGNED NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  INDEX `fk_driver_car_driver_idx` (`driver` ASC),
  INDEX `fk_driver_car_car_idx` (`carCircNo` ASC),
  CONSTRAINT `fk_driver_car_driver`
    FOREIGN KEY (`driver`)
    REFERENCES `alterUber_DB`.`user` (`username`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_driver_car_car`
    FOREIGN KEY (`carCircNo`)
    REFERENCES `alterUber_DB`.`car` (`circulationNo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `alterUber_DB`.`trip`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `alterUber_DB`.`trip` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `passenger` VARCHAR(20) NOT NULL,
  `startPoint` VARCHAR(100) NOT NULL,
  `destination` VARCHAR(100) NOT NULL,
  `noOfStops` TINYINT(2) UNSIGNED NULL DEFAULT '0',
  `requestDate` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
  `noOfPassengers` TINYINT(2) UNSIGNED NULL DEFAULT '1',
  `luggageCat` VARCHAR(20) NOT NULL,
  `responseDate` DATETIME NULL DEFAULT NULL,
  `driverCar` INT UNSIGNED NULL DEFAULT NULL,
  `tripStartTime` DATETIME NULL DEFAULT NULL,
  `tripEndTime` DATETIME NULL DEFAULT NULL,
  `passengerRatingOfDriver` TINYINT(1) UNSIGNED NULL DEFAULT NULL,
  `passengerRatingOfCar` TINYINT(1) UNSIGNED NULL DEFAULT NULL,
  `passengerComments` TEXT NULL DEFAULT NULL,
  `driverRatingOfPassenger` TINYINT(1) UNSIGNED NULL DEFAULT NULL,
  `driverComments` TEXT NULL DEFAULT NULL,
  `cancelledByPassenger` TINYINT(1) UNSIGNED NULL DEFAULT '0',
  `cancelledByDriver` TINYINT(1) UNSIGNED NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  INDEX `fk_trip_passenger_idx` (`passenger` ASC),
  INDEX `fk_trip_driverCar_idx` (`driverCar` ASC),
  CONSTRAINT `fk_trip_passenger`
    FOREIGN KEY (`passenger`)
    REFERENCES `alterUber_DB`.`user` (`username`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_trip_driverCar`
    FOREIGN KEY (`driverCar`)
    REFERENCES `alterUber_DB`.`driver_car` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `alterUber_DB`.`invoice`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `alterUber_DB`.`invoice` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `tripId` INT UNSIGNED NOT NULL,
  `dateIssued` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
  `chargePerKm` FLOAT NOT NULL,
  `vat` FLOAT NOT NULL,
  `distanceTravelled` FLOAT NOT NULL,
  `totalCharge` FLOAT NOT NULL,
  `paidInCash` TINYINT(1) UNSIGNED NULL DEFAULT '1',
  PRIMARY KEY (`id`),
  INDEX `fk_invoice_tripId_idx` (`tripId` ASC),
  CONSTRAINT `fk_invoice_tripId`
    FOREIGN KEY (`tripId`)
    REFERENCES `alterUber_DB`.`trip` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `alterUber_DB`.`credit_card_in_invoice`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `alterUber_DB`.`credit_card_in_invoice` (
  `cardNo` VARCHAR(20) NOT NULL,
  `invoiceId` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`cardNo`, `invoiceId`),
  INDEX `fk_credit_card_in_invoice_invoice_idx` (`invoiceId` ASC),
  INDEX `fk_credit_card_in_invoice_card_idx` (`cardNo` ASC),
  CONSTRAINT `fk_credit_card_in_invoice_card`
    FOREIGN KEY (`cardNo`)
    REFERENCES `alterUber_DB`.`user_credit_card` (`cardNo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_credit_card_in_invoice_invoice`
    FOREIGN KEY (`invoiceId`)
    REFERENCES `alterUber_DB`.`invoice` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
