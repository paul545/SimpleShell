import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;
import java.util.HashMap;

/**
 * 
 */



/**
 * @author paul5
 *
 */
public class SimpleShell {
	
	CommandLibrary commandLib;
	
	public SimpleShell() {
		
		commandLib = new CommandLibrary();
		
		File file = new File("./CommandFile.sav");
		
		if (file.exists())
			loadCommandLibrary("./CommandFile.sav");

	}
	

	/**
	 * Keywords: save, create, read, exit
	 */
	public void run() {

		String prompt = "ss> ";
		Scanner scan = new Scanner(System.in);
		String input;
		
		Runtime runtime = Runtime.getRuntime();
		
		while (true) {
			
			System.out.print(prompt);
			input = scan.next();
			
			switch(input) {
			
			case "exit":
				scan.close();
				System.exit(0);
				
		
			case "note":
				try { runtime.exec("notepad.exe"); } 
				catch (IOException e) { e.printStackTrace(); }	
				continue;
				
			case "create":
				System.out.print("Key: ");
				String key = scan.next();
				System.out.print("Program Path: ");
				String programPath = scan.next();
				System.out.print("Arguments: ");
				String args = scan.next();
				
				commandLib.addCommand(key, programPath, args);
				continue;
						
			case "save":
				saveCommandLibrary("./CommandFile.sav");
				continue;
			
			case "read":
				String input2 = scan.next();
				
				switch(input2) {
			
					case "physics":
						try { runtime.exec(commandLib.getArray("physics")); }
						catch ( IOException e) { e.printStackTrace(); }
						continue;
				}
				
			default:
					try { 
						runtime.exec(commandLib.getArray(input)); 
						}
					catch ( IOException e) { 
						System.out.println("Command not recognized");
						e.printStackTrace();
						continue;
					}
			}		
		}
	}
	
	public CommandLibrary loadCommandLibrary() {
		
		return null;
	}
	
	@SuppressWarnings("unchecked")
	protected void loadCommandLibrary(String file) {
		
		try {
			FileInputStream saveFile = new FileInputStream(file);
			try {
				ObjectInputStream restore = new ObjectInputStream(saveFile);
				try {
					commandLib.commands = (HashMap<String, String[]>) restore.readObject();
					restore.close();
				}
				catch (ClassNotFoundException e) { e.printStackTrace(); }
				catch (EOFException eof) {}
			}
			catch (IOException e) { e.printStackTrace(); }
			
			
		} catch (FileNotFoundException e) {	e.printStackTrace(); }
	}
	
	public boolean saveCommandLibary() {
		
		return false;
	}
	
	public boolean saveCommandLibrary(String file) {
		
		try { 
			FileOutputStream saveFile = new FileOutputStream(file);
			try {
				ObjectOutputStream save = new ObjectOutputStream(saveFile);
				save.writeObject(commandLib.commands);
				save.close();
				System.out.println("File Saved");
				return true;
			}
			catch (IOException e) { e.printStackTrace(); }	
		} 
		catch (FileNotFoundException e) { e.printStackTrace(); }
		
		return false;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SimpleShell ss = new SimpleShell();
		ss.run();

	}

}
