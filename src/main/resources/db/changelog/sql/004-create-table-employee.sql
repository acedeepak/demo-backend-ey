CREATE TABLE Employee (
    id NUMBER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    name VARCHAR2(150) NOT NULL,
    designation_id NUMBER NOT NULL,
    band NUMBER NOT NULL,
    created_on TIMESTAMP DEFAULT SYSTIMESTAMP NOT NULL,
    updated_on TIMESTAMP DEFAULT SYSTIMESTAMP NOT NULL,
    email_id VARCHAR2(255) UNIQUE NOT NULL,
    phone_number VARCHAR2(20),
    address VARCHAR2(1000),
    image_url VARCHAR2(1000),
    salary NUMBER,
    CONSTRAINT fk_employee_designation FOREIGN KEY (designation_id)
      REFERENCES Designation(id)
      ON DELETE CASCADE,
    CONSTRAINT fk_employee_band FOREIGN KEY (band)
      REFERENCES Band(id)
      ON DELETE CASCADE
)