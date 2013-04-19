/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dao.connection;

import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author Fogel
 */
public abstract class Connetion {

    protected Connection connection;

    public Connection getConnection() {
            return connection;
    }

    public void conectar() throws ClassNotFoundException, SQLException {}
    public void desconectar() throws ClassNotFoundException, SQLException {}
}
