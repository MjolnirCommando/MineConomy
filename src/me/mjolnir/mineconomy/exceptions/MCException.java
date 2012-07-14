package me.mjolnir.mineconomy.exceptions;

/**
 * Interface for standard MineConomy Exceptions.
 * 
 * @author MjolnirCommando
 */
public interface MCException
{
	/**
	 * Returns the method that caused the exception.
	 * 
	 * @return The method that caused the exception.
	 */
	public String getMethodCause();
	
	/**
	 * Returns the variable that caused the exception.
	 * 
	 * @return The variable that caused the exception.
	 */
	public String getVariableCause();
	
	/**
	 * Returns the description of the exception.
	 * 
	 * @return A description of the exception.
	 */
	public String getErrorDescription();
	
	/**
	 * Returns the exception.
	 * 
	 * @return Exception
	 */
	public Exception getError();
}
