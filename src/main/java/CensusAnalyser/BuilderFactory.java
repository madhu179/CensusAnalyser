package CensusAnalyser;

public class BuilderFactory {
	
	public static ICSVBuilder createBuilder()
	{
		return new CSVBuilderCustom();
	}

}
