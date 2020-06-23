package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;

import db.DB;

public class Program {

	public static void main(String[] args) {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		Connection conn = null;
		PreparedStatement pst = null;
		try {
			conn=DB.getConnection();
			
			pst = conn.prepareStatement("insert into department(Name) values ('D1'),('D2')",
					Statement.RETURN_GENERATED_KEYS);
		
			int rowsAffected = pst.executeUpdate();
			
			if(rowsAffected > 0) {
				ResultSet rs = pst.getGeneratedKeys();
				while(rs.next()) {
					int id = rs.getInt(1);
					System.out.println("Done! Id = " + id);
				}
			}
			else {
				System.out.println("No rows affected!");
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			DB.closeStatement(pst);
			DB.closeConnection();
		}
	}
}
