package br.inf.ufrgs.tecmides.services.tree;

import br.inf.ufrgs.tecmides.entities.tree.TreeModelInstance;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public interface TreeModelService {

    public List<TreeModelInstance> classify( List<TreeModelInstance> instances ) throws NoSuchFieldException, Exception;

    public void saveInstances( List<TreeModelInstance> dataset );

    public void updateModel() throws Exception;

}
