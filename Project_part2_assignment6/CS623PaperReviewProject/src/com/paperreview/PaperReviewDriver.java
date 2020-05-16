package com.paperreview;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PaperReviewDriver {
	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DATABASE_URL = "jdbc:mysql://localhost:3306/CONFERENCE_REVIEW";

	// Database credentials
	static final String USER = "root";
	static final String PASSWORD = "2705";

	public static void main(String[] args) {
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
//			Open a connection
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);

//          Get a submitted paper’s details by the author’s Primary Key. The query should return the
//          following data (columns): Paper.Id, Paper.Title, Paper.Abstract, Author.EmailAddress,
//          Author.FirstName, Author.LastName
			lineSpaceNextQuestion("One");
			getPaperAuthorDetailsByAuthorId("john@gmail.com", conn);
			
//          Get all reviews for a paper by the paper’s Id, where the paper was recommended to be
//          published. The query should return the following data (columns): All columns from the
//          Review table.
			lineSpaceNextQuestion("Two");
			getReviewDetailsForRecommendedToPublish(conn);

//          Get a count of all papers submitted.
			lineSpaceNextQuestion("Three");
			getCountOfSubmittedPapers(conn);

//          Create a new paper submission. Remember this includes creating new records in both
//          the Author and Paper tables.
			lineSpaceNextQuestion("Four");
			createNewPaperSubmission(conn, "lixin@gmail.com", "lixin", "Tao", "Dot Net", ".NET Framework is a software framework developed by Microsoft that runs primarily on Microsoft Windows", "dn_lixin");

//          Try and Delete the first “Author” row in your Author table by the author’s id. Did you
//          receive an error? If yes, print to the console the error you received. Also note in your
//          message why the query failed. If it didn’t fail, print a message explaining why you were
//          able to delete the row.
			lineSpaceNextQuestion("Five");
			deleteAuthorTableFirstRow(conn);
		} catch (SQLException se) {
			se.printStackTrace();
			System.out.println(se.getMessage());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		} finally {
			// finally block used to close resources
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
				System.out.println(se.getMessage());
			} // end finally try
		}

	}

	private static void lineSpaceNextQuestion(String questionNumber) {
		System.out.println();
		System.out.println();
		System.out.println("--------------------- Question Number " + questionNumber + "-----------------------------");
		System.out.println();
		System.out.println();
	}

	public static void getPaperAuthorDetailsByAuthorId(String key, Connection conn) {
		Statement stmt = null;
		System.out.println("Enter getPaperAuthorDetailsByAuthorId method!");
		try {
			// STEP 1: Execute a query
			System.out.println("Creating statement...");
			stmt = conn.createStatement();
			String sql;
			sql = "select p.paper_id as paperId, p.title, p.abstract, a.author_email as authorEmail, a.firstName, a.lastName from Paper p inner join author a on p.author_email=a.author_email where a.author_email='"
					+ key + "';";
			System.out.println("Query: " + sql);
			ResultSet rs = stmt.executeQuery(sql);

			// STEP 2: Extract data from result set
			while (rs.next()) {
				// Retrieve by column name
				int paperId = rs.getInt("paperId");
				String title = rs.getString("title");
				String abstract1 = rs.getString("abstract");
				String authorEmail = rs.getString("authorEmail");
				String firstName = rs.getString("firstName");
				String lastName = rs.getString("lastName");

				// Display values
				System.out.print("Paper ID: " + paperId);
				System.out.print(", Title: " + title);
				System.out.print(", Abstract: " + abstract1);
				System.out.println(", Author Email: " + authorEmail);
				System.out.print(", First Name: " + firstName);
				System.out.print(", Last Name: " + lastName);
			}
			// STEP 3: Clean-up environment
			rs.close();
			stmt.close();
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
			System.out.println(se.getMessage());
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
			System.out.println(e.getMessage());
		} finally {
			// finally block used to close resources
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
				System.out.println(se2.getMessage());
			} // nothing we can do
		} // end try
		System.out.println("\nExit getPaperAuthorDetailsByAuthorId method!");
	}

	public static void getReviewDetailsForRecommendedToPublish(Connection conn) {
		Statement stmt = null;
		System.out.println("Enter getReviewDetailsForRecommendedToPublish method!");
		try {
			// STEP 1: Execute a query
			System.out.println("Creating statement...");
			stmt = conn.createStatement();
			String sql = "select r.* from review r inner join paper p on p.paper_id=r.paper_id where TRIM(r.recommendation) <> '' and r.recommendation is NOT NULL;";
			System.out.println("Query: "+sql);
			ResultSet rs = stmt.executeQuery(sql);

			// STEP 2: Extract data from result set
			while (rs.next()) {
				// Retrieve by column name
				int reviewId = rs.getInt("review_id");
				String reviewerEmail = rs.getString("reviewer_email");
				String recommendation = rs.getString("recommendation");
				String meritscore = rs.getString("meritscore");
				String readabilityscore = rs.getString("readabilityscore");
				String originalityscore = rs.getString("originalityscore");
				String relevancescore = rs.getString("relevancescore");
				int paperId = rs.getInt("paper_id");

				// Display values
				System.out.print("\nReview ID: " + reviewId);
				System.out.print(", Reviewer Email: " + reviewerEmail);
				System.out.print(", Recommendation: " + recommendation);
				System.out.println(", Merit Score: " + meritscore);
				System.out.print(", Readability Score: " + readabilityscore);
				System.out.print(", Originality Score: " + originalityscore);
				System.out.print(", Relevance Score: " + relevancescore);
				System.out.print(", Paper Id: " + paperId);
			}
			// STEP 3: Clean-up environment
			rs.close();
			stmt.close();
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
			System.out.println(se.getMessage());
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
			System.out.println(e.getMessage());
		} finally {
			// finally block used to close resources
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
				System.out.println(se2.getMessage());
			} // nothing we can do
		} // end try
		System.out.println("\nExit getReviewDetailsForRecommendedToPublish method!");
	}

	public static void getCountOfSubmittedPapers(Connection conn) {
		Statement stmt = null;
		System.out.println("Enter getCountOfSubmittedPapers method!");
		try {
			// STEP 1: Execute a query
			System.out.println("Creating statement...");
			stmt = conn.createStatement();
			String sql;
			sql = "select count(*) as count from review;";
			System.out.println("Query: "+sql);
			ResultSet rs = stmt.executeQuery(sql);

			// STEP 2: Extract data from result set
			while (rs.next()) {
				// Retrieve by column name
				int count = rs.getInt("count");

				// Display values
				System.out.print("Count of All Papers Submitted: " + count);
			}
			// STEP 3: Clean-up environment
			rs.close();
			stmt.close();
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
			System.out.println(se.getMessage());
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
			System.out.println(e.getMessage());
		} finally {
			// finally block used to close resources
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
				System.out.println(se2.getMessage());
			} // nothing we can do
		} // end try
		System.out.println("\nExit getCountOfSubmittedPapers method!");
	}

	public static void createNewPaperSubmission(Connection conn, String authorEmail, String firstName, String lastName, String title, String abstract1, String filename) {
		Statement stmt = null;
		System.out.println("Enter createNewPaperSubmission method!");
		try {
			// STEP 1: Execute a Statement
			stmt = conn.createStatement();
			
			String authorSql = "INSERT INTO AUTHOR VALUES ('" + authorEmail + "', '"+firstName+"','"+lastName+"');";
			System.out.println("Author Query: "+authorSql);
			int status = stmt.executeUpdate(authorSql);
			if (status > 0)
                System.out.print("Thank you! You have successfully registered an author...");
			
			String paperSql = "INSERT INTO PAPER VALUES (NULL, '" + authorEmail + "', '"+title+"','"+abstract1+"', '"+filename+"');";
			System.out.println("Paper Query: "+paperSql);
			status = stmt.executeUpdate(paperSql);
			System.out.println();
            if (status > 0)
                System.out.print("Thank you! You have successfully submitted a paper...");
			System.out.println("Thank you! You have Successfully created a new paper submission");
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
			System.out.println(se.getMessage());
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
			System.out.println(e.getMessage());
		} finally {
			// finally block used to close resources
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
				System.out.println(se2.getMessage());
			} // nothing we can do
		} // end try
		System.out.println("\nExit createNewPaperSubmission method!");
	}

	public static void deleteAuthorTableFirstRow(Connection conn) {
		Statement stmt = null;
		System.out.println("Enter deleteAuthorTableFirstRow method!");
		try {
			// STEP 1: Execute a Statement
			stmt = conn.createStatement();
			
			String authorSql = "DELETE FROM author LIMIT 1;";
			System.out.println("Delete Query: "+authorSql);
			int status = stmt.executeUpdate(authorSql);
						
			if (status > 0)
                System.out.print("Thank you! You have successfully deleted first row of table author...");
			
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
			System.out.println(se.getMessage());
//			System.out.println("You must delete data in the child table which does not have any corresponding foreign key value to the parent table primary key");
			System.out.println("First Author cannot be deleted since there is already a paper row assigned to this author");
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
			System.out.println(e.getMessage());
		} finally {
			// finally block used to close resources
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
				System.out.println(se2.getMessage());
			} // nothing we can do
		} // end try
		System.out.println("\nExit deleteAuthorTableFirstRow method!");
	}
}
