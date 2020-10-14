package CensusAnalyser;

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

		try {
			Reader reader = Files.newBufferedReader(Paths.get(filePath));
			CsvToBean<StateCensusCSV> csvToBean = new CsvToBeanBuilder(reader).withType(StateCensusCSV.class)
					.withIgnoreLeadingWhiteSpace(true).build();
			Iterator<StateCensusCSV> censusTterator = csvToBean.iterator();
			Iterable<StateCensusCSV> censusIterable = () -> censusTterator;
			noOfStates = (int) StreamSupport.stream(censusIterable.spliterator(), false).count();
			System.out.println(noOfStates);
			return noOfStates;
		} catch (IOException e) {
			throw new CensusAnalyserException(e.getMessage(), CensusAnalyserException.Exception.INCORRECT_FILE);
		}
	}

}
