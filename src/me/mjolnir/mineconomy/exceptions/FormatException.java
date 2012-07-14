package me.mjolnir.mineconomy.exceptions;

/**
 * Thrown when there is no account with the given name.
 * 
 * @author MjolnirCommando
 */
public class FormatException extends RuntimeException implements MCException
{
    private String method;
    private String variable;
    
    /**
     * Create new NoAccountException object.
     * 
     * @param method 
     * @param variable 
     */
    public FormatException(String method, String variable)
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
        return "The input could not be formatted.";
    }
    
    public FormatException getError()
    {
        return this;
    }
}