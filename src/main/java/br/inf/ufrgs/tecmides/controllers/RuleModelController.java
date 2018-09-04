package br.inf.ufrgs.tecmides.controllers;

import br.inf.ufrgs.tecmides.entities.RuleModelInstance;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import br.inf.ufrgs.tecmides.services.rule.RuleModelService;

@RequestMapping("/rule_model")
@RestController
public class RuleModelController {

    @Autowired
    RuleModelService service;

    @RequestMapping(value = "/classify", method = RequestMethod.POST)
    public List<RuleModelInstance> classify( @RequestBody List<RuleModelInstance> instances ) {
        return service.classify(instances);
    }

    @RequestMapping(value = "/contribute", method = RequestMethod.POST)
    public ResponseEntity contribute( @RequestBody List<RuleModelInstance> instances ) {
        try {
            service.saveInstances(instances);
            service.updateModel();

            return ResponseEntity.ok("OK");
        } catch( Exception ex ) {
            return ResponseEntity.ok(ex.getMessage());
        }
    }

}
