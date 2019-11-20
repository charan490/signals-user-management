SET FOREIGN_KEY_CHECKS = 0;

TRUNCATE TABLE company;
TRUNCATE TABLE feature;
TRUNCATE TABLE groups;
TRUNCATE TABLE playbook;
TRUNCATE TABLE playbook_feature;
TRUNCATE TABLE groups_playbook_feature;
TRUNCATE TABLE role;
TRUNCATE TABLE user_type;
TRUNCATE TABLE user;
TRUNCATE TABLE user_groups;

insert into company
values (1, "Signals Analytics. Inc");

insert into role
values (1, "Admin");

insert into user_type
values (1, "Signals");

INSERT INTO user
VALUES (1, 'N Kiran Kumar Kowlgi', 'kiran.kowlgi@gmail.com', '2020-12-31', '0a6395fd55f0fda9240a27d4ae597379', 1, 1, 1);

