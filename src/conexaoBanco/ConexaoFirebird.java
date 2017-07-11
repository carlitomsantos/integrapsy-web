package conexaoBanco;

import java.sql.Connection;

import java.sql.DriverManager;

public class ConexaoFirebird {

	public static Connection getConnection() {
		try {
			Class.forName("org.firebirdsql.jdbc.FBDriver");
			Connection con = DriverManager.getConnection(
					"jdbc:firebirdsql:192.168.0.200:D:/ib_elab/anaclin/labclinic.gdb?encoding=ISO8859_1", "sysdba",
					"live");
			return con;

		} catch (Exception e) {
			System.out.println("Erro na conexão com o banco de dados" + e.getMessage());
			e.printStackTrace();

			return null;

		}
	}

	public static void close(Connection con) {
		try {
			con.close();
		} catch (Exception ex) {
		}
	}
}
