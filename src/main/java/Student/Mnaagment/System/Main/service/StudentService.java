package Student.Mnaagment.System.Main.service;

import Student.Mnaagment.System.Main.Entity.Student;
import Student.Mnaagment.System.Main.exception.EmailAlreadyExistsException;
import Student.Mnaagment.System.Main.exception.StudentNotFoundException;
import Student.Mnaagment.System.Main.repository.StudentRepo;
import ch.qos.logback.classic.boolex.StubEventEvaluator;
import org.hibernate.service.spi.InjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class StudentService {

    @Autowired
    private StudentRepo studentRepo;

    @Autowired
     private RedisTemplate<String, Object> redisTemplate;


    public void saveName(){
        redisTemplate.opsForValue().set("name", "Naved");
    }

    public String getName(){
        redisTemplate.opsForValue().get("name");
    }

    public void saveOtp(){
        redisTemplate
                .opsForValue()
                .set("otp", "123456", 60, TimeUnit.SECONDS);
    }

    public boolean deleteName(){
        return Boolean.TRUE.equals(redisTemplate.delete("name"));
    }

    public Student addStudent(Student student){
        if(studentRepo.existsByEmail(student.getEmail())){
            throw new EmailAlreadyExistsException("Email Already Exception");
        }
        return studentRepo.save(student);
    }


    public Page<Student> getAllStudent(int page , int size, String sortBy){
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return studentRepo.findAll(pageable);
    }

    @Cacheable(value = "students", key = "#id")
    public Student getStudentById(Long id){
        return studentRepo.findById(id).orElseThrow(() -> new StudentNotFoundException("Student not found with id : " + id));
    }

    @CachePut(value = "students", key = "id")
    public Student updateStudent(Long id, Student updateStudent){
        Student student = studentRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        student.setName(updateStudent.getName());
        student.setCourse(updateStudent.getCourse());

        return studentRepo.save(student);
    }

    @CacheEvict(value = "students", key = "#id")
    public String deleteStudent(Long id){
        studentRepo.findById(id).orElseThrow(() -> new RuntimeException("Student Not found"));
        studentRepo.deleteById(id);

        return "Student deleted Successfully";
    }

    public List<Student> findByName(String name){
        return studentRepo.findByName(name);
    }

    public List<Student> findByCourse(String course){
        return studentRepo.findByCourse(course);
    }

    public List<Student> findByNameAndCourse(String name, String course){
        return studentRepo.findByNameAndCourse(name, course);
    }
}
