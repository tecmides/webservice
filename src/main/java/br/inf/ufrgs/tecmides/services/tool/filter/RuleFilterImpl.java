package br.inf.ufrgs.tecmides.services.tool.filter;

import br.inf.ufrgs.tecmides.entities.rule.RuleOperand;
import br.inf.ufrgs.tecmides.entities.rule.Rule;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RuleFilterImpl implements RuleFilter {

    @Override
    public List<Rule> filterByMinLift( List<Rule> rules, double minLift ) {
        return rules.stream().filter(rule -> rule.getLift() >= minLift).distinct().collect(Collectors.toList());
    }

    @Override
    public List<Rule> filterByMinConviction( List<Rule> rules, double minConviction ) {
        return rules.stream().filter(rule -> rule.getConviction() >= minConviction).distinct().collect(Collectors.toList());
    }

    @Override
    public List<Rule> filterByConsequent( List<Rule> rules, List<RuleOperand> operands ) {
        List<Rule> filteredRules = new ArrayList<>();

        for( RuleOperand operand : operands ) {
            for( Rule rule : rules ) {
                List<RuleOperand> matchingConsequents = rule.getConsequent().stream().filter(consequent -> consequent.getName().equals(operand.getName()) && consequent.getValue().equals(operand.getValue())).collect(Collectors.toList());

                if(  ! matchingConsequents.isEmpty() ) {
                    filteredRules.add(rule);
                }
            }
        }

        return filteredRules.stream().distinct().collect(Collectors.toList());
    }

    @Override
    public List<Rule> filterByAntecedent( List<Rule> rules, List<RuleOperand> operands ) {
        List<Rule> filteredRules = new ArrayList<>();

        for( RuleOperand operand : operands ) {
            for( Rule rule : rules ) {
                List<RuleOperand> matchingConsequents = rule.getAntecedent().stream().filter(antecedent -> antecedent.getName().equals(operand.getName()) && antecedent.getValue().equals(operand.getValue())).collect(Collectors.toList());

                if(  ! matchingConsequents.isEmpty() ) {
                    filteredRules.add(rule);
                }
            }
        }

        return filteredRules.stream().distinct().collect(Collectors.toList());
    }

    @Override
    public List<Rule> filterByOperands( List<Rule> rules, List<RuleOperand> operands ) {
        List<Rule> filteredRules = new ArrayList<>();

        filteredRules.addAll(this.filterByAntecedent(rules, operands));
        filteredRules.addAll(this.filterByConsequent(rules, operands));

        return filteredRules.stream().distinct().collect(Collectors.toList());
    }

}
