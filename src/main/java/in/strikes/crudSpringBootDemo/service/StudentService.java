package in.strikes.crudSpringBootDemo.service;

import in.strikes.crudSpringBootDemo.dto.CreateStudentRequestDto;
import in.strikes.crudSpringBootDemo.dto.CreateStudentResponseDto;
import in.strikes.crudSpringBootDemo.dto.UpdateStudentRequestDto;
import in.strikes.crudSpringBootDemo.dto.UpdateStudentResponseDto;
import in.strikes.crudSpringBootDemo.entity.Student;
import in.strikes.crudSpringBootDemo.exception.DuplicateResourceException;
import in.strikes.crudSpringBootDemo.exception.ResourceNotFoundException;
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
      if(emailExists(student)){
          throw new DuplicateResourceException("Student with Email"+student.getEmail()+"already exist");
      }
      Student studentDtoRes=studentRepository.save(student);
           return mapToDto(studentDtoRes);
        }


    public CreateStudentResponseDto getStudentById(Long id) {
        Student studentResp=studentRepository
                .findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Student with id"+ id+"not found"));

        return mapToDto(studentResp);

    }

    public List<Student> getStudents() {
        List<Student>studentList =studentRepository.findAll();
        return studentList;
    }

    public UpdateStudentResponseDto updateStudent(Long id, UpdateStudentRequestDto studentReq) {

        Student existingStudent=studentRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Student with id"+ id+"not found"));



        existingStudent.setName(studentReq.getName());
        existingStudent.setAge(studentReq.getAge());
        existingStudent.setRollno(studentReq.getRollno());
       //studentToSave.setEmail(studentReq.getEmail());
        existingStudent.setSubject(studentReq.getSubject());
        Student studentrespo =  studentRepository.save(existingStudent);

           return mapToUpdateDto(studentrespo);
    }

    public void deleteStudent(Long id) {
      Student existingStudent = studentRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Student with id"+ id+"not found"));
//        if (existingStudent.isEmpty()) {
//            return "student not found";
//
//        }
            studentRepository.delete(existingStudent);


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
   // createStudentResponseDto.setId(student.getId());
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

   private UpdateStudentResponseDto mapToUpdateDto(Student student){
        UpdateStudentResponseDto updatedStudent=new UpdateStudentResponseDto();
        updatedStudent.setName(student.getName());
        updatedStudent.setAge(student.getAge());
        updatedStudent.setRollno(student.getRollno());
        updatedStudent.setSubject(student.getSubject());
        updatedStudent.setEmail(student.getEmail());
        updatedStudent.setUpdatedAt(student.getUpdatedAt());
        updatedStudent.setId(student.getId());
        updatedStudent.setMessage("Student updated successfully");
           return updatedStudent;

   }


   private boolean emailExists(Student student){
        return studentRepository.existsByEmail(student.getEmail());
   }
}