package br.inf.ufrgs.tecmides.services;

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

public abstract class BaseModelDataset implements ModelDataset {

    private final Instances dataset;

    public BaseModelDataset( String name, List<Object> instances ) throws NoSuchFieldException, UnsupportedOperationException {
        List<Attribute> attributes = this.getAttributeDefinition();
        this.dataset = new Instances(name, new ArrayList<>(attributes), instances.size());

        DenseInstance parsedInstance = new DenseInstance(attributes.size());
        parsedInstance.setDataset(this.dataset);

        for( Object instance : instances ) {
            for( int i = 0; i < attributes.size(); i ++ ) {
                try {
                    Field field = instance.getClass().getDeclaredField(this.dataset.attribute(i).name());
                    field.setAccessible(true);

                    switch( this.dataset.attribute(i).type() ) {
                        case Attribute.NOMINAL:
                            String value = (String) field.get(instance);

                            if( this.dataset.attribute(i).indexOfValue(value) < 0 ) {
                                parsedInstance.setMissing(i);
                            } else {
                                parsedInstance.setValue(i, value);
                            }

                            break;

                        case Attribute.NUMERIC:
                            parsedInstance.setValue(i, (double) field.get(instance));

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

}
