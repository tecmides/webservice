package br.inf.ufrgs.tecmides.services.tool.filter;

import br.inf.ufrgs.tecmides.entities.rule.RuleOperand;
import br.inf.ufrgs.tecmides.entities.rule.Rule;
import java.util.List;

public interface RuleFilter {

    public List<Rule> filterByMinLift( List<Rule> rules, double minLift );

    public List<Rule> filterByMinConviction( List<Rule> rules, double minConviction );

    public List<Rule> filterByConsequent( List<Rule> rules, List<RuleOperand> operands );

    public List<Rule> filterByAntecedent( List<Rule> rules, List<RuleOperand> operands );

    public List<Rule> filterByOperands( List<Rule> rules, List<RuleOperand> operands );

}
