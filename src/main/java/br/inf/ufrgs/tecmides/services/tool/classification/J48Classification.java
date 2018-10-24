package br.inf.ufrgs.tecmides.services.tool.classification;

import weka.classifiers.trees.J48;
import br.inf.ufrgs.tecmides.services.models.ModelDataset;
import java.io.File;
import weka.classifiers.Classifier;
import weka.core.Instance;
import weka.core.SerializationHelper;

public class J48Classification implements ClassificationTool {
    
    private Classifier classifier;
    
    @Override
    public void train( ModelDataset dataset ) throws Exception {
        J48 tree = new J48();
        String[] options = new String[1];
        options[0] = "-U";

        tree.setOptions(options);
        tree.buildClassifier(dataset.getInstances());

        this.setClassifier(tree);
    }

    @Override
    public double classify( Instance instance ) throws Exception {
        return this.getClassifier().classifyInstance(instance);
    }

    private void setClassifier(Classifier classifier) {
        this.classifier = classifier;
    }

    private Classifier getClassifier() {
        return this.classifier;
    }

    @Override
    public void save(String path) throws Exception {
        File file = new File(path);
        
        SerializationHelper.write(file.getPath(), this.getClassifier());
    }

    @Override
    public boolean restore(String path) throws Exception {
        File file = new File(path);
        
        if( file.exists() ) {
            this.setClassifier((Classifier) SerializationHelper.read(file.getAbsolutePath()));
            
            return true;
        }
        
        return false;
    }

}
