package tecmides.tool.association;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import weka.core.Instances;

public class AprioriAssociation implements AssociationTool {

    private Instances data;
    
    public AprioriAssociation(Instances data) {
        this.data = data;
    }
    
    
    /**
     * Generate the association rules
     *
     * @param numRules the max number of rules to be generated
     * 
     * @return String Output of the algorithm, consisting of rules
     * 
     * @throws java.lang.Exception
     */
    @Override
    public List<String> associate(int numRules) throws Exception {
        weka.associations.Apriori associator = new weka.associations.Apriori();
        
        String[] options = new String[4];
        options[0] = "-M";
        options[1] = "0.25";
        options[2] = "-C";
        options[3] = "0.7";
        
        associator.setOptions(options);
        associator.setNumRules(10);
        associator.setClassIndex(this.data.classIndex());
        associator.buildAssociations(this.data);
        
        return this.filter(associator.toString());
    }
    
    private List<String> filter(String rulesText) {
        List<String> rules = new ArrayList<>();
        
        Pattern pattern = Pattern.compile("[a-z_]+=(([0-9])|([A-F])) .+(\\n|$)");
            
        Matcher matcher = pattern.matcher(rulesText);

        while (matcher.find()) {
            rules.add(matcher.group(0));
        }
        
        return rules;
    }
    
}
