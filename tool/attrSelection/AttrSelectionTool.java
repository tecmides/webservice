package tecmides.tool.attrSelection;

import weka.core.Instances;

public interface AttrSelectionTool {

    public Instances select(Instances instances, String attrIndexes) throws Exception;
}
