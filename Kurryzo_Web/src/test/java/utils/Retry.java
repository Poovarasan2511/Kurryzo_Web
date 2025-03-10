package utils;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer {

	private int count = 0;
    private static final int MAX_RETRY_COUNT = 2; // Retry 2 times if a test fails
    
	@Override
	public boolean retry(ITestResult result) {
		// TODO Auto-generated method stub
		if(count< MAX_RETRY_COUNT)
		{
			count++;
			return true;
		}
		return false;
	}

	
	
}
