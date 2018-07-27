package br.inf.ufrgs.tecmides.tool.classification;

import weka.core.Instances;

public interface ClassificationTool {

    public String classify(Instances instances) throws Exception;

}
