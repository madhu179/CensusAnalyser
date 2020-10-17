package CensusAnalyser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.Comparator;
import java.util.stream.StreamSupport;

import com.capgemini.csvbuilder.BuilderException;
import com.capgemini.csvbuilder.BuilderFactory;
import com.capgemini.csvbuilder.ICSVBuilder;
import com.google.gson.Gson;

public class CensusAnalyser {

	public CensusAnalyser() {

	}
	
	List<StateCensusCSV> stateCensusList=null;
	List<StateCodeCSV> stateCodeList=null;

	public int loadStatesCSVData(String filePath) throws CensusAnalyserException {
		raiseExceptionIfIncorrect(filePath);

		try {
			Reader reader = Files.newBufferedReader(Paths.get(filePath));
			new BuilderFactory();
			ICSVBuilder csvBuilderCustom = BuilderFactory.createBuilder();

			stateCensusList = csvBuilderCustom.getCSVFileList(reader,StateCensusCSV.class);	
            
			return stateCensusList.size();
		} catch (IOException e) {
			throw new CensusAnalyserException(e.getMessage(), CensusAnalyserException.Exception.INCORRECT_FILE);
		}catch (BuilderException e) {
			throw new CensusAnalyserException(e.getMessage(),e.type.name());
		}
	}
	
	public int loadStateCodeCSVData(String filePath) throws CensusAnalyserException {
		int noOfStates = 0;
		
		raiseExceptionIfIncorrect(filePath);

		try {
			Reader reader = Files.newBufferedReader(Paths.get(filePath));
			new BuilderFactory();
			ICSVBuilder csvBuilderCustom = BuilderFactory.createBuilder();

			stateCodeList = csvBuilderCustom.getCSVFileList(reader,StateCodeCSV.class);
		
			return stateCodeList.size();
		} catch (IOException e) {
			throw new CensusAnalyserException(e.getMessage(), CensusAnalyserException.Exception.INCORRECT_FILE);
		} catch (BuilderException e) {
			throw new CensusAnalyserException(e.getMessage(),e.type.name());
		}
	}
	
	public String getStateDataSortedByArea(String filePath) throws CensusAnalyserException, IOException
	{
		if (stateCensusList == null || stateCensusList.size() == 0) {
            throw new CensusAnalyserException("No Census Data",CensusAnalyserException.Exception.NO_CENSUS_DATA);
        }
			
		Comparator<StateCensusCSV> censusComparator = Comparator.comparing(census->census.area);
        this.sortStateData(censusComparator);
        Collections.reverse(stateCensusList);
        String sortedStateCensus = new Gson().toJson(stateCensusList);  
        writeDataToJsonFile(filePath,sortedStateCensus);
        return sortedStateCensus;
	}
	
	public String getStateDataSortedByDensity(String filePath) throws CensusAnalyserException, IOException
	{
		if (stateCensusList == null || stateCensusList.size() == 0) {
            throw new CensusAnalyserException("No Census Data",CensusAnalyserException.Exception.NO_CENSUS_DATA);
        }
			
		Comparator<StateCensusCSV> censusComparator = Comparator.comparing(census->census.density);
        this.sortStateData(censusComparator);
        Collections.reverse(stateCensusList);
        String sortedStateCensus = new Gson().toJson(stateCensusList);  
        writeDataToJsonFile(filePath,sortedStateCensus);
        return sortedStateCensus;
	}
	
	public String getStateDataSortedByPopulation(String filePath) throws CensusAnalyserException, IOException
	{
		if (stateCensusList == null || stateCensusList.size() == 0) {
            throw new CensusAnalyserException("No Census Data",CensusAnalyserException.Exception.NO_CENSUS_DATA);
        }
		Comparator<StateCensusCSV> censusComparator = Comparator.comparing(census->census.population);
        this.sortStateData(censusComparator);
        Collections.reverse(stateCensusList);
        String sortedStateCensus = new Gson().toJson(stateCensusList);     
        writeDataToJsonFile(filePath,sortedStateCensus);
        return sortedStateCensus;
	}
	
	public void writeDataToJsonFile(String filePath,String sortedStateCensus) throws IOException
	{
		FileWriter fileWriter = new FileWriter(filePath);
		fileWriter.write(sortedStateCensus);
		fileWriter.close();		
	}
	
	public String getSortedStateNameData() throws CensusAnalyserException
	{
		if (stateCensusList == null || stateCensusList.size() == 0) {
            throw new CensusAnalyserException("No Census Data",CensusAnalyserException.Exception.NO_CENSUS_DATA);
        }
		
		Comparator<StateCensusCSV> censusComparator = Comparator.comparing(census->census.stateName);
        this.sortStateData(censusComparator);
        String sortedStateCensus = new Gson().toJson(stateCensusList);
        return sortedStateCensus;
	}
	
	private void sortStateData(Comparator<StateCensusCSV> comparator)
	{
		 for (int i = 0; i < stateCensusList.size() - 1; i++) {
	            for (int j = 0; j < stateCensusList.size() - i - 1; j++) {
	            	StateCensusCSV census1 = stateCensusList.get(j);
	            	StateCensusCSV census2 = stateCensusList.get(j + 1);
	                if (comparator.compare(census1, census2) > 0) {
	                	stateCensusList.set(j, census2);
	                	stateCensusList.set(j + 1, census1);
	                }
	            }
	        }
	}
	
	public String getSortedStateCodeData() throws CensusAnalyserException
	{
		if (stateCodeList == null || stateCodeList.size() == 0) {
            throw new CensusAnalyserException("No Census Data",CensusAnalyserException.Exception.NO_CENSUS_DATA);
        }
		
		Comparator<StateCodeCSV> censusComparator = Comparator.comparing(census->census.stateCode);
        this.sortStateCodeData(censusComparator);
        String sortedStateCodes = new Gson().toJson(stateCodeList);
        return sortedStateCodes;
	}
	
	private void sortStateCodeData(Comparator<StateCodeCSV> comparator)
	{
		 for (int i = 0; i < stateCodeList.size() - 1; i++) {
	            for (int j = 0; j < stateCodeList.size() - i - 1; j++) {
	            	StateCodeCSV census1 = stateCodeList.get(j);
	            	StateCodeCSV census2 = stateCodeList.get(j + 1);
	                if (comparator.compare(census1, census2) > 0) {
	                	stateCodeList.set(j, census2);
	                	stateCodeList.set(j + 1, census1);
	                }
	            }
	        }
	}
	private <E> int getNoOfStates(Iterator<E> censusTterator )
	{
		Iterable<E> censusIterable = () -> censusTterator;
				return (int) StreamSupport.stream(censusIterable.spliterator(),false)
						                  .count();
	}
	
	private void raiseExceptionIfIncorrect(String filePath) throws CensusAnalyserException
	{
		String[] file = filePath.split("[.]");
		if (!file[1].equals("csv")) {
			throw new CensusAnalyserException(CensusAnalyserException.Exception.INCORRECT_FILE_TYPE);
		}

		if (Files.exists(Paths.get(filePath))) {
			if (isWrongDelimiter(filePath)) {
				throw new CensusAnalyserException(CensusAnalyserException.Exception.INCORRECT_DELIMITER);
			}

			if (!isRightHeader(filePath)) {
				throw new CensusAnalyserException(CensusAnalyserException.Exception.INCORRECT_HEADER);
			}
		}
	}

	private boolean isWrongDelimiter(String filePath) {
		try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
			String line;
			while ((line = br.readLine()) != null) {
				if (line.contains(","))
					continue;
				else
					return true;
			}
		} catch (IOException e) {
		}

		return false;
	}

	private boolean isRightHeader(String filePath) {
		try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
			String line;
			line = br.readLine();
			String[] heads = line.split(",");
			String[] headingSates = { "stateName", "population", "area", "density" };
			String[] headingSateCode = { "stateName", "stateCode" };
			return Arrays.equals(heads, headingSates) || Arrays.equals(heads, headingSateCode);
		} catch (IOException e) {
		}

		return false;
	}

}
