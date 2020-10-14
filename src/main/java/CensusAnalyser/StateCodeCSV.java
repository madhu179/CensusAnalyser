package CensusAnalyser;

import com.opencsv.bean.CsvBindByName;


public class StateCodeCSV {
	
	@CsvBindByName(column = "stateName", required = true)
	public String stateName;

	@CsvBindByName(column = "stateCode", required = true)
	public String stateCode;
	
	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public String getStateName() {
		return stateName;
	}
	
	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}

	public String getStateCode() {
		return stateCode;
	}
	
	@Override
	public String toString() {
		return "StateCodeCSV [stateName=" + stateName + ", stateCode=" + stateCode + "]";
	}

}
