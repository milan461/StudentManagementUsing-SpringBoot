package in.strikes.crudSpringBootDemo.service;

import in.strikes.crudSpringBootDemo.entity.Student;
import in.strikes.crudSpringBootDemo.repository.StudentRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private StudentRepository studentRepository;
    public StudentService(StudentRepository studentRepository){
        this.studentRepository=studentRepository;
    }
    public Student createStudent(Student studentReq) {
       Student res=studentRepository.save(studentReq);
       return res;
    }

    public Student getStudentById(Long id) {
        Optional<Student> respGet=studentRepository.findById(id);
        if(respGet.isPresent()){
            return respGet.get();
        }
        return null;
    }

    public List<Student> getStudents() {
        List<Student>studentList =studentRepository.findAll();
        return studentList;
    }

    public Student updateStudent(Long id,Student studentReq) {
        Optional<Student>existingStudent=studentRepository.findById(id);
        if(existingStudent.isEmpty()){
            return null;
        }
        Student studentToSave = existingStudent.get();
        studentToSave.setName(studentReq.getName());
        studentToSave.setAge(studentReq.getAge());
        studentToSave.setRollno(studentReq.getRollno());
        studentToSave.setEmail(studentReq.getEmail());
        studentToSave.setSubject(studentReq.getSubject());
        return  studentRepository.save(studentToSave);


    }

    public String deleteStudent(Long id) {
        Optional<Student> existingStudent = studentRepository.findById(id);
        if (existingStudent.isEmpty()) {
            return "student not found";

        } else {
            studentRepository.delete(existingStudent.get());
            return "student deleted successfully";
        }
    }
}
