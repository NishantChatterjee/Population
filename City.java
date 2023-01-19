/**
 *	City data - the city name, state name, location designation,
 *				and population est. 2017
 *
 *	@author	Nishant Chatterjee
 *	@since January 10th, 2022
 */
public class City implements Comparable<City> {
	
	// fields
	private String cityName, stateName, cityType;
	private int population;
	
	// constructor
	public City(String cityName, String stateName, String cityType, int population) {
		this.cityName = cityName;
		this.stateName = stateName;
		this.cityType = cityType;
		this.population = population;
	}
	
	/**	Compare two cities populations
	 *	@param other		the other City to compare
	 *	@return				the following value:
	 *		If populations are different, then returns (this.population - other.population)
	 *		else if states are different, then returns (this.state - other.state)
	 *		else returns (this.name - other.name)
	 */
	@Override
	public int compareTo(City other) {
		if(this.population - other.population != 0) return this.population - other.population;
		if(this.stateName.compareTo(other.stateName) != 0) return this.stateName.compareTo(other.stateName);
		return this.cityName.compareTo(other.cityName);
	}
	
	/**	Equal city name and state name
	 *	@param other		the other City to compare
	 *	@return				true if city name and state name equal; false otherwise
	 */
	@Override
	public boolean equals(Object other) {
		try {
			City c = (City) other;
			return this.cityName.equals(c.cityName) && this.stateName.equals(c.stateName);
		} catch(Exception e) {
			return false;
		}
	}
	
	/**	Accessor methods */
	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

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
	
	/**	toString */
	@Override
	public String toString() {
		return String.format("%-22s %-22s %-10s %,12d", stateName, cityName, cityType, population);
		// return cityName;
	}
}
