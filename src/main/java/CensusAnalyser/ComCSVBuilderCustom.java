package CensusAnalyser;

import java.io.IOException;
import java.lang.reflect.Field;
import java.io.Reader;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;

import com.capgemini.csvbuilder.BuilderException;
import com.capgemini.csvbuilder.ICSVBuilder;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

public class ComCSVBuilderCustom<E> implements ICSVBuilder<E> {
	
	public Iterator<E> getCSVFileIterator(Reader reader,Class<E> csvClass) throws BuilderException
	{
		try {
			
			List<Field> fields = Arrays.asList(csvClass.getDeclaredFields());		
			String[] headers = (String[]) fields.stream().map(f -> f.getName()).toArray(String[]::new);

		CSVParser csvparser = new CSVParser(reader,CSVFormat.DEFAULT.withFirstRecordAsHeader().withHeader(headers).withIgnoreHeaderCase());
		return (Iterator<E>) csvparser.iterator();
		}catch(IllegalStateException | IOException e)
		{
			throw new BuilderException(e.getMessage(), BuilderException.Exception_Type.UNABLE_TO_PARSE);
		}
		
	
	}

	}
