package me.mjolnir.mineconomy.internal.util;

/**
 * @author Matthew Edwards
 * @version 1.0
 */
@SuppressWarnings("javadoc")
public class Version
{
    private final String   stringValue;
    private final int[] value;
    
    /**
     * Denotes a comparison being equal.
     */
    public static final int EQUAL = 0;
    /**
     * Denotes a comparison being newer.
     */
    public static final int NEWER = 1;
    /**
     * Denotes a comparison being older.
     */
    public static final int OLDER = 2;

    /**
     * Creates new Version object.
     */
    public Version()
    {
        stringValue = "0.0.0";
        value = new int[3];
        value[0] = 0;
        value[1] = 0;
        value[2] = 0;
    }

    /**
     * Creates new Version object using a String.
     * @param s
     */
    public Version(String s)
    {
        if (!isVersion(s))
        {
            s = "0.0.0";
        }

        stringValue = s;
        s = s.replace(".", "-");
        String[] temp = s.split("-");
        
        value = new int[temp.length];
        
        for (int i = 0; temp.length > i; i++)
        {
            value[i] = Integer.parseInt(temp[i]);
        }
    }

    /**
     * Creates new Version object using an existing Version object.
     * @param v
     */
    public Version(Version v)
    {
        this.stringValue = v.stringValue;
        this.value = v.value;
    }

    /**
     * Returns true if String can be converted to a Version.
     * @param s
     * @return
     */
    public boolean isVersion(String s)
    {
        char[] c = s.toCharArray();
        for (int i = 0; i < s.length(); i++)
        {
            if (c[i] != '.')
            {
                if (!Character.isDigit(c[i]))
                {
                    return false;
                }
            }
        }
        return true;
    }
    
    /**
     * Compares this Version to Version.
     * @param v
     * @return
     */
    public int compare(Version v)
    {
        return compare(this, v);
    }
    
    /**
     * Compares Version 1 to Version 2.
     * @param v1
     * @param v2
     * @return
     */
    public static int compare(Version v1, Version v2)
    {
        if (v1.equals(v2))
        {
            return EQUAL;
        }
        else
        {
            if (v1.isNewer(v2))
            {
                return NEWER;
            }
            else
            {
                return OLDER;
            }
        }
    }
    
    /**
     * Returns true if this Version is newer than Version.
     * @param v
     * @return
     */
    public boolean isNewer(Version v)
    {
        return isNewer(this, v);
    }
    
    /**
     * Returns true if Version is newer than Version 2.
     * @param v
     * @param v2
     * @return
     */
    public static boolean isNewer(Version v, Version v2)
    {
        int[] value = v.value;
        int[] value1 = v2.value;
        
        if (value.length < value1.length)
        {
            for (int i = 0; value.length > i; i++)
            {
                if (value[i] > value1[i])
                {
                    return true;
                }
            }
            
            return false;
        }
        else
        {
            for (int i = 0; value1.length > i; i++)
            {
                if (value1[i] > value[i])
                {
                    return false;
                }
            }
            
            return true;
        }
    }

    @Override
    public String toString()
    {
        return stringValue;
    }
    
    @Override
    public boolean equals(Object obj)
    {
        if (obj instanceof Version)
        {
            Version v = (Version) obj;
            
            if (v.stringValue.equals(this.stringValue))
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        else
        {
            return false;
        }
    }
    
    @Override
    public Object clone()
    {
        return new Version(this);
    }
}
