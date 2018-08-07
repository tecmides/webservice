package br.inf.ufrgs.tecmides.services;

import br.inf.ufrgs.tecmides.entities.Rule;
import br.inf.ufrgs.tecmides.tool.association.AprioriAssociation;
import br.inf.ufrgs.tecmides.tool.association.AssociationTool;
import br.inf.ufrgs.tecmides.tool.attrSelection.algorithm.AttrSelectionAlgorithmTool;
import br.inf.ufrgs.tecmides.tool.attrSelection.algorithm.CsfSelection;
import br.inf.ufrgs.tecmides.tool.filter.RuleFilter;
import br.inf.ufrgs.tecmides.tool.filter.RuleFilterImpl;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.stereotype.Service;
import weka.core.Instances;

@Service
public class RulesServiceImpl implements RulesService {

    @Override
    public List<Rule> generateRules(String ARFFString, int numRules, double minSupport, double minConfidence) {
        List<Rule> rules = new ArrayList<>();

        try {
            Instances instances = getInstances(ARFFString);
            AssociationTool associator = new AprioriAssociation();

            rules.addAll(associator.associate(instances, numRules, minSupport, minConfidence));

            rules = filter(rules);

        } catch (FileNotFoundException | UnsupportedEncodingException ex) {
            Logger.getLogger(RulesServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(RulesServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(RulesServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        return rules;
    }

    @Override
    public List<Rule> generateRulesByAttrRelativity(String ARFFString, int idxClassAttr, int numRules, double minSupport, double minConfidence) {
        List<Rule> rules = new ArrayList<>();

        try {
            Instances instances = getInstances(ARFFString);
            instances.setClassIndex(idxClassAttr);

            AssociationTool associator = new AprioriAssociation();
            AttrSelectionAlgorithmTool attrSelector = new CsfSelection();

            rules.addAll(associator.associate(attrSelector.run(instances, idxClassAttr), numRules, minSupport, minConfidence));

            rules = filter(rules);

        } catch (FileNotFoundException | UnsupportedEncodingException ex) {
            Logger.getLogger(RulesServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(RulesServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(RulesServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        return rules;
    }

    private static Instances getInstances(String ARFFString) throws FileNotFoundException, IOException, Exception {
        PrintWriter writer;

        String arffPath = System.getProperty("java.io.tmpdir") + "/tecmides" + System.currentTimeMillis() + ".arff";

        writer = new PrintWriter(arffPath, "UTF-8");
        writer.println(ARFFString);
        writer.close();

        Instances instances;
        try (BufferedReader reader = new BufferedReader(new FileReader(arffPath))) {
            instances = new Instances(reader);

            reader.close();
            Files.deleteIfExists((new File(arffPath)).toPath());
        }

        if (instances.numInstances() <= 0) {
            throw new Exception("There are no instances!");
        }

        return instances;
    }

    private static List<Rule> filter(List<Rule> rules) throws Exception {
        RuleFilter filter = new RuleFilterImpl();

        return filter.filterByMinLift(filter.filterByMinConviction(rules, 1.1), 1.1);
    }

}
