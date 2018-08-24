package br.inf.ufrgs.tecmides.tool.filter;

import br.inf.ufrgs.tecmides.entities.RuleOperand;
import br.inf.ufrgs.tecmides.entities.Rule;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class RuleFilterImpl implements RuleFilter {

    @Override
    public List<Rule> filterByMinLift(List<Rule> rules, double minLift) {
        return rules.stream().filter(rule -> rule.getLift() >= minLift).collect(Collectors.toList());
    }

    @Override
    public List<Rule> filterByMinConviction(List<Rule> rules, double minConviction) {
        return rules.stream().filter(rule -> rule.getConviction() >= minConviction).collect(Collectors.toList());
    }

    @Override
    public List<Rule> filterByConsequent(List<Rule> rules, RuleOperand operand) {
        List<Rule> filteredRules = new ArrayList<>();

        for (Rule rule : rules) {
            List<RuleOperand> matchingConsequents = rule.getConsequent().stream().filter(consequent -> consequent.getName().equals(operand.getName()) && consequent.getValue().equals(operand.getValue())).collect(Collectors.toList());

            if (!matchingConsequents.isEmpty()) {
                filteredRules.add(rule);
            }
        }

        return filteredRules;
    }

}
