package camt.se234.lab11.dao;

import camt.se234.lab11.entity.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentDaoImpl implements StudentDao {
    List<Student> students;
    public StudentDaoImpl(){
        students = new ArrayList<>();
        students.add(new Student("123","A","temp",2.33));
        students.add(new Student("456","B","bird",3.00));
        students.add(new Student("759","","cat",1.0));
        students.add(new Student("100","D","",4.0));

    }
    public StudentDaoImpl(String id, String n,String s,double score){
        students = new ArrayList<>();
        students.add(new Student(id,n,s,score));
    }

    @Override
    public List<Student> findAll() {
        return this.students;
    }
}
