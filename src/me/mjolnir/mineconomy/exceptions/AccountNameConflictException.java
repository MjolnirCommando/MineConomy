package me.mjolnir.mineconomy.exceptions;

/**
 * Thrown when two accounts have the same name.
 * 
 * @author MjolnirCommando
 */
public class AccountNameConflictException extends RuntimeException implements MCException
{
	private String method;
	private String variable;

	/**
	 * Creates new AccountNameConflictException object.
	 * 
	 * @param method
	 * @param variable
	 */
	public AccountNameConflictException(String method, String variable)
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
		return "The account name requested already belonged to an existing account.";
	}

	public AccountNameConflictException getError()
	{
		return this;
	}
}
