import java.sql.*;
import javax.swing.*;

public class sqliteconnection {
	Connection conn=null;
	public static Connection dbconnector()
	{
		try {
			Class.forName("org.sqlite.JDBC");
			Connection conn=DriverManager.getConnection("jdbc:sqlite:/home/vikky/Documents/databases/cabshare");
			return conn;
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, "well well isnt this an exception.");
			return null;
		}
	}

}
