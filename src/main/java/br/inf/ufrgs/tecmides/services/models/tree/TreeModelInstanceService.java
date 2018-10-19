package br.inf.ufrgs.tecmides.services.models.tree;

import br.inf.ufrgs.tecmides.entities.tree.TreeModelInstance;
import br.inf.ufrgs.tecmides.services.models.ModelInstanceService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.stereotype.Service;
import weka.core.Attribute;

@Service
public class TreeModelInstanceService implements ModelInstanceService<TreeModelInstance>{
    
    @Override
    public List<Attribute> getModelInstanceAttributes() {
        String[] quartiles = {"low", "medium", "medium-high", "high"};

        List<Attribute> attributes = new ArrayList<>();
        attributes.add(new Attribute("q_assign_view", new ArrayList<>(Arrays.asList(quartiles))));
        attributes.add(new Attribute("q_assign_submit", new ArrayList<>(Arrays.asList(quartiles))));
        attributes.add(new Attribute("q_forum_create", new ArrayList<>(Arrays.asList(quartiles))));
        attributes.add(new Attribute("q_forum_group_access", new ArrayList<>(Arrays.asList(quartiles))));
        attributes.add(new Attribute("q_forum_discussion_access", new ArrayList<>(Arrays.asList(quartiles))));
        attributes.add(new Attribute("q_resource_view", new ArrayList<>(Arrays.asList(quartiles))));

        return attributes;
    }

    @Override
    public Attribute getModelInstanceClassificaitonAttribute() {
        String[] approvedValues = {"no", "yes"};

        return new Attribute("approved", new ArrayList<>(Arrays.asList(approvedValues)));
    }

    @Override
    public void setModelInstanceClassification( TreeModelInstance instance, double classification ) {
        instance.setApproved(this.getModelInstanceClassificaitonAttribute().value((int) classification));
    }
    
}
