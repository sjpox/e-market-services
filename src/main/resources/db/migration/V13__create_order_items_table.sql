CREATE TABLE order_items (
  order_item_id VARCHAR(50) PRIMARY KEY,
  order_id      VARCHAR(50) NOT NULL,
  product_id    VARCHAR(50) NOT NULL,
  quantity      INT NOT NULL,
  price_at_order DECIMAL(10,2) NOT NULL,
  FOREIGN KEY (order_id) REFERENCES orders(order_id),
  FOREIGN KEY (product_id) REFERENCES products(product_id)
);
