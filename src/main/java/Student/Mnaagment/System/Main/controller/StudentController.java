package Student.Mnaagment.System.Main.controller;

import Student.Mnaagment.System.Main.Entity.Student;
import Student.Mnaagment.System.Main.service.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(
        name = "Student Managment APIs",
        description = "CRUD Operation for Student Managment System"
)
@RestController
@RequestMapping("/Students")
public class StudentController {


    @Autowired
    private StudentService studentService;

    @PostMapping
    public Student addStudent(@Valid @RequestBody Student student){
        return studentService.addStudent(student);
    }


    @Operation(
            summary = "Get All Students",
            description = "Return All students with pagination and sorting"
    )
    @GetMapping
    public Page<Student> getAllStudent(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy){
        return studentService.getAllStudent(page, size, sortBy);
    }

    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable Long id){
        return studentService.getStudentById(id);
    }

    @PutMapping("/{id}")
    public Student updateStudent(@PathVariable Long id, @RequestBody Student student){
        return studentService.updateStudent(id, student);
    }

    @DeleteMapping("/{id}")
    public String deleteStudent(@PathVariable Long id){
        return studentService.deleteStudent(id);
    }

    @GetMapping("/name/{name}")
    public List<Student> getStudentByName(@PathVariable String name){
        return studentService.findByName(name);
    }

    @GetMapping("/course/{course}")
    public List<Student> getStudentByCourse(@PathVariable String course){
        return studentService.findByCourse(course);
    }

    public List<Student> searchStudent(@RequestParam String name, @RequestParam String course){
        return studentService.findByNameAndCourse(name , course);
    }
}
