//-	Tạo các indexes hoặc các constraints cho dữ liệu trong đồ thị
//Course
CREATE CONSTRAINT uq_course_id
FOR (n:Course) REQUIRE n.course_id IS UNIQUE;

CREATE CONSTRAINT FOR (n:Course) REQUIRE n.course_id IS NOT NULL;

//Department
CREATE CONSTRAINT uq_department_id
FOR (n:Department) REQUIRE n.dept_id IS UNIQUE;

CREATE CONSTRAINT FOR (n:Department) REQUIRE n.dept_id IS NOT NULL;

//Student
CREATE CONSTRAINT uq_student_id
FOR (n:Student) REQUIRE n.student_id IS UNIQUE;

CREATE CONSTRAINT FOR (n:Student) REQUIRE n.student_id IS NOT NULL;

//ENROLLED
CREATE CONSTRAINT uq_enrolled_id FOR ()-[r:ENROLLED]-() REQUIRE (r.course_id, r.student_id) IS UNIQUE; //5.7

SHOW CONSTRAINT;

//course_id,name,hours,dept_id
LOAD CSV WITH HEADERS FROM 'file:///courses/courses.csv' AS row
WITH row WHERE row.course_id IS NOT NULL
MERGE (n:Course{course_id:row.course_id})
SET n.name = row.name,
n.hours = toInteger(row.hours);

//dept_id,name,dean,building,room
LOAD CSV WITH HEADERS FROM 'file:///courses/departments.csv' AS row
WITH row WHERE row.dept_id IS NOT NULL
MERGE (n:Department{dept_id:row.dept_id})
SET n.name = row.name,
n.dean = row.dean,
n.building = row.building,
n.room = row.room;

MATCH (n:Department) RETURN n LIMIT 25;

//student_id,name,gpa
LOAD CSV WITH HEADERS FROM 'file:///courses/students.csv' AS row
WITH row WHERE row.student_id IS NOT NULL
MERGE (n:Student{student_id:row.student_id})
SET n.name = row.name,
n.gpa = toFloat(row.gpa);

//Relationship between Department and Course
LOAD CSV WITH HEADERS FROM 'file:///courses/courses.csv' AS row
WITH row WHERE row.course_id IS NOT NULL
MATCH (c:Course{course_id:row.course_id})
MATCH (d:Department{dept_id:row.dept_id})
MERGE (c) - [:BELONGS_TO] -> (d);

//Relationship between Student and Course
//course_id,student_id
LOAD CSV WITH HEADERS FROM 'file:///courses/enrollments.csv' AS row
WITH row WHERE row.course_id IS NOT NULL
MATCH (c:Course{course_id:row.course_id})
MATCH (s:Student{student_id:row.student_id})
MERGE (s) - [:ENROLLED] -> (c);

//CRUD
//ADD===============================================================
CREATE (n:Course {course_id:"JAVA01", name:"Basic Java Programming", hours:30});
//READ
MATCH (c:Course{course_id:"JAVA01"}) RETURN c;
//UPDATE
MERGE (c:Course{course_id:"JAVA01", name:"Basic Java",hours:30})
//DELETE - Detach delete relationship and without can't delete if have relationship
MATCH (c:Course{course_id:"JAVA01"}) DETACH DELETE c;
//GET list
MATCH (c:Course) SKIP 3 LIMIT 3 RETURN c;

//Change password
ALTER USER neo4j SET PASSWORD 'root';

MATCH (s:Student)-[:ENROLLED]->(c:Course)-[:BELONGS_TO]->(d:Department)
RETURN d.name, COUNT(DISTINCT s) AS numStudents