INSERT INTO PUBLIC.projects (id, name, duration, creator_id) VALUES ('Predefined Project', '100', 'c2c8045d-affe-4ef4-bde3-dc2c801e495b');

INSERT INTO PUBLIC.tasks(name, description, duration, start_at, end_at, status, resource_id, project_id, task_parent_id)
    VALUES ('0', 'Something', 2, NULL, NULL, NULL, NULL, (SELECT id FROM PUBLIC.projects WHERE name = 'Predefined Project'), NULL);
INSERT INTO PUBLIC.tasks(name, description, duration, start_at, end_at, status, resource_id, project_id, task_parent_id)
    VALUES ('1', 'Something', 1, NULL, NULL, NULL, NULL, (SELECT id FROM PUBLIC.projects WHERE name = 'Predefined Project'), NULL);
INSERT INTO PUBLIC.tasks(name, description, duration, start_at, end_at, status, resource_id, project_id, task_parent_id)
    VALUES ('2', 'Something', 2, NULL, NULL, NULL, NULL, (SELECT id FROM PUBLIC.projects WHERE name = 'Predefined Project'), NULL);
INSERT INTO PUBLIC.tasks(name, description, duration, start_at, end_at, status, resource_id, project_id, task_parent_id)
    VALUES ('3', 'Something', 3, NULL, NULL, NULL, NULL, (SELECT id FROM PUBLIC.projects WHERE name = 'Predefined Project'), NULL);
INSERT INTO PUBLIC.tasks(name, description, duration, start_at, end_at, status, resource_id, project_id, task_parent_id)
    VALUES ('4', 'Something', 2, NULL, NULL, NULL, NULL, (SELECT id FROM PUBLIC.projects WHERE name = 'Predefined Project'), NULL);
INSERT INTO PUBLIC.tasks(name, description, duration, start_at, end_at, status, resource_id, project_id, task_parent_id)
    VALUES ('5', 'Something', 4, NULL, NULL, NULL, NULL, (SELECT id FROM PUBLIC.projects WHERE name = 'Predefined Project'), NULL);
INSERT INTO PUBLIC.tasks(name, description, duration, start_at, end_at, status, resource_id, project_id, task_parent_id)
    VALUES ('6', 'Something', 1, NULL, NULL, NULL, NULL, (SELECT id FROM PUBLIC.projects WHERE name = 'Predefined Project'), NULL);
INSERT INTO PUBLIC.tasks(name, description, duration, start_at, end_at, status, resource_id, project_id, task_parent_id)
    VALUES ('7', 'Something', 2, NULL, NULL, NULL, NULL, (SELECT id FROM PUBLIC.projects WHERE name = 'Predefined Project'), NULL);

INSERT INTO PUBLIC.task_dependency (task_id, dependent_task_id, dependency_type)
    VALUES ((SELECT id FROM PUBLIC.tasks WHERE name = '1'), (SELECT id FROM PUBLIC.tasks WHERE name = '0'), 'SS');
INSERT INTO PUBLIC.task_dependency (task_id, dependent_task_id, dependency_type)
    VALUES ((SELECT id FROM PUBLIC.tasks WHERE name = '5'), (SELECT id FROM PUBLIC.tasks WHERE name = '1'), 'SS');
INSERT INTO PUBLIC.task_dependency (task_id, dependent_task_id, dependency_type)
    VALUES ((SELECT id FROM PUBLIC.tasks WHERE name = '7'), (SELECT id FROM PUBLIC.tasks WHERE name = '5'), 'SS');
INSERT INTO PUBLIC.task_dependency (task_id, dependent_task_id, dependency_type)
    VALUES ((SELECT id FROM PUBLIC.tasks WHERE name = '6'), (SELECT id FROM PUBLIC.tasks WHERE name = '7'), 'FS');
INSERT INTO PUBLIC.task_dependency (task_id, dependent_task_id, dependency_type)
    VALUES ((SELECT id FROM PUBLIC.tasks WHERE name = '2'), (SELECT id FROM PUBLIC.tasks WHERE name = '1'), 'FS');
INSERT INTO PUBLIC.task_dependency (task_id, dependent_task_id, dependency_type)
    VALUES ((SELECT id FROM PUBLIC.tasks WHERE name = '3'), (SELECT id FROM PUBLIC.tasks WHERE name = '1'), 'FS');
INSERT INTO PUBLIC.task_dependency (task_id, dependent_task_id, dependency_type)
    VALUES ((SELECT id FROM PUBLIC.tasks WHERE name = '4'), (SELECT id FROM PUBLIC.tasks WHERE name = '2'), 'FS');
INSERT INTO PUBLIC.task_dependency (task_id, dependent_task_id, dependency_type)
    VALUES ((SELECT id FROM PUBLIC.tasks WHERE name = '4'), (SELECT id FROM PUBLIC.tasks WHERE name = '3'), 'SS');