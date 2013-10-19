import java.util.ArrayList;

/**
 * Instruction class.
 * @author Faisal
 *
 */

public class Instruction implements GraphComponent{
	
	private String type;
	private String address;
	private ArrayList<String> args;
	private Instruction prev;
	
	public Instruction(){
		args = new ArrayList<String>();
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Instruction getPrev() {
		return prev;
	}

	public void setPrev(Instruction prev) {
		this.prev = prev;
	}

	public ArrayList<String> getArgs() {
		return args;
	}
	
	public void addArgument(String arg){
		args.add(arg);
	}

	@Override
	public void addComponent(GraphComponent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeComponent(GraphComponent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void process() {
		// TODO Auto-generated method stub
		
	}
}
