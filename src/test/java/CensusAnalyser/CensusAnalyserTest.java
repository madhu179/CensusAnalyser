package CensusAnalyser;

import org.junit.*;

public class CensusAnalyserTest {

	public static final String FILE_PATH = "C:\\Users\\MADHUBABU\\eclipse-workspace\\CensusAnalyser\\statecensus.csv";
	public static final String WRONG_FILE_PATH = "C:\\Users\\MADHUBABU\\eclipse-workspace\\CensusAnalyser\\state.csv";
	public static final String WRONG_TYPE_FILE_PATH = "C:\\Users\\MADHUBABU\\eclipse-workspace\\CensusAnalyser\\statecensus.txt";
	public static final String WRONG_DELIMITER_FILE_PATH = "C:\\Users\\MADHUBABU\\eclipse-workspace\\CensusAnalyser\\censusdelimiter.csv";
	public static final String WRONG_HEADER_FILE_PATH = "C:\\Users\\MADHUBABU\\eclipse-workspace\\CensusAnalyser\\censusheader.csv";

	public static final String STATE_CODE_FILE_PATH = "C:\\Users\\MADHUBABU\\eclipse-workspace\\CensusAnalyser\\statecodes.csv";
	public static final String STATE_CODE_WRONG_FILE_PATH = "C:\\Users\\MADHUBABU\\eclipse-workspace\\CensusAnalyser\\states.csv";
	public static final String STATE_CODE_WRONG_TYPE_FILE_PATH = "C:\\Users\\MADHUBABU\\eclipse-workspace\\CensusAnalyser\\statecodes.txt";
	public static final String STATE_CODE_WRONG_DELIMITER_FILE_PATH = "C:\\Users\\MADHUBABU\\eclipse-workspace\\CensusAnalyser\\statecodedelimiter.csv";
	public static final String STATE_CODE_WRONG_HEADER_FILE_PATH = "C:\\Users\\MADHUBABU\\eclipse-workspace\\CensusAnalyser\\statecodeheader.csv";

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
		} catch (CensusAnalyserException e) {
			Assert.assertEquals(e.exception, CensusAnalyserException.Exception.INCORRECT_DELIMITER);
		}
	}

	@Test
	public void givenCSVFileWithWrongHeaderShouldRaiseCustomException() {
		CensusAnalyser censusAnalyser = new CensusAnalyser();
		int count;
		try {
			count = censusAnalyser.loadStatesCSVData(WRONG_HEADER_FILE_PATH);
		} catch (CensusAnalyserException e) {
			Assert.assertEquals(e.exception, CensusAnalyserException.Exception.INCORRECT_HEADER);
		}

	}

	@Test
	public void givenStateCodeCSVFileNumbeOfRecordsShouldMatch() {
		CensusAnalyser censusAnalyser = new CensusAnalyser();
		int count;
		try {
			count = censusAnalyser.loadStateCodeCSVData(STATE_CODE_FILE_PATH);
			Assert.assertEquals(3, count);
		} catch (CensusAnalyserException e) {
			e.printStackTrace();
		}

	}

	@Test
	public void givenWrongStateCodeCSVFileShouldRaiseCustomException() {
		CensusAnalyser censusAnalyser = new CensusAnalyser();
		int count;
		try {
			count = censusAnalyser.loadStateCodeCSVData(STATE_CODE_WRONG_FILE_PATH);
		} catch (CensusAnalyserException e) {
			Assert.assertEquals(e.exception, CensusAnalyserException.Exception.INCORRECT_FILE);
		}

	}

	@Test
	public void givenStateCodeCSVFileWithWrongTypeShouldRaiseCustomException() {
		CensusAnalyser censusAnalyser = new CensusAnalyser();
		int count;
		try {
			count = censusAnalyser.loadStateCodeCSVData(STATE_CODE_WRONG_TYPE_FILE_PATH);
		} catch (CensusAnalyserException e) {
			Assert.assertEquals(e.exception, CensusAnalyserException.Exception.INCORRECT_FILE_TYPE);
		}

	}

	@Test
	public void givenStateCodeCSVFileWithWrongDelimiterShouldRaiseCustomException() {
		CensusAnalyser censusAnalyser = new CensusAnalyser();
		int count;
		try {
			count = censusAnalyser.loadStateCodeCSVData(STATE_CODE_WRONG_DELIMITER_FILE_PATH);
		} catch (CensusAnalyserException e) {
			Assert.assertEquals(e.exception, CensusAnalyserException.Exception.INCORRECT_DELIMITER);
		}
	}

	@Test
	public void givenStateCodeCSVFileWithWrongHeaderShouldRaiseCustomException() {
		CensusAnalyser censusAnalyser = new CensusAnalyser();
		int count;
		try {
			count = censusAnalyser.loadStateCodeCSVData(STATE_CODE_WRONG_HEADER_FILE_PATH);
		} catch (CensusAnalyserException e) {
			Assert.assertEquals(e.exception, CensusAnalyserException.Exception.INCORRECT_HEADER);
		}

	}

}
