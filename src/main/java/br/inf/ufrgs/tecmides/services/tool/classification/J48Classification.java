package br.inf.ufrgs.tecmides.services.tool.classification;

import weka.classifiers.trees.J48;
import br.inf.ufrgs.tecmides.services.ModelDataset;

public class J48Classification extends BaseClassificationModel {

    @Override
    public String getModelName() {
        return "j48_tree";
    }

    @Override
    public void build( ModelDataset dataset ) throws Exception {
        J48 tree = new J48();
        String[] options = new String[1];
        options[0] = "-U";

        tree.setOptions(options);
        tree.buildClassifier(dataset.getInstances());

        this.setClassifier(tree);
    }

}
