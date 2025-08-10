package Exception;

import com.Wipro.oops.InvalidMonthException;

public class MonthChecker {

	 public static void checkMonth(int month) throws InvalidMonthException 
	 {
	        if (month < 1 || month > 12) 
	        {
	            throw new InvalidMonthException("Invalid Month");
	        } 
	        else 
	        {
	            System.out.println("Valid Month");
	        }
	    }
	
	    public static void main(String[] args) {
	        try 
	        {
	            int month = 13;
	            checkMonth(month);
	        }
	        catch (InvalidMonthException e) 
	        {
	            System.out.println(e.getMessage());
	        }
	    }
}
