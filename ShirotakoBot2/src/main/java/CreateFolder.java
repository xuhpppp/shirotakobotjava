import java.io.File;

public class CreateFolder {

	public static void createFolder(String folderName) {
		String path = "D:\\jee-2021-09/ShirotakoBot2/" + folderName;
		File folder = new File(path);
		
		if (!folder.exists()) {
			folder.mkdir();
		}
	}
}
