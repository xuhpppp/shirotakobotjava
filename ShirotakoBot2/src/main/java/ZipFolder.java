import java.io.File;
import org.zeroturnaround.zip.ZipUtil;

public class ZipFolder {

	public static void zipFolder(String name) {
		ZipUtil.pack(new File("D:\\jee-2021-09/ShirotakoBot2/" + name), new File("D:\\jee-2021-09/ShirotakoBot2/" + name + ".zip"));
		
		System.out.println("finish zipping");
	}
	
}
