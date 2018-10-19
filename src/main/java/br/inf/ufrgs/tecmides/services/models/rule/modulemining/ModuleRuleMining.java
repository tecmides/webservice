package br.inf.ufrgs.tecmides.services.models.rule.modulemining;

import br.inf.ufrgs.tecmides.entities.rule.Rule;
import br.inf.ufrgs.tecmides.entities.rule.RuleModelInstance;
import br.inf.ufrgs.tecmides.services.models.ModelDataset;
import br.inf.ufrgs.tecmides.services.models.ModelDatasetFactory;
import br.inf.ufrgs.tecmides.services.models.ModelInstanceService;
import br.inf.ufrgs.tecmides.services.models.rule.RuleService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import weka.core.Attribute;

public abstract class ModuleRuleMining {

    private final ModelDatasetFactory<RuleModelInstance> datasetFactory = new ModelDatasetFactory<>();

    public abstract List<Rule> getRules( ModelInstanceService instanceService, List<RuleModelInstance> instances, RuleService ruleService ) throws Exception;

    protected ModelDataset createCustomDataset( String name, String[] attributes, ModelInstanceService instanceService, List<RuleModelInstance> instances ) {
        List<String> wantedAttributes = new ArrayList<>(Arrays.asList(attributes));
        List<Attribute> baseAttributes = instanceService.getModelInstanceAttributes();
        List<Attribute> selectedAttributes = baseAttributes.stream().filter(attr -> wantedAttributes.contains(attr.name())).collect(Collectors.toList());

        return this.datasetFactory.create(name, selectedAttributes, instanceService.getModelInstanceClassificaitonAttribute(), instances);
    }

}
