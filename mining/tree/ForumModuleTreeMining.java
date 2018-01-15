package tecmides.mining.tree;

import tecmides.domain.MiningAttribute;
import tecmides.tool.attrSelection.AttrSelectionTool;
import tecmides.tool.attrSelection.GeneralSelection;
import tecmides.tool.classification.ClassificationTool;
import weka.core.Instances;

public class ForumModuleTreeMining implements ModuleTreeMining {

    @Override
    public String run(Instances instances, ClassificationTool classificator) throws Exception {
        AttrSelectionTool attrSelector = new GeneralSelection();

        // '-4' beacuse of the number of attributes removed before the index
        Instances preparedInstances = attrSelector.run(instances, "2,3,7,8,10,11", MiningAttribute.ST_GROUP_ASSIGN_LTSUBMIT.ordinal() - 4);
        
        // TODO: Extrair a árvore para um formato navegável */
        return classificator.run(preparedInstances);
    }

}
