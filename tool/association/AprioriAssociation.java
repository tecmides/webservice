package tecmides.tool.association;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import tecmides.domain.Rule;
import weka.core.Instances;

public class AprioriAssociation implements AssociationTool {

    private final Instances data;
    private List<Rule> rules;

    public AprioriAssociation(Instances data) {
        this.data = data;
        this.rules = new ArrayList<>();
    }

    /**
     * @return the rules
     */
    public List<Rule> getRules() {
        return this.rules;
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
    public List<Rule> associate(int numRules) throws Exception {
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

        List<String> strRules = this.findRules(associator.toString());

        this.rules = this.parseRules(strRules);

        return this.rules;
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

    private List<Rule> parseRules(List<String> strRules) {
        Iterator<String> itrStrRules = strRules.iterator();

        while (itrStrRules.hasNext()) {
            String strRule = itrStrRules.next();

            this.rules.add(new Rule(strRule));
        }

        return this.rules;
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

}
