package br.inf.ufrgs.tecmides.entities;

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
public class Rule extends AuditModel implements Matchable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private String antecedents;
        
    @Transient
    private List<RuleOperand> opAntecedents;
    
    private String consequents;
    
    @Transient
    private List<RuleOperand> opConsequents;
    
    private Double confidence;
    
    private Double lift;
    
    private Double conviction;

    protected Rule() {
    }

    public Rule( String strRule ) throws Exception {
        strRule = strRule.trim();

        this.opAntecedents = new ArrayList<>();
        this.opConsequents = new ArrayList<>();
        this.parseOperands(strRule);
        this.parseProperties(strRule);
        
        this.antecedents = this.opAntecedents.stream().map(o -> o.toString()).collect(Collectors.joining(";"));
        this.consequents = this.opConsequents.stream().map(o -> o.toString()).collect(Collectors.joining(";"));
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
                
                if( isAntecedent ){
                    this.opAntecedents.add(operand);
                } else {
                    this.opConsequents.add(operand);
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
        this.opAntecedents = new ArrayList<>();
        
        for(String strOperand : this.antecedents.split(";")) {
            try {
                this.opAntecedents.add(new RuleOperand(strOperand));
            } catch (Exception ex) {
                Logger.getLogger(Rule.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        this.opConsequents = new ArrayList<>();
        
        for(String strOperand : this.consequents.split(";")) {
            try {
                this.opConsequents.add(new RuleOperand(strOperand));
            } catch (Exception ex) {
                Logger.getLogger(Rule.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    @Override
    @JsonIgnore
    public Map<String, String> getMatchableProperties() {
        Map<String, String> map = new HashMap<>();

        for( RuleOperand operand : this.opAntecedents ) {
            map.put(operand.getName(), operand.getValue());
        }
        
        for( RuleOperand operand : this.opConsequents ) {
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

    public Double getLift() {
        return lift;
    }

    public Double getConviction() {
        return conviction;
    }

    public List<RuleOperand> getConsequent() {
        return this.opConsequents;
    }

    public List<RuleOperand> getAntescendt() {
        return this.opAntecedents;
    }

    @Override
    public String toString() {
        return String.format(
                "id=%d %s ==> %s conf: %f lift: %f conv: %f",
                id,
                antecedents,
                consequents,
                confidence,
                lift,
                conviction
        );
    }

}
