package br.inf.ufrgs.tecmides.services.tool.classification;

import br.inf.ufrgs.tecmides.services.ModelDataset;
import java.io.File;
import java.io.IOException;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.ResourceUtils;
import weka.classifiers.Classifier;
import weka.core.SerializationHelper;
import weka.core.Instance;
import weka.core.Instances;

public abstract class BaseClassificationModel implements ClassificationTool {

    private Classifier classifier;

    @Override
    public void save() throws IOException, Exception {
        ClassLoader classLoader = getClass().getClassLoader();
        String basePath = classLoader.getResource(".").getPath() + "/models";
        new File(basePath).mkdirs();
        File file = new File(basePath + "/" + this.getModelName() + ".model");
        file.createNewFile();

        SerializationHelper.write(file.getPath(), this.getClassifier());
    }

    @Override
    public void restore() throws IOException, Exception {
        ClassLoader classLoader = getClass().getClassLoader();
        String basePath = classLoader.getResource(".").getPath() + "/models";
        new File(basePath).mkdirs();
        File file = new File(basePath + "/" + this.getModelName() + ".model");

        if( file.exists() ) {
            this.setClassifier((Classifier) SerializationHelper.read(file.getAbsolutePath()));
        }
    }

    @Override
    public ModelDataset classify( ModelDataset dataset ) throws Exception {
        Instances instances = dataset.getInstances();

        for( int idxInstance = 0; idxInstance < instances.size(); idxInstance ++ ) {
            double classification = this.getClassifier().classifyInstance(instances.get(idxInstance));

            dataset.setModelInstanceClassification(idxInstance, classification);
        }

        return dataset;
    }

    public Classifier getClassifier() {
        return classifier;
    }

    public void setClassifier( Classifier classifier ) {
        this.classifier = classifier;
    }

}
