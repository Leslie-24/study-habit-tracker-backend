package auca.ac.rw.study_habit_tracker.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table (name = "subject")
public class Subject {
    @Id 
    @GeneratedValue 
    private UUID id;
    private String name;
    @ManyToMany (mappedBy = "subjects")
    @JsonIgnore
    private List<Mentor> mentors = new ArrayList<>();

    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public List<Mentor> getMentors() {
        return mentors;
    }
    public void setMentors(List<Mentor> mentors) {
        this.mentors = mentors;
    }
}
