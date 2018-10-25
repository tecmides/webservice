package br.inf.ufrgs.tecmides.services.models.rule;

import br.inf.ufrgs.tecmides.entities.rule.Rule;
import br.inf.ufrgs.tecmides.entities.rule.RuleModelInstance;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import weka.core.Attribute;

@Service
public class RuleModelInstanceServiceImpl implements RuleModelInstanceService {

    @Autowired
    RuleService ruleService;
    
    @Autowired
    MatchService matchService;
    
    @Override
    public Boolean instanceMatchesRule( RuleModelInstance instance, Rule rule ) {
        Map<String, String> map = this.mappedFields(instance);
        Map<String, String> toMap = ruleService.mappedFields(rule);

        return matchService.matches(map, toMap);
    }
    
    @Override
    public void setModelInstanceClassification( RuleModelInstance instance, double classification ) {
        instance.setDiscouraged(this.getModelInstanceClassificaitonAttribute().value((int)classification));
    }
    
    @Override
    public void setModelInstanceCoeficient(RuleModelInstance instance, double coeficient) {
        instance.setDiscouraged_coeficient(coeficient);
    }
    
    @Override
    public Attribute getModelInstanceClassificaitonAttribute() {
        String[] discouraged = {"no", "yes"};

        return new Attribute("discouraged", new ArrayList<>(Arrays.asList(discouraged)));
    }
    
    @Override
    public List<Attribute> getModelInstanceAttributes() {
        String[] grades = {"A", "B", "C", "D", "F"};
        String[] quartiles = {"low", "medium", "medium-high", "high"};
        String[] recurrency = {"never", "rarely", "sometimes", "often", "always"};
        String[] state = {"satisfied", "dissatisfied", "discouraged", "animated", "other", "none"};

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
        attributes.add(new Attribute("st_indiv_assign_ltsubmit", new ArrayList<>(Arrays.asList(state))));
        attributes.add(new Attribute("st_group_assign_ltsubmit", new ArrayList<>(Arrays.asList(state))));
        attributes.add(new Attribute("st_indiv_subject_diff", new ArrayList<>(Arrays.asList(state))));

        return attributes;
    }
    
    @Override
    public Map<String, String> mappedFields(RuleModelInstance toMap) {
        Map<String, String> map = new HashMap<>();

        map.put("grade", toMap.getGrade());
        map.put("q_assign_view", toMap.getQ_assign_view());
        map.put("q_assign_submit", toMap.getQ_assign_submit());
        map.put("q_forum_create", toMap.getQ_forum_create());
        map.put("q_forum_group_access", toMap.getQ_forum_group_access());
        map.put("q_forum_discussion_access", toMap.getQ_forum_discussion_access());
        map.put("q_resource_view", toMap.getQ_resource_view());
        map.put("st_indiv_assign_ltsubmit", toMap.getSt_indiv_assign_ltsubmit());
        map.put("st_group_assign_ltsubmit", toMap.getSt_group_assign_ltsubmit());
        map.put("st_indiv_subject_diff", toMap.getSt_indiv_subject_diff());
        map.put("rc_indiv_assign_ltsubmit", toMap.getRc_indiv_assign_ltsubmit());
        map.put("rc_group_assign_ltsubmit", toMap.getRc_group_assign_ltsubmit());
        map.put("rc_indiv_subject_keepup", toMap.getRc_indiv_subject_keepup());
        map.put("rc_indiv_subject_diff", toMap.getRc_indiv_subject_diff());

        return map;
    }
}
