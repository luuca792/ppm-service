package com.ctu.se.oda.model11.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "project-configuration")
@Data
@NoArgsConstructor
public class ProjectConfiguration {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @JsonIgnore
    @OneToOne(mappedBy = "projectConfiguration")
    private Project project;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    List<ProjectConfigurationEmail> projectConfigurationEmails = new ArrayList<>();

    public ProjectConfiguration(UUID id, Project project) {
        this.id = id;
        this.project = project;
    }

    @Override
    public String toString() {
        return "ProjectConfiguration{" +
                "id=" + id +
                ", project=" + project.getId() +
                '}';
    }
}
