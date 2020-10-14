package CensusAnalyser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.StreamSupport;

import com.opencsv.*;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

public class CensusAnalyser {

	public CensusAnalyser() {

	}

	public int loadStatesCSVData(String filePath) throws CensusAnalyserException {
		int noOfStates = 0;

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

		try {
			Reader reader = Files.newBufferedReader(Paths.get(filePath));
			CsvToBean<StateCensusCSV> csvToBean = new CsvToBeanBuilder(reader).withType(StateCensusCSV.class)
					.withSeparator(',').withIgnoreLeadingWhiteSpace(true).build();
			Iterator<StateCensusCSV> censusTterator = csvToBean.iterator();
			Iterable<StateCensusCSV> censusIterable = () -> censusTterator;
			noOfStates = (int) StreamSupport.stream(censusIterable.spliterator(), false).count();

			return noOfStates;
		} catch (IOException e) {
			throw new CensusAnalyserException(e.getMessage(), CensusAnalyserException.Exception.INCORRECT_FILE);
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
			String[] headings = { "stateName", "population", "area", "density" };
			return Arrays.equals(heads, headings);
		} catch (IOException e) {
		}

		return false;
	}

	public int loadStateCodeCSVData(String filePath) throws CensusAnalyserException {
		int noOfStates = 0;
		
		String[] file = filePath.split("[.]");
		if (!file[1].equals("csv")) {
			throw new CensusAnalyserException(CensusAnalyserException.Exception.INCORRECT_FILE_TYPE);
		}

		try {
			Reader reader = Files.newBufferedReader(Paths.get(filePath));
			CsvToBean<StateCodeCSV> csvToBean = new CsvToBeanBuilder(reader).withType(StateCodeCSV.class)
					.withSeparator(',').withIgnoreLeadingWhiteSpace(true).build();
			Iterator<StateCodeCSV> censusTterator = csvToBean.iterator();
			Iterable<StateCodeCSV> censusIterable = () -> censusTterator;
			noOfStates = (int) StreamSupport.stream(censusIterable.spliterator(), false).count();

			return noOfStates;
		} catch (IOException e) {
			throw new CensusAnalyserException(e.getMessage(), CensusAnalyserException.Exception.INCORRECT_FILE);
		}
	}

}
