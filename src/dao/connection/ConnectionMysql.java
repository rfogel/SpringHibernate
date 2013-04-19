/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dao.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Fogel
 */
public class ConnectionMysql extends Connetion 
{
	public static final String URL = "jdbc:mysql://localhost:3306/ProjetoSpring";
	public static final String USUARIO = "mysql";
    public static final String PASSWORD = "mysql";
	public static final String MYSQL_DRIVER = "com.mysql.jdbc.Driver";

	@Override
	public void conectar() throws SQLException, ClassNotFoundException
	{
		try 
		{
			Class.forName(MYSQL_DRIVER);     
			connection = DriverManager.getConnection(URL, USUARIO, PASSWORD);
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public void desconectar() throws SQLException, ClassNotFoundException
	{
		try
		{
			connection.close();
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}

	@Override
	public Connection getConnection()
	{
		return connection;
	}

}
