package CensusAnalyser;

import java.io.Reader;
import java.util.Iterator;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

public class CSVBuilderCustom<E> implements ICSVBuilder<E> {
	
	public Iterator<E> getCSVFileIterator(Reader reader,Class<E> csvClass) throws CensusAnalyserException
	{
		try {
		CsvToBean<E> csvToBean = new CsvToBeanBuilder<E>(reader).withType(csvClass)
				                                                .withSeparator(',')
				                                                .withIgnoreLeadingWhiteSpace(true)
				                                                .build();
		return csvToBean.iterator();
		}catch(IllegalStateException e)
		{
			throw new CensusAnalyserException(e.getMessage(), CensusAnalyserException.Exception.UNABLE_TO_PARSE);
		}
		
	}

}
