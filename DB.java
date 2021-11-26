package application;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DB {
    //connection components
    private static Connection connection;
    private static Statement statement;
    private static ResultSet rs;

    //queries
    private static final String GET_QUESTIONS = "select * from questions";
    private static final String GET_USER = "select * from users where username = ? and password = ?";
    private static final String ADD_USER = "INSERT INTO `journal`.`users`(`username`, `password`, `fname`, `lname`) VALUES (?,?,?,?)";
    private static final String SAVE_POST = "INSERT INTO `journal`.`posts`(`username`, `type`, `date`, `text`) VALUES (?,?,?,?)";
    private static final String GET_POST = "select * from posts where username = ? and date = ?";
    private static final String UPDATE_POST = "update posts set text = ? where username = ? and date = ?";
    private static final String GET_USER_POSTS = "select type, date, text from posts where username = ?";
    
    //constructor
    public DB() throws SQLException {
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/journal", "root", "123456");
        statement = connection.createStatement();
    }
    
    //methods to get/set DB records
    public static void savePost(String un, String type, Date date, String text) throws SQLException {
    	PreparedStatement checkExistingRecord = connection.prepareStatement(GET_POST);
    	checkExistingRecord.setString(1,un);
    	checkExistingRecord.setDate(2, date);
    	try {
    		rs = checkExistingRecord.executeQuery();
			if (rs.next()) {
				boolean cond = Confirm.display("Are you sure? \n"
											+ "override last post?");
				if(!cond)
					return;
				PreparedStatement updatePost = connection.prepareStatement(UPDATE_POST);
				updatePost.setString(1, text);
				updatePost.setString(2, un);
				updatePost.setDate(3, date);
				updatePost.executeUpdate();
				checkExistingRecord.close();
				updatePost.close();
			}	
			else {
		    	PreparedStatement pstmt = connection.prepareStatement(SAVE_POST);
				pstmt.setString(1, un);
				pstmt.setString(2, type);
				pstmt.setDate(3, date);
				pstmt.setString(4, text);
				pstmt.executeUpdate();
				pstmt.close();
			}
    	}catch (SQLException e) {
			throw new SQLException(e.getMessage());
		}
	}
    
    public static ResultSet getPastPosts(User user) throws SQLException{
    	
    	try {
    		PreparedStatement getPosts = connection.prepareStatement(GET_USER_POSTS);
    		getPosts.setString(1, user.getUsername());
    		rs = getPosts.executeQuery();
    	}catch (SQLException e) {
			throw new SQLException("Something went wrong :(");
		}
    	return rs;
    }
    
    public static ResultSet getQuestions() throws SQLException{
    	try {
    	rs = statement.executeQuery(GET_QUESTIONS);
    	return rs;
    	}catch (SQLException e) {
			throw new SQLException(e.getMessage());
		}
    }

    public static User authenticate(String username, String password){
        String un="", fn="", ln="";
        
        try {
        	PreparedStatement pstmt = connection.prepareStatement(GET_USER);
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            rs = pstmt.executeQuery();
            while (rs.next()){
                un = rs.getString("username");
                fn = rs.getString("fname");
                ln = rs.getString("lname");
            }
        } catch (SQLException e) {
            throw new IllegalArgumentException();
        }
        return new User(un,fn,ln);
    }
    
    public static User addUser(String un, String pw,String fn, String ln) throws SQLException {

		PreparedStatement pstmt = connection.prepareStatement(ADD_USER);
		pstmt.setString(1, un);
		pstmt.setString(2, pw);
		pstmt.setString(3, fn);
		pstmt.setString(4, ln);
		pstmt.executeUpdate();
		pstmt.close();
		return new User(un, fn, ln);
	}
}

