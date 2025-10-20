CREATE TABLE municipality (
    municipality_id VARCHAR(50) PRIMARY KEY,
    municipality_name VARCHAR(100) NOT NULL,
    province_id VARCHAR(50) NOT NULL REFERENCES province(province_id) ON DELETE CASCADE
);
