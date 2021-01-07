CREATE TABLE IF NOT EXISTS epmschema."Employee"
(
    "empId" serial,
    name character varying(40) NOT NULL,
    address character varying(100) NOT NULL,
    mail character varying(100) NOT NULL,
    PRIMARY KEY ("empId")
);

INSERT INTO epmschema."Employee" (name, address, mail) VALUES
('Marcel', 'Mons', 'marcel@gmail.com'),
('Banana', 'Londres', 'banana@gmail.com');