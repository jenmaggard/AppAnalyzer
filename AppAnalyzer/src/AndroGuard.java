import java.io.File;

/**
 * Decompiler AndroGuard extends abstract class Decompiler
 * @author Mengfei
 *
 */
public class AndroGuard extends Decompiler{
	public String name;
	protected String bytecodeFile;
	
	/**
	 * Decompile the android application to dalvik byte code
	 * return bytecode file path
	 */
	@Override
	public String decompile() {
		// TODO Auto-generated method stub
		Process p;
		String apk="apk\\Test.apk";
		String directory="F:\\projects\\AppAnalyzer\\AppAnalyzer\\";
		
		//Get .xml files
		String apktool=directory+"decompile\\apktool.bat";

		ProcessBuilder pb=new ProcessBuilder();
		pb.command(apktool,"d","-f",directory+apk,directory+"apk\\Test");
		try{
			p=pb.start();
			p.waitFor(); // wait for process finishes
		}catch (Exception e){
			e.printStackTrace();
		}
		
		//Get Dalvik Byte Code
		pb.directory(new File(directory));
		String androGuard=".\\decompile\\androguard\\cfgAndroFile.py";
		bytecodeFile=".\\bytecode\\Test.txt";
		pb.command("python",androGuard,"-i",apk,"-o",bytecodeFile);
		try{
			p=pb.start();
			p.waitFor(); // wait for process finishes
		}catch (Exception e){
			e.printStackTrace();
		}
		return bytecodeFile;
	}

	@Override
	public void setFilePath(String fp) {
		// TODO Auto-generated method stub
		this.bytecodeFile=fp;
	}
	
	@Override
	public String getAppName() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
