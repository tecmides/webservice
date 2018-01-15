package tecmides.tool.association;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import tecmides.domain.MiningAttribute;
import tecmides.domain.Operand;
import tecmides.domain.Rule;
import weka.core.Instances;

public class AprioriAssociation implements AssociationTool {

    /**
     * Generate the association rules
     *
     * @param instances
     * @param numRules the max number of rules to be generated
     *
     * @return String Output of the algorithm, consisting of rules
     *
     * @throws java.lang.Exception
     */
    @Override
    public List<Rule> run(Instances instances, int numRules) throws Exception {
        weka.associations.Apriori associator = new weka.associations.Apriori();

        String[] options = new String[4];
        options[0] = "-M";
        options[1] = "0.25";
        options[2] = "-C";
        options[3] = "0.7";

        associator.setOptions(options);
        associator.setNumRules(10);
        associator.setClassIndex(instances.classIndex());
        associator.buildAssociations(instances);

        List<String> strRules = this.findRules(associator.toString());

        List<Rule> rules = this.parseRules(strRules);

        return rules;
    }

    private List<String> findRules(String rulesText) {
        List<String> strRules = new ArrayList<>();

        Pattern pattern = Pattern.compile("[a-z_]+=(([0-9])|([A-F])) .+(\\n|$)");

        Matcher matcher = pattern.matcher(rulesText);

        while (matcher.find()) {
            strRules.add(matcher.group(0));
        }

        return strRules;
    }

    private List<Rule> parseRules(List<String> strRules) throws Exception {
        List<Rule> rules = new ArrayList<>();
        Iterator<String> itrStrRules = strRules.iterator();

        while (itrStrRules.hasNext()) {
            String strRule = itrStrRules.next();
            
            rules.add(new Rule(strRule));
        }

        return rules;
    }

    public static List<Rule> filterByMinLift(List<Rule> rules, double minLift) {
        List<Rule> filteredRules = new ArrayList<>();

        Iterator<Rule> itrRules = rules.iterator();

        while (itrRules.hasNext()) {
            Rule rule = itrRules.next();

            if (rule.getLift() >= minLift) {
                filteredRules.add(rule);
            }
        }

        return filteredRules;
    }

    public static List<Rule> filterByMinConviction(List<Rule> rules, double minConviction) {
        List<Rule> filteredRules = new ArrayList<>();

        Iterator<Rule> itrRules = rules.iterator();

        while (itrRules.hasNext()) {
            Rule rule = itrRules.next();

            if (rule.getConviction() >= minConviction) {
                filteredRules.add(rule);
            }
        }

        return filteredRules;
        
    }

    public static List<Rule> filterByAttributeValue(List<Rule> rules, MiningAttribute attribute, String value) {
        List<Rule> filteredRules = new ArrayList<>();

        Iterator<Rule> itrRules = rules.iterator();

        while (itrRules.hasNext()) {
            Rule rule = itrRules.next();
            boolean found = false;

            Iterator<Operand> itrAntecedent = rule.getAntecedent().iterator();
            Iterator<Operand> itrConsequent = rule.getConsequent().iterator();

            while (itrAntecedent.hasNext()) {
                Operand operand = itrAntecedent.next();

                if (operand.getName().equalsIgnoreCase(attribute.name()) && operand.getValue().equalsIgnoreCase(value)) {
                    filteredRules.add(rule);
                    found = true;
                }
            }

            if (!found) {
                while (itrConsequent.hasNext()) {
                    Operand operand = itrConsequent.next();

                    if (operand.getName().equalsIgnoreCase(attribute.name()) && operand.getValue().equalsIgnoreCase(value)) {
                        filteredRules.add(rule);
                    }
                }
            }
        }

        return filteredRules;
    }

}
