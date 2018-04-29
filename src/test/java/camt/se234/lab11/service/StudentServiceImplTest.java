package camt.se234.lab11.service;

import camt.se234.lab11.dao.StudentDao;
import camt.se234.lab11.dao.StudentDaoImpl;
import camt.se234.lab11.entity.Student;
import camt.se234.lab11.exception.NoDataException;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(JUnitParamsRunner.class)
public class StudentServiceImplTest {
    StudentDao studentDao ;
    StudentServiceImpl studentService ;
    @Before
    public  void setUp(){
          studentDao = mock(StudentDao.class);
       studentService = new StudentServiceImpl();
        studentService.setStudentDao(studentDao);
    }

    @Test
    public void testFindById(){

        assertThat(studentService.findStudentById("123"),is(new Student("123","A","temp",2.33)));
        assertThat(studentService.findStudentById("456"),is(new Student("456","B","bird",3.00)));
        assertThat(studentService.findStudentById("759"),is(new Student("759","","cat",1.0)));
        assertThat(studentService.findStudentById("100"),is(new Student("100","D","",4.0)));

    }
    @Test
    public void testGetAverageGpa(){

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
        studentService.setStudentDao(studentDao);
        assertThat(studentService.getAverageGpa(),is(expectedGrade));

    }

    @Test
    public void testWithMock(){
        List<Student> mockStudents = new ArrayList<>();
        mockStudents.add(new Student("123","A","temp",2.23));
        mockStudents.add(new Student("124","B","temp",2.23));
        mockStudents.add(new Student("223","C","temp",2.23));
        mockStudents.add(new Student("224","D","temp",2.23));
        when(studentDao.findAll()).thenReturn(mockStudents);
        assertThat(studentService.findStudentById("123"),is(new Student("123","A","temp",2.23)));

    }

    @Test
    public void testWithMock2(){
        List<Student> mockStudents = new ArrayList<>();
        mockStudents.add(new Student("456","A","temp",2.23));
        mockStudents.add(new Student("789","B","temp",2.23));
        when(studentDao.findAll()).thenReturn(mockStudents);
        assertThat(studentService.findStudentById("789"),is(new Student("789","B","temp",2.23)));
    }

    @Test
    public void testAverageGPAWithMock2(){
        List<Student> mockStudents = new ArrayList<>();
        mockStudents.add(new Student("123","A","",2.00));
        mockStudents.add(new Student("456","B","",4.00));
        mockStudents.add(new Student("789","C","",3.00));
        when(studentDao.findAll()).thenReturn(mockStudents);
        assertThat(studentService.getAverageGpa(),is(3.00));
    }

    @Test
    public void testFindByPartOfId(){
        List<Student> mockStudents = new ArrayList<>();
        studentService.setStudentDao(studentDao);
        mockStudents.add(new Student("123","A","temp",2.33));
        mockStudents.add(new Student("124","B","temp",2.33));
        mockStudents.add(new Student("223","C","temp",2.33));
        mockStudents.add(new Student("224","D","temp",2.33));
        when(studentDao.findAll()).thenReturn(mockStudents);
        assertThat(studentService.findStudentByPartOfId("22"),hasItem(new Student("223","C","temp",2.33)));
        assertThat(studentService.findStudentByPartOfId("22"),hasItems(new Student("223","C","temp",2.33),new Student("224","D","temp",2.33)));

    }
    @Test
    public void testFindByPartOfId2(){
        List<Student> mockStudents = new ArrayList<>();
        mockStudents.add(new Student("111","A","",2.33));
        mockStudents.add(new Student("112","B","",2.33));
        mockStudents.add(new Student("222","C","",2.33));
        mockStudents.add(new Student("223","D","",2.33));
        when(studentDao.findAll()).thenReturn(mockStudents);
        assertThat(studentService.findStudentByPartOfId("11"),hasItems(new Student("111","A","",2.33),new Student("112","B","",2.33)));
        assertThat(studentService.findStudentByPartOfId("22"),hasItems(new Student("222","C","",2.33),new Student("223","D","",2.33)));
    }

    @Test (expected = NoDataException.class)
    public void testNoDataException(){
        List<Student> mockStudents = new ArrayList<>();
        mockStudents.add(new Student("123","A","temp",2.33));
        when(studentDao.findAll()).thenReturn(mockStudents);
        assertThat(studentService.findStudentById("55"),nullValue());
    }

    @Test (expected = NoDataException.class)
    public void testNoDataException2(){
        List<Student> mockStudents = new ArrayList<>();
        mockStudents.add(new Student("123","A","temp",2.33));
        when(studentDao.findAll()).thenReturn(mockStudents);
        assertThat(studentService.findStudentByPartOfId("55"),nullValue());
    }

    @Test (expected = ArithmeticException.class)
    public void testArithmeticException(){
        List<Student> mockStudents = new ArrayList<>();

        when(studentDao.findAll()).thenReturn(mockStudents);
        assertThat(studentService.getAverageGpa(),is(nullValue()));
    }





}

