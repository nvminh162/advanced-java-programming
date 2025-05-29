select * from person;
select * from studentgrade;

-- Calculate the average score of the students' courses
-- DBMS - native query

select p.PersonID, p.firstName,p.lastName,p.enrollmentDate, avg(sg.grade) as avgGrade
from person p inner join studentgrade sg on p.personID = sg.studentID
where p.Discriminator = 'Student'
  and sg.grade is not null
group by  p.PersonID, p.firstName,p.lastName,p.enrollmentDate;

-- native query
-- Calculate the number of students in each department, the result is decreasing the number of students.

select d.DepartmentID, d.Name, d.Administrator, d.Budget, d.StartDate, count(sg.studentid) as number
from department d inner  join course c on c.DepartmentID = d.DepartmentID
                  inner join studentgrade sg on sg.CourseID = c.CourseID
group by d.DepartmentID, d.Name, d.Administrator, d.Budget, d.StartDate
order by number desc;
