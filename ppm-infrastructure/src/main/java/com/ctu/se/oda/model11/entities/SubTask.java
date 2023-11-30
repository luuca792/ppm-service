package com.ctu.se.oda.model11.entities;

import com.ctu.se.oda.model11.enums.TaskStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Data
@Table(name = "subtasks")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SubTask {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;

	@ManyToOne
	@JoinColumn(name = "task_parent_id")
	private Task taskParentId;

	@Column(name = "name")
	private String name;

	@Column(name = "description")
	private String description;

	@Column(name = "is_done")
	private Boolean isDone;
}
