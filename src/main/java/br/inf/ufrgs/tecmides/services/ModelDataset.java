package br.inf.ufrgs.tecmides.services;

import java.util.List;
import weka.core.Instances;

public interface ModelDataset<T> {

    public Instances getInstances();

    public List<T> getModelInstances();

    public void setModelInstanceClassification( int index, double classification );

}
