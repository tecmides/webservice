/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tecmides.tool.classification;

import weka.classifiers.Classifier;
import weka.classifiers.trees.J48;
import weka.core.Instances;

public class J48Classification implements ClassificationTool {

    @Override
    public String run(Instances instances) throws Exception {
        Classifier classifier = new J48();
        classifier.buildClassifier(instances);
        
        return classifier.toString();
    }
    
}
