package conexaoBanco;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
 

public class ConexaoFirebird {
	
	private Connection con = null;
	private Statement stm = null;
	private ResultSet rs = null;
	private String query = "";
	private String servidor;
	private String caminhoBanco;
	private String usuario;
	private String senha;
	
	public ConexaoFirebird(){
		try {
			Class.forName("org.firebirdsql.jdbc.FBDriver");
			con =  DriverManager.getConnection(
		               "jdbc:firebirdsql:192.168.0.200:D:/ib_elab/anaclin/labclinic.gdb?encoding=ISO8859_1",
		               "sysdba",
		               "live");
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public ResultSet executeSql(String query){
		try {
			stm = con.createStatement();
		    rs = stm.executeQuery(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return rs;
	}

	public Connection getCon() {
		return con;
	}

	public void setCon(Connection con) {
		this.con = con;
	}

	public Statement getStm() {
		return stm;
	}

	public void setStm(Statement stm) {
		this.stm = stm;
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}
}
