package tema2;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.TreeSet;

public class Writer {
	private File file;
	private BufferedWriter writer;
	/**
	 * @param path	calea fisierului in care voi scrie
	 * @throws IOException	in cazul in care nu se poate scrie in fisier va aparea eroarea
	 */
	public Writer(String path) throws IOException {
		this.file = new File(path);
		this.writer = new BufferedWriter(new FileWriter(this.file));
	}
	
	public void writeLine(String str) throws IOException {
		this.writer.append(str + "\n");
	}
	public void write(String str) throws IOException {
		this.writer.append(str);
	}
	public void string(String str) throws IOException {
		this.writer.append(str);
	}
	public void space() throws IOException {
		this.writer.append("\n");
	}
	
	public void closeWriter() throws IOException {
		this.writer.close();
	}

}
