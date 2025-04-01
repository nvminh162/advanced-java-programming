//csv
//departments.csv
//id	    name	                location
//DEP001	Internal  Medicine	  3rd Floor

//doctors.csv
//ID	    Name	      Phone	        Speciality	            DepartmentID
//DR.001	John Smith	0987.654.321	Dermatology Services	  DEP001

//patients.csv
//ID	    Name	      Phone	        Gender	  DateOfBirth	    Address
//PT001	  Lucy Brown	012-345-6789	FEMALE	  1/1/1990	      123 Main Street - New York

//treatments.csv
//DoctorID	PatientID	  StartDate	  EndDate	    Diagnosis
//DR.013	  PT007	      1/1/2022	  1/2/2022	  Throat infection (tonsillitis)

//Câu 1:
//- Tạo các unique index trên các thuộc tính id cho tất cả các node.
// https://neo4j.com/docs/cypher-manual/current/constraints/syntax/#create-property-uniqueness-constraints
CREATE CONSTRAINT uq_department_id
FOR (d:Department)
REQUIRE d.department_id IS UNIQUE;

CREATE CONSTRAINT uq_doctor_id
FOR (d:Doctor)
REQUIRE d.doctor_id IS UNIQUE;

CREATE CONSTRAINT uq_patient_id
FOR (p:Patient)
REQUIRE p.patient_id IS UNIQUE;

SHOW CONSTRAINT

//- Load dữ liệu từ các CSV file cho trước vào database.
// https://neo4j.com/docs/cypher-manual/5/clauses/load-csv/
LOAD CSV WITH HEADERS FROM 'file:///hospital/departments.csv' AS row
WITH row
WHERE row.id IS NOT NULL
MERGE (d:Department {department_id: row.id})
SET d.name = row.name,
    d.location = row.location;

LOAD CSV WITH HEADERS FROM 'file:///hospital/doctors.csv' AS row
WITH row
WHERE row.ID IS NOT NULL
MERGE (d:Doctor {doctor_id: row.ID})
SET d.name = row.Name,
    d.phone = row.Phone,
    d.speciality = row.Speciality;

LOAD CSV WITH HEADERS FROM 'file:///hospital/patients.csv' AS row
WITH row
WHERE row.ID IS NOT null
MERGE (p:Patient {patient_id: row.ID})
SET p.name = row.Name,
    p.phone = row.Phone,
    p.gender = row.Gender,
    p.dateOfBirth = date(row.DateOfBirth),
    p.address = row.Address;

//Test date
MATCH (p:Patient)
WHERE p.dateOfBirth.year<2000
RETURN p;

//- Thiết lập các relationships giữa các node như mô hình đồ thị cho trên.
//https://neo4j.com/docs/cypher-manual/5/clauses/load-csv/
LOAD CSV WITH HEADERS FROM 'file:///hospital/doctors.csv' AS row
WITH row
WHERE row.ID IS NOT NULL AND row.DepartmentID IS NOT NULL
MATCH (doc:Doctor {doctor_id: row.ID})
MATCH (dep:Department {department_id: row.DepartmentID})
MERGE (doc)-[:BELONG_TO]->(dep);

LOAD CSV WITH HEADERS FROM 'file:///hospital/treatments.csv' AS row
WITH row
WHERE row.DoctorID IS NOT NULL AND row.PatientID IS NOT NULL
MATCH (d:Doctor {doctor_id: row.DoctorID})
MATCH (p:Patient {patient_id: row.PatientID})
MERGE (p)-[b:BE_TREATED]->(d)
SET b.startDate = date(row.StartDate),
    b.endDate = date(row.EndDate),
    b.diagnosis = row.Diagnosis;

//Cau 3
//a. Thêm mới một bác sỹ.
CREATE (d:Doctor {doctor_id: "22003405"})
SET d.name = "NguyenVanMinh",
    d.phone = "0353999798",
    d.speciality = "Kỹ thuật phần mềm"
RETURN d

MATCH (d:Doctor {doctor_id: "22003405"})
RETURN d

//b. Thống kê số bác sỹ theo từng chuyên khoa (speciality)
//   của một khoa (department) nào đó khi biết tên khoa.
MATCH (d:Doctor)-[:BELONG_TO]->(dep:Department {name: "Cardiology"})
RETURN d.speciality AS Speciality, count(d) AS NumOfDoctors

//c. Dùng full-text search, tìm kiếm các bác sỹ theo chuyên khoa.
//   https://neo4j.com/docs/cypher-manual/current/indexes/semantic-indexes/full-text-indexes/#summary
CREATE FULLTEXT INDEX txt_index_speciality
FOR (d:Doctor)
ON EACH [d.speciality];

CALL db.index.fulltext.queryNodes("txt_index_speciality", "Research")
YIELD node, score
RETURN node;

SHOW INDEXES YIELD name, type WHERE type = "FULLTEXT";

//d. Cập nhật lại chẩn đoán (diagnosis) của một lượt điều trị khi biết mã số bác sỹ và mã
//số bệnh nhân. Lưu ý, chỉ được phép cập nhật khi lượt điều trị này vẫn còn đang điều
//trị (tức ngày kết thúc điều trị là null)
MATCH (p:Patient {patient_id: "PT003"})-[b:BE_TREATED]->(d:Doctor {doctor_id: "DR.001"})
WHERE b.endDate IS NULL
SET b.diagnosis = "abcd"

MATCH (p:Patient {patient_id: "PT003"}), (d:Doctor {doctor_id: "DR.001"})
CREATE (p)-[:BE_TREATED {startDate: date("2025-03-01")}]->(d);

MATCH (p:Patient {patient_id: "PT003"})-[b:BE_TREATED]->(d:Doctor {doctor_id: "DR.001"})
RETURN p, b, d