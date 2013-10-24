
/**
 * Decompiler AndroGuard extends abstract class Decompiler
 * @author Mengfei
 *
 */
public class AndroGuard extends Decompiler{
	public String appName,plaintName;
	protected String bytecodeFile;
	
	public AndroGuard(String name){
		this.appName=name;
		int index=name.indexOf(".");
		plaintName=name.substring(0, index);
	}
	
	/**
	 * Decompile the android application to dalvik byte code
	 * return bytecode file path
	 */
	@Override
	public String decompile() {
		// TODO Auto-generated method stub
		Process p;
		String apk="apk\\"+appName;
		String directory="F:\\projects\\AppAnalyzer\\AppAnalyzer\\";
		
		//Get .xml files
		String apktool=directory+"decompile\\apktool.bat";

		ProcessBuilder pb=new ProcessBuilder();
		pb.command(apktool,"d","-f",directory+apk,directory+"apk\\"+plaintName);
		try{
			p=pb.start();
			p.waitFor(); // wait for process finishes
			System.out.println("Get XML files.");
		}catch (Exception e){
			e.printStackTrace();
		}
		
		//Get Dalvik Byte Code
		//pb.directory(new File(directory));
		String androGuard=directory+"decompile\\androguard\\cfgAndroFile.py";
		bytecodeFile=directory+"bytecode\\"+plaintName+".txt";
		pb.command("python",androGuard,"-i",apk,"-o",bytecodeFile);
		try{
			p=pb.start();
			p.waitFor(); // wait for process finishes
			System.out.println("Get Byte Code file.");
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
	public String getFilePath() {
		// TODO Auto-generated method stub
		return this.bytecodeFile;
	}
		
}
