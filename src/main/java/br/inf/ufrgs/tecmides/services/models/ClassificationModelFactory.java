package br.inf.ufrgs.tecmides.services.models;

import br.inf.ufrgs.tecmides.services.tool.classification.ClassificationTool;
import java.io.File;
import java.io.IOException;
import weka.core.Instances;

public class ClassificationModelFactory {

    public ClassificationModel create( String name, ClassificationTool tool ) {
        return new BaseClassificationModel(name, tool);
    }

    class BaseClassificationModel implements ClassificationModel {

        private final String name;
        private final ClassificationTool tool;

        public BaseClassificationModel(String name, ClassificationTool tool)
        {
            this.name = name;
            this.tool = tool;
        }
        
        @Override
        public void train( ModelDataset dataset ) throws Exception {
            tool.train(dataset);
        }

        @Override
        public void save() throws IOException, Exception {
            ClassLoader classLoader = getClass().getClassLoader();
            String basePath = classLoader.getResource(".").getPath() + "/models";
            new File(basePath).mkdirs();
            File file = new File(basePath + "/" + this.name + ".model");
            file.createNewFile();

            this.tool.save(file.getAbsolutePath());
        }

        @Override
        public void restore() throws IOException, Exception {
            ClassLoader classLoader = getClass().getClassLoader();
            String basePath = classLoader.getResource(".").getPath() + "/models";
            new File(basePath).mkdirs();

            this.tool.restore(basePath + "/" + this.name + ".model");
        }

        @Override
        public ModelDataset classify( ModelInstanceService service, ModelDataset dataset ) throws Exception {
            Instances instances = dataset.getInstances();

            for( int idxInstance = 0; idxInstance < instances.size(); idxInstance ++ ) {
                double classification = this.tool.classify(instances.get(idxInstance));

                service.setModelInstanceClassification(dataset.getModelInstances().get(idxInstance), classification);
            }

            return dataset;
        }   
    }
}
