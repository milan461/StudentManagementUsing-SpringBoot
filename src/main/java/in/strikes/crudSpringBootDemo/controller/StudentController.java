package in.strikes.crudSpringBootDemo.controller;

import in.strikes.crudSpringBootDemo.dto.CreateStudentRequestDto;
import in.strikes.crudSpringBootDemo.dto.CreateStudentResponseDto;
import in.strikes.crudSpringBootDemo.entity.Student;
import in.strikes.crudSpringBootDemo.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private StudentService studentService;
    private StudentController(StudentService studentService){
        this.studentService= studentService;
      }

    @PostMapping("/create")
    public ResponseEntity<CreateStudentResponseDto> createStudent(@RequestBody CreateStudentRequestDto studentRequestDto){
        CreateStudentResponseDto createdStudent = studentService.createStudent(studentRequestDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(createdStudent);
    }
   @GetMapping("/get/{id}")
    public ResponseEntity<Student>getAStudent(@PathVariable Long id){
         Student responseGet=studentService.getStudentById(id);
//         return ResponseEntity
//                 .status(HttpStatus.FOUND)
//                 .body(responseGet);
       if(responseGet==null){
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
       }
       return ResponseEntity.ok(responseGet);
   }

    @GetMapping("/getAll")
    public ResponseEntity<List<Student>> getAllStudent(){
        List<Student> responseList=studentService.getStudents();
//         return ResponseEntity
//                 .status(HttpStatus.FOUND)
//                 .body(responseGet);
        if(responseList.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(responseList);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Student>UpdateStudent(@PathVariable Long id,
                                            @RequestBody Student studentReq){
        Student responseGet=studentService.updateStudent(id,studentReq);
//         return ResponseEntity
//                 .status(HttpStatus.FOUND)
//                 .body(responseGet);
        if(responseGet==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(responseGet);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String>deleteAStudent(@PathVariable Long id) {
        String responseGet = studentService.deleteStudent(id);
//         return ResponseEntity
//                 .status(HttpStatus.FOUND)
//                 .body(responseGet);
        if (responseGet.equals("student not found")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(responseGet);
    }
}
