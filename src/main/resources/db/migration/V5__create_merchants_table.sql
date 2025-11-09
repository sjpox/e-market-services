CREATE TABLE IF NOT EXISTS merchants (
  merchant_id     VARCHAR(50) PRIMARY KEY,
  merchant_name   VARCHAR(255) NOT NULL,
  email           VARCHAR(255) UNIQUE,
  contact_number  VARCHAR(50),
  address         TEXT,
  created_at      TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  updated_at      TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);
