package CensusAnalyser;

import org.junit.*;

public class CensusAnalyserTest {

	public static final String FILE_PATH = "C:\\Users\\MADHUBABU\\eclipse-workspace\\CensusAnalyser\\statecensus.csv";
	public static final String WRONG_FILE_PATH = "C:\\Users\\MADHUBABU\\eclipse-workspace\\CensusAnalyser\\state.csv";
	public static final String WRONG_TYPE_FILE_PATH = "C:\\Users\\MADHUBABU\\eclipse-workspace\\CensusAnalyser\\statecensus.txt";
	public static final String WRONG_DELIMITER_FILE_PATH = "C:\\Users\\MADHUBABU\\eclipse-workspace\\CensusAnalyser\\censusdelimiter.csv";
	
	@Test
	public void givenCSVFileNumbeOfRecordsShouldMatch() {
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
	public void givenWrongCSVFileShouldRaiseCustomException() {
		CensusAnalyser censusAnalyser = new CensusAnalyser();
		int count;
		try {
			count = censusAnalyser.loadStatesCSVData(WRONG_FILE_PATH);
		} catch (CensusAnalyserException e) {
			Assert.assertEquals(e.exception, CensusAnalyserException.Exception.INCORRECT_FILE);
		}

	}

	@Test
	public void givenCSVFileWithWrongTypeShouldRaiseCustomException() {
		CensusAnalyser censusAnalyser = new CensusAnalyser();
		int count;
		try {
			count = censusAnalyser.loadStatesCSVData(WRONG_TYPE_FILE_PATH);
		} catch (CensusAnalyserException e) {
			Assert.assertEquals(e.exception, CensusAnalyserException.Exception.INCORRECT_FILE_TYPE);
		}

	}
	
	@Test
	public void givenCSVFileWithWrongDelimiterShouldRaiseCustomException() {
		CensusAnalyser censusAnalyser = new CensusAnalyser();
		int count;
		try {
			count = censusAnalyser.loadStatesCSVData(WRONG_DELIMITER_FILE_PATH);
		    System.out.println("abboo");
		} catch (CensusAnalyserException e) {
			Assert.assertEquals(e.exception, CensusAnalyserException.Exception.INCORRECT_DELIMITER);
		}

	}

}
