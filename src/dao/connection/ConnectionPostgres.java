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
public class ConnectionPostgres extends Connetion 
{
    public static final String URL = "jdbc:postgresql://localhost:5432/ProjetoSpring";
    public static final String USUARIO = "postgres";
    public static final String PASSWORD = "postgres";
    public static final String POSTGRES_SQL_DRIVER = "org.postgresql.Driver";

    @Override
    public void conectar() throws SQLException, ClassNotFoundException
    {
    	try
    	{
    		Class.forName(POSTGRES_SQL_DRIVER);
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
    	} catch (SQLException e)
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
