package tecmides.tool.classification;

import weka.classifiers.Classifier;
import weka.classifiers.trees.J48;
import weka.core.Instances;

public class J48Classification implements ClassificationTool {

    @Override
    public String classify(Instances instances) throws Exception {
        Classifier classifier = new J48();
        classifier.buildClassifier(instances);
        
        return classifier.toString();
    }
    
}
