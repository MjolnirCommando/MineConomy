package me.mjolnir.mineconomy.exceptions;

/**
 * Thrown when there is no account with the given name.
 * 
 * @author MjolnirCommando
 */
public class NoAccountException extends RuntimeException implements MCException
{
	private String method;
	private String variable;
	
	/**
	 * Create new NoAccountException object.
	 * 
	 * @param method 
	 * @param variable 
	 */
	public NoAccountException(String method, String variable)
	{
		super();
		this.method = method;
		this.variable = variable;
	}
	
	public String getMethodCause()
	{
		return method;
	}
	
	public String getVariableCause()
	{
		return variable;
	}
	
	public String getErrorDescription()
	{
		return "The account you requested could not be found.";
	}
	
	public NoAccountException getError()
	{
		return this;
	}
}
