CREATE TABLE IF NOT EXISTS product_categories (
    product_id VARCHAR(50) NOT NULL,
    category_id VARCHAR(50) NOT NULL,
    PRIMARY KEY (product_id, category_id),
    CONSTRAINT fk_pc_product
        FOREIGN KEY (product_id)
        REFERENCES products(product_id)
        ON DELETE CASCADE,
    CONSTRAINT fk_pc_category
        FOREIGN KEY (category_id)
        REFERENCES categories(category_id)
        ON DELETE CASCADE
);
