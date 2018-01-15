package tecmides.tool.filter;

import java.util.List;
import tecmides.domain.Operand;
import tecmides.domain.Rule;

public interface RuleFilter {
    
    public List<Rule> filterByMinLift(List<Rule> rules, double minLift);

    public List<Rule> filterByMinConviction(List<Rule> rules, double minConviction);
    
    public List<Rule> filterByConsequent(List<Rule> rules, Operand operand);
}
