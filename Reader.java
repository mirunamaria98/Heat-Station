package tema2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;

public class Reader {
	private File file;
	private BufferedReader reader;
	/**
	 * 
	 * @param path calea in care se afla fisierul din care voi citi
	 * @throws FileNotFoundException	in cazul in care nu se poate citi din fisier se va afisa eroarea
	 */
	public Reader(String path) throws FileNotFoundException {
		this.file = new File(path);
		this.reader = new BufferedReader(new FileReader(this.file));
	}
	/**
	 * Aici citesc ,transform si adaug informatiile sitite din fisier
	 * @return	returnez vectorul cu valorile citite
	 * @throws IOException	in cazul in care nu se poate citi din fisier afisez eroarea
	 */
	public Vector<Object> readHouseSetup() throws IOException {
		String line = this.reader.readLine();
		String[] arr = line.split(" ", 3);
		Vector<Object> vec = new Vector<Object>();
		vec.add(Integer.parseInt(arr[0]));
		vec.add(Double.parseDouble(arr[1]));
		vec.add(Integer.parseInt(arr[2]));
		return vec;
	}
	
	public Vector<Object> readRoomSetup() throws IOException {
		String line = this.reader.readLine();
		String[] arr = line.split(" ", 3);
		Vector<Object> vec = new Vector<Object>();
		vec.add(arr[0].toString());
		vec.add(arr[1].toString());
		vec.add(Double.parseDouble(arr[2]));
		return vec;
	}
	
	public String readCommand() throws IOException {
		return this.reader.readLine();
	}
}
