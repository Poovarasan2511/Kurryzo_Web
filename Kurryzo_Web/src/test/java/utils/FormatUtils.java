package utils;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FormatUtils 
{
	//private static final SimpleDateFormat dateTimeFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm a");
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    // Reusable method for formatting prices
    public static String formatPrice(double price) 
    {
        DecimalFormat df = new DecimalFormat("0.00");
        return df.format(price);
    }
    
	public static String getcurrentDateTime()
	{
		//return dateTimeFormat.format(new Date());
		 SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm a", java.util.Locale.ENGLISH);
		    return sdf.format(new Date()); // Current date and time in desired format
	}
	
	public static  String getcurrentDate()
	{
		 return dateFormat.format(new Date());
	}
	
}