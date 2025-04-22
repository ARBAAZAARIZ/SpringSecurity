create table users(username varchar(50) not null primary key,password varchar(500) not null,enabled boolean not null);

create table authorities (username varchar(50) not null,authority varchar(50) not null,
constraint fk_authorities_users foreign key(username) references users(username));

create unique index ix_auth_username on authorities (username,authority)



INSERT IGNORE INTO `users` VALUES ('user', '{noop}nexus@5678', '1');
INSERT IGNORE INTO `authorities` VALUES ('user', 'read');

INSERT IGNORE INTO `users` VALUES ('admin', '{bcrypt}$2a$12$my.Ci4lw4lJGUXRlZl15DOuht9iCrVTKB9i580sDrr2OK5juHl5/i', '1');
INSERT IGNORE INTO `authorities` VALUES ('admin', 'admin');