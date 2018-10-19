package br.inf.ufrgs.tecmides.controllers;

import br.inf.ufrgs.tecmides.entities.tree.TreeModelInstance;
import java.util.List;
import java.util.logging.Level;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import br.inf.ufrgs.tecmides.services.models.ModelService;

@RequestMapping("/tree_model")
@RestController
public class TreeModelController {

    @Autowired
    ModelService<TreeModelInstance> service;

    private final Logger log = LoggerFactory.getLogger(TreeModelController.class);

    @RequestMapping(value = "/classify", method = RequestMethod.POST)
    public ResponseEntity<List<TreeModelInstance>> classify( @RequestBody List<TreeModelInstance> instances ) {
        log.trace("Classify method called");

        try {
            service.initialize();
            
            return new ResponseEntity<>(service.classify(instances), HttpStatus.OK);
        } catch( Exception ex ) {
            String error = "Unabled to classify the instances!";
            log.error(error, ex);
            return new ResponseEntity<>(instances, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/contribute", method = RequestMethod.POST)
    public ResponseEntity contribute( @RequestBody List<TreeModelInstance> instances ) {
        try {
            service.initialize();
            service.updateTrainingData(instances);
            log.trace("Instances saved");
            service.updateModel();
            log.trace("Model updated");

            return new ResponseEntity<>(HttpStatus.OK);
        } catch( Exception ex ) {
            String error = "Unabled to save the provided instances and update the model!";
            log.error(error, ex);
            return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
