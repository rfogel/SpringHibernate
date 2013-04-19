
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.connection;

/**
 *
 * @author Fogel
 */
public class ConnectionFactory {

    private ConnectionFactory instance;

    public Connetion getConexaoPostgres()
    {
        return new ConnectionPostgres();
    }
    
    public Connetion getConexaoMysql()
    {
        return new ConnectionMysql();
    }

    public ConnectionFactory getInstance()
    {
        if ( instance == null )
            instance = new ConnectionFactory();
        return instance;
    }

}
