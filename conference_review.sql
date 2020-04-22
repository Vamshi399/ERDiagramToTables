DROP SCHEMA IF EXISTS CONFERENCE_REVIEW;

CREATE SCHEMA IF NOT EXISTS CONFERENCE_REVIEW;

USE CONFERENCE_REVIEW;

drop table IF EXISTS review;
drop table IF EXISTS paper_reviewer;
drop table IF EXISTS paper;
drop table IF EXISTS author;

drop table IF EXISTS topic;
drop table IF EXISTS reviewer;
######################################################################
drop procedure IF EXISTS GetPaperReviewer;
drop procedure IF EXISTS GetAuthor;
drop procedure IF EXISTS GetPaper;
drop procedure IF EXISTS GetReviewer;
drop procedure IF EXISTS GetReview;
drop procedure IF EXISTS GetTopic;
########################################################################

DELIMITER $$
CREATE PROCEDURE GetAuthor()
BEGIN
	CREATE TABLE AUTHOR
(
  author_email     VARCHAR(150) NOT NULL, 				# Unique Author Email for the record
  firstname        VARCHAR(150) NOT NULL,               # First Name of the author
  lastname         VARCHAR(150) NOT NULL,             	# Last Name of the author
  PRIMARY KEY      (author_email)                       # Make the author_email the primary key
);    
END$$
DELIMITER ;

call GetAuthor();

INSERT INTO AUTHOR VALUES ('John@gmail.com', 'John', 'Clinton');
INSERT INTO AUTHOR VALUES ('Murat@gmail.com', 'Murat', 'Genc');
INSERT INTO AUTHOR VALUES ('Yegin@gmail.com', 'Yegin', 'Genc');
INSERT INTO AUTHOR VALUES ('Teresa@gmail.com', 'Teresa', 'Brooks');
INSERT INTO AUTHOR VALUES ('Abraham@gmail.com', 'Abraham', 'Guerra');

SELECT * from AUTHOR;
###########################################################

DELIMITER $$
CREATE PROCEDURE GetPaper()
BEGIN
	CREATE TABLE PAPER
(
  paper_id         INT unsigned NOT NULL AUTO_INCREMENT, # Unique Paper ID for the record
  author_email     VARCHAR(150) NOT NULL,				 # Author Email for the record
  title            VARCHAR(150) NOT NULL,                # Title of the paper
  abstract         VARCHAR(2000) NOT NULL,                # abstract of the paper
  filename         VARCHAR(150) NOT NULL,                # File Name of the paper
  PRIMARY KEY      (paper_id),                           # Make the paper_id the primary key
  FOREIGN KEY (author_email)							 # Make the author_email foreign key references author_email of the author table
      REFERENCES author(author_email)
	  ON UPDATE CASCADE ON DELETE RESTRICT
);  
END$$
DELIMITER ;

call GetPaper();

INSERT INTO PAPER VALUES (NULL, 'John@gmail.com', 'Artificial Intelligence', 'theory and development of computer systems able to perform tasks requires human intelligence,visual percept, speech recog, decision, translation', 'ai_john');
INSERT INTO PAPER VALUES (NULL, 'Murat@gmail.com', 'Internet Computing', 'Internet computing is the only architecture that supports all information flows and processes over the Internet — providing access to all applications', 'ic_murat');
INSERT INTO PAPER VALUES (NULL, 'Yegin@gmail.com', 'Big Data', 'extremely large data sets that may be analyzed computationally to reveal patterns, trends, and associations,relating to human behavior and interactions', 'bd_yegin');
INSERT INTO PAPER VALUES (NULL, 'Teresa@gmail.com', 'DBMS', 'software package designed to define, manipulate, retrieve and manage data in a database', 'dbms_teresa');
INSERT INTO PAPER VALUES (NULL, 'Abraham@gmail.com', 'Data Mining', 'Data mining is process of discovering patterns in large data involving methods at intersection of machine learning, statistics, database systems', 'dm_abraham');

SELECT * from PAPER;
#############################################################

DELIMITER $$
CREATE PROCEDURE GetReviewer()
BEGIN
	CREATE TABLE REVIEWER
(
  reviewer_email   VARCHAR(150) NOT NULL,				 # Unique REVIEWER EMAIL for the record
  firstname        VARCHAR(150) NOT NULL,                # First Name of the reviewer
  lastname         VARCHAR(150) NOT NULL,                # Last Name of the reviewer
  authorfeedback   VARCHAR(200) NOT NULL,                # Feedback of the reviewer to the Author
  commiteefeedback VARCHAR(200) NOT NULL,                # Feedback of the reviewer to the commitee
  phonenum		   BIGINT(10) NOT NULL,                  # Phone Number of the reviewer
  affiliation	   VARCHAR(150) NOT NULL,                # reviewer affiliation
  PRIMARY KEY      (reviewer_email)                      # Make the reviewer_email the primary key
);  
END$$
DELIMITER ;

call GetReviewer();

INSERT INTO REVIEWER VALUES ('John1@gmail.com', 'John', 'Clinton', 'Excellent', 'Excellent', 1234567890, 'International Artificial Intelligence Society');
INSERT INTO REVIEWER VALUES ('Murat1@gmail.com', 'Murat', 'Genc', 'Excellent', 'Excellent', 1212121212, 'International Internet Computing Society');
INSERT INTO REVIEWER VALUES ('Yegin1@gmail.com', 'Yegin', 'Genc', 'Excellent', 'Excellent', 7878787878, 'International Big Data Society');
INSERT INTO REVIEWER VALUES ('Teresa1@gmail.com', 'Teresa', 'Brooks', 'Excellent', 'Excellent', 4545454545, 'International DBMS Society');
INSERT INTO REVIEWER VALUES ('Abraham1@gmail.com', 'Abraham', 'Guerra', 'Excellent', 'Excellent', 8989898989, 'International Data Mining Society');

SELECT * from REVIEWER;
#################################################################

DELIMITER $$
CREATE PROCEDURE GetReview()
BEGIN
	CREATE TABLE REVIEW
(
  review_id        INT unsigned NOT NULL AUTO_INCREMENT, # Unique Review ID for the record
  reviewer_email   VARCHAR(150) NOT NULL,                # reviewer email
  recommendation   VARCHAR(150) NOT NULL,                # recommendation of the review
  meritscore       VARCHAR(150) NOT NULL,                # merit score of the review
  readabilityscore VARCHAR(150) NOT NULL,                # readability score of the review
  originalityscore VARCHAR(150) NOT NULL,                # originality score of the review
  relevancescore   VARCHAR(150) NOT NULL,				 # relevance score of the review
  paper_id         INT unsigned NOT NULL,				 # paper id of the review
  PRIMARY KEY     (review_id, paper_id, reviewer_email), # Make the review_id, paper_id, reviewer_email the composite primary key
  FOREIGN KEY (paper_id)								 # Make the paper_id foreign key references paper_id of the paper table
      REFERENCES paper(paper_id)
      ON UPDATE CASCADE ON DELETE RESTRICT,
	  
  FOREIGN KEY (reviewer_email)							 # Make the reviewer_email foreign key references reviewer_email of the reviewer table
	  REFERENCES reviewer(reviewer_email)
	  ON UPDATE CASCADE ON DELETE RESTRICT
);  
END$$
DELIMITER ;

call GetReview();

INSERT INTO REVIEW VALUES (NULL, 'John1@gmail.com', 'Improve the technical content', '8', '10', '7', '5', '1');
INSERT INTO REVIEW VALUES (NULL, 'Murat1@gmail.com', 'Improve the technical content', '8', '10', '7', '5', '2');
INSERT INTO REVIEW VALUES (NULL, 'Yegin1@gmail.com', 'Improve the technical content', '8', '10', '7', '5', '3');
INSERT INTO REVIEW VALUES (NULL, 'Teresa1@gmail.com', 'Improve the technical content', '8', '10', '7', '5', '4');
INSERT INTO REVIEW VALUES (NULL, 'Abraham1@gmail.com', 'Improve the technical content', '8', '10', '7', '5', '5');

SELECT * from REVIEW;
##########################################################

DELIMITER $$
CREATE PROCEDURE GetTopic()
BEGIN
	CREATE TABLE TOPIC
(
  topic_id        INT unsigned NOT NULL AUTO_INCREMENT, # Unique Topic ID for the record
  reviewer_email  VARCHAR(150) NOT NULL,				# Reviewer email of the Topic
  topicname       VARCHAR(150) NOT NULL,                # Topic Name
  PRIMARY KEY     (topic_id),                           # Make the topic_id the primary key
  FOREIGN KEY (reviewer_email)							# Make the reviewer_email foreign key references reviewer_email of the reviewer table				
	  REFERENCES reviewer(reviewer_email)
	  ON UPDATE CASCADE ON DELETE RESTRICT
);  
END$$
DELIMITER ;

call GetTopic();

INSERT INTO TOPIC VALUES (NULL, 'John1@gmail.com', 'Artificial Intelligence');
INSERT INTO TOPIC VALUES (NULL, 'Murat1@gmail.com', 'Internet Computing');
INSERT INTO TOPIC VALUES (NULL, 'Yegin1@gmail.com', 'Big Data');
INSERT INTO TOPIC VALUES (NULL, 'Teresa1@gmail.com', 'DBMS');
INSERT INTO TOPIC VALUES (NULL, 'Abraham1@gmail.com', 'Data Mining');

SELECT * from TOPIC;
##################################################################

DELIMITER $$
CREATE PROCEDURE GetPaperReviewer()
BEGIN
	CREATE TABLE PAPER_REVIEWER
(
  paper_id         INT unsigned NOT NULL, # Unique Paper ID for the record
  reviewer_email   VARCHAR(150) NOT NULL,				 # Unique Reviewer Email for the record
  PRIMARY KEY     (paper_id, reviewer_email),            # Make the paper_id, reviewer_email the composite primary key
  FOREIGN KEY (paper_id)								 # Make the paper_id foreign key references paper_id of the paper table				
	  REFERENCES paper(paper_id)
	  ON UPDATE CASCADE ON DELETE RESTRICT,
  FOREIGN KEY (reviewer_email)							 # Make the reviewer_email foreign key references reviewer_email of the reviewer table				
	  REFERENCES reviewer(reviewer_email)
	  ON UPDATE CASCADE ON DELETE RESTRICT
);    
END$$
DELIMITER ;

call GetPaperReviewer();

INSERT INTO PAPER_REVIEWER VALUES (1, 'John1@gmail.com');
INSERT INTO PAPER_REVIEWER VALUES (2, 'Murat1@gmail.com');
INSERT INTO PAPER_REVIEWER VALUES (3, 'Yegin1@gmail.com');
INSERT INTO PAPER_REVIEWER VALUES (4, 'Teresa1@gmail.com');
INSERT INTO PAPER_REVIEWER VALUES (5, 'Abraham1@gmail.com');

SELECT * from PAPER_REVIEWER;
########################################################


