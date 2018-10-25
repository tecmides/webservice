package br.inf.ufrgs.tecmides.services.models.rule.modulemining;

import br.inf.ufrgs.tecmides.entities.rule.Rule;
import br.inf.ufrgs.tecmides.entities.rule.RuleModelInstance;
import br.inf.ufrgs.tecmides.services.models.ModelDataset;
import br.inf.ufrgs.tecmides.services.models.ModelInstanceService;
import br.inf.ufrgs.tecmides.services.models.rule.RuleService;
import java.util.ArrayList;
import java.util.List;

public class AssignRuleMining extends ModuleRuleMining {

    @Override
    public List<Rule> getRules( ModelInstanceService instanceService, List<RuleModelInstance> instances, RuleService ruleService ) throws Exception {
        List<Rule> rules = new ArrayList<>();

        rules.addAll(ruleService.generateRules(this.getAssignDataset1(instanceService, instances), 10, 0.2, 0.7));
        rules.addAll(ruleService.generateRules(this.getAssignDataset2(instanceService, instances), 10, 0.2, 0.7));
        rules.addAll(ruleService.generateRules(this.getAssignDataset3(instanceService, instances), 10, 0.2, 0.7));

        return rules;
    }

    public ModelDataset getAssignDataset1( ModelInstanceService instanceService, List<RuleModelInstance> instances ) {
        String[] wantedAttributes = {
            "st_indiv_assign_ltsubmit",
            "rc_indiv_assign_ltsubmit",
            "rc_indiv_subject_diff",
            "grade",
            "q_assign_submit"
        };

        return this.createCustomDataset("assign_rel1", wantedAttributes, instanceService, instances);
    }

    public ModelDataset getAssignDataset2( ModelInstanceService instanceService, List<RuleModelInstance> instances ) {
        String[] wantedAttributes = {
            "st_group_assign_ltsubmit",
            "rc_group_assign_ltsubmit",
            "rc_indiv_subject_diff",
            "grade",
            "q_assign_submit"
        };

        return this.createCustomDataset("assign_rel2", wantedAttributes, instanceService, instances);
    }

    public ModelDataset getAssignDataset3( ModelInstanceService instanceService, List<RuleModelInstance> instances ) {
        String[] wantedAttributes = {
            "st_indiv_subject_diff",
            "rc_indiv_subject_diff",
            "grade",
            "q_assign_submit"
        };

        return this.createCustomDataset("assign_rel3", wantedAttributes, instanceService, instances);
    }

}
