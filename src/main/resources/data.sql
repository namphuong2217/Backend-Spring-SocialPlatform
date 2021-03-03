-- INSERT USERS

insert into my_user (user_id, username, email, password) values (nextval('user_seq'), 'Richard Carson', 'carson@gmail.com', '$2a$10$f3EHMliIBRP09G1ChqNOzewJd/xH9KLgDmdbGEGI83ZhneP.bivfy');
insert into my_user (user_id, username, email, password) values (nextval('user_seq'), 'Honor Miles', 'miles@gmail.com', '$2a$10$f3EHMliIBRP09G1ChqNOzewJd/xH9KLgDmdbGEGI83ZhneP.bivfy');
insert into my_user (user_id, username, email, password) values (nextval('user_seq'), 'Tony Roggers', 'roggers@gmail.com', '$2a$10$f3EHMliIBRP09G1ChqNOzewJd/xH9KLgDmdbGEGI83ZhneP.bivfy');

insert into community (community_id, community_name, description, user_id) VALUES
(nextval('community_seq'), 'First community', 'This is description of first community', 1);
insert into community (community_id, community_name, description, user_id) VALUES
(nextval('community_seq'), 'Second community', 'This is description of Second community', 2);
insert into community (community_id, community_name, description, user_id) VALUES
(nextval('community_seq'), 'Third community', 'This is description of Third community', 3);

insert into post (post_id, post_name, url, description, user_id, community_id) values
(nextval('post_seq'), 'This is the first post', 'http://google.com', 'The first post', 1, 1);
insert into post (post_id, post_name, url, description, user_id, community_id) values
(nextval('post_seq'), 'This is the second post', 'http://google.com', 'The second post', 2, 2);
insert into post (post_id, post_name, url, description, user_id, community_id) values
(nextval('post_seq'), 'This is the third post', 'http://google.com', 'The third post', 3, 3);

