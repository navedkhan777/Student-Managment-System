package Student.Mnaagment.System.Main.Entity;


import com.fasterxml.jackson.annotation.JsonAnyGetter;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;



@Entity
@Table(name = "studnets")

@Getter
@Setter
public class Student {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Email is required")
    private String course;

    @Email(message = "Invalid email")
    @NotBlank(message = "Email is required")
    private String email;

    public Student(){

    }

    public Student(String name, String course, String email){
        this.name = name;
        this.course = course;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getCourse() {
        return course;
    }

    public String getEmail() {
        return email;
    }
}
