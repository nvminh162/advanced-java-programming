// 22003405 - Nguyen Van Minh - StudySelf for Midterm

//-Tạo các indexes hoặc các constraints cho dữ liệu trong đồ thị
CREATE CONSTRAINT course_id_unique FOR (c:Course) REQUIRE c.course_id IS UNIQUE;
CREATE CONSTRAINT dept_id_unique FOR (d:Department) REQUIRE d.dept_id IS UNIQUE;
CREATE CONSTRAINT student_id_unique FOR (s:Student) REQUIRE s.student_id IS UNIQUE;
//CREATE INDEX course_id_index FOR (c:Course) ON (c.course_id);
//CREATE INDEX dept_id_index FOR (d:Department) ON (d.dept_id);
//CREATE INDEX student_id_index FOR (s:Student) ON (s.student_id);

SHOW CONSTRAINT

//-Load dữ liệu từ các CSV files
LOAD CSV WITH HEADERS FROM 'file:///courses/departments.csv' AS row
CREATE (:Department {
  dept_id: row.dept_id,
  name: row.name,
  dean: row.dean,
  building: row.building,
  room: row.room
});

LOAD CSV WITH HEADERS FROM 'file:///courses/courses.csv' AS row
CREATE (:Course {
  course_id: row.course_id,
  name: row.name,
  hours: toInteger(row.hours),
  dept_id: row.dept_id
});

LOAD CSV WITH HEADERS FROM 'file:///courses/students.csv' AS row
CREATE (:Student {
  student_id: row.student_id,
  name: row.name,
  gpa: toFloat(row.gpa)
});

//-Tạo các relationships giữa các nodes
LOAD CSV WITH HEADERS FROM 'file:///courses/enrollments.csv' AS row
MATCH (s:Student {student_id: row.student_id})
MATCH (c:Course {course_id: row.course_id})
CREATE (s)-[:ENROLLED_IN]->(c);

MATCH (c:Course)
MATCH (d:Department {dept_id: c.dept_id})
CREATE (c)-[:BELONGS_TO]->(d);

//Querying the Graph
//1.Liệt kê danh sách n sinh viên
MATCH (s:Student)
RETURN s
LIMIT 5

//2.Tìm kiếm sinh viên khi biết mã số
MATCH (s:Student {student_id: "13"})
RETURN s

//3.Tìm danh sách khóa học thuộc của một khoa nào đó khi biết mã khoa
MATCH (c:Course)-[:BELONGS_TO]->(d:Department {dept_id: "IE"})
RETURN c

//4.Cập nhật name = “Mathematics” cho department_id = “Math”
MATCH (d:Department {dept_id: "Math"})
SET d.name = "Mathematics"
RETURN d

//5.Cập nhật name = “Rock n Roll” cho department_id = “Music”
MATCH (d:Department {dept_id: "Music"})
SET d.name = "Rock n Roll"
RETURN d

//6.Thêm khóa học vào khoa IE: IE202, Simulation, 3 hours.
CREATE (c:Course {
  course_id: "IE202",
  name: "Simulation",
  hours: 3,
  dept_id: "IE"
})
WITH c
MATCH (d:Department {dept_id: "IE"})
CREATE (c)-[:BELONGS_TO]->(d)

//7.Xóa toàn bộ các khóa học -> Later
MATCH (c:Course) DETACH DELETE c

//8.Liệt kê tất cả các khoa
MATCH (d:Department) RETURN d

//9.Liệt kê tên của tất cả các trưởng khoa
MATCH (d:Department) RETURN d.dean

//10.Tìm tên của trưởng khoa CS
MATCH (d:Department {dept_id: "CS"}) RETURN d.dean

//11.Liệt kê tất cả các khóa học của CS và IE
MATCH (c:Course)-[b:BELONGS_TO]->(d:Department)
WHERE d.dept_id IN ["CS", "IE"]
RETURN c, b, d

//12.Liệt kê danh sách các tên của các sinh viên đăng ký học khóa học CS101
MATCH (s:Student)-[e:ENROLLED_IN]->(c:Course {course_id: "CS101"})
RETURN s, e, c

//13.Tổng số sinh viên đăng ký học của mỗi khoa
MATCH (s:Student)-[e:ENROLLED_IN]->(c:Course)-[b:BELONGS_TO]->(d:Department)
RETURN d.dept_id AS department, COUNT(DISTINCT s) AS totalStudent

//14.Tổng số sinh viên đăng ký học của mỗi khoa, kết quả sắp xếp theo mã khoa
MATCH (s:Student)-[e:ENROLLED_IN]->(c:Course)-[b:BELONGS_TO]->(d:Department)
RETURN d.dept_id AS department, COUNT(DISTINCT s) AS totalStudent
ORDER BY department;

//15.Tổng số sinh viên đăng ký học của mỗi khoa, kết quả sắp xếp theo số sinh viên
MATCH (s:Student)-[e:ENROLLED_IN]->(c:Course)-[b:BELONGS_TO]->(d:Department)
RETURN d.dept_id AS department, COUNT(DISTINCT s) AS totalStudent
ORDER BY totalStudent;

//16.Liệt kê danh sách tên của các trưởng khoa mà các khoa này không có sinh viên đăng ký học
MATCH (d:Department)
WHERE NOT (:Student)-[:ENROLLED_IN]->(:Course)-[:BELONGS_TO]->(d)
RETURN d.dean AS dean_name, d.name AS department_name;

//17.Danh sách khoa có số sinh viên đăng ký học nhiều nhất
MATCH (s:Student)-[:ENROLLED_IN]->(c:Course)-[:BELONGS_TO]->(d:Department)
WITH d, COUNT(DISTINCT s) AS total_students
WITH MAX(total_students) AS max_students
MATCH (s:Student)-[:ENROLLED_IN]->(c:Course)-[:BELONGS_TO]->(d:Department)
WITH d, COUNT(DISTINCT s) AS total_students, max_students
WHERE total_students = max_students
RETURN d.name AS department_name, total_students;

//18.Danh sách sinh viên có gpa >= 3.2, kết quả sắp xếp giảm dần theo
MATCH (s:Student)
WHERE s.gpa >= 3.2
RETURN s.student_id, s.name, s.gpa
ORDER BY s.gpa DESC;
