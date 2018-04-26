package camt.se234.lab11.service;

import camt.se234.lab11.dao.StudentDaoImpl;
import camt.se234.lab11.entity.Student;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@RunWith(JUnitParamsRunner.class)
public class StudentServiceImplTest {
    @Test
    public void testFindById(){
        StudentDaoImpl studentDao = new StudentDaoImpl();
        StudentServiceImpl studentService = new StudentServiceImpl();
        studentService.setStudentDao(studentDao);
        assertThat(studentService.findStudentById("123"),is(new Student("123","A","temp",2.33)));
        assertThat(studentService.findStudentById("456"),is(new Student("456","B","bird",3.00)));
        assertThat(studentService.findStudentById("759"),is(new Student("759","","cat",1.0)));
        assertThat(studentService.findStudentById("100"),is(new Student("100","D","",4.0)));

    }
    @Test
    public void testGetAverageGpa(){
        StudentDaoImpl studentDao = new StudentDaoImpl();
        StudentServiceImpl studentService = new StudentServiceImpl();
        studentService.setStudentDao(studentDao);
        assertThat(studentService.getAverageGpa(),is(2.5825));
    }

    public Object paramsForTestGetAverageGpa(){
        return new Object[][]{
                {"111","","",4.0,4.0},
                {"111","","",3.0,3.0},
                {"111","","",2.0,2.0}

        };
    }
    @Test
    @Parameters(method = "paramsForTestGetAverageGpa")
    @TestCaseName("Test getGrade Params [{index}] : input is {0}, expect \"{1}\"")
    public void testGetAverageGpaparams(String id,String name ,String surname,double score,double expectedGrade){
        StudentDaoImpl studentDao = new StudentDaoImpl(id,name,surname,score);
        StudentServiceImpl studentService = new StudentServiceImpl();
        studentService.setStudentDao(studentDao);
        assertThat(studentService.getAverageGpa(),is(expectedGrade));

    }


}
