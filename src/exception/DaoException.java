/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package exception;

/**
 *
 * @author Fogel
 */
public class DaoException extends Exception 
{
	private static final long serialVersionUID = -337117892815947659L;
	
	private String mensagem;
	
	public DaoException(String mensagem){
        super(mensagem);
        this.mensagem = mensagem;
    }

    public String getErrorMessage(){
        return mensagem;
    }
}
