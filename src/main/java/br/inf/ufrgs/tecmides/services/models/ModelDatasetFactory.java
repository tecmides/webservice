package br.inf.ufrgs.tecmides.services.models;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.Instances;
import weka.core.converters.ArffSaver;

public class ModelDatasetFactory<J> {

    public ModelDataset create( String name, List<Attribute> attributes, Attribute classAttribute, List<J> instances ) {
        try {
            return new BaseModelDataset<>(name, attributes, classAttribute, instances);
        } catch( NoSuchFieldException ex ) {
            return null;
        }
    }

    class BaseModelDataset<J> implements ModelDataset<J> {

        private List<J> modelInstances;
        private Instances dataset;

        public BaseModelDataset( String name, List<Attribute> attributes, Attribute classAttribute, List<J> instances ) throws NoSuchFieldException {
            attributes.add(classAttribute);
            this.dataset = new Instances(name, new ArrayList<>(attributes), instances.size());
            this.dataset.setClass(classAttribute);
            this.modelInstances = instances;

            DenseInstance parsedInstance = new DenseInstance(attributes.size());
            parsedInstance.setDataset(this.dataset);

            for( int idxInstance = 0; idxInstance < instances.size(); idxInstance ++ ) {
                J instance = instances.get(idxInstance);

                for( int idxAttribute = 0; idxAttribute < attributes.size(); idxAttribute ++ ) {
                    try {
                        Field field = instance.getClass().getDeclaredField(this.dataset.attribute(idxAttribute).name());
                        field.setAccessible(true);

                        switch( this.dataset.attribute(idxAttribute).type() ) {
                            case Attribute.NOMINAL:
                                String value = (String) field.get(instance);

                                if( value != null || idxAttribute != attributes.size() - 1 ) {
                                    if( this.dataset.attribute(idxAttribute).indexOfValue(value) < 0 ) {
                                        parsedInstance.setMissing(idxAttribute);
                                    } else {
                                        parsedInstance.setValue(idxAttribute, value);
                                    }
                                } else {
                                    parsedInstance.setClassMissing();
                                }

                                break;

                            case Attribute.NUMERIC:
                                parsedInstance.setValue(idxAttribute, (double) field.get(instance));

                                break;

                            default:
                                throw new UnsupportedOperationException("There is no support for this data type conversion");
                        }

                    } catch( IllegalArgumentException | IllegalAccessException ex ) {
                        Logger.getLogger(BaseModelDataset.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

                this.dataset.add(parsedInstance);
            }
        }

        public void saveToArff( String filePath ) throws IOException {
            ArffSaver saver = new ArffSaver();
            saver.setInstances(this.dataset);
            saver.setFile(new File(filePath));
            saver.setDestination(new File(filePath));
            saver.writeBatch();
        }

        @Override
        public Instances getInstances() {
            return this.dataset;
        }

        @Override
        public List<J> getModelInstances() {
            return this.modelInstances;
        }
    }
}
