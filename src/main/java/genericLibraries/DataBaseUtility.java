package genericLibraries;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.jdbc.Driver;

/*
 * This class contains methods to perform actions on database
 *
 */
public class DataBaseUtility
{
  Driver dbdriver;
  Connection connection;
  Statement statement;
  ResultSet result;
  /*
   * This method is used to establish connection with database
   *  @param url
   * @param username
   * @param password
   */
  public void openDataBaseConnection(String url, String username, String password) 
  {
	  try {
		dbdriver=new Driver();
	} catch (SQLException e) {
		e.printStackTrace();
	}
	  try {
		DriverManager.registerDriver(dbdriver);
	} catch (SQLException e) {
		e.printStackTrace();
	}
	  try {
		connection=DriverManager.getConnection(url,username ,password);
	} catch (SQLException e) {
		e.printStackTrace();
	}  
  }
  /*
   * This method is used to fetch data from the database
   */
  public List<String> fetchDataFromDataBase(String query, String columnname)
  {
	  try {
		statement=connection.createStatement();
	} catch (SQLException e) {
		e.printStackTrace();
	}
	try {
		result= statement.executeQuery(query);
	} catch (SQLException e) {
		e.printStackTrace();
	}
	List<String> list=new ArrayList<>();
	
		try {
			while(result.next())
			{
				list.add(result.getString(columnname));
			}
		} catch (SQLException e) {
		     e.printStackTrace();
		}
	return list;
  }
  /*This method is used to modify the database
   * 
   */
  public int modifyDataInDataBase(String query) throws SQLException
  {
	  
		statement=connection.createStatement();
		int res=statement.executeUpdate(query);
		return res; 
  }
  /*
   * This method is used to close database
   * 
   */
  public void closeDataBase()
  {
	  try {
		connection.close();
	} catch (SQLException e) {
		e.printStackTrace();
	}
  }
}
