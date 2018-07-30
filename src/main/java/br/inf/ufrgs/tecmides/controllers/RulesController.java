/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.inf.ufrgs.tecmides.controllers;

import br.inf.ufrgs.tecmides.RulesService;
import br.inf.ufrgs.tecmides.entities.Rule;
import java.security.Principal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author preto
 */
@RequestMapping("/rules")
@RestController
public class RulesController {
    
    @Autowired
    RulesService rulesService;
    
    @RequestMapping(value = "/generate", method = RequestMethod.GET)
    public List<Rule> generate(Principal principal) {
        
        String ARFFString = "";
        
        return rulesService.generateRules(ARFFString, 0, 0, 0);
    }
    
    @RequestMapping(value = "/generateByAttrRelativity", method = RequestMethod.GET)
    public List<Rule> generateByAttrRelativity(Principal principal) {
        
         String ARFFString = "";
         
//         String ARFFString, int idxClassAttr, int numRules, double minSupport, double minConfidence
        return rulesService.generateRulesByAttrRelativity(ARFFString, 0, 0, 0, 0);
    }
    
}
