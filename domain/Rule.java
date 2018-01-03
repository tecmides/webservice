package tecmides.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Rule {

    private final List<String> antecedent;
    private final List<String> consequent;
    private double confidence;
    private double lift;
    private double conviction;

    public Rule(String strRule) {
        strRule = strRule.trim();

        this.antecedent = new ArrayList<>();
        this.consequent = new ArrayList<>();

        this.parseOperands(strRule);
        this.parseProperties(strRule);
    }

    private void parseOperands(String strRule) {
        Matcher descriptionMatcher = Pattern.compile("(([a-z_]+=[0-5])) |(==> )").matcher(strRule);
        boolean isAntecedent = true;

        while (descriptionMatcher.find()) {
            String match = descriptionMatcher.group().trim();

            if (match.equals("==>")) {
                isAntecedent = false;
            } else {
                if (isAntecedent) {
                    this.antecedent.add(match);
                } else {
                    this.consequent.add(match);
                }
            }

        }
    }

    private void parseProperties(String strRule) {
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

    /**
     * @return the confidence
     */
    public double getConfidence() {
        return confidence;
    }

    /**
     * @return the lift
     */
    public double getLift() {
        return lift;
    }

    /**
     * @return the conviction
     */
    public double getConviction() {
        return conviction;
    }

    /**
     * @return the antecedente
     */
    public List<String> getAntecedent() {
        return antecedent;
    }

    /**
     * @return the consequent
     */
    public List<String> getConsequent() {
        return consequent;
    }

}
