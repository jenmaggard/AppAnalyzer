import java.util.ArrayList;

/**
 * Method class. This is the same as IntraProcedural Graph
 * @author Faisal
 *
 */

public class Method implements GraphComponent {
	
	private String packageAndClass;
	private String name;
	private String[] paramsTypes;
	private String returnType;
	private ArrayList<GraphComponent> basicblocks;
	
	
	public Method(){
		basicblocks = new ArrayList<GraphComponent>();
	}

	public String getPackageAndClass() {
		return packageAndClass;
	}


	public void setPackageAndClass(String packageAndClass) {
		this.packageAndClass = packageAndClass;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String[] getParamsTypes() {
		return paramsTypes;
	}


	public void setParamsTypes(String[] paramsTypes) {
		this.paramsTypes = paramsTypes;
	}


	public String getReturnType() {
		return returnType;
	}


	public void setReturnType(String returnType) {
		this.returnType = returnType;
	}

	@Override
	public void addComponent(GraphComponent e) {
		basicblocks.add(e);
		
	}

	@Override
	public void removeComponent(GraphComponent e) {
		
		basicblocks.remove(e);
	}
	
	
}
