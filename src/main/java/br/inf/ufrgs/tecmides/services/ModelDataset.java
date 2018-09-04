package br.inf.ufrgs.tecmides.services;

import java.util.List;
import weka.core.Attribute;
import weka.core.Instances;

public interface ModelDataset {

    public List<Attribute> getAttributeDefinition();

    public Instances getInstances();

}
