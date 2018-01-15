package tecmides.tool.classification;

import weka.core.Instances;

public interface ClassificationTool {
    
    public String run(Instances instances) throws Exception;
    
}
