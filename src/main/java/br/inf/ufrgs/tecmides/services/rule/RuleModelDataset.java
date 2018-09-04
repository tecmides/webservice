package br.inf.ufrgs.tecmides.services.rule;

import br.inf.ufrgs.tecmides.entities.RuleModelInstance;
import br.inf.ufrgs.tecmides.services.BaseModelDataset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import weka.core.Attribute;

public class RuleModelDataset extends BaseModelDataset {

    public RuleModelDataset( String name, List<RuleModelInstance> instances ) throws NoSuchFieldException {
        super(name, new ArrayList<>(instances));
    }

    @Override
    public List<Attribute> getAttributeDefinition() {
        String[] grades = {"A", "B", "C", "D", "E", "F"};
        String[] quartiles = {"low", "medium", "medium-high", "high"};
        String[] recurrency = {"never", "rarely", "sometimes", "often", "always"};
        String[] discouraged = {"satisfied", "dissatisfied", "discouraged", "animated", "other", "none"};

        List<Attribute> attributes = new ArrayList<>();
        attributes.add(new Attribute("grade", new ArrayList<>(Arrays.asList(grades))));
        attributes.add(new Attribute("q_assign_view", new ArrayList<>(Arrays.asList(quartiles))));
        attributes.add(new Attribute("q_assign_submit", new ArrayList<>(Arrays.asList(quartiles))));
        attributes.add(new Attribute("q_forum_create", new ArrayList<>(Arrays.asList(quartiles))));
        attributes.add(new Attribute("q_forum_group_access", new ArrayList<>(Arrays.asList(quartiles))));
        attributes.add(new Attribute("q_forum_discussion_access", new ArrayList<>(Arrays.asList(quartiles))));
        attributes.add(new Attribute("q_resource_view", new ArrayList<>(Arrays.asList(quartiles))));
        attributes.add(new Attribute("rc_indiv_assign_ltsubmit", new ArrayList<>(Arrays.asList(recurrency))));
        attributes.add(new Attribute("rc_group_assign_ltsubmit", new ArrayList<>(Arrays.asList(recurrency))));
        attributes.add(new Attribute("rc_indiv_subject_keepup", new ArrayList<>(Arrays.asList(recurrency))));
        attributes.add(new Attribute("rc_indiv_subject_diff", new ArrayList<>(Arrays.asList(recurrency))));
        attributes.add(new Attribute("st_indiv_assign_ltsubmit", new ArrayList<>(Arrays.asList(discouraged))));
        attributes.add(new Attribute("st_group_assign_ltsubmit", new ArrayList<>(Arrays.asList(discouraged))));
        attributes.add(new Attribute("st_indiv_subject_diff", new ArrayList<>(Arrays.asList(discouraged))));

        return attributes;
    }

}
