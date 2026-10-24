package auca.ac.rw.study_habit_tracker.model;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "study_log")
public class StudyLog {
    @Id 
    @GeneratedValue 
    private UUID id;
    private String topic;
    private String reflection;
    private int minutesStudied;
    private LocalDate loggedDate;

    @ManyToOne 
    @JoinColumn(name="study_session_id")
    private StudySession studySession;
    public UUID getId() {
        return id; 
    }
    public void setId(UUID id) {
        this.id = id; 
    }
    public String getTopic() { 
        return topic; 
    }
    public void setTopic(String topic) { 
        this.topic = topic; 
    }
    public String getReflection() { 
        return reflection; 
    }
    public void setReflection(String reflection) { 
        this.reflection = reflection; 
    }
    public int getMinutesStudied() { 
        return minutesStudied; 
    }
    public void setMinutesStudied(int minutesStudied) { 
        this.minutesStudied = minutesStudied; 
    }
    public LocalDate getLoggedDate() { 
        return loggedDate; 
    }
    public void setLoggedDate(LocalDate loggedDate) { 
        this.loggedDate = loggedDate; 
    }
    public StudySession getStudySession() { 
        return studySession; 
    }
    public void setStudySession(StudySession studySession) { 
        this.studySession = studySession;
    }
}
