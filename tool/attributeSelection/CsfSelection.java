/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tecmides.tool.attributeSelection;

import weka.attributeSelection.BestFirst;
import weka.attributeSelection.CfsSubsetEval;
import weka.core.Instances;
import weka.filters.Filter;
import weka.filters.supervised.attribute.AttributeSelection;

/**
 *
 * @author Bosista
 */
public class CsfSelection implements AttributeSelectionTool {

    @Override
    public Instances select(Instances data) throws Exception {
        AttributeSelection filter = new AttributeSelection();
        
        CfsSubsetEval evaluator = new CfsSubsetEval();
        filter.setEvaluator(evaluator);
        
        BestFirst search = new BestFirst();
        filter.setSearch(search);

        filter.setInputFormat(data);
        
        return Filter.useFilter(data, filter);
    }
    
}
