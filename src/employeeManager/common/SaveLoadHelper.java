package employeeManager.common;

import java.io.*;
import java.util.ArrayList;

public class SaveLoadHelper {
	final public static int OUTPUT_MODE = 0;
	final public static int INPUT_MODE = 1;
	final public static int WRITER_MODE = 2;
	
	private FileOutputStream eFileOut;
	private ObjectOutputStream eObjectOut;
	private FileInputStream eFileIn;
	private ObjectInputStream eObjectIn;
	private FileWriter eWriter;
	
	public SaveLoadHelper(File eFile, int mode) throws Exception {
		if (mode == OUTPUT_MODE) {
			eFileOut = new FileOutputStream(eFile);
			eObjectOut = new ObjectOutputStream(eFileOut);
		}
		if (mode == INPUT_MODE) {
			eFileIn = new FileInputStream(eFile);
			eObjectIn = new ObjectInputStream(eFileIn);
		}
		if (mode == WRITER_MODE) {
			eWriter = new FileWriter(eFile);
		}
	}
	
	public void write(ArrayList<Employee> eList) throws Exception {
		eObjectOut.writeObject(eList);
		eObjectOut.close();
	}
	public ArrayList<Employee> load() throws Exception {
		@SuppressWarnings("unchecked")
		ArrayList<Employee> tmpList = (ArrayList<Employee>) eObjectIn.readObject();
		eObjectIn.close();
		return tmpList;
	}
	public void writeText(String st) throws Exception {
		eWriter.write(st);
		eWriter.close();
	}
}
