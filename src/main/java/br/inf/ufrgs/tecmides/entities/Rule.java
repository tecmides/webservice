package br.inf.ufrgs.tecmides.entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Rule extends AuditModel implements Matchable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<RuleOperand> ruleOperands = new ArrayList<>();

    private Double confidence;
    private Double lift;
    private Double conviction;

    protected Rule() {
    }

    public Rule( String strRule ) throws Exception {
        strRule = strRule.trim();

        this.ruleOperands = new ArrayList<>();
        this.parseOperands(strRule);
        this.parseProperties(strRule);
    }

    private void parseOperands( String strRule ) throws Exception {
        Matcher descriptionMatcher = Pattern.compile("(([a-z_]+=[a-z-]+)|([a-z_]+=[A-F])) |(==> )").matcher(strRule);
        boolean isAntecedent = true;

        while( descriptionMatcher.find() ) {
            String match = descriptionMatcher.group().trim();

            if( match.equals("==>") ) {
                isAntecedent = false;
            } else {
                String[] terms = match.split("=");

                if( terms.length > 2 ) {
                    throw new Exception("Operand is not in the right format");
                }

                String name = terms[0];
                String value = terms[1];

                RuleOperand operand = new RuleOperand(name, value);
                operand.setType(isAntecedent ? RuleOperandType.ANTECEDENT : RuleOperandType.CONSEQUENT);
                operand.setRule(this);

                this.ruleOperands.add(operand);
            }
        }
    }

    private void parseProperties( String strRule ) {
        Matcher confidenceMatcher = Pattern.compile("(conf:\\()([0-9]+\\.*[0-9]*)").matcher(strRule);
        Matcher liftMatcher = Pattern.compile("(lift:\\()([0-9]+\\.*[0-9]*)").matcher(strRule);
        Matcher convictionMatcher = Pattern.compile("(conv:\\()([0-9]+\\.*[0-9]*)").matcher(strRule);

        confidenceMatcher.find();
        this.confidence = Double.parseDouble(confidenceMatcher.group(2));

        liftMatcher.find();
        this.lift = Double.parseDouble(liftMatcher.group(2));

        convictionMatcher.find();
        this.conviction = Double.parseDouble(convictionMatcher.group(2));
    }

    @Override
    public Map<String, String> getMatchableProperties() {
        Map<String, String> map = new HashMap<>();

        for( RuleOperand operand : this.ruleOperands ) {
            map.put(operand.getName(), operand.getValue());
        }

        return map;
    }

    public Long getId() {
        return id;
    }

    public void setId( Long id ) {
        this.id = id;
    }

    public Double getConfidence() {
        return confidence;
    }

    public void setConfidence( Double confidence ) {
        this.confidence = confidence;
    }

    public Double getLift() {
        return lift;
    }

    public void setLift( Double lift ) {
        this.lift = lift;
    }

    public Double getConviction() {
        return conviction;
    }

    public void setConviction( Double conviction ) {
        this.conviction = conviction;
    }

    public List<RuleOperand> getConsequent() {
        return this.ruleOperands.stream().filter(op -> op.getType() == RuleOperandType.ANTECEDENT).collect(Collectors.toList());
    }

    public List<RuleOperand> getAntescendt() {
        return this.ruleOperands.stream().filter(op -> op.getType() == RuleOperandType.CONSEQUENT).collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return String.format(
                "Rule["
                + "id=%d, "
                + "confidence=%f, "
                + "lift=%f, "
                + "conviction=%f, "
                + "operands=%s"
                + "]",
                id,
                confidence,
                lift,
                conviction,
                ruleOperands.toString()
        );
    }

}
