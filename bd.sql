DROP TABLE Insurance_contract;
DROP TABLE Employee;
DROP TABLE Branch;
DROP TABLE Client;
DROP TABLE Tariff;

CREATE TABLE Tariff (
	Tariff_id INT NOT NULL CONSTRAINT PK_TariffID PRIMARY KEY,
	title VARCHAR(100) NOT NULL,
	yearH_IR FLOAT NOT NULL,
	year_IR FLOAT NOT NULL,
	year3_IR FLOAT NOT NULL 
);

CREATE TABLE Client(
	passport_id INT NOT NULL CONSTRAINT PK_passportID PRIMARY KEY,
	first_name VARCHAR(20) NOT NULL,
	middle_name VARCHAR(20) NOT NULL,
	last_name VARCHAR(20) NOT NULL,
	address VARCHAR(100) NOT NULL,
	phone_number VARCHAR(20) NOT NULL
);

CREATE TABLE Branch(
	branch_id INT NOT NULL CONSTRAINT PK_branchID PRIMARY KEY,
	branch_name VARCHAR(20) NOT NULL,
	address VARCHAR(100) NOT NULL,
	phone_number VARCHAR(20) NOT NULL
);

CREATE TABLE Employee(
	employee_id INT NOT NULL CONSTRAINT PK_employeeID PRIMARY KEY,
	password_emp VARCHAR(20) NOT NULL,
	first_name VARCHAR(20)  NOT NULL,
	middle_name VARCHAR(20)  NOT NULL,
	last_name VARCHAR(20)  NOT NULL,
	phone_number VARCHAR(20)  NOT NULL,
	sex VARCHAR(20)  NOT NULL,
	appointment VARCHAR(20)  NOT NULL,
	branch_id INT CONSTRAINT FK_branchID REFERENCES Branch (branch_id)
);

CREATE TABLE Insurance_contract (
	contract_id INT PRIMARY KEY,
	employee_id INT CONSTRAINT FK_employeeID REFERENCES Employee (employee_id),
	passport_id INT CONSTRAINT FK_passportID REFERENCES Client (passport_id),
	Tariff_id INT CONSTRAINT FK_TariffID REFERENCES Tariff (Tariff_id),
	date_start DATE  NOT NULL, 
	time FLOAT  NOT NULL,
	interest_rate FLOAT  NOT NULL,
	payment_amount FLOAT  NOT NULL
);

INSERT INTO Branch(branch_id, branch_name, address, phone_number)
                VALUES (0, 'ZA WARUDO', 'ул.Пушкина д.Калотушкина', '88005553535')
				
SELECT * FROM Employee WHERE employee_id = 1

SELECT passport_id FROM Client WHERE passport_id = 1

SELECT * FROM Tariff

INSERT INTO Insurance_contract(contract_id, employee_id, passport_id, tariff_id, date_start, time, interest_rate, payment_amount) VALUES (2, 1, 228, 1, NOW(), 0.5, 5.0, 5000.0)
