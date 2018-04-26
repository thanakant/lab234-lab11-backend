package camt.se234.lab11.service;

import camt.se234.lab11.dao.StudentDaoImpl;
import camt.se234.lab11.entity.Student;
import org.junit.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
public class StudentServiceImplTest {
    @Test
    public void testFindById(){
        StudentDaoImpl studentDao = new StudentDaoImpl();
        StudentServiceImpl studentService = new StudentServiceImpl();
        studentService.setStudentDao(studentDao);
        assertThat(studentService.findStudentById("123"),is(new Student("123","A","temp",2.33)));
    }

}
