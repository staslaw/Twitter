package pl.coderslab.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import pl.coderslab.model.User;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "teachers")
public class Teacher extends User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToMany
    @JsonIgnore
    private Set<Subject> subjects;
    @OneToOne(mappedBy = "educator")
    @JsonIgnore
    private Team educatorGroup;
    @ManyToMany
    @JsonIgnore
    private List<Team> teams;
    @OneToMany(mappedBy = "teacher")
    @JsonIgnore
    private List<Lesson> lessons = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Team getEducatorGroup() {
        return educatorGroup;
    }

    public void setEducatorGroup(Team educatorGroup) {
        this.educatorGroup = educatorGroup;
    }

    public Set<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(Set<Subject> subjectList) {
        this.subjects = subjectList;
    }

    public List<Team> getTeams() {
        return teams;
    }

    public void setTeams(List<Team> groupList) {
        this.teams = groupList;
    }

    public List<Lesson> getLessons() {
        return lessons;
    }

    public void setLessons(List<Lesson> lessons) {
        this.lessons = lessons;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }
}
