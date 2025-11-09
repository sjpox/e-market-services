CREATE TABLE IF NOT EXISTS product_availability (
  availability_id VARCHAR(50) PRIMARY KEY,
  product_id      VARCHAR(50) NOT NULL,
  province_id     VARCHAR(50) NULL,
  municipality_id VARCHAR(50) NULL,
  barangay_id     VARCHAR(50) NULL,
  available       BOOLEAN DEFAULT TRUE,
  created_at      TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY (product_id) REFERENCES products(product_id),
  FOREIGN KEY (province_id) REFERENCES provinces(province_id),
  FOREIGN KEY (municipality_id) REFERENCES municipalities(municipality_id),
  FOREIGN KEY (barangay_id) REFERENCES barangays(barangay_id)
);
