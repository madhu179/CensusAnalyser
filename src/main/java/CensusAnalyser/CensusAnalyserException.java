package CensusAnalyser;

public class CensusAnalyserException extends Exception{
	
	enum Exception
	{
		INCORRECT_FILE,
		INCORRECT_FILE_TYPE
	}
	
	Exception exception;
	
	public CensusAnalyserException(Exception exception)
	{
		this.exception = exception;
	}
	
	public CensusAnalyserException(String message,Exception exception)
	{
		super(message);
		this.exception = exception;
	}

}
