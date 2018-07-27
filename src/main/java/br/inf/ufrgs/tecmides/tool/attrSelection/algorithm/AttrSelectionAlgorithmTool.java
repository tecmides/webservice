package br.inf.ufrgs.tecmides.tool.attrSelection.algorithm;

import weka.core.Instances;

public interface AttrSelectionAlgorithmTool {

    public Instances run(Instances instances, int classIndex) throws Exception;

}
