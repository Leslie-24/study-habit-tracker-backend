package auca.ac.rw.study_habit_tracker.service;
import auca.ac.rw.study_habit_tracker.model.Mentor;
import auca.ac.rw.study_habit_tracker.model.StudyRoom;
import auca.ac.rw.study_habit_tracker.model.Subject;
import auca.ac.rw.study_habit_tracker.repository.MentorRepository;
import auca.ac.rw.study_habit_tracker.repository.StudyRoomRepository;
import auca.ac.rw.study_habit_tracker.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class MentorService {
    @Autowired 
    private MentorRepository mentorRepository;
    @Autowired
    private SubjectRepository subjectRepository;  
    @Autowired
    private StudyRoomRepository studyRoomRepository;
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
    public String addSubjectToMentor(UUID mentorId, UUID subjectId) {
        Optional<Mentor> foundMentor = mentorRepository.findById(mentorId);
        if (foundMentor.isEmpty()) {
            return "The mentor with that id does not exist";
        }
        Optional<Subject> foundSubject = subjectRepository.findById(subjectId);
        if (foundSubject.isEmpty()) {
            return "That subject does not exist";
        }
        Mentor mentor = foundMentor.get();
        Subject subject = foundSubject.get();
        for (Subject s : mentor.getSubjects()) {
            if (s.getId().equals(subject.getId())) {
                return "The mentor already has that subject";
            }
        }
        mentor.getSubjects().add(subject);
        mentorRepository.save(mentor);
        return "Subject added to the mentor";
    }
    public String changeStudyRoom(UUID mentorId, int roomNumber) {
        Optional<Mentor> foundMentor = mentorRepository.findById(mentorId);
        if (foundMentor.isEmpty()) {
            return "The mentor with that id does not exist";
        }
        Optional<StudyRoom> foundRoom = studyRoomRepository.findByRoomNumber(roomNumber);
        if (foundRoom.isEmpty()) {
            return "The study room with that roomNumber does not exist";
        }
        Mentor mentor = foundMentor.get();
        StudyRoom room = foundRoom.get();
        boolean isOwnRoom = mentor.getStudyRoom() != null
            && mentor.getStudyRoom().getId().equals(room.getId());
        if (!isOwnRoom && mentorRepository.existsByStudyRoom(room)) {
            return "That study room is taken by another mentor";
        }
        mentor.setStudyRoom(room);
        mentorRepository.save(mentor);
        return "Study room changed successfully";
    }
}
