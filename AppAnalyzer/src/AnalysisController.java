/**
 * Analysis controller class. Drives the analysis of the app
 * @author Faisal
 *
 */
public class AnalysisController {

	private Decompiler decompiler;
	private StaticAnalysisTool analyzer;
	private GraphGenerator graphGenerator;
	private String apkFilePath;
	
	public AnalysisController(Decompiler d, StaticAnalysisTool t, GraphGenerator gg, String fp){
		this.decompiler = d;
		this.analyzer = t;
		this.graphGenerator = gg;
		this.apkFilePath = fp;
	}
	
	public ScanResult scan(){
		decompiler.setFP(apkFilePath);
		String bcfp = decompiler.decompile();
		
		graphGenerator.setFP(bcfp);
		GraphComponent gc = graphGenerator.generate();
		
		analyzer.setGraph(gc);
		return analyzer.analyze();
	}
}
