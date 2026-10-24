package auca.ac.rw.study_habit_tracker.service;
import auca.ac.rw.study_habit_tracker.model.Mentor;
import auca.ac.rw.study_habit_tracker.repository.MentorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class MentorService {
    @Autowired 
    private MentorRepository mentorRepository;
    public String saveMentor(Mentor mentor) {
        mentorRepository.save(mentor);
        return "Mentor saved successfully";
    }
    public List<Mentor> getAllMentors() {
        return mentorRepository.findAll();
    }
    public Optional<Mentor> getMentorById(UUID id) {
        return mentorRepository.findById(id);
    }
    public String updateMentor(UUID id, Mentor newMentor) {
        Optional<Mentor> found = mentorRepository.findById(id);
        if (found.isEmpty()) {
            return "The mentor with that id does not exist";
        }
        Mentor mentor = found.get();
        mentor.setFirstName(newMentor.getFirstName());
        mentor.setLastName(newMentor.getLastName());
        mentor.setEmail(newMentor.getEmail());
        mentor.setStudyRoom(newMentor.getStudyRoom());
        mentor.setSubjects(newMentor.getSubjects());
        mentorRepository.save(mentor);
        return "Mentor updated successfully";
    }
    public String deleteMentor(UUID id) {
        if (!mentorRepository.existsById(id)) {
            return "The mentor with that id does not exist";
        }
        mentorRepository.deleteById(id);
        return "Mentor deleted successfully";
    }
    public List<Mentor> getMentorsBySubject(String name) {
        return mentorRepository.findMentorsBySubjectName(name);
    }
}
