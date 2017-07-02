package helpers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import model.Properties;

public class FileHandler {
	static File file = new File("./properties/config.properties");
	/**
	 * read properties file
	 */
	public static void readProperties(){
		FileReader fr = null;
		BufferedReader br = null;
		try {
			fr = new FileReader(file);
			br = new BufferedReader(fr);
			String line1, line2;
			line1 = br.readLine();
			line2 = br.readLine();
			if (line1 != null && line2 != null) {
				Properties.IS_INITIAL = Boolean.parseBoolean(line1.split(":")[1]);
				Properties.IS_MEASUREMENT_SET = Boolean.parseBoolean(line2.split(":")[1]);
			}
			System.out.println("properties file read successfully");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("properties file not found");
		} catch (IOException e) {
			System.out.println("Error occurred while reading properties file");
		} finally {
			try {
				if (br != null) {
					br.close();
				}
				
				if (fr != null) {
					fr.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("Error occurred while reading properties file");
			}
		}	
	}
	
	/**
	 * write properties file
	 */
	public static void writeProperties(){
		FileWriter fw = null;
		BufferedWriter bw = null;
		try {
			fw = new FileWriter(file);
			bw = new BufferedWriter(fw);
			String content = "isInitial:" + Properties.IS_INITIAL;
			bw.write(content);
			bw.newLine();
			content = "isMeasurementsSet:" + Properties.IS_MEASUREMENT_SET;
			bw.write(content);
			System.out.println("properties file updated successfully");
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Error occurred while writing properties file");
		} finally {
			try {
				if (bw != null) {
					bw.close();
				}
				
				if (fw != null) {
					fw.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("Error occurred while reading properties file");
			}
		}	
		
	}

}
