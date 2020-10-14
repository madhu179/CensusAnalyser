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
		System.out.println(file[1]);
		if (!file[1].equals("csv")) {
			System.out.println("count");
			throw new CensusAnalyserException(CensusAnalyserException.Exception.INCORRECT_FILE_TYPE);
		}

		if (Files.exists(Paths.get(filePath))) {
			if (iswrongdelimiter(filePath)) {
				throw new CensusAnalyserException(CensusAnalyserException.Exception.INCORRECT_DELIMITER);
			}
		}

		try {
			Reader reader = Files.newBufferedReader(Paths.get(filePath));
			CsvToBean<StateCensusCSV> csvToBean = new CsvToBeanBuilder(reader).withType(StateCensusCSV.class)
					.withSeparator(',').withIgnoreLeadingWhiteSpace(true).build();
			Iterator<StateCensusCSV> censusTterator = csvToBean.iterator();
			Iterable<StateCensusCSV> censusIterable = () -> censusTterator;
			noOfStates = (int) StreamSupport.stream(censusIterable.spliterator(), false).count();

			System.out.println(noOfStates);
			return noOfStates;
		} catch (IOException e) {
			System.out.println("file");
			throw new CensusAnalyserException(e.getMessage(), CensusAnalyserException.Exception.INCORRECT_FILE);
		}
	}

	private boolean iswrongdelimiter(String filePath) {
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

}
