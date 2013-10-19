/**
 * Static Analysis Tool interface
 * @author Faisal
 *
 */
public interface StaticAnalysisTool {
	public ScanResult analyze();
	public void setGraph(GraphComponent e);
}
