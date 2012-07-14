package me.mjolnir.mineconomy.exceptions;

/**
 * Thrown when balance is set below maximum amount of debt.
 * 
 * @author MjolnirCommando
 */
public class MaxDebtException extends RuntimeException implements MCException
{
	private String method;
	private String variable;
	
	/**
	 * Creates new MaxDebtException object.
	 * 
	 * @param method 
	 * @param variable 
	 */
	public MaxDebtException(String method, String variable)
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
		return "The account's balance requested cannot be set below the maximum amount of debt.";
	}

	public MaxDebtException getError()
	{
		return this;
	}
}
