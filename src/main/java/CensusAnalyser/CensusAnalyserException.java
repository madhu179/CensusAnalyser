package CensusAnalyser;

public class CensusAnalyserException extends Exception{
	
	enum Exception
	{
		UNABLE_TO_PARSE,
		INCORRECT_FILE,
		INCORRECT_FILE_TYPE,
		INCORRECT_DELIMITER,
		INCORRECT_HEADER,
		NO_CENSUS_DATA
	}
	
	Exception exception;
	
	public CensusAnalyserException(Exception exception)
	{
		this.exception = exception;
	}
	
	public CensusAnalyserException(String message,String exceptionName)
	{
		super(message);
		this.exception = Exception.valueOf(exceptionName);
	}
	
	public CensusAnalyserException(String message,Exception exception)
	{
		super(message);
		this.exception = exception;
	}

}
