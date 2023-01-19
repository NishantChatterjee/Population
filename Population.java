import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

/**
 *	Population - <description goes here>
 *
 *	Requires FileUtils and Prompt classes.
 *
 *	@author	
 *	@since	
 */
public class Population {
	
	// List of cities
	private List<City> cities;
	
	// US data file
	private final String DATA_FILE = "usPopData2017.txt";
	
	/**
	 * Loads the cities from the text file into a list
	 */
	private void loadCities() throws FileNotFoundException {
		Scanner s = new Scanner(new File(DATA_FILE)).useDelimiter("[\t\n]");
		cities = new ArrayList<>();
		while(s.hasNext()) {
			String state = s.next();
			String city = s.next();
			String type = s.next();
			int population = s.nextInt();
			City curr = new City(city, state,	type, population);
			cities.add(curr);
		}
	}

	/**
	 * Uses selection sort to sort a list of cities by population in ascending order
	 *
	 * @param arr The array to sort
	 * @return The sorted list
	 */
	private List<City> sortAscendingPopulation(List<City> arr) {
		int n = arr.size();
		for(int i = 0; i < n; i ++) {
			int min = i;
			for(int j = i + 1; j < n; j ++) {
				if(arr.get(j).getPopulation() < arr.get(min).getPopulation()) {
					min = j;
				}
			}
			City tmp = arr.get(i);
			arr.set(i, arr.get(min));
			arr.set(min, tmp);
		}
		return arr;
	}

	/**
	 * Merge sort helper method
	 */
	private List<City> mergeDescendingPopulation(List<City> l, List<City> r) {
		List<City> ret = new ArrayList<>();
		int n = l.size();
		int m = r.size();
		for(int i = 0, j = 0; i < n || j < m; ) {
			if(i >= n) {
				ret.add(r.get(j));
				j ++;
			} else if(j >= m) {
				ret.add(l.get(i));
				i ++;
			} else {
				if(l.get(i).getPopulation() >= r.get(j).getPopulation()) {
					ret.add(l.get(i));
					i ++;
				} else {
					ret.add(r.get(j));
					j ++;
				}
			}
		}
		return ret;
	}

	/**
	 * Merge sort helper method
	 */
	private List<City> sortDescendingPopulation(List<City> arr, int l, int r) {
		if(l >= r) {
			List<City> ret = new ArrayList<>();
			ret.add(arr.get(l));
			return ret;
		}
		int m = l + (r - l) / 2;
		return mergeDescendingPopulation(sortDescendingPopulation(arr, l, m), sortDescendingPopulation(arr, m + 1, r));
	}

	/**
	 * Uses merge sort to sort the cities by population in descending order
	 *
	 * @param arr The array to sort
	 * @return the sorted list
	 */
	private List<City> sortDescendingPopulation(List<City> arr) {
		return sortDescendingPopulation(arr, 0, arr.size() - 1);
	}

	/**
	 * Uses insertion sort to sort an array by name in ascending order
	 *
	 * @param arr The array to sort
	 * @return the sorted list
	 */
	private List<City> sortAscendingName(List<City> arr) {
		int n = arr.size();
		for(int i = 1; i < n; i ++) {
			int j;
			City tmp = arr.get(i);
			for(j = i; j >= 1 && tmp.getCityName().compareTo(arr.get(j - 1).getCityName()) < 0; j --) {
				arr.set(j, arr.get(j - 1));
			}
			arr.set(j, tmp);
		}
		return arr;
	}

	/**
	 * Merge sort helper method
	 */
	private List<City> mergeDescendingName(List<City> l, List<City> r) {
		List<City> ret = new ArrayList<>();
		int n = l.size();
		int m = r.size();
		for(int i = 0, j = 0; i < n || j < m; ) {
			if(i >= n) {
				ret.add(r.get(j));
				j ++;
			} else if(j >= m) {
				ret.add(l.get(i));
				i ++;
			} else {
				if(l.get(i).getCityName().compareTo(r.get(j).getCityName()) >= 0) {
					ret.add(l.get(i));
					i ++;
				} else {
					ret.add(r.get(j));
					j ++;
				}
			}
		}
		return ret;
	}

	/**
	 * Merge sort helper method
	 */
	private List<City> sortDescendingName(List<City> arr, int l, int r) {
		if(l >= r) {
			List<City> ret = new ArrayList<>();
			ret.add(arr.get(l));
			return ret;
		}
		int m = l + (r - l) / 2;
		return mergeDescendingName(sortDescendingName(arr, l, m), sortDescendingName(arr, m + 1, r));
	}

	/**
	 * Uses merge sort to sort a list of cities by name in descending order
	 *
	 * @param arr The array to sort
	 * @return the sorted list
	 */
	private List<City> sortDescendingName(List<City> arr) {
		return sortDescendingName(arr, 0, arr.size() - 1);
	}
	
	/**	Prints the introduction to Population */
	public void printIntroduction() {
		System.out.println("   ___                  _       _   _");
		System.out.println("  / _ \\___  _ __  _   _| | __ _| |_(_) ___  _ __ ");
		System.out.println(" / /_)/ _ \\| '_ \\| | | | |/ _` | __| |/ _ \\| '_ \\ ");
		System.out.println("/ ___/ (_) | |_) | |_| | | (_| | |_| | (_) | | | |");
		System.out.println("\\/    \\___/| .__/ \\__,_|_|\\__,_|\\__|_|\\___/|_| |_|");
		System.out.println("           |_|");
		System.out.println();
	}

	/**
	 * Prints upwards of 50 cities from a list of cities
	 *
	 * @param cities The list of cities to print
	 */
	private void printCities(List<City> cities) {
		System.out.printf("    %-22s %-22s %-11s  %-13s\n", "State", "City", "Type", "Population");
		for(int i = 0; i < Math.min(50, cities.size()); i ++) {
			System.out.printf("%-2d: %s\n", i + 1, cities.get(i).toString());
		}
	}

	/**
	 * Main method that runs the program
	 */
	public static void main(String[] args) {
		Population pop = new Population();
		try {
			pop.loadCities();
		} catch(FileNotFoundException e) {
			System.out.println("Couldn't find file");
		}
		boolean done = false;
		while(!done) {
			pop.printMenu();
			Scanner s = new Scanner(System.in);
			System.out.print("Make selection -> ");
			int choice = s.nextInt();
			if(choice == 1) {
				System.out.println("\nFifty least populous cities\n");
				pop.printCities(pop.sortAscendingPopulation(pop.cities));
			} else if(choice == 2) {
				System.out.println("\nFifty most populous cities\n");
				pop.printCities(pop.sortDescendingPopulation(pop.cities));
			} else if(choice == 3) {
				System.out.println("\nFifty cities sorted by name\n");
				pop.printCities(pop.sortAscendingName(pop.cities));
			} else if(choice == 4) {
				System.out.println("\nFifty cities sorted by name descending\n");
				pop.printCities(pop.sortDescendingName(pop.cities));
			} else if(choice == 5) {
				List<City> list = new ArrayList<>();
				System.out.print("Enter state name -> ");
				String state = s.next();
				for(City c : pop.cities) if(c.getStateName().equals(state)) list.add(c);
				System.out.println("Most populous cities in " + state);
				pop.printCities(pop.sortDescendingPopulation(list));
			} else if(choice == 6) {
				List<City> list = new ArrayList<>();
				System.out.print("Enter city name -> ");
				String city = s.next();
				for(City c : pop.cities) if(c.getCityName().equals(city)) list.add(c);
				System.out.println("City " + city + " by population");
				pop.printCities(pop.sortDescendingPopulation(list));
			} else if(choice == 9) {
				done = true;
			}
		}
	}
	
	/**	Print out the choices for population sorting */
	public void printMenu() {
		System.out.println("1. Fifty least populous cities in USA (Selection Sort)");
		System.out.println("2. Fifty most populous cities in USA (Merge Sort)");
		System.out.println("3. First fifty cities sorted by name (Insertion Sort)");
		System.out.println("4. Last fifty cities sorted by name descending (Merge Sort)");
		System.out.println("5. Fifty most populous cities in named state");
		System.out.println("6. All cities matching a name sorted by population");
		System.out.println("9. Quit");
	}
	
}
