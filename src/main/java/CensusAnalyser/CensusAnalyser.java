package CensusAnalyser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.StreamSupport;

import com.capgemini.csvbuilder.BuilderException;
import com.capgemini.csvbuilder.BuilderFactory;
import com.capgemini.csvbuilder.ICSVBuilder;
import com.opencsv.*;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

public class CensusAnalyser {

	public CensusAnalyser() {

	}

	public int loadStatesCSVData(String filePath) throws CensusAnalyserException {
		raiseExceptionIfIncorrect(filePath);

		try {
			Reader reader = Files.newBufferedReader(Paths.get(filePath));
			new BuilderFactory();
			ICSVBuilder csvBuilderCustom = BuilderFactory.createBuilder();

				List<StateCensusCSV> censusList = csvBuilderCustom.getCSVFileList(reader,StateCensusCSV.class);	
            
			return censusList.size();
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

			List<StateCodeCSV> censusList = csvBuilderCustom.getCSVFileList(reader,StateCodeCSV.class);
			


			return censusList.size();
		} catch (IOException e) {
			throw new CensusAnalyserException(e.getMessage(), CensusAnalyserException.Exception.INCORRECT_FILE);
		} catch (BuilderException e) {
			throw new CensusAnalyserException(e.getMessage(),e.type.name());
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
