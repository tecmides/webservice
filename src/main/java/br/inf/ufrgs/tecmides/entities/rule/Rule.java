package br.inf.ufrgs.tecmides.entities.rule;

import br.inf.ufrgs.tecmides.entities.AuditModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PostLoad;
import javax.persistence.Transient;

@Entity
public class Rule extends AuditModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String antecedent;

    @Transient
    private List<RuleOperand> antecedentOperands;

    private String consequent;

    @Transient
    private List<RuleOperand> consequentOperands;

    private Double confidence;

    private Double lift;

    private Double conviction;

    protected Rule() {
    }

    public Rule( String strRule ) throws Exception {
        strRule = strRule.trim();

        this.antecedentOperands = new ArrayList<>();
        this.consequentOperands = new ArrayList<>();
        this.parseOperands(strRule);
        this.parseProperties(strRule);

        this.antecedent = this.antecedentOperands.stream().map(o -> o.toString()).collect(Collectors.joining(";"));
        this.consequent = this.consequentOperands.stream().map(o -> o.toString()).collect(Collectors.joining(";"));
    }

    private void parseOperands( String strRule ) throws Exception {
        Matcher descriptionMatcher = Pattern.compile("(([a-z_]+=[a-z-]+)|([a-z_]+=[A-F])) |(==> )").matcher(strRule);
        boolean isAntecedent = true;

        while( descriptionMatcher.find() ) {
            String match = descriptionMatcher.group().trim();

            if( match.equals("==>") ) {
                isAntecedent = false;
            } else {
                RuleOperand operand = new RuleOperand(match);

                if( isAntecedent ) {
                    this.antecedentOperands.add(operand);
                } else {
                    this.consequentOperands.add(operand);
                }
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

    @PostLoad
    protected void createOperands() {
        this.antecedentOperands = new ArrayList<>();

        for( String strOperand : this.antecedent.split(";") ) {
            try {
                this.antecedentOperands.add(new RuleOperand(strOperand));
            } catch( Exception ex ) {
                Logger.getLogger(Rule.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        this.consequentOperands = new ArrayList<>();

        for( String strOperand : this.consequent.split(";") ) {
            try {
                this.consequentOperands.add(new RuleOperand(strOperand));
            } catch( Exception ex ) {
                Logger.getLogger(Rule.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
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

    public Double getLift() {
        return lift;
    }

    public Double getConviction() {
        return conviction;
    }

    public List<RuleOperand> getConsequent() {
        return this.consequentOperands;
    }

    public List<RuleOperand> getAntecedent() {
        return this.antecedentOperands;
    }

    @Override
    public String toString() {
        return String.format("id=%d %s ==> %s conf: %f lift: %f conv: %f",
                id,
                antecedent,
                consequent,
                confidence,
                lift,
                conviction
        );
    }

}
