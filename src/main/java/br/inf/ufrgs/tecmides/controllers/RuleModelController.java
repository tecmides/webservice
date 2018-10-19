package br.inf.ufrgs.tecmides.controllers;

import br.inf.ufrgs.tecmides.entities.rule.RuleModelInstance;
import br.inf.ufrgs.tecmides.services.models.ModelService;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/rule_model")
@RestController
public class RuleModelController {

    @Autowired
    ModelService<RuleModelInstance> service;

    private final Logger log = LoggerFactory.getLogger(RuleModelController.class);

    @RequestMapping(value = "/classify", method = RequestMethod.POST)
    public ResponseEntity classify( @RequestBody List<RuleModelInstance> instances ) {
        try {
            log.trace("Classify method called");

            return new ResponseEntity<>(service.classify(instances), HttpStatus.OK);
        } catch( Exception ex ) {
            String error = "Unabled to classify the instances!";
            log.error(error, ex);
            return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/contribute", method = RequestMethod.POST)
    public ResponseEntity contribute( @RequestBody List<RuleModelInstance> instances ) {
        try {
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
