package me.mjolnir.mineconomy.exceptions;

/**
 * Thrown when the currency id requested could not be found.
 * 
 * @author MjolnirCommando
 */
public class NoCurrencyIdException extends RuntimeException implements MCException
{
    private String method;
    private String variable;
    
    /**
     * Creates new NoCurrencyException object
     * 
     * @param method
     * @param variable
     */
    public NoCurrencyIdException(String method, String variable)
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
        return "The currency id requested could not be found.";
    }

    public NoCurrencyIdException getError()
    {
        return this;
    }

}