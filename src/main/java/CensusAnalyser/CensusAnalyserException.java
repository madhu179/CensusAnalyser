package CensusAnalyser;

public class CensusAnalyserException extends Exception{
	
	enum Exception
	{
		INCORRECT_FILE
	}
	
	Exception exception;
	
	public CensusAnalyserException(String message)
	{
		super(message);
	}
	
	public CensusAnalyserException(String message,Exception exception)
	{
		super(message);
		this.exception = exception;
	}

}
