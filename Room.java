package tema2;

import java.util.ArrayList;
import java.io.*;
import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

public class Room {
	private String name;
	private String device_id;
	private double suprafata;
	private Dispozitiv dispozitiv;
/**
 * Setez id-ul, numele camerei si temperatura
 * @param id	id-ul camerei
 * @param nume	numele camerei
 * @param suprafata	suprafata camerei
 */
	public Room(String id, String nume, double suprafata) {
		this.device_id = id;
		this.name = nume;
		this.suprafata = suprafata;
		this.dispozitiv = new Dispozitiv();
	}
	/**
	 * Adaug temperaturi la ora corespunzatoare
	 * @param globalTimestamp  	timestamp-ul global
	 * @param currentTimestamp	timestamp-ul primit ca parametru
	 * @param temperature		temperatura ce trebuie adaugata
	 */
	public void addTemperature(int globalTimestamp, int currentTimestamp, double temperature) {
		dispozitiv.addTemperature(globalTimestamp, currentTimestamp, temperature);
	}

	/**
	 * 
	 * @param specificRoom	camera pentru care trebuie afisate temperaturile 
	 * @param timestamp1	ora pana la care afisez	
	 * @param timestamp2	ora de la care afisez
	 * @param writer		scrie in fisier
	 * @throws IOException	in cazul in care nu se poate scrie in fisier afisez exceptia
	 */
	public void printRoom(String specificRoom,int timestamp1, int timestamp2,Writer writer) throws IOException {
		DecimalFormat numberFormat = new DecimalFormat("#.00");
		if(this.name.equals(specificRoom)) {
			writer.string(specificRoom);
			for(Map.Entry<Integer, TreeSet<Double>> x :
				this.dispozitiv.getTemperatures().entrySet()) {
				if(x.getKey() >=timestamp2 && x.getKey() < timestamp1) {
					for (Double val : x.getValue()) {
						writer.write(String.format(" "+numberFormat.format(val)));
					}
				}
			}
		}

	}
	/**
	 * @return	returnez temperatura minima din ultimul interval 
	 * 			in care s-au inregistrat temperaturi
	 */
	public double getSmallestTemperatureInLastInterval() {
		return this.dispozitiv.getLastInterval().first();
	}

	public String getDevice_id() {
		return this.device_id;
	}

	public double getSuprafata() {
		return suprafata;
	}

	public String getName() {
		return this.name;
	}
}
