CREATE TABLE IF NOT EXISTS categories (
  category_id    VARCHAR(50) PRIMARY KEY,
  category_name  VARCHAR(255) NOT NULL,
  description    TEXT
);
