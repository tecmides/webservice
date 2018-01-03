package tecmides.tool.attributeSelection;

import weka.core.Instances;

public interface AttributeSelectionTool {
    
    public Instances select(Instances data) throws Exception;
    
}
