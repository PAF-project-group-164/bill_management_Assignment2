package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Bill {
	//A common method to connect to the DB
			private Connection connect(){ 
							Connection con = null; 							
							try{ 
									Class.forName("com.mysql.jdbc.Driver"); 
	 
									//Provide the correct details: DBServer/DBName, username, password 
									con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/pafprojecteletricity", "root", ""); 
							} 
							catch (Exception e) {
								e.printStackTrace();
								} 
							
							return con; 
				} 
			
			
	public String readbilling() 
			{ 
				String output = ""; 
				try
				{ 
					Connection con = connect(); 
			 if (con == null) 
			 { 
			 return "Error while connecting to the database for reading."; 
			 } 
			 // Prepare the html table to be displayed
			 output = "<table border=\"1\" class=\"table\"><tr>"
			 		+ "<th>User ID</th>"
			 		+ "<th>Usage</th>"
			 		+ "<th>Value</th>"
			 		+ "<th>Vat</th>"
			 		+ "<th>Total</th>"
			 		+ "<th>Update</th>"
			 		+ "<th>Remove</th></tr>"; 
			
			 String query = "select * from billingapi"; 
			 Statement stmt = con.createStatement(); 
			 ResultSet rs = stmt.executeQuery(query); 
			 // iterate through the rows in the result set
			 while (rs.next()) 
			 { 
			 String bid = Integer.toString(rs.getInt("bid")); 
			 String uid = rs.getString("uid"); 
			 String busage = rs.getString("busage"); 
			 String value = rs.getString("value");
			 String vat = rs.getString("vat"); 
			 String total = rs.getString("total"); 
			 // Add into the html table
			 output += "<tr><td><input id='hidbidUpdate' name='hiduidUpdate' type='hidden' value='"+bid+"'>"+uid+"</td>"; 
			 output += "<td>" + busage + "</td>"; 
			 output += "<td>" + value + "</td>"; 
			 output += "<td>" + vat + "</td>"; 
			 output += "<td>" + total + "</td>";
			 // buttons
			 output += "<td><input name='btnUpdate' type='button' value='Update' "
					 + "class='btnUpdate btn btn-primary' data-bid='" + bid + "'></td>"
					 + "<td><input name='btnRemove' type='button' value='Remove' "
					 + "class='btnRemove btn btn-danger' data-bid='" + bid + "'></td></tr>"; 
			 
			 } 
			 con.close(); 
			 // Complete the html table
			 output += "</table>"; 
			 } 
			 
			catch (Exception e) 
			 { 
			 output = "Error while reading the bill."; 
			 System.err.println(e.getMessage()); 
			 } 
			return output; 
			}		
	
public String insertBilling(String uid, String busage, String value, String vat, String total){ 
				
				String output = ""; 
				
				try
				{ 
					Connection con = connect(); 
					
					if (con == null) 
					{
						return "Error while connecting to the database for inserting."; 
						
					} 
					// create a prepared statement
					
					String query = " insert into billingapi(bid,`uid`,`busage`,`value`,`vat`,`total`)" + " values ( ?, ?, ?, ?, ?, ?)";
					PreparedStatement preparedStmt = con.prepareStatement(query); 
					// binding values
					preparedStmt.setInt(1, 0);
					preparedStmt.setString(2, uid);
					preparedStmt.setString(3, busage); 
					preparedStmt.setString(4, value); 
					preparedStmt.setString(5, vat);
					preparedStmt.setString(6, total); 
					// execute the statement

					preparedStmt.execute(); 
					con.close(); 
					
					String newBill = readbilling(); 
					output = "{\"status\":\"success\",\"data\":\""+newBill+"\"}"; 
				} 
				
				catch (Exception e) 
				{ 
					output = "{\"status\":\"error\", \"data\":\"Error while inserting the bill.\"}"; 
					System.err.println(e.getMessage()); 
				} 
				return output; 
		}
			
	public String updateBilling(String bid, String uid, String busage, String value, String vat, String total) {
				String output = "";

				try {
					Connection con = connect();

					if (con == null) {
						return "Error while connecting to the database for updating.";
					}

					// create a prepared statement
					String query = "UPDATE billingapi set uid=?,busage=?,value=?,vat=?,total=?  WHERE bid=?";

					PreparedStatement preparedStmt = con.prepareStatement(query);

					// binding values
					
					preparedStmt.setString(1, uid);
					preparedStmt.setString(2, busage);
				    preparedStmt.setString(3, value);
					preparedStmt.setString(4, vat);
					preparedStmt.setString(5, total);
					preparedStmt.setInt(6, Integer.parseInt(bid));
					
					// execute the statement
					preparedStmt.execute();
					con.close();

					String newBill = readbilling(); 
					output = "{\"status\":\"success\",\"data\":\""+newBill+"\"}"; 
				
				} catch (Exception e) {
					output = "{\"status\":\"error\", \"data\":\"Error while updating the Bill.\"}";
					System.err.println(e.getMessage());
				}

				return output;
			}
			
			public String deleteBilling(String bid) {
				String output = "";

				try {
					Connection con = connect();

					if (con == null) {
						return "Error while connecting to the database for deleting.";
					}

					// create a prepared statement
					String query = "delete from billingapi where bid =?";

					PreparedStatement preparedStmt = con.prepareStatement(query);

					// binding values
					preparedStmt.setInt(1, Integer.parseInt(bid));

					// execute the statement
					preparedStmt.execute();
					con.close();

					String newBill = readbilling(); 
					output = "{\"status\":\"success\",\"data\":\""+newBill+"\"}"; 
				
				} catch (Exception e) {
					output = "{\"status\":\"error\", \"data\":\"Error while delete the Bill.\"}";
					System.err.println(e.getMessage());
				}

				return output;
			}
			
}