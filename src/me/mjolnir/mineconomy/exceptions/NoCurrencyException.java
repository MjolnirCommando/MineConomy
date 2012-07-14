package me.mjolnir.mineconomy.exceptions;

/**
 * Thrown when the currency requested could not be found.
 * 
 * @author MjolnirCommando
 */
public class NoCurrencyException extends RuntimeException implements MCException
{
    private String method;
    private String variable;
    
    /**
     * Creates new NoCurrencyException object
     * 
     * @param method
     * @param variable
     */
    public NoCurrencyException(String method, String variable)
    {
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
        return "The currency requested could not be found.";
    }

    public NoCurrencyException getError()
    {
        return this;
    }

}
