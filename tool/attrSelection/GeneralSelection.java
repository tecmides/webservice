package tecmides.tool.attrSelection;

import weka.core.Instances;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.Remove;

public class GeneralSelection implements AttrSelectionTool {

    @Override
    public Instances select(Instances instances, String attrIndexes) throws Exception {
        Instances filteredData;

        if (attrIndexes.length() > 0) {
            Remove remove = new Remove();
            remove.setAttributeIndices(attrIndexes);
            remove.setInputFormat(instances);
            filteredData = Filter.useFilter(instances, remove);
        } else {
            filteredData = instances;
        }

        return filteredData;
    }
}
