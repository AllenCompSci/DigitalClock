package generic.graphics;

import java.io.*;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Scanner;

public class FileHelper {
	private static String dir = "resource/";
	private static FileReader fRead;
	private static BufferedReader bRead;
	private static Scanner sc;
	private static FileWriter fWrite;
	private static BufferedWriter bWrite;

	public static void writeFile(ArrayList<Object> n, String FileName) throws IOException{
		if(!FileExists(FileName)){
			File file = new File(dir+FileName);
			file.createNewFile();
		}
		fWrite = new FileWriter(new File(dir+FileName));
		bWrite = new BufferedWriter(fWrite);
		for(Object val : n){
			bWrite.write(val.toString());
			bWrite.newLine();
		}
		bWrite.close();
		fWrite.close();
	}

	public static boolean FileExists(String FileName){
		File tempDir = new File(dir+FileName);
		return tempDir.exists();
	}

	public static ArrayList<BigInteger> readFile(String FileName){
		if(!FileExists(FileName)){
			return null;
		}
		ArrayList<BigInteger> create = new ArrayList <BigInteger>();
		try{
			fRead = new FileReader(dir + FileName);
			bRead = new BufferedReader(fRead);
			sc = new Scanner(fRead);
			while(sc.hasNextLine()){
				String line = sc.nextLine();
				create.add(new BigInteger(line));
			}
			sc.close();
			bRead.close();
			fRead.close();
		}catch(IOException ex){
			ex.printStackTrace();
		}
		
		return create;
	}
	
}
