package Student.Mnaagment.System.Main;

import Student.Mnaagment.System.Main.Entity.Student;
import Student.Mnaagment.System.Main.controller.StudentController;
import Student.Mnaagment.System.Main.exception.StudentNotFoundException;
import Student.Mnaagment.System.Main.repository.StudentRepo;
import Student.Mnaagment.System.Main.service.StudentService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.lang.module.ResolutionException;
import java.util.Optional;

import static javax.swing.UIManager.get;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Disabled
@ExtendWith(MockitoExtension.class)
public class StudentServiceTest {

    @Mock
    private StudentRepo studentRepo;

    @InjectMocks
    private StudentService studentService;

    @Test
    void testGetStudentByid(){

         Student student = new Student();
         student.setId(1L);
         student.setName("Rahul");

         when(studentRepo.findById(1l))
                 .thenReturn(Optional.of(student));

         Student result = studentService.getStudentById(1l);

         assertEquals("Rahul", result.getName());
    }

    @Test
    void testGetStudentById_NotFound(){

        when(studentRepo.findById(1l))
            .thenReturn(Optional.empty());

        assertThrows(
                StudentNotFoundException.class,
                () -> studentService.getStudentById(1l)
        );
    }
    
    @WebMvcTest(StudentController.class)
    public class StudentControllerTest{

        @Autowired
        private MockMvc mockMvc;

        @MockitoBean
        private StudentService studentService;


//        @Test
//        void testGetStudentById() throws Exception{
//
//            Student student = new Student();
//            student.setId(1l);
//            student.setName("rahul");
//
//            when(studentService.getStudentById(1l))
//                    .thenReturn(student);
//
//            MockMvc.perform(get("/Students/1"))
//                    .andExpect(status().isOk())
//                    .andExpect(jsonPath("$.id").value(1))
//                    .andExpect(jsonPath("$.name").value("Rahul"));
//        }
    }
}
