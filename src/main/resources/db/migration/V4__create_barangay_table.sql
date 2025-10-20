CREATE TABLE barangay (
    barangay_id VARCHAR(50) PRIMARY KEY,
    barangay_name VARCHAR(100) NOT NULL,
    municipality_id VARCHAR(50) NOT NULL REFERENCES municipality(municipality_id) ON DELETE CASCADE
);

