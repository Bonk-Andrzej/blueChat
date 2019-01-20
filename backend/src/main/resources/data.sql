

INSERT INTO photo(photo) VALUES ('rgb(88,218,71)')
INSERT INTO photo(photo) VALUES ('rgb(0,140,178)')
INSERT INTO photo(photo) VALUES ('rgb(218,132,160)')
INSERT INTO photo(photo) VALUES ('rgb(10,28,66)')

INSERT INTO photo(photo) VALUES ('rgb(23,151,11)')
INSERT INTO photo(photo) VALUES ('rgb(33,27,29)')


INSERT INTO user(nick, password, email, description, id_photo ) VALUES ('Igor', 'igorpass', 'igorsowa@wp.pl', 'I am a very clever guy', 1)
INSERT INTO user(nick, password, email, description, id_photo ) VALUES ('Paweł', 'pawelpass', 'paweljastrzebski96@gmail.com', 'I am a very clever guy', 2)
INSERT INTO user(nick, password, email, description, id_photo ) VALUES ('Milena', 'milenapass', 'milena@wp.pl', 'I am a very clever guy', 3)
INSERT INTO user(nick, password, email, description, id_photo ) VALUES ('test', 'test', 'test@wp.pl', 'some testing value description', 4)

INSERT INTO user_contain_friend(date_friend_ship, user1, user2) values (DATE_ADD(NOW(), INTERVAL 1 DAY ), 4 , 1)
INSERT INTO user_contain_friend(date_friend_ship, user1, user2) values (DATE_ADD(NOW(), INTERVAL 2 DAY ), 1 , 3)
INSERT INTO user_contain_friend(date_friend_ship, user1, user2) values (DATE_ADD(NOW(), INTERVAL 3 DAY ), 1 , 2)
INSERT INTO user_contain_friend(date_friend_ship, user1, user2) values (DATE_ADD(NOW(), INTERVAL 4 DAY ), 4 , 2)
INSERT INTO user_contain_friend(date_friend_ship, user1, user2) values (DATE_ADD(NOW(), INTERVAL 5 DAY ), 4 , 3)

INSERT INTO channel(name, is_public, channel_owner, id_photo ) VALUE ('general', true , 1, 6)
INSERT INTO channel(name, is_public, channel_owner, id_photo ) VALUE ('SmallTalk', true , 2, 5)

INSERT INTO channel_contain_users(id_channel, id_user) VALUE (1, 1)
INSERT INTO channel_contain_users(id_channel, id_user) VALUE (1, 2)
INSERT INTO channel_contain_users(id_channel, id_user) VALUE (1, 3)
INSERT INTO channel_contain_users(id_channel, id_user) VALUE (1, 4)

INSERT INTO channel_contain_users(id_channel, id_user) VALUE (2, 1)
INSERT INTO channel_contain_users(id_channel, id_user) VALUE (2, 2)
INSERT INTO channel_contain_users(id_channel, id_user) VALUE (2, 4)

INSERT INTO channels_message(content, sent_date, channel_id, sender) VALUES ('Welcome All', DATE_ADD(NOW(), INTERVAL 5 MINUTE ), 1 , 1)
INSERT INTO channels_message(content, sent_date, channel_id, sender) VALUES ('I thing that uor application is grate :)', DATE_ADD(NOW(), INTERVAL 6 MINUTE ), 1 , 1)
INSERT INTO channels_message(content, sent_date, channel_id, sender) VALUES ('Welcome too', DATE_ADD(NOW(), INTERVAL 7 MINUTE ), 1 , 2)
INSERT INTO channels_message(content, sent_date, channel_id, sender) VALUES ('Nice to see, have opportunity to talk in group message', DATE_ADD(NOW(), INTERVAL 8 MINUTE ), 1 , 2)
INSERT INTO channels_message(content, sent_date, channel_id, sender) VALUES ('Hello guys, Yes your application will help us to have save communicator',DATE_ADD(NOW(), INTERVAL 9 MINUTE ), 1 , 3)

INSERT INTO message(content, sent_date, is_read, receiver, sender) VALUES ('Hello ;)', DATE_ADD(NOW(), INTERVAL 5 MINUTE ), false, 1 , 2)
INSERT INTO message(content, sent_date, is_read, receiver, sender) VALUES ('Hello', DATE_ADD(NOW(), INTERVAL 6 MINUTE ), false, 2 , 1)
INSERT INTO message(content, sent_date, is_read, receiver, sender) VALUES ('I am very glad', DATE_ADD(NOW(), INTERVAL 7 MINUTE ), false, 1 , 2)
INSERT INTO message(content, sent_date, is_read, receiver, sender) VALUES ('Why you are glad ?', DATE_ADD(NOW(), INTERVAL 8 MINUTE ), false, 2 , 1)
INSERT INTO message(content, sent_date, is_read, receiver, sender) VALUES ('You think about our application ?', DATE_ADD(NOW(), INTERVAL 9 MINUTE ), false, 2 , 1)
INSERT INTO message(content, sent_date, is_read, receiver, sender) VALUES ('Yes ;)', DATE_ADD(NOW(), INTERVAL 10 MINUTE ), false, 1 , 2)
INSERT INTO message(content, sent_date, is_read, receiver, sender) VALUES ('It nice to have it in mobile device ;)', DATE_ADD(NOW(), INTERVAL 11 MINUTE ), false, 1 , 2)
INSERT INTO message(content, sent_date, is_read, receiver, sender) VALUES ('I hope that we will be develop our communicator', DATE_ADD(NOW(), INTERVAL 12 MINUTE ), false, 1 , 2)
INSERT INTO message(content, sent_date, is_read, receiver, sender) VALUES ('Until we reach all goals ', DATE_ADD(NOW(), INTERVAL 13 MINUTE ), false, 1 , 2)
INSERT INTO message(content, sent_date, is_read, receiver, sender) VALUES ('I hope too ;)', DATE_ADD(NOW(), INTERVAL 14 MINUTE ), false, 2 , 1)
INSERT INTO message(content, sent_date, is_read, receiver, sender) VALUES ('so see you nex time :)', DATE_ADD(NOW(), INTERVAL 15 MINUTE ), false, 1 , 2)
INSERT INTO message(content, sent_date, is_read, receiver, sender) VALUES ('Bye bye..', DATE_ADD(NOW(), INTERVAL 16 MINUTE ), false, 1 , 2)
INSERT INTO message(content, sent_date, is_read, receiver, sender) VALUES ('bye ..', DATE_ADD(NOW(), INTERVAL 17 MINUTE ), false, 2 , 2)

