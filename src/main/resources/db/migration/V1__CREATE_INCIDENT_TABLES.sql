-- Table for main incidence records
CREATE TABLE IF NOT EXISTS incident (
    incident_id INT AUTO_INCREMENT PRIMARY KEY,
    shipment_id INT NOT NULL,            -- Foreign Key reference to shipment
    store_id INT NOT NULL,               -- Foreign Key reference to store
    user_email VARCHAR(255) NOT NULL,    -- Email of the user reporting the incidence
    status ENUM('OPEN', 'CLOSED') NOT NULL DEFAULT 'OPEN',
    solution VARCHAR(255) NULL,          -- Solution text, nullable
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    deleted_at TIMESTAMP DEFAULT NULL    -- Soft delete field
    );

-- Table for incidence details
CREATE TABLE IF NOT EXISTS incident_detail (
    incident_detail_id INT AUTO_INCREMENT PRIMARY KEY,
    incident_id INT NOT NULL,           -- Foreign Key reference to incidence
    product_sku VARCHAR(255) NOT NULL,   -- SKU of the product affected
    affected_quantity INT NOT NULL,      -- Quantity affected
    reason VARCHAR(255) NOT NULL,        -- Reason for the incidence ('Wrong product', etc.)
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    deleted_at TIMESTAMP DEFAULT NULL,   -- Soft delete field
    FOREIGN KEY (incident_id) REFERENCES incident(incident_id) ON DELETE CASCADE
    );
