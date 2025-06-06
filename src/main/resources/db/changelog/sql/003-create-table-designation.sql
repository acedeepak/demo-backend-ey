CREATE TABLE Designation (
    id NUMBER PRIMARY KEY,
    name VARCHAR2(100) NOT NULL,
    department_id NUMBER NOT NULL,
    description VARCHAR2(4000),
    CONSTRAINT fk_designation_department FOREIGN KEY (department_id)
      REFERENCES Department(id)
      ON DELETE CASCADE
)