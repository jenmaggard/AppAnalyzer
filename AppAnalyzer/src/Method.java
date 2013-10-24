import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.swing.event.ListSelectionEvent;

/**
 * Method class. This is the same as IntraProcedural Graph
 * @author Faisal
 *
 */

public class Method implements GraphComponent {
	
	private String packageAndClass;
	private String name;
	private ArrayList<String> paramsTypes;
	private String returnType;
	private ArrayList<GraphComponent> basicblocks;
	private HashMap<String, BasicBlock> basicBlockMap;
	private ArrayList<String> BBOrder;
	
	
	public Method(){
		basicblocks = new ArrayList<GraphComponent>();
		paramsTypes = new ArrayList<String>();
		basicBlockMap = new HashMap<String, BasicBlock>();
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


	public ArrayList<String> getParamsTypes() {
		return paramsTypes;
	}
	
	public void addParamType(String pt){
		paramsTypes.add(pt);
	}


	public String getReturnType() {
		return returnType;
	}


	public void setReturnType(String returnType) {
		this.returnType = returnType;
	}
	
	public ArrayList<GraphComponent> getBasicBlocks(){
		return basicblocks;
	}
	
	/**
	 * Removes loops from the method by performing a dfs.
	 */
	public void removeLoops(){
		
		//Compute dominator set for each BB
		HashMap<String, HashSet<String>> dominators = calculateDominatorSets();
		
		//Now, for each BB check to see if it is dominated by a child BB
		for(String current : this.basicBlockMap.keySet()){
			
			for(String child : this.basicBlockMap.get(current).getNext()){
				
				//See if child dominated current
				if(dominators.get(current).contains(child)){
					//remove child from current's next
					this.basicBlockMap.get(current).revomeNext(child);
					
					//remove current from child's prev
					this.basicBlockMap.get(child).removePrev(current);
				}
			}
		}
	}
	
	
	/**
	 * Calculates the dominator sets for each of the basic block
	 * @return the final dominator sets for each of the basic blocks
	 */
	private HashMap<String, HashSet<String>> calculateDominatorSets(){
		
		HashMap<String, HashSet<String>> dominators = new HashMap<String, HashSet<String>>();
		
		//Get the root
		String root = ((BasicBlock)basicblocks.get(0)).getName();
		
		//Add the dominator for the root
		Set<String> set = new HashSet<String>();
		set.add(root);
		dominators.put(root, (HashSet<String>) set);
		
		//Now add dominators for every other basic block
		set = this.basicBlockMap.keySet();
		for(String bb : set){
			if(bb == root)
				continue;
			else
				dominators.put(bb, new HashSet<String>(set));
		}
		
		
		Boolean changed = true;
		while(changed == true){
			changed = false;
			
			//recompute dominators for each bb and see if any change
			for(String bb : set){
				BasicBlock current = this.basicBlockMap.get(bb);
				
				//Find all the predecessors
				Iterator<String> iterator = current.getPrev().iterator();
				
				//Calculate the intersection of all the predecessors
				HashSet<String> intersect = new HashSet<String>();
				if(iterator.hasNext())
					intersect.addAll(dominators.get(iterator.next()));
				while(iterator.hasNext()){
					intersect.retainAll(dominators.get(iterator.next()));
				}
				
				//Add current bb to the intersect
				intersect.add(bb);
				
				//Now see if it has changed
				if(!dominators.get(bb).equals(intersect)){
					changed = true;
					dominators.put(bb, intersect);
				}
			}
			
		}
		return dominators;
	}
	
	/**
	 * Computes the BB visit order
	 */
	public void computeBBOrder(){
		
		//Get the post order traversal of the BBs
		String root = ((BasicBlock)basicblocks.get(0)).getName();
		
		this.BBOrder = new ArrayList<String>();
		
		postOrderTraversal(root);
		
		//Now reverse the post order to get visit order
		Collections.reverse(this.BBOrder);
		
	}
	
	
	/**
	 * Performs post order traversal
	 * @param root the root node
	 * @param list list of nodes in post order
	 */
	private void postOrderTraversal(String root){
		
		//Traverse each of the children
		for (String child : this.basicBlockMap.get(root).getNext()){
			postOrderTraversal(child);
		}
		
		//visit root if not visited
		if(!this.BBOrder.contains(root))
			this.BBOrder.add(root);
	}
	
	@Override
	public void addComponent(GraphComponent e) {
		basicblocks.add(e);
		
		if(e instanceof BasicBlock)
			this.basicBlockMap.put(((BasicBlock) e).getName(), (BasicBlock)e);
	}

	@Override
	public void removeComponent(GraphComponent e) {
		
		basicblocks.remove(e);
	}

	@Override
	public void process() {
		// TODO Auto-generated method stub
		
	}
	
	
}
