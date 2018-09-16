import java.util.HashMap;
import java.util.Scanner;

/**
 * 
 */

/**
 * @author paul5
 *
 */
public class CommandLibrary {
	
	HashMap<String, String[]> commands;
	int size;
	
	
	public CommandLibrary() {
		commands = new HashMap<>();
		size = 0;
		
		String[] phys = {"\"C:\\Program Files\\SumatraPDF\\SumatraPDF.exe\"","\"C:\\Users\\paul5\\Documents\\uncaStuff\\Fall2018\\University Physics with Modern Physics, 13th Edition.pdf\""}; 
		
		commands.put("physics", phys);
	}
	
	
	public void addCommand(String key, String program, String path) {
		
		String[] array = {program, path};
		
		if (commands.containsKey(key)){
			Scanner scan = new Scanner(System.in);
			System.out.println("Key already exists. This will overwrite it's value. Confirm? y or n");
			String ans = scan.next();
			if (ans.equals("y"))
				commands.put(key, array);
			else {
				System.out.println("Canceling");
			}
			scan.close();
		} 
		else 
			commands.put(key, array);
	}
	
	public void removeBook(String key) {
		commands.remove(key);
	}
	
	
	public String[] getArray(String key) {
		return commands.get(key);
	}
	
	protected boolean containsCommand(String key) {
		if (commands.containsKey(key))
			return true;
		return false;
	}
	
	
	
	
	
	
	
}
