package CensusAnalyser;

import com.opencsv.bean.CsvBindByName;

public class StateCensusCSV {
	
	public StateCensusCSV()
	{
		
	}
	
	@CsvBindByName
	public String stateName;
	
	@CsvBindByName
	public int population;
	
	@CsvBindByName
	public int area;
	
	@CsvBindByName
	public int density;

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public int getPopulation() {
		return population;
	}

	public void setPopulation(int population) {
		this.population = population;
	}

	public int getArea() {
		return area;
	}

	public void setArea(int area) {
		this.area = area;
	}

	public int getDensity() {
		return density;
	}

	public void setDensity(int density) {
		this.density = density;
	}
	
	@Override
	public String toString() {
		return "StateCensusCSV [stateName=" + stateName + ", population=" + population + ", area=" + area
				+ ", density=" + density + "]";
	}

}
