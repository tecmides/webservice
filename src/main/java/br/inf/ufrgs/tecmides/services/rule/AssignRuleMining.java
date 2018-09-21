package br.inf.ufrgs.tecmides.services.rule;

import br.inf.ufrgs.tecmides.entities.Rule;
import br.inf.ufrgs.tecmides.entities.RuleModelInstance;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import weka.core.Attribute;

public class AssignRuleMining implements ModuleRuleMining {

    @Override
    public List<Rule> getRules( List<RuleModelInstance> instances, RuleService ruleService ) throws Exception {
        List<Rule> rules = new ArrayList<>();

        rules.addAll(ruleService.generateRules(new AssignRuleMiningDataset1("assign_rel1", instances), 10, 0.2, 0.7));
        rules.addAll(ruleService.generateRules(new AssignRuleMiningDataset2("assign_rel2", instances), 10, 0.2, 0.7));
        rules.addAll(ruleService.generateRules(new AssignRuleMiningDataset3("assign_rel3", instances), 10, 0.2, 0.7));

        return rules;
    }

    class AssignRuleMiningDataset1 extends RuleModelDataset {

        public AssignRuleMiningDataset1( String name, List<RuleModelInstance> instances ) throws NoSuchFieldException {
            super(name, instances);
        }

        @Override
        public List<Attribute> getAttributeDefinition() {
            List<String> selectedAttributes = new ArrayList<>();
            selectedAttributes.add("st_indiv_assign_ltsubmit");
            selectedAttributes.add("rc_indiv_assign_ltsubmit");
            selectedAttributes.add("rc_indiv_subject_diff");
            selectedAttributes.add("grade");
            selectedAttributes.add("q_assign_submit");

            return super.getAttributeDefinition().stream().filter(attr -> selectedAttributes.contains(attr.name())).collect(Collectors.toList());
        }

    }

    class AssignRuleMiningDataset2 extends RuleModelDataset {

        public AssignRuleMiningDataset2( String name, List<RuleModelInstance> instances ) throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException, UnsupportedOperationException {
            super(name, instances);
        }

        @Override
        public List<Attribute> getAttributeDefinition() {
            List<String> selectedAttributes = new ArrayList<>();
            selectedAttributes.add("st_group_assign_ltsubmit");
            selectedAttributes.add("rc_group_assign_ltsubmit");
            selectedAttributes.add("rc_indiv_subject_diff");
            selectedAttributes.add("grade");
            selectedAttributes.add("q_assign_submit");

            return super.getAttributeDefinition().stream().filter(attr -> selectedAttributes.contains(attr.name())).collect(Collectors.toList());
        }

    }

    class AssignRuleMiningDataset3 extends RuleModelDataset {

        public AssignRuleMiningDataset3( String name, List<RuleModelInstance> instances ) throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException, UnsupportedOperationException {
            super(name, instances);
        }

        @Override
        public List<Attribute> getAttributeDefinition() {
            List<String> selectedAttributes = new ArrayList<>();
            selectedAttributes.add("st_indiv_subject_diff");
            selectedAttributes.add("rc_indiv_subject_diff");
            selectedAttributes.add("grade");
            selectedAttributes.add("q_assign_submit");

            return super.getAttributeDefinition().stream().filter(attr -> selectedAttributes.contains(attr.name())).collect(Collectors.toList());
        }

    }

}
