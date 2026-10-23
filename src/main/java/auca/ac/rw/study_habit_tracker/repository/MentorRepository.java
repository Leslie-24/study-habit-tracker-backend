package auca.ac.rw.study_habit_tracker.repository;
import auca.ac.rw.study_habit_tracker.model.Mentor;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface MentorRepository extends JpaRepository<Mentor, UUID> {
    
}
