package br.inf.ufrgs.tecmides.services.models;

import java.util.List;
import org.springframework.stereotype.Service;

@Service
public interface ModelService<T> {

    public void initialize();
    
    public List<T> classify( List<T> instances ) throws NoSuchFieldException, Exception;

    public void updateTrainingData( List<T> dataset );

    public void updateModel() throws Exception;

}
