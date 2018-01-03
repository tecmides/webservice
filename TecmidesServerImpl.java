package tecmides;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jws.WebService;
import tecmides.tool.association.AprioriAssociation;
import tecmides.tool.attributeSelection.CsfSelection;
import tecmides.tool.classification.J48Classification;

import weka.core.Instances;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.Remove;

@WebService(endpointInterface = "tecmides.TecmidesServer")
public class TecmidesServerImpl implements TecmidesServer {

    @Override
    public String generateRules(String ARFFString) {
        PrintWriter writer;
        try {
            String arffPath = System.getProperty("java.io.tmpdir") + "tecmides.arff";
            
            writer = new PrintWriter(arffPath, "UTF-8");
            writer.println(ARFFString);
            writer.close();
            
            BufferedReader reader = new BufferedReader(new FileReader(arffPath));
            Instances data = new Instances(reader);
            reader.close();
            
            return generateJSON(data);
            
        } catch (FileNotFoundException | UnsupportedEncodingException ex) {
            Logger.getLogger(TecmidesServerImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(TecmidesServerImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return "";
    }
    
    private static String generateJSON(Instances data) {
        Gson gson = new Gson();
            
        List<String> assignRules = assign_start_mining(data);
        List<String> forumRules = forum_start_mining(data);
        List<String> resourceRules = resource_start_mining(data);
        
        // Creates the map for the inner level
        Map<String, String> rules = new HashMap<>();
        rules.put("assign", gson.toJson(assignRules));
        rules.put("forum", gson.toJson(forumRules));
        rules.put("resource", gson.toJson(resourceRules));
        
        // Creates the parent map for the inner level
        Map<String, String> output = new HashMap<>();
        output.put("rules", gson.toJson(rules));
        
        return gson.toJson(output);
    }
    
    private static List<String> assign_start_mining(Instances data) {
        List<String> rules = new ArrayList<>();

        try {
            rules.addAll(associate_by_attribute_mining(data, MiningAttribute.ST_INDIV_ASSIGN_LTSUBMIT));

            rules.addAll(associate_by_attribute_mining(data, MiningAttribute.ST_GROUP_ASSIGN_LTSUBMIT));

            rules.addAll(associate_by_attribute_mining(data, MiningAttribute.ST_INDIV_SUBJECT_DIFF));
                        
            // Regras encontradas. Filtrar pelas melhores.
            // 1.1 lift e convicção
            // Regras que contenham desânimo
            
        } catch (Exception ex) {
            Logger.getLogger(TecmidesServerImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        return rules;
    }

    private static List<String> associate_by_attribute_mining(Instances data, MiningAttribute index) throws Exception {
        data.setClassIndex(index.ordinal());

        Instances filteredData = new CsfSelection().select(data);

        return new AprioriAssociation(filteredData).associate(10);
    }

    private static List<String> forum_start_mining(Instances data) {
        List<String> rules = new ArrayList<>();
        
        try {
            Instances preparedData = forum_prepare_data(data);
            
            J48Classification classificator = new J48Classification(preparedData);
            classificator.train();
            
            // System.out.println(classificator.tree());
            // Extrair a árvore
            
            rules.addAll(new AprioriAssociation(preparedData).associate(20));
            
            // Regras encontradas. Filtrar pelas melhores.
            // 1.1 lift e convicção
            // Regras que contenham desânimo
            
        } catch (Exception ex) {
            Logger.getLogger(TecmidesServerImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return rules;
    }
    
    private static Instances forum_prepare_data(Instances data) throws Exception {
        // Removal of not interesting attributes
        Remove remove = new Remove();
        remove.setAttributeIndices("2,3,7,8,10,11");
        remove.setInputFormat(data);
        Instances filteredData = Filter.useFilter(data, remove);

        // '-4' beacuse of the number of attributes removed before the index
        filteredData.setClassIndex(MiningAttribute.ST_GROUP_ASSIGN_LTSUBMIT.ordinal() - 4);
        
        return filteredData;
    }
    
    private static List<String> resource_start_mining(Instances data) {
        List<String> rules = new ArrayList<>();

        try {
            rules.addAll(associate_by_attribute_mining(data, MiningAttribute.ST_INDIV_ASSIGN_LTSUBMIT));
            
            rules.addAll(associate_by_attribute_mining(data, MiningAttribute.ST_INDIV_SUBJECT_DIFF));

            // Regras encontradas. Filtrar pelas melhores.
            // 1.1 lift e convicção
            // Regras que contenham desânimo
            
        } catch (Exception ex) {
            Logger.getLogger(TecmidesServerImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        return rules;
    }
    
}