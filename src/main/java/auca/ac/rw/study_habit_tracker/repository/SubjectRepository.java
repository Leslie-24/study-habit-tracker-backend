package auca.ac.rw.study_habit_tracker.repository;
import auca.ac.rw.study_habit_tracker.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;


public interface SubjectRepository extends JpaRepository<Subject, UUID> {
    @Query("SELECT s FROM Subject s WHERE s.mentors IS EMPTY")
    List<Subject> findUnusedSubjects();
}
