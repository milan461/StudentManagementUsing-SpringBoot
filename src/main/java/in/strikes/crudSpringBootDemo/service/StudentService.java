package in.strikes.crudSpringBootDemo.service;

import in.strikes.crudSpringBootDemo.dto.CreateStudentRequestDto;
import in.strikes.crudSpringBootDemo.dto.CreateStudentResponseDto;
import in.strikes.crudSpringBootDemo.entity.Student;
import in.strikes.crudSpringBootDemo.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private StudentRepository studentRepository;
    public StudentService(StudentRepository studentRepository){
        this.studentRepository=studentRepository;
    }
    public CreateStudentResponseDto createStudent(CreateStudentRequestDto studentReqDto) {
      Student student = mapToEntity(studentReqDto);
      Student studentDtoRes=studentRepository.save(student);
           return mapToDto(studentDtoRes);
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

    private Student mapToEntity(CreateStudentRequestDto studentReqDto) {
    Student student =new Student();
    student.setName(studentReqDto.getName());
    student.setEmail(studentReqDto.getEmail());
    student.setAge(studentReqDto.getAge());
    student.setRollno(studentReqDto.getRollno() );
    student.setSubject(studentReqDto.getSubject());
    student.setCreatedAt(LocalDateTime.now());
    student.setUpdatedAt(LocalDateTime.now());
    return student;
    }

    private CreateStudentResponseDto mapToDto(Student student){
    CreateStudentResponseDto createStudentResponseDto =new CreateStudentResponseDto();
    createStudentResponseDto.setId(student.getId());
    createStudentResponseDto.setName(student.getName());
    createStudentResponseDto.setAge(student.getAge());
    createStudentResponseDto.setRollno(student.getRollno());
    createStudentResponseDto.setEmail(student.getEmail());
    createStudentResponseDto.setSubject(student.getSubject());
    createStudentResponseDto.setCreatedAt(student.getCreatedAt());
    createStudentResponseDto.setUpdatedAt(student.getUpdatedAt());
    createStudentResponseDto.setMessage("Student Created Successfully");
    return createStudentResponseDto;
}
}