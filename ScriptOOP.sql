

CREATE TABLE USERS 
(
  idUser VARCHAR(45) NOT NULL,
  Password VARCHAR(45) NOT NULL,
  UName VARCHAR(45) NOT NULL,
  PRIMARY KEY (idUser));

INSERT INTO USERS (idUser, Password, UName) VALUES 
('Admin', 'Admin12', 'Barry'),
('aiza12@yahoo.com', 'aiza1201', 'Aiza'),
('alia12@gmail.com', 'Al02$', 'Alia'),
('dana65@iau.edu.sa', 'dana786', 'Dana'),
('dareen43@gmail.com', 'petstar43', 'Dareen'),
('hebaM@yahoo.com', 'Mheba$17', 'Heba'),
('sarah5@gmail.com', 'sarah32', 'Sara');


CREATE TABLE ADMINS (
  idAdmin INT NOT NULL,
  Email VARCHAR(45) NOT NULL,
  User_idUser VARCHAR(45) NOT NULL,
  PRIMARY KEY (idAdmin),
    FOREIGN KEY (User_idUser)
    REFERENCES USERS (idUser)
ON DELETE CASCADE);

INSERT INTO ADMINS (idAdmin, Email, User_idUser) VALUES 
(1234, 'admin12@gmail.com','Admin');


CREATE TABLE CUSTOMERS (
  idCustomer INT NOT NULL GENERATED ALWAYS AS IDENTITY,
  Phone VARCHAR(45) NOT NULL,
  RewardPoints INT,
  User_idUser VARCHAR(45) NOT NULL,
  PRIMARY KEY (idCustomer),
    FOREIGN KEY (User_idUser)
    REFERENCES USERS (idUser)
    ON DELETE CASCADE);

INSERT INTO CUSTOMERS (Phone, RewardPoints, User_idUser) VALUES
('0548583214', 50, 'alia12@gmail.com'),
('0543622378', 10, 'hebaM@yahoo.com'),
('0534481553', 0, 'sarah5@gmail.com'),
('0521883214', 30, 'dareen43@gmail.com'),
('0509166442', 20, 'dana65@iau.edu.sa'),
('0547895647', 100, 'aiza12@yahoo.com');



CREATE TABLE SERVICES(
  idService INT NOT NULL GENERATED ALWAYS AS IDENTITY,
  ServiceName VARCHAR(45) NOT NULL,
  Price DOUBLE NOT NULL,
  PRIMARY KEY (idService));


INSERT INTO SERVICES (ServiceName, Price) VALUES 
('Haircut', 70),
('Shower', 50),
('Nail trim', 35),
('Check up', 100),
('Rabies Vaccine', 200),
('Dental Cleaning', 150),
('Deworming', 45),
('Blood Test', 400),
('X-ray', 150);

CREATE TABLE APPOINTMENTS(
  idAppointment INT NOT NULL GENERATED ALWAYS AS IDENTITY,
  Date DATE NOT NULL,
  Time VARCHAR(45) NOT NULL,
  CustomerName VARCHAR(45),
  Customer_idCustomer INT NOT NULL,
  S_id INT NOT NULL,
  PRIMARY KEY (idAppointment),
    FOREIGN KEY (Customer_idCustomer)
    REFERENCES CUSTOMERS (idCustomer)
    ON DELETE CASCADE,
    FOREIGN KEY (S_id)
    REFERENCES SERVICES (idService)
    ON DELETE CASCADE);

INSERT INTO APPOINTMENTS (Date, Time, CustomerName, Customer_idCustomer,S_id) VALUES
('2021-10-20', '8:00', 'Alia', 1, 1),
('2021-10-23', '12:30', 'Heba', 2, 2),
('2021-10-23', '13:00', 'Dareen', 4, 2),
('2023-02-01', '3:15', 'Sara', 3, 3);

CREATE TABLE PAYMENTS (
  idPayment INT NOT NULL GENERATED ALWAYS AS IDENTITY,
  PayMethod VARCHAR(45),
  TotalAmount DOUBLE NOT NULL,
  Tax DOUBLE,
  Date DATE,
  PRIMARY KEY (idPayment));

  INSERT INTO PAYMENTS (PayMethod, TotalAmount, Tax, Date) VALUES 
  ('Cash', 57.5, 0.15, '2021-09-07'),
  ('Credit', 135, 0.15, '2021-10-15'),
  ('Cash', 34.5, 0.15, '2021-11-05'),
  ('Cash', 80, 0.15, '2022-01-05'),
  ('Cash', 130, 0.15, '2022-01-05'),
  ('Credit', 27, 0.15, '2022-02-10'),
  ('Credit', 50, 0.15, '2022-02-22'),
  ('Credit', 47.5, 0.15, '2022-02-12'),
  ('Cash', 50, 0.15, '2023-01-02'),
  ('Credit', 20, 0.15, '2022-05-17');


CREATE TABLE CREDITPAYMENTS (
  idCreditPayment INT NOT NULL GENERATED ALWAYS AS IDENTITY,
  CardType VARCHAR(45) NOT NULL,
  Payment_idPayment INT NOT NULL,
  PRIMARY KEY (idCreditPayment),
    FOREIGN KEY (Payment_idPayment)
    REFERENCES PAYMENTS (idPayment)
    ON DELETE CASCADE);

INSERT INTO CREDITPAYMENTS (CardType, Payment_idPayment) VALUES 
('VISA', 1),
('MasterCard', 5),
('ApplePay', 6),
('VISA', 7),
('VISA', 9),
('MADA', 10);

CREATE TABLE GROOMINGSERVICES (
  idGroomingService INT NOT NULL GENERATED ALWAYS AS IDENTITY,
  PetType VARCHAR(45) NOT NULL,
  Package VARCHAR(45) NOT NULL,
  sID INT NOT NULL,
  PRIMARY KEY (idGroomingService),
    FOREIGN KEY (sID)
    REFERENCES SERVICES(idService)
    ON DELETE CASCADE);

INSERT INTO GROOMINGSERVICES (PetType, Package, sID) VALUES
('CAT', 'Healthy', 2),
('CAT', 'DRY', 2),
('DOG', 'PREMIUM', 1),
('DOG', 'BASIC', 2),
('DOG', 'HEALTHY', 2),
('CAT', 'PREMIUM', 3);

CREATE TABLE ITEMS (
  idItem INT NOT NULL GENERATED ALWAYS AS IDENTITY,
  ItemName VARCHAR(60) NOT NULL,
  Price DOUBLE NOT NULL,
  Quantity INT NOT NULL,
  PetType VARCHAR(45) NOT NULL,
  Image VARCHAR(100),
  Flavor VARCHAR(45),
  FoodType VARCHAR(45),
  Expiry DATE,
  PRIMARY KEY (idItem));

INSERT INTO ITEMS (ItemName, Price, Quantity, PetType, Image, Flavor, FoodType, Expiry) VALUES
('BRIT INDOOR', 50, 40, 'CAT', 'BRIT_INDOOR.jpg','CHICKEN','DRY','2023-03-01' ),
('COLLAR RED', 20, 60, 'CAT', 'Collar_red.png', NULL, NULL, NULL),
('KITKAT LITTER BABY POWDER', 40, 30, 'CAT', 'KKBP.png', NULL, NULL, NULL),
('LIFECAT POUCH', 7, 100, 'CAT', 'LCP.png', 'SALMON','WET','2023-09-22'),
('TRIXIE SHAMPOO', 30, 20, 'DOG', 'TS.png', NULL, NULL, NULL),
('BRIT TREATS', 20, 50, 'DOG', 'BT.png','BEEF','DRY','2024-01-01'),
('CLIPPERS', 30, 10, 'CAT', 'Clippers.jpg', NULL, NULL, NULL);

CREATE TABLE PETS (
  Name VARCHAR(45) NOT NULL,
  Age INT,
  PetType VARCHAR(45) NOT NULL,
  Customer_idCustomer INT NOT NULL,
  PRIMARY KEY (Name, Customer_idCustomer),
    FOREIGN KEY (Customer_idCustomer)
    REFERENCES CUSTOMERS(idCustomer)
    ON DELETE CASCADE);

INSERT INTO PETS (Name, Age, PetType, Customer_idCustomer) VALUES
('BREEZY',3,'DOG',1),
('COCO', 3, 'DOG', 2),
('MERLIN', 5, 'CAT', 4),
('MILLIE', 2, 'CAT', 6),
('SMOKEY', 4, 'CAT', 3);

CREATE TABLE VETSERVICES (
  idVetService INT NOT NULL GENERATED ALWAYS AS IDENTITY,
  VetServiceType VARCHAR(45) NOT NULL,
  SID INT NOT NULL,
  PRIMARY KEY (idVetService),
    FOREIGN KEY (SID)
    REFERENCES SERVICES (idService)
    ON DELETE CASCADE);

INSERT INTO VETSERVICES (VetServiceType, SID) VALUES 
('VACCINATION', 5),
('DENTAL', 6),
('MEDICATION', 7),
('LAB', 8),
('LAB', 9),
('CHECK UP', 4);

CREATE TABLE VETDOCTORS (
  VetDoctorName VARCHAR(45) NOT NULL,
  VID INT NOT NULL,
  PRIMARY KEY (VetDoctorName, VID),
    FOREIGN KEY (VID)
    REFERENCES VETSERVICES (idVetService)
    ON DELETE CASCADE);

INSERT INTO VETDOCTORS (VetDoctorName, VID) VALUES 
('ABDULLAH', 1),
('MARIA', 1),
('HESHAM', 2),
('MAGED', 2),
('HESHAM', 4),
('HESHAM',5),
('MARIA',3),
('MARIA', 6);







