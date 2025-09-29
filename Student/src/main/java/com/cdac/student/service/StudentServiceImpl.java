
package com.cdac.student.service;

import com.cdac.student.dao.StudentDaoImpl;
import com.cdac.student.entity.Student;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author hcdc
 */



@Service
@Transactional
public class StudentServiceImpl implements StudentService{

    private StudentDaoImpl studentDao; 
    @Override
    public String register(Student s) {
        Student s1 = studentDao.findByEmail(s.getEmail());
        if(s1 != null){
            return "EmailAlreadyExists";
        }
        else{
        Student s2 = new Student();
        s2.setAge(s.getAge());
        s2.setEmail(s.getEmail());
        s2.setName(s.getName());
        s2.setPassword(s.getPassword());
        return "Success";
        }
        
    }
    
    

    @Override
    public Student getStudentByRollNo(String rollNo) {
        Student s3 = studentDao.findByRollNo(rollNo);
        return s3;
    }

    @Override
    public List<Student> getAllStudents() {
      List ls = studentDao.findAll();
      return ls;
    }

    @Override
    public List<Student> getStudentClassWise(String studentClass) {
        
        return studentDao.getStudentsByClass(studentClass);
    }
    
    
    
    
}
    

