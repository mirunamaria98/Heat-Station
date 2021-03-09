package tema2;

import java.io.IOException;
import java.util.Map;
import java.util.TreeSet;
import java.util.Vector;
/**
 * 
 * @author Miruna
 *Deschid fisierele din care voi citi si in care voi scrie,
 *Citesc si adaug camerele in House,
 *Apelez metoda de a schimba temperatura globala (daca este cazul),
 *Verific daca trebuie pornita centrala,
 *Afisez intervalele corespunzatoare orelor primite
 *
 */
public class Main {
	public static void main(String[] args) throws IOException {
		Reader reader = new Reader("/therm.in");
		Writer writer = new Writer("/therm.out");
		
		Vector<Object> setupHouse = reader.readHouseSetup();
		House house = new House((Integer)setupHouse.get(2), (Double)setupHouse.get(1));
		int numberOfRooms = (Integer)setupHouse.get(0);
		
		
		for (int i = 0; i < numberOfRooms; i++) {
			Vector<Object> roomSetup = reader.readRoomSetup();
			house.addRoom(roomSetup.get(1).toString(), roomSetup.get(0).toString(), (Double)roomSetup.get(2));
		}
		
		String st;
		while ((st = reader.readCommand()) != null) {
			if (st.equals("TRIGGER HEAT")) {
				if (house.shouldTriggerHeatingSystem()) {
					writer.writeLine("YES");
				} else {
					writer.writeLine("NO");
				}
				continue;
			}
			
			String[] arrOfStr = st.split(" ");
			
			if (arrOfStr.length == 4 && arrOfStr[0].equals("OBSERVE")) {
				String id = arrOfStr[1];
				int time = Integer.parseInt(arrOfStr[2]);
				double temperature = Double.parseDouble(arrOfStr[3]);
				
				if(house.containsRoom(id)) {
					house.addTemperatureForRoom(id, time, temperature);
				}
				
			}
			
			if (arrOfStr.length == 2 && arrOfStr[0].equals("TEMPERATURE")) {
				house.setGlobalTemperature(Double.parseDouble(arrOfStr[1]));
			}
			if(arrOfStr.length == 4 && arrOfStr[0].equals("LIST")) {
				String specificRoom=arrOfStr[1];
				int timestamp1=Integer.parseInt(arrOfStr[2]);
				timestamp1=(house.getGlobalTimestamp()-timestamp1)/3600;
				int timestamp2=Integer.parseInt(arrOfStr[3]);
				timestamp2=(house.getGlobalTimestamp()-timestamp2)/3600;
				house.printHouse(specificRoom, timestamp1, timestamp2,writer);
				writer.space();
				
				
			}
			
			
		}
		
		writer.closeWriter();
	}
}
