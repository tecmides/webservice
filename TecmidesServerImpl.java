package tecmides;

import tecmides.mining.rule.ResourceModuleRuleMining;
import tecmides.mining.rule.AssignModuleRuleMining;
import tecmides.mining.rule.ForumModuleRuleMining;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jws.WebService;
import tecmides.domain.Rule;
import tecmides.tool.association.AprioriAssociation;
import tecmides.tool.association.AssociationTool;

import weka.core.Instances;

@WebService(endpointInterface = "tecmides.TecmidesServer")
public class TecmidesServerImpl implements TecmidesServer {

    @Override
    public String generateRules(String ARFFString) {
        List<Rule> rules = new ArrayList<>();

        try {
            Instances instances = getInstances(ARFFString);

            AssociationTool associator = new AprioriAssociation();

            rules.addAll(new AssignModuleRuleMining().mine(instances, associator));
            rules.addAll(new ForumModuleRuleMining().mine(instances, associator));
            rules.addAll(new ResourceModuleRuleMining().mine(instances, associator));

        } catch (FileNotFoundException | UnsupportedEncodingException ex) {
            Logger.getLogger(TecmidesServerImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(TecmidesServerImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(TecmidesServerImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        return new Gson().toJson(rules);
    }
    
    private static Instances getInstances(String ARFFString) throws FileNotFoundException, IOException, Exception {
        PrintWriter writer;
        String arffPath = System.getProperty("java.io.tmpdir") + "tecmides.arff";

        writer = new PrintWriter(arffPath, "UTF-8");
        writer.println(ARFFString);
        writer.close();

        Instances instances;
        try (BufferedReader reader = new BufferedReader(new FileReader(arffPath))) {
            instances = new Instances(reader);
        }

        if (instances.numInstances() <= 0) {
            throw new Exception("There are no instances!");
        }
        
        return instances;
    }

}
