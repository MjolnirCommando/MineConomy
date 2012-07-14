package me.mjolnir.mineconomy.exceptions;

/**
 * Thrown when the requested bank could not be found.
 * 
 * @author MjolnirCommando
 */
public class NoBankException extends RuntimeException implements MCException
{
	private String method;
	private String variable;
	
	/**
	 * Creates new NoBankException object.
	 * 
	 * @param method
	 * @param variable
	 */
	public NoBankException(String method, String variable)
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
		return "The requested bank could not be found.";
	}

	public NoBankException getError()
	{
		return this;
	}

}
