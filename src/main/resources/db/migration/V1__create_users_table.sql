CREATE TABLE users (
    user_id           VARCHAR(50) PRIMARY KEY,
    first_name        VARCHAR(100),
    last_name         VARCHAR(100),
    email             VARCHAR(150) UNIQUE NOT NULL,
    password_hash     VARCHAR(255) NOT NULL,
    phone_number      VARCHAR(20),
    role              VARCHAR(50) DEFAULT 'CUSTOMER',
    created_at        TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at        TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
