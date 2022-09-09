-- To drop all tables
DROP TABLE IF EXISTS CONTACT_SKILL;
DROP TABLE IF EXISTS CONTACT;
DROP TABLE IF EXISTS SKILL;


-- Table Contact
CREATE TABLE CONTACT
(
    contact_id SERIAL PRIMARY KEY NOT NULL,
    first_name VARCHAR NOT NULL,
    last_name VARCHAR NOT NULL,
    full_name VARCHAR NOT NULL,
    city VARCHAR NOT NULL,
    postal_code NUMERIC NOT NULL,
    country VARCHAR NOT NULL,
    street_name VARCHAR NOT NULL,
    street_number NUMERIC NOT NULL,
    email VARCHAR NOT NULL,
    mobile_phone_number VARCHAR NOT NULL
);

-- Table Skill
CREATE TABLE SKILL
(
    name VARCHAR PRIMARY KEY NOT NULL,
    description VARCHAR
);

-- Table ContactSkill
CREATE TABLE CONTACT_SKILL
(
    contact_id SERIAL REFERENCES CONTACT,
    skill_name VARCHAR REFERENCES SKILL,
    level NUMERIC NOT NULL,
    PRIMARY KEY (contact_id, skill_name)
);

INSERT INTO CONTACT(first_name, last_name, full_name, city, postal_code, country, street_name, street_number, email, mobile_phone_number) VALUES ('Tom', 'Gobillard', 'Tom Gobillard', 'Annecy', 74000, 'France', 'Rue Vaugelas', 25, 'tom.goo@hotmail.com', '0655555555');