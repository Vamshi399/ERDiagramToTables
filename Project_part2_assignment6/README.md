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

---------------------------------------------------------------------------------------------------------------------------------------------------------------------

In this part of the project you will create a simple command line, Java application to interact with
your database.
1. Part A: Setting Up Your Java Development Environment
Before getting started, you must set up your Java development environment. Note, if you
already have a Java development environment setup feel free to skip this step.
Setting up your environment includes installing the Java JDK (version 8 or 9) and installing an
integrated development environment (IDE) such as Eclipse.
Please note, if you are comfortable writing and debugging your Java application via a simple
text editor and the command line you do not need to install an IDE. If you have another prefered
Java IDE such as IntelliJ or Netbeans feel free to use that instead of Eclipse.
** The remaining sections assume you are using Eclipse. The steps are similar for other IDEs
etc but the details will vary.
To install Eclipse and the Java JDK follow this tutorial. It has instructions for Linux, OS X
and Windows:
https://www.ntu.edu.sg/home/ehchua/programming/howto/EclipseJava_HowTo.html
2. Part B: Create A Java Project In Eclipse
A project in Eclipse is essentially a folder containing all the source code and other files you need
to build your application. Let’s start by creating a project for your program.
1. Open Eclipse 2. From the File menu select New → Java Project
2. From the File menu select New → Java Project
3. Give your project a name, like “CS623PaperReviewProject.” You can use the default values
for all the other settings. Make note of the project folder in the “Location” box; you will need to
remember this later.
4. Press Finish to create the project
5. Eclipse might ask you whether you want to switch to the Java perspective. If so, say Yes.
6. You should see an empty project.
Part C: Installing The JDBC Driver To Connect To Your MySQL Database
In this step, we install the Connector/J driver so your program can connect to MySQL. Please
make sure you download the version that is compatible with your version of MySQL.
1. Go to this web page: http://www.mysql.com/downloads/connector/j/
2. Press the download button next to the “Platform Independent (Architecture Independent),
ZIP Archive” version. (Or the TAR version if you prefer; it doesn’t really matter.)
3. The next page will ask you to create an account. Instead, click the little blue link on the
bottom that says “No thanks, just start my download.”
4. Open the .zip file you just downloaded, and look for a file inside named something like
mysql-connector-java-xxxx-bin.jar. Copy this file into your project directory (from the Location
box in step 3 of the last section).
5. Now that we have the driver, we need to tell your project about it. Go back to Eclipse, right
click on the project, and select Build Path → Add External Archives…
6. Select mysql-connector-java-xxxx-bin.jar and press Open
7. Now we’re ready to configure the project for your copy of MySQL and run it!
Part D: Let’s Write Some Code!
First, create a new java class called PaperReviewDriver.java in the java project you created.
Note, this is the class where you will implement and test the methods that implement your
database queries.
To create a new Java class:
1. Right click on the project name and select New->Class from the context menu.
2. When the New Class dialog appears, enter “PaperReviewDriver” as the class name and
check the box public static void main(String[] args).
3. Click on the Finish button.
To run your program:
4. Right click on the file with the main method, and from the context menu select the
Run->Java Application menu option.
5. The program runs. Any console output is directed to a window at the bottom of the
screen.
JDBC code examples:
6. https://www.tutorialspoint.com/jdbc/jdbc-sample-code.htm
7. https://examples.javacodegeeks.com/core-java/sql/preparedstatement/java-sql-prepared
statement-example/
Next, in the above class, create java methods that implement the queries below using SQL.
Implement a method per query. Note, all of your queries should be parameterized queries if
they are taking in input such as an Id etc. For each method print the query’s result set to the
console. If no results were returned, print a message that indicates no results were found etc.
Please follow the standard Java naming conventions listed in the “Basic Naming
Conventions Java” document located in “Documents > Course Materials” in blackboard.
Queries:
1. Get a submitted paper’s details by the author’s Primary Key. The query should return the
following data (columns): Paper.Id, Paper.Title, Paper.Abstract, Author.EmailAddress,
Author.FirstName, Author.LastName
2. Get all reviews for a paper by the paper’s Id, where the paper was recommended to be
published. The query should return the following data (columns): All columns from the
Review table.
3. Get a count of all papers submitted.
4. Create a new paper submission. Remember this includes creating new records in both
the Author and Paper tables.
5. Try and Delete the first “Author” row in your Author table by the author’s id. Did you
receive an error? If yes, print to the console the error you received. Also note in your
message why the query failed. If it didn’t fail, print a message explaining why you were
able to delete the row.
Lastyly, in the main method, call each of the methods you have created. Make sure all results
from your queries are being printed to the console
