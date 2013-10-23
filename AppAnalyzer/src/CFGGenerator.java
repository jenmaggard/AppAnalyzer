import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;


/**
 * CFG generator Class
 * @author Faisal
 *
 */
public class CFGGenerator implements GraphGenerator{
	
	private String bytecodeFilePath;

	public String getBytecodeFilePath() {
		return bytecodeFilePath;
	}

	public void setFP(String fp) {
		this.bytecodeFilePath = fp;
	}
	
	
	/**
	 * This method parses the bytecode file into a CFG.
	 * It uses helper methods to generate each of the components of the CFG.
	 * 
	 * @return a CFG representation of the bytecode
	 */
	public GraphComponent generate(){
		
		CFG cfg = new CFG();
		cfg.setBytecodeFilePath(bytecodeFilePath);
		
		
		try {
			
			//Try opening and reading from the bytecode file
			BufferedReader input = new BufferedReader(new FileReader(bytecodeFilePath));
			
			String line = input.readLine().trim();
			
			//Build a string for each method and call method parser to parse it.
			while (line != null){
				if(line.startsWith("Lcom")){
					StringBuilder builder = new StringBuilder();
					do{
						builder.append(line.trim()+"\n");
						line = input.readLine();
					}while(line != null && !line.startsWith("Lcom"));
					
					//Call parseMethod to parse the string into a method.
					Method method = parseMethod(builder.toString());
					
					//Add method to the CFG
					if(method != null)
						cfg.addComponent(method);
				}
				
				else
					//The program flow will never get here
					System.out.println("Something wrong");
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		//Now we need to remove the loops from the CFG. 
		cfg.detectLoops();
		
		//Now determine BB order for each method.
		cfg.computeBBOrder();
		
		return cfg;
	}
	
	
	
	/**
	 * This method takes in a method as a string and parses it into a Method Object.
	 * 
	 * @param string - a string representation of the method
	 * @return Method Object of parsed method
	 */
	private Method parseMethod(String string) {
		Method method = null;
		
		//Read the string line by line.
		BufferedReader input = new BufferedReader(new StringReader(string));
		
		try {
			String line = input.readLine();
			
			//Get the package and class name of method
			int index = line.indexOf(';');
			String methodPackageAndClass = line.substring(0, index).trim();
			
			//We don't care about BuildConfig or any methods in R.style
			if (methodPackageAndClass.contains("R$") || methodPackageAndClass.contains("BuildConfig"))
				return null;
			
			method = new Method();
			method.setPackageAndClass(methodPackageAndClass);
			
			//Get the other method attributes
			line = line.substring(index+1);
			index = line.indexOf('(');
			method.setName(line.substring(0,index-1).trim());
			
			line = line.substring(index+1);
			index = line.indexOf(')');
			String temp = line.substring(0,index);
			String [] methodParamsTypes = temp.split(";");
			
			for(String current : methodParamsTypes){
				method.addParamType(current.trim());
			}
		
			line = line.substring(index+1);
			method.setReturnType(line.trim());
			
			
			//Now continue reading the string and build a string for each basic block within the method
			line = input.readLine();
			while(line != null){
				if(line.contains("BB@")){
					StringBuilder builder = new StringBuilder();
					do{
						builder.append(line+"\n");
						line = input.readLine();
					}while(line != null && !line.contains("BB@"));
					
					BasicBlock basicblock = parseBasicBlock(builder.toString());
					if(basicblock !=null)
						method.addComponent(basicblock);
				}
				else
					//Program flow should never get here
					System.out.println("Something wrong");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return method;
	}

	
	
	/**
	 * This method takes in a basic block as a string and parses it into a BasicBlock Object
	 * 
	 * @param string - a string representation of the basic block
	 * @return BasicBlock Object of parsed basic block
	 */
	private BasicBlock parseBasicBlock(String string) {
		BasicBlock bb = new BasicBlock();
		
		//Set up buffered reader to read string line by line
		BufferedReader input = new BufferedReader(new StringReader(string));
		
		try {
			String line = input.readLine();
			
			//First line contains basic block attributes that need to be parsed
			int index = line.indexOf('[');
			String temp = line.substring(0,index).trim();
			String [] stuff = temp.split(" ");
			
			//Set the attributes
			bb.setName(stuff[0].trim());
			bb.setStartAddress(stuff[1].trim());
			bb.setEndAddress(stuff[2].trim());
			
			line = line.substring(index+1);
			index = line.indexOf(']');
			String nexts = line.substring(0, index).trim();
			String prevs = line.substring(index+1).trim();
			
			index = nexts.indexOf('=');
			nexts = nexts.substring(index+1).trim();
			String[] next =  nexts.split(" ");
			for(String current : next){
				index = current.indexOf('-');
				index = current.indexOf('-', index+1);
				bb.addNext(current.substring(index+1).trim());
			}
			
			index = prevs.indexOf('=');
			int index2 = prevs.indexOf(']');
			prevs = prevs.substring(index+1, index2).trim();
			String[] prev = prevs.split(",");
			for(String current : prev){
				bb.addPrev(current.trim());
			}
			
			
			//Now we need to parse each instruction in the basic block
			line = input.readLine();
			while(line !=null){
				//Parse the instruction and add it to the basic block
				Instruction prevInstruction = null;
				Instruction instruction = parseInstruction(line);
				instruction.setPrev(prevInstruction);
				bb.addComponent(instruction);
				
				line = input.readLine();
				prevInstruction = instruction;
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return bb;
	}
	

	/**
	 * This method takes in a string instruciton and parses it into an Instruction Object
	 * 
	 * @param string - the string representation of an instruction
	 * @return Instruction Object of the parsed instruciton
	 */
	private Instruction parseInstruction(String string) {
		Instruction instruction = new Instruction();
		
		//Parse the instruction from the string
		int index = string.indexOf(' ');
		instruction.setAddress(string.substring(0, index).trim());
		
		string = string.substring(index+1);
		index = string.indexOf(' ');
		
		//This happens when we have an instruction with no arguments, such as return-void
		if(index == -1)
		{
			instruction.setType(string.trim());
			return instruction;
		}
		
		//Otherwise, we have an instruction with arguments
		instruction.setType(string.substring(0, index));
		string = string.substring(index+1);
		string.replaceAll(" ", "");
		String [] args = string.split(",");
		
		for(String current : args){
			instruction.addArgument(current.trim());
		}
		
		return instruction;
	}
	
	
	/**
	 * DRIVER TO DEBUG CODE
	 */
	
	public static void main(String[] args){
		
		CFGGenerator cfgGen = new CFGGenerator();
		cfgGen.setFP("C:\\Users\\Faisal\\workspace\\blah\\src\\blah\\TestMalicious.txt");
		
		CFG cfg = (CFG) cfgGen.generate();
	}
}
