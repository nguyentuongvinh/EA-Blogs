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
INSERT INTO `article` (`id`, `subject`, `subtilte`, `content`, `publish_date`, `category_id`, `published_by`) VALUES (1, 'Why IT tasks take as long as they take', 'An hour or a week? For technical work, estimating the time required is the eternal challenge', "In the decades I�ve been in deep IT, there have been a few constants in the sea of change. One of those constants is that the simpler solution is generally the better solution. Also, estimating the amount of time any one task will require is a fool�s errand. We�ve all been in technical meetings where a widget needs to be tweaked, a service needs to be modified, or what have you. Invariably a nontechnical person will state the task can�t be hard to do, and an engineer or developer, eyes growing wide, will counter that it will take several days at least. Many times, another techie, perhaps looking to curry favor, will chime in and say it should only take a few hours to work out. The truth is usually somewhere in the middle.", '2016-03-07 03:00:00', 1, 2);
INSERT INTO `article` (`id`, `subject`, `subtilte`, `content`, `publish_date`, `category_id`, `published_by`) VALUES (2, 'Open source Java projects: Spring Batch', 'Reading and writing CSV files with Spring Batch and MySQL', "Implementing a batch process to handle gigabytes of data is a veritable tsunami of a task, but you can take it down a chunk with the help of Spring Batch. This popular Spring module has been engineered to handle the details of batch processing for all sorts of files. Get started with Spring Batch by building a simple job that imports products from a CSV file into a MySQL database, then explore the module's batch processing capabilities with a single or multiple processors and one or more helpful tasklets. Finally, get a quick overview of Spring Batch's resiliency tools for skipping records, retrying records, and restarting batch jobs.Exploring JavaFX's Application class JavaFX's Application class is an essential part of every JavaFX application.", '2014-07-29 12:13:00', 4, 3);
INSERT INTO `article` (`id`, `subject`, `subtilte`, `content`, `publish_date`, `category_id`, `published_by`) VALUES (3, 'Java 101: Polymorphism in Java', 'Use subtype polymorphism to execute different forms of the same method', "Our universe exhibits many examples of entities that can change form: A butterfly morphs from larva to pupa to imago, its adult form. On Earth, the normal state of water is liquid, but water changes to a solid when frozen, and to a gas when heated to its boiling point. This ability to change form is known as polymorphism. Modeling polymorphism in a programming language lets you create a uniform interface to different kinds of operands, arguments, and objects. The result is code that is more concise and easier to maintain.", '2016-02-23 12:47:00', 2, 2);
INSERT INTO `article` (`id`, `subject`, `subtilte`, `content`, `publish_date`, `category_id`, `published_by`) VALUES (4, 'Sputnik automates code review for Java projects on GitHub', 'More than a service, Sputnik can be downloaded and deployed, and integration with other code hosting services is on the road map', "Sputnik, a new service that performs continuous automatic code reviews for Java, can integrate directly with open source projects hosted on GitHub at no charge. The service's underlying software is open source and is hosted on GitHub. Users can contribute to the code directly, or they pull a copy to use on premises, although the convenience of plugging into an existing GitHib repository is a big draw. Sputnik uses the Jenkins continuous integration server and the Maven and Gradle build systems. It performs code analyses with the Gerrit or Stash code tools, featuring support for Checkstyle, PMD, FindBugs, CodeNarc, JSHint, JSLint, TSLint, and Sonar. Any problems found by those tools are reported back to Gerrit or Stash, although with Sonar, rules generated by that program can be plugged directly into Sputnik's configuration file. Sputnik's service version takes care of all the integration with the third-party tools.", '2016-04-07 11:15:33', 4, 2);
INSERT INTO `article` (`id`, `subject`, `subtilte`, `content`, `publish_date`, `category_id`, `published_by`) VALUES (5, 'Break the tyranny of native mobile apps', "Once freedom reigned in the form of Web apps that worked across every platform. Mobile apps have taken a big step back with platform lock-in -- but there's hope", "If like me you're a Windows Phone user it can be painful to re-enter the United States after an overseas trip. At certain airports, including SFO, people with Apple and Android phones get to skip passport control. Not me -- I had to line up at a kiosk with the hoi polloi, jam my passport into the machine, and take a 20th-century selfie. I see this (admittedly) first-world problem in two ways. As an owner of a minority smartphone with an uncertain future I wonder if I should bite the bullet and switch. But as a citizen of both the United States and the Web, I instead wonder why mobile passport control should have anything to do with the Apple and Google app stores. Can't a mobile Web app handle this relatively simple chore?", '2015-12-07 03:02:00', 3, 3);

-- Comment
INSERT INTO `comment` (`id`, `content`, `post_date`, `article_id`, `post_id`) VALUES (1, 'Very true!', NOW(), 1, 1);
INSERT INTO `comment` (`id`, `content`, `post_date`, `article_id`, `post_id`) VALUES (2, 'The most powerful article about polymorphism in lovely java programming language, Thanks.', '2016-03-01 15:32:09', 3, 3);
INSERT INTO `comment` (`id`, `content`, `post_date`, `article_id`, `post_id`) VALUES (3, 'Java is the most sophisticated in term of object oriented. Great post. Thank you.', '2016-03-01 15:32:09', 3, 1);
INSERT INTO `comment` (`id`, `content`, `post_date`, `article_id`, `post_id`) VALUES (4, 'India has seen mobile application development companies doing great overall few years. Existing IT infrastructure and availability of top mobile developers are some of the key reasons. One can search for top companies in India and then start the due diligence process. Visiting their respective offices is always better to meet the team of developers directly.', '2015-12-28 10:53:06', 5, 1);
