package tecmides.mining.tree;

import tecmides.tool.classification.ClassificationTool;
import weka.core.Instances;

public interface ModuleTreeMining {
    
    public String run(Instances instances, ClassificationTool classificator) throws Exception;
}
