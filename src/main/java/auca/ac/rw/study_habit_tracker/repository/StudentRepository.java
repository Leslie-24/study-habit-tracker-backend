package auca.ac.rw.study_habit_tracker.repository;
import auca.ac.rw.study_habit_tracker.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;
import java.util.List;

public interface StudentRepository extends JpaRepository<Student, UUID> {
    List<Student> findByLastNameIgnoreCaseOrderByFirstNameAsc(String lastName);
}
