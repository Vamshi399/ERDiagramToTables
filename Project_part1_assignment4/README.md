# ERDiagramToTables
This Assignment deals with the creation of tables with the help of ER Diagram 3.34

Consider a CONFERENCE_REVIEW database in which researchers submit
their research papers for consideration. Reviews by reviewers are recorded
for use in the paper selection process. The database system caters primarily
to reviewers who record answers to evaluation questions for each paper they
review and make recommendations regarding whether to accept or reject
the paper. The data requirements are summarized as follows:
■ Authors of papers are uniquely identified by e-mail id. First and last names
are also recorded.
■ Each paper is assigned a unique identifier by the system and is described
by a title, abstract, and the name of the electronic file containing the paper.
■ A paper may have multiple authors, but one of the authors is designated as
the contact author.
■ Reviewers of papers are uniquely identified by e-mail address. Each reviewer’s first name, last name, phone number, affiliation, and topics of interest are also recorded.
■ Each paper is assigned between two and four reviewers. A reviewer rates
each paper assigned to him or her on a scale of 1 to 10 in four categories:
technical merit, readability, originality, and relevance to the conference.
Finally, each reviewer provides an overall recommendation regarding
each paper.
■ Each review contains two types of written comments: one to be seen by
the review committee only and the other as feedback to the author(s).
The files uploaded are entity–relationship diagram and sql files of Relational database for the CONFERENCE_REVIEW database and build the design using a data modeling tool such as ERwin or Rational Rose.

Prerequisites: Install MySQL
Part A: Install MySQL
Part B: Implement Database Schema For “Conference Reviews” Database
Part C: Populate Your Database.

For Converting the ER Diagrams into Relational Database. Please refer the below website for any queries.
https://www.tutorialcup.com/dbms/er-model-into-tables.htm

Important Instructions to follow:

1. You need to have MySQL installed in your system.

2. First create a database called Conference_View.

3. Then to use this database type USE Conference_View.

4. Then Create tables using create table syntax. For appropriate syntax see file conference_review.sql

5. We then insert values using insert command.For appropriate syntax see conference_review.sql

6. To see the whole table along with inserted values. We give command SELECT * FROM TABLENAME.

NOTE: While creating table Primary key is must as it uniquely identifies the row. Foregin Key is written at the end in create table command. To see refer file conference_review.sql file.
