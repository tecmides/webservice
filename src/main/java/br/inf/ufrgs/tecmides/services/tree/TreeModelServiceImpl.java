package br.inf.ufrgs.tecmides.services.tree;

import br.inf.ufrgs.tecmides.entities.tree.TreeModelInstance;
import br.inf.ufrgs.tecmides.repositories.TreeModelInstanceRepository;
import br.inf.ufrgs.tecmides.services.tool.classification.ClassificationTool;
import br.inf.ufrgs.tecmides.services.tool.classification.J48Classification;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.inf.ufrgs.tecmides.services.ModelDataset;
import br.inf.ufrgs.tecmides.services.ModelDatasetFactory;

@Service
public class TreeModelServiceImpl implements TreeModelService {

    @Autowired
    TreeModelInstanceRepository instanceRepository;

    private final ModelDatasetFactory<TreeModelInstance> datasetFactory = new ModelDatasetFactory<>();

    @Override
    public List<TreeModelInstance> classify( List<TreeModelInstance> instances ) throws Exception {
        ModelDataset dataset = datasetFactory.create("classifiable", TreeModelInstance.getAttributes(), TreeModelInstance.getClassificaitonAttribute(), instances);

        ClassificationTool classifier = new J48Classification();
        classifier.restore();
        classifier.classify(dataset);

        return dataset.getModelInstances();
    }

    @Override
    public void saveInstances( List<TreeModelInstance> instances ) {
        for( TreeModelInstance instance : instances ) {
            TreeModelInstance oldInstance = instanceRepository.findByCourseidAndUserid(instance.getCourseid(), instance.getUserid());

            if( oldInstance != null ) {
                instance.setId(oldInstance.getId());
            }

            instanceRepository.save(instance);
        }

        this.instanceRepository.saveAll(instances);
    }

    @Override
    public void updateModel() throws Exception {
        List<TreeModelInstance> instances = this.instanceRepository.findAll();
        ModelDataset dataset = datasetFactory.create("contribute", TreeModelInstance.getAttributes(), TreeModelInstance.getClassificaitonAttribute(), instances);

        ClassificationTool classifier = new J48Classification();
        classifier.build(dataset);
        classifier.save();
    }

}
