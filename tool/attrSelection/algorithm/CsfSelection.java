package tecmides.tool.attrSelection.algorithm;

import weka.attributeSelection.BestFirst;
import weka.attributeSelection.CfsSubsetEval;
import weka.core.Instances;
import weka.filters.Filter;
import weka.filters.supervised.attribute.AttributeSelection;

public class CsfSelection implements AttrSelectionAlgorithmTool {

    @Override
    public Instances run(Instances instances, int classIndex) throws Exception {
        instances.setClassIndex(classIndex);

        AttributeSelection filter = new AttributeSelection();

        CfsSubsetEval evaluator = new CfsSubsetEval();
        filter.setEvaluator(evaluator);

        BestFirst search = new BestFirst();
        filter.setSearch(search);

        filter.setInputFormat(instances);

        return Filter.useFilter(instances, filter);
    }

}
