package in.strikes.crudSpringBootDemo.dto;

import jakarta.validation.constraints.*;
import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDateTime;

public class CreateStudentResponseDto {
    //private Long id;
    @NotBlank(message = "name can't be null or empty")
    @Size(min=2,max=50)
    private String name;
    @Email(message = "email should have @")
    private String email;
    @Min(value = 18,message = "age should be grater than 18 ")
    private int age ;
    @NotEmpty(message = "fill valid roll no.")
    private Integer rollno;
    @NotBlank(message = "subject can't be null or empty")
    private String subject;
    private String message;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getRollno() {
        return rollno;
    }

    public void setRollno(int rollno) {
        this.rollno = rollno;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
