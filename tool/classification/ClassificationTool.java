/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tecmides.tool.classification;

import weka.classifiers.Classifier;
import weka.core.Instances;

/**
 *
 * @author Bosista
 */
public interface ClassificationTool {
    
    public void train() throws Exception;
    public String tree() throws Exception;
    
}
