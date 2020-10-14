package CensusAnalyser;

import org.junit.*;

public class CensusAnalyserTest {
	
	public static final String FILE_PATH = "C:\\Users\\MADHUBABU\\eclipse-workspace\\CensusAnalyser\\statecensus.csv"; 
	public static final String WRONG_FILE_PATH = "C:\\Users\\MADHUBABU\\eclipse-workspace\\CensusAnalyser\\state.csv";
	
	@Test
	public void givenCSVFileNumbeOfRecordsShouldMatch()
	{
		CensusAnalyser censusAnalyser = new CensusAnalyser();
		int count;
		try {
			count = censusAnalyser.loadStatesCSVData(FILE_PATH);
			Assert.assertEquals(2, count);
		} catch (CensusAnalyserException e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void givenWrongCSVFileShouldRaiseCustomException()
	{
		CensusAnalyser censusAnalyser = new CensusAnalyser();
		int count;
		try {
			count = censusAnalyser.loadStatesCSVData(WRONG_FILE_PATH);
		} catch (CensusAnalyserException e) {
			Assert.assertEquals(e.exception, CensusAnalyserException.Exception.INCORRECT_FILE);
		}
		
	}

}
