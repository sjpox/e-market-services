CREATE TABLE IF NOT EXISTS products (
  product_id       VARCHAR(50) PRIMARY KEY,
  merchant_id      VARCHAR(50) NOT NULL,
  product_name     VARCHAR(255) NOT NULL,
  description      TEXT,
  category_id      VARCHAR(50) NOT NULL,
  price            DECIMAL(10,2) NOT NULL,
  stock_quantity   INT DEFAULT 0,
  status           ENUM('ACTIVE', 'INACTIVE') DEFAULT 'ACTIVE',
  created_at       TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  updated_at       TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  FOREIGN KEY (merchant_id) REFERENCES merchants(merchant_id),
  FOREIGN KEY (category_id) REFERENCES categories(category_id)
);
