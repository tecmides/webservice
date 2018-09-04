package br.inf.ufrgs.tecmides.services.rule;

import br.inf.ufrgs.tecmides.entities.Rule;
import br.inf.ufrgs.tecmides.entities.RuleOperand;
import br.inf.ufrgs.tecmides.services.tool.association.AprioriAssociation;
import br.inf.ufrgs.tecmides.services.tool.association.AssociationTool;
import br.inf.ufrgs.tecmides.services.tool.filter.RuleFilter;
import br.inf.ufrgs.tecmides.services.tool.filter.RuleFilterImpl;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import weka.core.Instances;

@Service
public class RuleServiceImpl implements RuleService {

    @Override
    public List<Rule> generateRules( RuleModelDataset dataset, int numRules, double minSupport, double minConfidence, double minLift, double minConviction ) throws Exception {
        List<Rule> rules = new ArrayList<>();

        Instances instances = dataset.getInstances();
        AssociationTool associator = new AprioriAssociation();

        rules.addAll(associator.associate(instances, numRules, minSupport, minConfidence));

        return rules;
    }

    @Override
    public List<Rule> filter( List<Rule> rules, double minLift, double minConviction ) {
        RuleFilter filter = new RuleFilterImpl();

        List<RuleOperand> operands = new ArrayList<>();
        operands.add(new RuleOperand("st_indiv_assign_ltsubmit", "discouraged"));
        operands.add(new RuleOperand("st_group_assign_ltsubmit", "discouraged"));
        operands.add(new RuleOperand("st_indiv_subject_diff", "discouraged"));

        return filter.filterByConsequent(filter.filterByMinLift(filter.filterByMinConviction(rules, minLift), minConviction), operands);
    }

}
