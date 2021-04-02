DROP TABLE IF EXISTS credit_card;

CREATE TABLE credit_card (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  name VARCHAR(250) NOT NULL UNIQUE,
  card_number VARCHAR(250) NOT NULL UNIQUE,
  balance DECIMAL(20, 2) NOT NULL,
  debt_limit INT DEFAULT NOT null
);


  