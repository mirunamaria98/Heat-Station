package tema2;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

public class House {
	private int timestamp;
	private double globalTemperature;
	private HashMap<String, Room> rooms;
	/**
	 * Clasa House contine timestampul si temperatura globala initiala
	 * Aici adaug fiecare camera si le diferentiez in functie de id
	 * 
	 */
	public House() {
		this.timestamp = 0;
		this.globalTemperature = 0;
		this.rooms = new HashMap<String, Room>();
	}
	
	public House(int timestamp, double globalTemperature) {
		this.timestamp = timestamp;
		this.globalTemperature = globalTemperature;
		this.rooms = new HashMap<String, Room>();
	}
	/**
	 * @param roomId este id-ul camerei
	 * @param time	ora primita
	 * @param temperature	temperatura primita
	 */
	public void addTemperatureForRoom(String roomId, int time, double temperature) {
		this.rooms.get(roomId).addTemperature(timestamp, time, temperature);
	}
	public int getGlobalTimestamp() {
		return this.timestamp;
	}
	/**
	 * 
	 * @param newGlobalTemperature setez noua temperatura globala
	 */
	public void setGlobalTemperature(double newGlobalTemperature) {
		this.globalTemperature = newGlobalTemperature;
	}
	/**
	 * 
	 * @param id id-ul primit ca parametru in functie de fiecare camera
	 * @param name	setez numele camerei
	 * @param surface	setez suprafata camerei
	 */
	public void addRoom(String id, String name, double surface) {
		Room newRoom = new Room(id, name, surface);
		this.rooms.put(id, newRoom);
	}
	/**
	 * 
	 * @param id in functie de id verific existenta camerei
	 * @return	returnez id-ul cammerei
	 */
	public boolean containsRoom(String id) {
		return this.rooms.containsKey(id);
	}
	
	public Room getRoom(String id) {
		return this.rooms.get(id);
	}
	/**
	 * calculez media ponderata si in functie de asta stabilesc
	 * daca trebuie pornita centrala sau nu
	 * @return	returnez valoarea specifica operatiei de comparare
	 */
	public boolean shouldTriggerHeatingSystem() {
		double sumSurfaceTimesTemperature = 0, totalSurface = 0;
		for (Map.Entry<String, Room> x : this.rooms.entrySet()) {
			sumSurfaceTimesTemperature += x.getValue().getSmallestTemperatureInLastInterval() 
											* x.getValue().getSuprafata();
			totalSurface += x.getValue().getSuprafata();
		}
		double mediePonderata = sumSurfaceTimesTemperature / totalSurface;
		return mediePonderata < this.globalTemperature;
	}
	/**
	 * 
	 * @param specificRoom	numele camerei
	 * @param timestamp1	ora pana la care trebuie sa afisez intervalul de temperaturi
	 * @param timestamp2	ora de la care afisez intervalul de teperaturi
	 * @param writer		fisierul in care scriu intervalele
	 * @throws IOException	Daca nu se poate realiza scrierea in fisier se afiseaza exceptia
	 */

	public void printHouse(String specificRoom,int timestamp1,int timestamp2, Writer writer) throws IOException {
		TreeSet<Double> val= null;
		for (Map.Entry<String, Room> x : this.rooms.entrySet()) {
			x.getValue().printRoom(specificRoom,timestamp1,timestamp2,writer);
		}
	}
}
