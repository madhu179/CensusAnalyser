package CensusAnalyser;

import java.io.IOException;

import org.junit.*;

import com.google.gson.Gson;

public class CensusAnalyserTest {

	public static final String FILE_PATH = "C:\\Users\\MADHUBABU\\eclipse-workspace\\CensusAnalyser\\statecensus.csv";
	public static final String WRONG_FILE_PATH = "C:\\Users\\MADHUBABU\\eclipse-workspace\\CensusAnalyser\\state.csv";
	public static final String WRONG_TYPE_FILE_PATH = "C:\\Users\\MADHUBABU\\eclipse-workspace\\CensusAnalyser\\statecensus.txt";
	public static final String WRONG_DELIMITER_FILE_PATH = "C:\\Users\\MADHUBABU\\eclipse-workspace\\CensusAnalyser\\censusdelimiter.csv";
	public static final String WRONG_HEADER_FILE_PATH = "C:\\Users\\MADHUBABU\\eclipse-workspace\\CensusAnalyser\\censusheader.csv";
	public static final String JSON_FILE_PATH_POPULATION = "C:\\Users\\MADHUBABU\\eclipse-workspace\\CensusAnalyser\\statecensuspopulation.json";
	public static final String JSON_FILE_PATH_DENSITY = "C:\\Users\\MADHUBABU\\eclipse-workspace\\CensusAnalyser\\statecensusdensity.json";
	public static final String JSON_FILE_PATH_AREA = "C:\\Users\\MADHUBABU\\eclipse-workspace\\CensusAnalyser\\statecensusarea.json";

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
			Assert.assertEquals(6, count);
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
			Assert.assertEquals(6, count);
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
	
	@Test
	public void givenStateCensusDataOnSortingByStateNameShouldMatchSortedResult() {
		CensusAnalyser censusAnalyser = new CensusAnalyser();
		try {
			censusAnalyser.loadStatesCSVData(FILE_PATH);
			String sortedData = censusAnalyser.getSortedStateNameData();
			StateCensusCSV[] stateData = new Gson().fromJson(sortedData,StateCensusCSV[].class);
			Assert.assertEquals("Andhra Pradesh", stateData[0].stateName);
			Assert.assertEquals("West Bengal", stateData[5].stateName);
		} catch (CensusAnalyserException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void givenStateCodeDataOnSortingByStateCodeShouldMatchSortedResult() {
		CensusAnalyser censusAnalyser = new CensusAnalyser();
		try {
			censusAnalyser.loadStateCodeCSVData(STATE_CODE_FILE_PATH);
			String sortedData = censusAnalyser.getSortedStateCodeData();
			StateCodeCSV[] stateData = new Gson().fromJson(sortedData,StateCodeCSV[].class);
			Assert.assertEquals("AP", stateData[0].stateCode);
			Assert.assertEquals("WB", stateData[5].stateCode);
		} catch (CensusAnalyserException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void givenStateCensusDataOnSortingByPopulationShouldMatchSortedResult() {
		CensusAnalyser censusAnalyser = new CensusAnalyser();
		try {
			censusAnalyser.loadStatesCSVData(FILE_PATH);
			String sortedData = censusAnalyser.getStateDataSortedByPopulation(JSON_FILE_PATH_POPULATION);
			StateCensusCSV[] stateData = new Gson().fromJson(sortedData,StateCensusCSV[].class);
			Assert.assertEquals(100000, stateData[0].population);
			Assert.assertEquals(20000, stateData[5].population);
		} catch (CensusAnalyserException | IOException e) {
			e.printStackTrace();
		} 
	}
	
	@Test
	public void givenStateCensusDataOnSortingByDensityShouldMatchSortedResult() {
		CensusAnalyser censusAnalyser = new CensusAnalyser();
		try {
			censusAnalyser.loadStatesCSVData(FILE_PATH);
			String sortedData = censusAnalyser.getStateDataSortedByDensity(JSON_FILE_PATH_DENSITY);
			StateCensusCSV[] stateData = new Gson().fromJson(sortedData,StateCensusCSV[].class);
			Assert.assertEquals(1000, stateData[0].density);
			Assert.assertEquals(150, stateData[5].density);
		} catch (CensusAnalyserException | IOException e) {
			e.printStackTrace();
		} 
	}
	
	@Test
	public void givenStateCensusDataOnSortingByAreaShouldMatchSortedResult() {
		CensusAnalyser censusAnalyser = new CensusAnalyser();
		try {
			censusAnalyser.loadStatesCSVData(FILE_PATH);
			String sortedData = censusAnalyser.getStateDataSortedByArea(JSON_FILE_PATH_AREA);
			StateCensusCSV[] stateData = new Gson().fromJson(sortedData,StateCensusCSV[].class);
			Assert.assertEquals(4321, stateData[0].area);
			Assert.assertEquals(1289, stateData[5].area);
		} catch (CensusAnalyserException | IOException e) {
			e.printStackTrace();
		} 
	}

}
