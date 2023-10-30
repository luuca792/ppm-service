package com.ctu.se.oda.model11.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@Table(name = "tasks")
@NoArgsConstructor
public class Task implements IEntity{
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "description", nullable = true)
    private String description;
    @Column(name = "start_at")
    private LocalDate startAt;
    @Column(name = "end_at")
    private LocalDate endAt;
    @Column(name = "duration")
    private Double duration;
    @Column(name = "project_id")
    private UUID projectId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_parent_id")
    private Task taskParent;
    @OneToMany(mappedBy = "taskParent", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Task> subtasks = new ArrayList<>();

    public Task(String name, String description, LocalDate startAt, LocalDate endAt, Double duration, UUID projectId) {
        this.name = name;
        this.description = description;
        this.startAt = startAt;
        this.endAt = endAt;
        this.duration = duration;
        this.projectId = projectId;
    }

    public Task(UUID id, String name, String description, LocalDate startAt, LocalDate endAt, Double duration, UUID projectId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.startAt = startAt;
        this.endAt = endAt;
        this.duration = duration;
        this.projectId = projectId;
    }

    public Task getTaskParent() {
        return taskParent;
    }
    public void setTaskParent(UUID taskParentId) {
        if(this.taskParent == null) {
            this.taskParent = new Task();
        }
        this.taskParent.setId(taskParentId);
    }

    public void setTaskParent(Task taskParent) {
        this.taskParent = taskParent;
    }

    public List<Task> getSubtasks() {
        if (subtasks == null) {
            subtasks = new ArrayList<>();
        }
        return subtasks;
    }

    public void setSubtasks(List<Task> subtasks) {
        this.subtasks = subtasks;
        for (Task subtask : this.subtasks) {
            subtask.setTaskParent(this);
        }
    }

    public void addSubtask(Task subtask) {
        subtasks.add(subtask);
        subtask.setTaskParent(this);
    }
}
