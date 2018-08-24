package br.inf.ufrgs.tecmides.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
public class RuleOperand extends AuditModel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @ManyToOne(optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Rule rule;
    
    private String name;
    private String value;
    private RuleOperandType type;
    
    protected RuleOperand() {}

    public RuleOperand(Rule rule, String name, String value, RuleOperandType type) {
        this.rule = rule;
        this.name = name;
        this.value = value;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Rule getRule() {
        return rule;
    }

    public void setRule(Rule rule) {
        this.rule = rule;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public RuleOperandType getType() {
        return type;
    }

    public void setType(RuleOperandType type) {
        this.type = type;
    }
    
    @Override
    public String toString() {
        return String.format(
            "Operand["
                    + "id=%d, "
                    + "name=%s, "
                    + "value=%s, "
                    + "type=%s"
            + "]",
            id,
            name,
            value,
            type.name()
        );
    }
}
