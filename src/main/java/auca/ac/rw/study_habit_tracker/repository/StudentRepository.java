package auca.ac.rw.study_habit_tracker.repository;
import auca.ac.rw.study_habit_tracker.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface StudentRepository extends JpaRepository<Student, UUID> {
    List<Student> findByLastNameIgnoreCaseOrderByFirstNameAsc(String lastName);
    @Query("SELECT DISTINCT ss.student FROM StudySession ss WHERE ss.mentor.id = :mentorId")
    List<Student> findStudentsOfMentor(@Param("mentorId") UUID mentorId);
}
