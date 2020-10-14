package CensusAnalyser;

import org.junit.*;

public class CensusAnalyserTest {
	
	public static final String FILE_PATH = "C:\\Users\\MADHUBABU\\eclipse-workspace\\CensusAnalyser\\statecensus.csv"; 
	
	@Test
	public void givenCSVFileNumbeOfRecordsShouldMatch()
	{
		CensusAnalyser censusAnalyser = new CensusAnalyser();
		int count = censusAnalyser.loadStatesCSVData(FILE_PATH);
		Assert.assertEquals(2, count);
	}

}
