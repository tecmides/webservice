package br.inf.ufrgs.tecmides.services.tool.classification;

import br.inf.ufrgs.tecmides.services.models.ModelDataset;
import weka.core.Instance;

public interface ClassificationTool {

    public void train( ModelDataset dataset ) throws Exception;
    
    public double classify( Instance instance ) throws Exception;
    
    public void save(String path) throws Exception;
    
    public boolean restore(String path) throws Exception;

}
