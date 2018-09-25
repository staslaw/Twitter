# SchoolOnlineRegister

Application with access for three types of users:
  1. Admin - manages classes of teachers, students, classes, subjects, classrooms, news.
  - standard CRUD functions
  - relations between classes: subject - teacher, student - class, subject - class and many other
  - after adding a new teacher or student, the application draws a password and sends him an email with his username and password
  2. Teacher - browses the news to teachers and introduces grades to classes that teach.
  - creates a series of grades and determines their weights and then adds grades to each student
  3. Student - browses the news to students and his grades.
  - sees the class average and the his weighted average of each subject

The application uses:
  1. Spring boot libraries: validation, data-jpa, security, web.
  2. In addition: hibernate, mysql-conector, jstl, tomcat, mail.
  3. In the viewing layer: HTML, CSS, JSP, Bootstrap, JavaScript, JQuery, ajax.
