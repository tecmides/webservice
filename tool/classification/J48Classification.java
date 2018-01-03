/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tecmides.tool.classification;

import java.util.Random;
import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.trees.J48;
import weka.core.Instance;
import weka.core.Instances;

/**
 *
 * @author Bosista
 */
public class J48Classification implements ClassificationTool {

    private Instances data;
    // private Instances trainData;
    // private Instances usefulData;
    private Classifier classifier;
    
    public J48Classification(Instances data) {
        this.data = data;
        
        // this.trainData = new Instances(data);
        
        // this.usefulData = new Instances(data);
        // this.usefulData.clear();
        
        this.classifier = new J48();
    }
    
    @Override
    public void train() throws Exception {
        /*int numInstances = this.data.numInstances();

        for(int i = numInstances - 1; i >= (Math.round(numInstances * 0.75)); i-- ) {
            Instance instance = this.trainData.get(i);
            this.usefulData.add(instance);
            this.trainData.remove(i);
        }
        
        this.classifier.buildClassifier(this.trainData);
        
        System.out.println(this.usefulData.numInstances());
        System.out.println(this.trainData.numInstances());*/
        
        this.classifier.buildClassifier(this.data);
    }

    @Override
    public String tree() throws Exception {
        return this.classifier.toString();
    }
    
}
