INSERT INTO PUBLIC.projects (id, name, duration, creator_id) VALUES ('76ba10aa-d226-4775-97be-415bfd40a4bc', 'Predefined Project', '100', 'c2c8045d-affe-4ef4-bde3-dc2c801e495b');

INSERT INTO PUBLIC.tasks(id, name, description, duration, start_at, end_at, status, resource_id, project_id)
    VALUES ('96280f9d-151a-45bd-9eea-21eb481bd668', '0', 'Something', 2, NULL, NULL, NULL, NULL, (SELECT id FROM PUBLIC.projects WHERE name = 'Predefined Project'));
INSERT INTO PUBLIC.tasks(id, name, description, duration, start_at, end_at, status, resource_id, project_id)
    VALUES ('da89940f-cc2b-419c-ae91-f3b796f30f34', '1', 'Something', 1, NULL, NULL, NULL, NULL, (SELECT id FROM PUBLIC.projects WHERE name = 'Predefined Project'));
INSERT INTO PUBLIC.tasks(id, name, description, duration, start_at, end_at, status, resource_id, project_id)
    VALUES ('140cb6cc-09a7-4893-bca5-47444314559b', '2', 'Something', 2, NULL, NULL, NULL, NULL, (SELECT id FROM PUBLIC.projects WHERE name = 'Predefined Project'));
INSERT INTO PUBLIC.tasks(id, name, description, duration, start_at, end_at, status, resource_id, project_id)
    VALUES ('010e9fe3-87ac-48d9-8072-808f6cf3b10a', '3', 'Something', 3, NULL, NULL, NULL, NULL, (SELECT id FROM PUBLIC.projects WHERE name = 'Predefined Project'));
INSERT INTO PUBLIC.tasks(id, name, description, duration, start_at, end_at, status, resource_id, project_id)
    VALUES ('43fd7068-a97f-4699-a48d-5670623c5f70', '4', 'Something', 2, NULL, NULL, NULL, NULL, (SELECT id FROM PUBLIC.projects WHERE name = 'Predefined Project'));
INSERT INTO PUBLIC.tasks(id, name, description, duration, start_at, end_at, status, resource_id, project_id)
    VALUES ('b7b19ddd-604a-4f23-bcdb-09bea1b0f1a0', '5', 'Something', 4, NULL, NULL, NULL, NULL, (SELECT id FROM PUBLIC.projects WHERE name = 'Predefined Project'));
INSERT INTO PUBLIC.tasks(id, name, description, duration, start_at, end_at, status, resource_id, project_id)
    VALUES ('bc433a28-3ec1-4e12-8c3d-f6af86902e10', '6', 'Something', 1, NULL, NULL, NULL, NULL, (SELECT id FROM PUBLIC.projects WHERE name = 'Predefined Project'));
INSERT INTO PUBLIC.tasks(id, name, description, duration, start_at, end_at, status, resource_id, project_id)
    VALUES ('09c57873-8423-415c-9696-42e80f50e9bb', '7', 'Something', 2, NULL, NULL, NULL, NULL, (SELECT id FROM PUBLIC.projects WHERE name = 'Predefined Project'));

INSERT INTO PUBLIC.task_dependency (id, task_id, dependent_task_id, dependency_type)
    VALUES ('db7095dd-ab52-4371-a282-1f795f188458', (SELECT id FROM PUBLIC.tasks WHERE name = '1'), (SELECT id FROM PUBLIC.tasks WHERE name = '0'), 'FS');
INSERT INTO PUBLIC.task_dependency (id, task_id, dependent_task_id, dependency_type)
    VALUES ('597d58f8-c1de-4b92-9be3-02fca1a92483', (SELECT id FROM PUBLIC.tasks WHERE name = '5'), (SELECT id FROM PUBLIC.tasks WHERE name = '1'), 'FS');
INSERT INTO PUBLIC.task_dependency (id, task_id, dependent_task_id, dependency_type)
    VALUES ('a78aac31-3dc9-414a-80f1-43b7f411014a', (SELECT id FROM PUBLIC.tasks WHERE name = '7'), (SELECT id FROM PUBLIC.tasks WHERE name = '5'), 'FS');
INSERT INTO PUBLIC.task_dependency (id, task_id, dependent_task_id, dependency_type)
    VALUES ('5d5499f0-6b4f-4c65-9214-414a4a1fb928', (SELECT id FROM PUBLIC.tasks WHERE name = '6'), (SELECT id FROM PUBLIC.tasks WHERE name = '7'), 'FS');
INSERT INTO PUBLIC.task_dependency (id, task_id, dependent_task_id, dependency_type)
    VALUES ('b90dcfaa-f28b-4faa-8078-f2513b3b1106', (SELECT id FROM PUBLIC.tasks WHERE name = '2'), (SELECT id FROM PUBLIC.tasks WHERE name = '1'), 'FS');
INSERT INTO PUBLIC.task_dependency (id, task_id, dependent_task_id, dependency_type)
    VALUES ('8e209229-f0d5-4a81-86f5-1f6ef242097a', (SELECT id FROM PUBLIC.tasks WHERE name = '3'), (SELECT id FROM PUBLIC.tasks WHERE name = '1'), 'FS');
INSERT INTO PUBLIC.task_dependency (id, task_id, dependent_task_id, dependency_type)
    VALUES ('36c9402c-a284-432b-9b14-0b7f07c378a4', (SELECT id FROM PUBLIC.tasks WHERE name = '4'), (SELECT id FROM PUBLIC.tasks WHERE name = '2'), 'FS');
INSERT INTO PUBLIC.task_dependency (id, task_id, dependent_task_id, dependency_type)
    VALUES ('81ce39f1-7060-4b2a-9257-2c5c8a8584bf', (SELECT id FROM PUBLIC.tasks WHERE name = '4'), (SELECT id FROM PUBLIC.tasks WHERE name = '3'), 'FS');