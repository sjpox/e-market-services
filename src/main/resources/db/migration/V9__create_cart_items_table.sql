CREATE TABLE IF NOT EXISTS cart_items (
  cart_item_id  VARCHAR(50) PRIMARY KEY,
  cart_id       VARCHAR(50) NOT NULL,
  product_id    VARCHAR(50) NOT NULL,
  quantity      INT NOT NULL,
  price_at_add  DECIMAL(10,2) NOT NULL,
  created_at    TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  updated_at    TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  FOREIGN KEY (cart_id) REFERENCES carts(cart_id),
  FOREIGN KEY (product_id) REFERENCES products(product_id)
);
