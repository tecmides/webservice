package br.inf.ufrgs.tecmides.services.rule;

import br.inf.ufrgs.tecmides.entities.rule.Rule;
import br.inf.ufrgs.tecmides.entities.rule.RuleModelInstance;
import br.inf.ufrgs.tecmides.services.ModelDataset;
import java.util.ArrayList;
import java.util.List;

public class AssignRuleMining extends ModuleRuleMining {

    @Override
    public List<Rule> getRules( List<RuleModelInstance> instances, RuleService ruleService ) throws Exception {
        List<Rule> rules = new ArrayList<>();

        rules.addAll(ruleService.generateRules(this.getAssignDataset1(instances), 10, 0.2, 0.7));
        rules.addAll(ruleService.generateRules(this.getAssignDataset2(instances), 10, 0.2, 0.7));
        rules.addAll(ruleService.generateRules(this.getAssignDataset3(instances), 10, 0.2, 0.7));

        return rules;
    }

    public ModelDataset getAssignDataset1( List<RuleModelInstance> instances ) {
        String[] wantedAttributes = {
            "st_indiv_assign_ltsubmit",
            "rc_indiv_assign_ltsubmit",
            "rc_indiv_subject_diff",
            "grade",
            "q_assign_submit"
        };

        return this.createCustomDataset("assign_rel1", wantedAttributes, instances);
    }

    public ModelDataset getAssignDataset2( List<RuleModelInstance> instances ) {
        String[] wantedAttributes = {
            "st_group_assign_ltsubmit",
            "rc_group_assign_ltsubmit",
            "rc_indiv_subject_diff",
            "grade",
            "q_assign_submit"
        };

        return this.createCustomDataset("assign_rel2", wantedAttributes, instances);
    }

    public ModelDataset getAssignDataset3( List<RuleModelInstance> instances ) {
        String[] wantedAttributes = {
            "st_indiv_subject_diff",
            "rc_indiv_subject_diff",
            "grade",
            "q_assign_submit"
        };

        return this.createCustomDataset("assign_rel3", wantedAttributes, instances);
    }

}
