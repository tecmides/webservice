package br.inf.ufrgs.tecmides.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Rule {

    private final List<Operand> antecedent;
    private final List<Operand> consequent;
    private double confidence;
    private double lift;
    private double conviction;

    public Rule() {
        this.antecedent = new ArrayList<>();
        this.consequent = new ArrayList<>();
        this.confidence = 0;
        this.lift = 0;
        this.conviction = 0;

    }

    public Rule(String strRule) throws Exception {
        strRule = strRule.trim();

        this.antecedent = new ArrayList<>();
        this.consequent = new ArrayList<>();

        this.parseOperands(strRule);
        this.parseProperties(strRule);
    }

    private void parseOperands(String strRule) throws Exception {
        Matcher descriptionMatcher = Pattern.compile("(([a-z_]+=[0-5])|([a-z_]+=[A-F])) |(==> )").matcher(strRule);
        boolean isAntecedent = true;

        while (descriptionMatcher.find()) {
            String match = descriptionMatcher.group().trim();

            if (match.equals("==>")) {
                isAntecedent = false;
            } else {
                Operand operand = new Operand(match);

                if (isAntecedent) {
                    this.antecedent.add(operand);
                } else {
                    this.consequent.add(operand);
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
    public List<Operand> getAntecedent() {
        return antecedent;
    }

    /**
     * @return the consequent
     */
    public List<Operand> getConsequent() {
        return consequent;
    }

    @Override
    public String toString() {
        return String.format("%s => %s, conf: %f, lift: %f, conv: %f", this.antecedent.toString(), this.consequent.toString(), this.confidence, this.lift, this.conviction);
    }
}
