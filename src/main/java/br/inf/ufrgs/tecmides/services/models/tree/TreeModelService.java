package br.inf.ufrgs.tecmides.services.models.tree;

import br.inf.ufrgs.tecmides.entities.tree.TreeModelInstance;
import br.inf.ufrgs.tecmides.repositories.TreeModelInstanceRepository;
import br.inf.ufrgs.tecmides.services.models.ClassificationModel;
import br.inf.ufrgs.tecmides.services.models.ClassificationModelFactory;
import br.inf.ufrgs.tecmides.services.tool.classification.J48Classification;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.inf.ufrgs.tecmides.services.models.ModelDataset;
import br.inf.ufrgs.tecmides.services.models.ModelDatasetFactory;
import br.inf.ufrgs.tecmides.services.models.ModelInstanceService;
import br.inf.ufrgs.tecmides.services.models.ModelService;

@Service
public class TreeModelService implements ModelService<TreeModelInstance> {

    @Autowired
    TreeModelInstanceRepository instanceRepository;
    
    @Autowired
    ModelInstanceService<TreeModelInstance> instanceService;
    
    private ModelDatasetFactory<TreeModelInstance> datasetFactory;
    private ClassificationModel model;

    @Override
    public void initialize() {
        this.datasetFactory = new ModelDatasetFactory<>();
        this.model = (new ClassificationModelFactory()).create("j48_tree", new J48Classification());
    }
    
    @Override
    public List<TreeModelInstance> classify( List<TreeModelInstance> instances ) throws Exception {
        ModelDataset dataset = datasetFactory.create("classifiable", this.instanceService.getModelInstanceAttributes(), this.instanceService.getModelInstanceClassificaitonAttribute(), instances);
        model.restore();
        model.classify(instanceService, dataset);
        
        return dataset.getModelInstances();
    }

    @Override
    public void updateTrainingData( List<TreeModelInstance> instances ) {
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
        ModelDataset dataset = datasetFactory.create("contribute", this.instanceService.getModelInstanceAttributes(), this.instanceService.getModelInstanceClassificaitonAttribute(), instances);
        model.train(dataset);
        model.save();
    }

}
