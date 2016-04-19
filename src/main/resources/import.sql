-- User
INSERT INTO `user` (`id`, `username`, `password`, `fullname`, `email`, `aboutuser`) VALUES (1, 'reader01', '123456', 'Fausto John', 'fjohn@mum.edu', 'Fausto John is a reader');
INSERT INTO `user` (`id`, `username`, `password`, `fullname`, `email`, `aboutuser`) VALUES (2, 'publisher01', '123456', 'Bernie Black', 'bblack@mum.edu', 'Bernie Black is a publisher focus on Spring');
INSERT INTO `user` (`id`, `username`, `password`, `fullname`, `email`, `aboutuser`) VALUES (3, 'publisher02', '123456', 'Jimmy White', 'jwhite@mum.edu', 'Jimmy White is a publisher focus on Mobile Java');

-- User Role
INSERT INTO `user_roleset` (`User_id`, `roleSet`) VALUES (1, 'ROLE_READER'), (1, 'ROLE_PUBLISHER');
INSERT INTO `user_roleset` (`User_id`, `roleSet`) VALUES (2, 'ROLE_READER'), (2, 'ROLE_PUBLISHER');
INSERT INTO `user_roleset` (`User_id`, `roleSet`) VALUES (3, 'ROLE_READER'), (3, 'ROLE_PUBLISHER');

-- Category
INSERT INTO `category` (`id`, `name`, `description`) VALUES (1, 'Programming Careers', 'Careers news, information, and advice from JavaWorld');
INSERT INTO `category` (`id`, `name`, `description`) VALUES (2, 'Learn Java', 'Learn Java news, information, and how-to advice');
INSERT INTO `category` (`id`, `name`, `description`) VALUES (3, 'Mobile Java', 'Mobile Java news, information, and how-to advice');
INSERT INTO `category` (`id`, `name`, `description`) VALUES (4, 'Java App Dev', 'Java App Dev news, information, and how-to advice');

-- Article
INSERT INTO `article` (`id`, `subject`, `subtilte`, `content`, `publish_date`, `category_id`, `published_by`) VALUES (1, 'Which freaking big data programming language should I use?', 'When it comes to wrangling data at scale, R, Python, Scala, and Java have you covered -- mostly', 'You have a big data project. You understand the problem domain, you know what infrastructure to use, and maybe you have even decided on the framework you will use to process all that data, but one decision looms large: What language should I choose? (Or perhaps more pointed: What language should I force all my developers and data scientists to suffer?) It is a question that can be put off for only so long.', NOW(), 4, 2);

-- Comment
INSERT INTO `comment` (`id`, `content`, `post_date`, `article_id`, `post_id`) VALUES (1, 'Nice article!', NOW(), 1, 1);