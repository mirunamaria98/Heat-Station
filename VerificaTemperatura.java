package tema2;
import java.io.*;

public class VerificaTemperatura {
	/**
	 * Daca timestampul global este mai mic decat cel primit ca parametru 
	 * acesta nu o sa fie luat in considerare
	 * @param timestamp	timestamp-ul global
	 * @param timp_curent	timpul la care se inregistreaza o noua temperatura
	 * @return	returnez timpul transformat
	 */
	public static int ConvertTime(int timestamp , int timp_curent){
		if (timestamp < timp_curent) {
			return -1;
		}
		int timp= (timestamp-timp_curent) / 3600;
		if (timp > 24)
			return -1;
		return timp;	
	}
}
