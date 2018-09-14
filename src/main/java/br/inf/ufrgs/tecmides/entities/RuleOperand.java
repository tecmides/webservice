package br.inf.ufrgs.tecmides.entities;

public class RuleOperand {

    private String name;
    private String value;

    public RuleOperand( String strOperand ) throws Exception {
        String[] terms = strOperand.split("=");

        if( terms.length > 2 ) {
            throw new Exception("Operand is not in the right format");
        }

        this.name = terms[0];
        this.value = terms[1];
    }
    
    public RuleOperand( String name, String value ) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName( String name ) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue( String value ) {
        this.value = value;
    }

    @Override
    public String toString() {
        return String.format(
                "%s=%s",
                name,
                value
        );
    }

}
