package br.inf.ufrgs.tecmides.services.tool.classification;

import java.io.IOException;
import br.inf.ufrgs.tecmides.services.ModelDataset;

public interface ClassificationTool {

    public ModelDataset classify( ModelDataset dataset ) throws Exception;

    public String getModelName();

    public void build( ModelDataset dataset ) throws Exception;

    public void save() throws Exception;

    public void restore() throws IOException, Exception;

}
