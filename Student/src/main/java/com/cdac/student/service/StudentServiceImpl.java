package com.cdac.student.service;

import com.cdac.student.dao.StudentDao;
import com.cdac.student.entity.BaseEntity.UserRole;
import com.cdac.student.entity.Student;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class StudentServiceImpl implements StudentService {

    private final StudentDao studentDao;
    
    private final ModelMapper modelMapper;

    public StudentServiceImpl(StudentDao studentDao, ModelMapper modelMapper) {
        this.studentDao = studentDao;
        this.modelMapper = modelMapper;
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

    @Override
    public Student findByRollNo(String rollNo) {
        return studentDao.findByRollNo(rollNo);
    }

    @Override
    public void updateStudent(Student student) {
    Long id = student.getId(); 
        Student s1 = studentDao.findById(id).orElseThrow(()-> new RuntimeException("Student not found"));
        if (s1 == null) {
        throw new RuntimeException("Student not found");
    }
    
        
    modelMapper.typeMap(Student.class, Student.class).addMappings(mapper -> {
        mapper.skip(Student::setId);
        mapper.skip(Student::setUserRole);
    });
    modelMapper.map(student, s1);
    studentDao.save(s1);
    
    }

    @Override
    public void deleteStudentByRollNo(String rollNo) {
        studentDao.deleteByRollNo(rollNo);
    }

    @Override
    public void addStudent(Student student) {
        studentDao.save(student);
    }

    @Override
    public List<Student> findStudentsByStd(int std) {
        return studentDao.findByStd(std);
    }
    
    
    
    
    
}
