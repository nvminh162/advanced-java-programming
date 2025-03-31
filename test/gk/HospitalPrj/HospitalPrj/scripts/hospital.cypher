
//# Tao cac constaints
CREATE CONSTRAINT unique_department_id FOR (d: Department) REQUIRE d.dept_id IS UNIQUE;
CREATE CONSTRAINT unique_doctor_id FOR (d: Doctor) REQUIRE d.doctor_id IS UNIQUE;
CREATE CONSTRAINT unique_patient_id FOR (d: Patient) REQUIRE d.patient_id IS UNIQUE;

show constraints;

//# LOAD departments
LOAD CSV WITH HEADERS FROM "file:///hospital/departments.csv" AS row
WITH row WHERE row.id IS NOT NULL
MERGE (d:Department {dept_id: row.id})
SET d.name = row.name,
    d.location = row.location;

//# LOAD doctors
LOAD CSV WITH HEADERS FROM "file:///hospital/doctors.csv" AS row
WITH row WHERE row.ID IS NOT NULL
MERGE (doc:Doctor {doctor_id: row.ID})
SET doc.name = row.Name,
    doc.speciality = row.Speciality,
    doc.phone = row.Phone;

//# LOAD patients
LOAD CSV WITH HEADERS FROM "file:///hospital/patients.csv" AS row
WITH row WHERE row.ID IS NOT null
MERGE (p:Patient {patient_id: row.ID})
SET p.name = row.Name,
    p.phone = row.Phone,
    p.address = row.Address,
    p.gender = row.Gender,
    p.date_of_birth = date(row.DateOfBirth);

//Find patients born in 1990
MATCH (p:Patient)
WHERE p.date_of_birth.year=1990
RETURN p;

//# LOAD BELONG_TO relationships
LOAD CSV WITH HEADERS FROM "file:///hospital/doctors.csv" AS row
WITH row WHERE row.ID IS NOT NULL AND row.DepartmentID IS NOT NULL
MATCH (d:Department {dept_id: row.DepartmentID})
MATCH (doc:Doctor {doctor_id: row.ID})
MERGE (doc)-[:BELONG_TO]->(d);

//# LOAD BE_TREATED relationships
LOAD CSV WITH HEADERS FROM "file:///hospital/treatments.csv" AS row
WITH row WHERE row.PatientID IS NOT NULL AND row.DoctorID IS NOT NULL
MATCH (doc:Doctor {doctor_id: row.DoctorID})
MATCH (p:Patient {patient_id: row.PatientID})
MERGE (p)-[:BE_TREATED {start_date: date(row.StartDate), end_date: date(row.EndDate), diagnosis: row.Diagnosis}]->(doc);

//# Script Cau a: Tạo một bác sĩ mới
CREATE (d:Doctor {doctor_id: $doctor_id, name: $name, phone: $phone, speciality: $speciality}) RETURN d

//# Script Cau b: Thống kê số bác sỹ theo từng chuyên khoa (speciality) của một khoa (department)
//nào đó khi biết tên khoa.
//+ getNoOfDoctorsBySpeciality (departmentName: String) : Map<String, Long>
MATCH (d:Doctor)-[:BELONG_TO]->(dep:Department {name: "Cardiology"})
RETURN d.speciality AS speciality, count(d) AS noOfDoctors

//# Script Cau c:  Tìm bác sĩ theo chuyên khoa (sử dụng FULL TEXT SEARCH)
//# Tạo FULL TEXT INDEX trên thuộc tính speciality của Doctor

CREATE FULLTEXT INDEX txt_index_speciality FOR (doc: Doctor) ON EACH [doc.speciality];
CALL db.index.fulltext.queryNodes("txt_index_speciality", "Sports") YIELD node, score RETURN node;

//# Script Cau d: Cập nhật nhật lại chuẩn đoán của bệnh nhân
MATCH (p:Patient {patient_id: $patientId})-[r:BE_TREATED]->(d:Doctor {doctor_id: $doctorId})
WHERE r.end_date IS NULL
SET r.diagnosis = $diagnosis

// r.end_date = date();

//Add some relationships between patients and doctors, and set the start_date property
MATCH (p:Patient {patient_id: "PT003"}), (d:Doctor {doctor_id: "DR.001"})
CREATE (p)-[:BE_TREATED {start_date: date("2025-03-01")}]->(d);

MATCH (p:Patient {patient_id: "PT003"}), (d:Doctor {doctor_id: "DR.001"})
RETURN p, d;

//Update the diagnosis and end_date properties of the relationship
MATCH (p:Patient {patient_id: "PT003"})-[r:BE_TREATED]->(d:Doctor {doctor_id: "DR.001"})
WHERE r.end_date IS NULL
SET r.diagnosis = "abc";

MATCH (p:Patient {patient_id: "PT003"})-[r:BE_TREATED]->(d:Doctor {doctor_id: "DR.001"})
RETURN p, d

//select after update
MATCH (p:Patient {patient_id: "PT003"})-[r:BE_TREATED]->(d:Doctor {doctor_id: "DR.001"})
RETURN r.start_date, r.end_date, r.diagnosis;

