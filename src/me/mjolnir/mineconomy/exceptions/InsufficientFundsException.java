package me.mjolnir.mineconomy.exceptions;

/**
 * Thrown when an account does not have enough money to take away.
 * 
 * @author MjolnirCommando
 */
public class InsufficientFundsException extends RuntimeException implements MCException
{
	private String method;
	private String variable;
	
	/**
	 * Creates new InsufficientFundsException object.
	 * 
	 * @param method 
	 * @param variable 
	 */
	public InsufficientFundsException(String method, String variable)
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
		return "The account requested did not have a large enough balance to deduct the requested amount from.";
	}

	public InsufficientFundsException getError()
	{
		return this;
	}

}
