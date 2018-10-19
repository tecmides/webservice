package br.inf.ufrgs.tecmides.services.models;

import java.io.IOException;
import org.springframework.stereotype.Service;

@Service
public interface ClassificationModel {

    public ModelDataset classify( ModelInstanceService service, ModelDataset dataset ) throws Exception;

    public void train( ModelDataset dataset ) throws Exception;
    
    public void save() throws Exception;

    public void restore() throws IOException, Exception;

}
