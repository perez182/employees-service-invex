DROP TABLE IF EXISTS employees;

CREATE TABLE employees (

    id               BIGINT AUTO_INCREMENT PRIMARY KEY,
    first_name       VARCHAR(50) NOT NULL,
    middle_name      VARCHAR(50),
    last_name        VARCHAR(50) NOT NULL,
    second_last_name VARCHAR(50),
    
    age              INT,

    gender           CHAR(1) CHECK (gender IN ('F', 'M')),

    birth_date       DATE NOT NULL,
    position         VARCHAR(100),

    created_at       TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    
    is_active        TINYINT DEFAULT 1 CHECK (is_active IN (0, 1))
);