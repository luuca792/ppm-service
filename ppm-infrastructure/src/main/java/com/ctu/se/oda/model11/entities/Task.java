package com.ctu.se.oda.model11.entities;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import com.ctu.se.oda.model11.enums.TaskStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@Table(name = "tasks")
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Task implements IEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;

	@Column(name = "name")
	private String name;

	@Column(name = "description")
	private String description;

	@Column(name = "start_at")
	private LocalDate startAt;

	@Column(name = "end_at")
	private LocalDate endAt;

	@Column(name = "duration")
	private Double duration;

	@ManyToOne
	@JoinColumn(name = "project_id")
	private Project projectId;

	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "resource_id", referencedColumnName = "id")
	private Resource resource;

	@Enumerated(EnumType.STRING)
	@Column(name = "status")
	private TaskStatus status;
	
	@OneToMany(mappedBy = "taskId", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<TaskDependency> dependencies;

}
