/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package exception;

/**
 *
 * @author Fogel
 */
public class BusinessException extends Exception
{
	private static final long serialVersionUID = -88237426027806218L;

	private String mensagem;
	
	public BusinessException(String mensagem){
        super(mensagem);
        this.mensagem = mensagem;
    }
	
    public String getErrorMessage(){
        return mensagem;
    }
}
