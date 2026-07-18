package Student.Mnaagment.System.Main.repository;

import Student.Mnaagment.System.Main.Entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepo extends JpaRepository<Student, Long> {

    List<Student> findByName(String name);

    List<Student> findByCourse(String course);

    List<Student> findByNameAndCourse(String name, String course);

    boolean existsByEmail(String email);
}
