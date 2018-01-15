package tecmides.domain;

public class Operand {

    private final String name;
    private final String value;

    public Operand(String operand) throws Exception {
        String[] terms = operand.split("=");
        
        if( terms.length > 2 ) {
            throw new Exception("Operand is not in the right format");
        }
        
        this.name = terms[0];
        this.value = terms[1];
    }
    
    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the value
     */
    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return String.format("%s=%s", this.name, this.value);
    }
}