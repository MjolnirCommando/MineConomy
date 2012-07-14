package me.mjolnir.mineconomy.exceptions;

/**
 * Thrown when a specified number can not be negative or zero.
 * 
 * @author MjolnirCommando
 */
public class NaturalNumberException extends RuntimeException implements MCException
{
	private String method;
	private String variable;
	
	/**
	 * Creates new NaturalNumberException.
	 * 
	 * @param method
	 * @param variable
	 */
	public NaturalNumberException(String method, String variable)
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
		return "The specified variable must be a natural number.";
	}

	public NaturalNumberException getError()
	{
		return this;
	}

}
