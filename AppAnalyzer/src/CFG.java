import java.util.ArrayList;


/**
 * 
 * @author Faisal
 *
 */
public class CFG implements GraphComponent{

	private String bytecodeFilePath;
	private ArrayList<GraphComponent> methods;
	
	public CFG(){
		methods = new ArrayList<GraphComponent>();
	}

	public String getBytecodeFilePath() {
		return bytecodeFilePath;
	}

	public void setBytecodeFilePath(String bytecodeFilePath) {
		this.bytecodeFilePath = bytecodeFilePath;
	}

	public ArrayList<GraphComponent> getMethods() {
		return methods;
	}

	@Override
	public void addComponent(GraphComponent e) {
		methods.add(e);
	}

	@Override
	public void removeComponent(GraphComponent e) {
		methods.remove(e);
	}

	@Override
	public void process() {
		// TODO Auto-generated method stub
		
	}
	
	
}
