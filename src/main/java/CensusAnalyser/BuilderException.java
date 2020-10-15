package CensusAnalyser;

public class BuilderException extends Throwable {
	
	public enum Exception_Type
	{
		UNABLE_TO_PARSE,
		INCORRECT_FILE,
		INCORRECT_FILE_TYPE,
		INCORRECT_DELIMITER,
		INCORRECT_HEADER
	}
	
	public Exception_Type type;
	
	public BuilderException(String message,Exception_Type type)
	{
		super(message);
		this.type = type;
	}

}
