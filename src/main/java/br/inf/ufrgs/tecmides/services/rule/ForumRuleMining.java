package br.inf.ufrgs.tecmides.services.rule;

import br.inf.ufrgs.tecmides.entities.rule.Rule;
import br.inf.ufrgs.tecmides.entities.rule.RuleModelInstance;
import br.inf.ufrgs.tecmides.services.ModelDataset;
import java.util.ArrayList;
import java.util.List;

public class ForumRuleMining extends ModuleRuleMining {

    @Override
    public List<Rule> getRules( List<RuleModelInstance> instances, RuleService ruleService ) throws Exception {
        List<Rule> rules = new ArrayList<>();

        rules.addAll(ruleService.generateRules(getForumDataset1(instances), 20, 0.25, 0.7));

        return rules;
    }

    public ModelDataset getForumDataset1( List<RuleModelInstance> instances ) {
        String[] wantedAttributes = {
            "grade",
            "q_forum_create",
            "q_forum_group_access",
            "q_forum_discussion_access",
            "st_group_assign_ltsubmit",
            "rc_group_assign_ltsubmit",
            "rc_indiv_subject_keepup",
            "rc_indiv_subject_diff"
        };

        return this.createCustomDataset("forum_rel1", wantedAttributes, instances);
    }

}
