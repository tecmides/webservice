package br.inf.ufrgs.tecmides.services.rule;

import br.inf.ufrgs.tecmides.entities.Rule;
import br.inf.ufrgs.tecmides.entities.RuleModelInstance;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import weka.core.Attribute;

public class ForumRuleMining implements ModuleRuleMining {

    @Override
    public List<Rule> getRules( List<RuleModelInstance> instances, RuleService ruleService ) throws Exception {
        List<Rule> rules = new ArrayList<>();

        rules.addAll(ruleService.generateRules(new ForumRuleMiningDataset1("forum_rel1", instances), 20, 0.2, 0.7, 1.1, 1.1));

        return rules;
    }

    class ForumRuleMiningDataset1 extends RuleModelDataset {

        public ForumRuleMiningDataset1( String name, List<RuleModelInstance> instances ) throws NoSuchFieldException {
            super(name, instances);
        }

        @Override
        public List<Attribute> getAttributeDefinition() {
            List<String> selectedAttributes = new ArrayList<>();
            selectedAttributes.add("grade");
            selectedAttributes.add("q_forum_create");
            selectedAttributes.add("q_forum_group_access");
            selectedAttributes.add("q_forum_discussion_access");
            selectedAttributes.add("st_group_assign_ltsubmit");
            selectedAttributes.add("rc_group_assign_ltsubmit");
            selectedAttributes.add("rc_indiv_subject_keepup");
            selectedAttributes.add("rc_indiv_subject_diff");

            return super.getAttributeDefinition().stream().filter(attr -> selectedAttributes.contains(attr.name())).collect(Collectors.toList());
        }

    }

}
