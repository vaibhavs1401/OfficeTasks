package com.cdac.student.service;

import com.cdac.student.dao.StudentDao;
import com.cdac.student.entity.Student;
import com.cdac.student.entity.BaseEntity.UserRole;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class StudentServiceImpl implements StudentService {

    private final StudentDao studentDao;

    public StudentServiceImpl(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    @Override
    public String register(Student s) {
        if (studentDao.existsByEmail(s.getEmail())) {
            return "EmailAlreadyExists";
        }
        Student toSave = new Student();
        toSave.setName(s.getName());
        toSave.setEmail(s.getEmail());
        toSave.setPassword(s.getPassword());     
        toSave.setStd(s.getStd());
        toSave.setAge(s.getAge());
        toSave.setRollNo(s.getRollNo());
        toSave.setUserRole(UserRole.ROLE_STUDENT); 
        studentDao.save(toSave);
        return "Success";
    }

    @Override
    @Transactional(readOnly = true)
    public Student authenticate(String email, String password) {
        Student user = studentDao.findByEmail(email);
        if (user == null) return null;
        return (password != null && password.equals(user.getPassword())) ? user : null;
    }

    @Override @Transactional(readOnly = true)
    public Student getStudentByRollNo(String rollNo) { return studentDao.findByRollNo(rollNo); }

    @Override @Transactional(readOnly = true)
    public List<Student> getAllStudents() { return studentDao.findAll(); }

    @Override @Transactional(readOnly = true)
    public List<Student> getStudentClassWise(int std) { return studentDao.findByStd(std); }
}
