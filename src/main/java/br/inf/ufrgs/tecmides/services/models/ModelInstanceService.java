package br.inf.ufrgs.tecmides.services.models;

import java.util.List;
import org.springframework.stereotype.Service;
import weka.core.Attribute;

@Service
public interface ModelInstanceService<T> {

    public void setModelInstanceClassification(T instance, double classification );
    
    public Attribute getModelInstanceClassificaitonAttribute();
    
    public List<Attribute> getModelInstanceAttributes();

}
