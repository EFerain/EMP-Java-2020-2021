create TABLE IF NOT EXISTS epmschema."Employee"
(
	"empId"	serial,
	"name"	character varying(40)	NOT NULL,
	"address"	character varying(40)	NOT NULL,
	"mail"	character varying(100),
	PRIMARY KEY ("empId")
);