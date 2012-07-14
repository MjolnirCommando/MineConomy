package me.mjolnir.mineconomy.exceptions;

/**
 * Thrown when there is a conflict of bank names.
 * 
 * @author MjolnirCommando
 */
public class BankNameConflictException extends RuntimeException implements MCException
{
	private String method;
	private String variable;

	/**
	 * Creates new BankNameConflictException object.
	 * 
	 * @param method
	 * @param variable
	 */
	public BankNameConflictException(String method, String variable)
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
		return "The bank name requested already belonged to an existing bank.";
	}

	public BankNameConflictException getError()
	{
		return this;
	}
}
