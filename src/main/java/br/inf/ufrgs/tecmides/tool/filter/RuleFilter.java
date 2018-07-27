package br.inf.ufrgs.tecmides.tool.filter;

import br.inf.ufrgs.tecmides.domain.Operand;
import br.inf.ufrgs.tecmides.domain.Rule;
import java.util.List;


public interface RuleFilter {

    public List<Rule> filterByMinLift(List<Rule> rules, double minLift);

    public List<Rule> filterByMinConviction(List<Rule> rules, double minConviction);

    public List<Rule> filterByConsequent(List<Rule> rules, Operand operand);
}
