CREATE TABLE IF NOT EXISTS roles (
	role VARCHAR(10) NOT NULL,
	PRIMARY KEY(role)
);
INSERT INTO roles ("user");
INSERT INTO roles ("driver");
INSERT INTO roles ("admin");

CREATE TABLE IF NOT EXISTS user (
	username VARCHAR(20) NOT NULL,
	password VARCHAR(20) NOT NULL,
	fulname VARCHAR(255),
	email VARCHAR(50) NOT NULL,
	telephone VARCHAR(14) DEFAULT NULL,
	photo TEXT DEFAULT NULL,
	role VARCHAR(10) NOT NULL,
	registrationDate DATETIME NOT NULL,
	deletedAccount INT(1) DEFAULT NULL,
	FOREIGN KEY(role) REFERENCES roles(role),
	PRIMARY KEY(username)
);

CREATE TABLE IF NOT EXISTS user_address (
	id INT NOT NULL AUTOINCREMENT,
	username VARCHAR(20) NOT NULL,
	address VARCHAR(255) NOT NULL,
	municipality VARCHAR(255) NOT NULL,
	district VARCHAR(255) NOT NULL,
	postalCode VARCHAR(5) NOT NULL,
	gmapPoint TEXT NOT NULL,
	FOREIGN KEY(username) REFERENCES user(username),
	UNIQUE KEY(username, city, address),
	PRIMARY KEY(id)
);

CREATE TABLE IF NOT EXISTS user_credit_card (
	user VARCHAR(20) NOT NULL,
	cardNo VARCHAR(20) NOT NULL,
	cardType VARCHAR(10),
	nameOnCard VARCHAR(100),
	securityCode INT(3),
	FOREIGN KEY(user) REFERENCES user(username),
	PRIMARY KEY(cardNo)
);

CREATE TABLE IF NOT EXISTS car (
	circNo VARCHAR(10) NOT NULL,
	maxPassengers INT(2) NOT NULL,
	maxLoad FLOAT NOT NULL,
	manufacturer VARCHAR(50) NOT NULL,
	model VARCHAR(255) NOT NULL,
	colour VARCHAR(50) NOT NULL,
	productionYear INT(4) NOT NULL,
	registrationDate DATETIME NOT NULL,
	deleted INT(1) DEFAULT NULL,
	FOREIGN KEY(category) REFERENCES car_categories(category),
	PRIMARY KEY(circNo)
);

CREATE TABLE IF NOT EXISTS driver_car (
	id INT NOT NULL AUTOINCREMENT,
	driver VARCHAR(20) NOT NULL,
	carCircNo VARCHAR(10) NOT NULL,
	deleted INT(1) DEFAULT NULL,
	FOREIGN KEY(driver) REFERENCES user(username),
	FOREIGN KEY(carCircNo) REFERENCES car(circNo),
	PRIMARY KEY(id)
);

CREATE TABLE IF NOT EXISTS charge_per_km (
	id INT NOT NULL AUTOINCREMENT,
	charge FLOAT NOT NULL,
	setDate DATETIME NOT NULL,
	PRIMARY KEY(id)
)
INSERT INTO charge_per_km (charge, setDate) VALUES (0.60, NOW());

CREATE TABLE IF NOT EXISTS trip (
	id INT NOT NULL AUTOINCREMENT,
	passenger VARCHAR(20) NOT NULL,
	startPoint TEXT NOT NULL,
	destination TEXT NOT NULL,
	noOfStops INT(2) NOT NULL,
	requestDate DATETIME NOT NULL,
	responseDate DATETIME,
	driverCar VARCHAR(20),
	tripStartTime DATETIME,
	tripEndTime DATETIME,
	passengerRatingOfDriver INT(1),
	passengerRatingOfCar INT(1),
	passengerComments TEXT,
	driverRating INT(1),
	driverComments TEXT,
	cancelledByPassenger INT(1),
	cancelledByDriver INT(1),
	FOREIGN KEY(driverCar) REFERENCES driver_car(id),
	FOREIGN KEY(passenger) REFERENCES user(username),
	PRIMARY KEY(id)
);

CREATE TABLE IF NOT EXISTS invoice (
	id INT NOT NULL AUTOINCREMENT,
	tripId INT NOT NULL,
	dateIssued DATETIME,
	chargePerKm FLOAT NOT NULL,
	vat FLOAT NOT NULL,
	distanceTravelledInKms FLOAT,
	totalCharge FLOAT,
	paidInCash INT(1),
	FOREIGN KEY(tripId) REFERENCES trip(id),
	PRIMARY KEY(id)
);

CREATE TABLE IF NOT EXISTS credit_card_in_invoice (
	cardNo VARCHAR(20) NOT NULL,
	invoiceId INT NOT NULL,
	FOREIGN KEY(cardNo) REFERENCES user_credit_card(cardNo),
	FOREIGN KEY(invoiceId) REFERENCES invoice(id),
	PRIMARY KEY(cardNo, invoiceId)
);
