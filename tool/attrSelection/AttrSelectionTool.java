package tecmides.tool.attrSelection;

import weka.core.Instances;

public interface AttrSelectionTool {

    public Instances run(Instances instances, String attrIndexes, int classIndex) throws Exception;
}
