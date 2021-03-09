package tema2;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * 
 * @author Miruna
 *Aici voi adauga temperaturile inregistrate la timpul specific
 */
public class Dispozitiv {
	private TreeMap<Integer, TreeSet<Double>> temperaturesAtTime;
	
	public Dispozitiv() {
		this.temperaturesAtTime = new TreeMap<Integer, TreeSet<Double>>();
	}
	/**
	 * Verific daca ora la care vreau sa adaug o temperatura exista
	 * In caz ca nu exista adaug o cheie noua ce va fii ora respectiva
	 * Adaug temperatura la ora specifica
	 * @param globalTimestamp	timestamp global
	 * @param currentTimestamp	timestamp-ul primit ca parametru
	 * @param temperature		temperatura primita ca parametru
	 */
	public void addTemperature(int globalTimestamp, int currentTimestamp, double temperature) {
		int timestampKey = VerificaTemperatura.ConvertTime(globalTimestamp, currentTimestamp);
		if (timestampKey != -1) {
			if (this.temperaturesAtTime.containsKey(timestampKey)) {
				this.temperaturesAtTime.get(timestampKey).add(temperature);
			} else {
				TreeSet<Double> toAdd = new TreeSet<Double>();
				toAdd.add(temperature);
				this.temperaturesAtTime.put(timestampKey, toAdd);
			}
		}
	}
	/**
	 * @return	 Returnez cheia ultimei ore la care au fost adaugate temperaturi
	 */
	public TreeSet<Double> getLastInterval() {
		return this.temperaturesAtTime.get(this.temperaturesAtTime.firstKey());
	}
	
	public TreeMap<Integer, TreeSet<Double>> getTemperatures() {
		return this.temperaturesAtTime;
	}
}
