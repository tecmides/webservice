package br.inf.ufrgs.tecmides.services.models.rule;

import br.inf.ufrgs.tecmides.services.models.rule.modulemining.ForumRuleMining;
import br.inf.ufrgs.tecmides.services.models.rule.modulemining.ResourceRuleMining;
import br.inf.ufrgs.tecmides.services.models.rule.modulemining.AssignRuleMining;
import br.inf.ufrgs.tecmides.entities.rule.Rule;
import br.inf.ufrgs.tecmides.entities.rule.RuleModelInstance;
import br.inf.ufrgs.tecmides.repositories.RuleModelInstanceRepository;
import br.inf.ufrgs.tecmides.repositories.RuleRepository;
import br.inf.ufrgs.tecmides.services.models.ModelService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RuleModelService implements ModelService<RuleModelInstance> {

    @Autowired
    RuleRepository ruleRepository;

    @Autowired
    RuleModelInstanceRepository instanceRepository;

    @Autowired
    RuleService ruleService;

    @Autowired
    RuleModelInstanceService instanceService;

    @Override
    public void initialize() {}
    
    @Override
    public List<RuleModelInstance> classify( List<RuleModelInstance> instances ) {
        List<RuleModelInstance> classifiedInstances = new ArrayList<>();
        List<Rule> rules = this.ruleRepository.findAll();

        for( RuleModelInstance instance : instances ) {
            int matchCount = 0;
            double coeficient;

            for( Rule rule : rules ) {
                Boolean itMatches = this.instanceService.instanceMatchesRule(instance, rule);

                if( itMatches ) {
                    matchCount ++;
                }
            }

            coeficient = ( (double) matchCount ) / rules.size();

            this.instanceService.setModelInstanceClassification(instance, ( coeficient >= 0.75 ) ? 1 : 0);
            this.instanceService.setModelInstanceCoeficient(instance, coeficient);
            
            classifiedInstances.add(instance);
        }

        return classifiedInstances;
    }

    @Override
    public void updateTrainingData( List<RuleModelInstance> instances ) {
        for( RuleModelInstance instance : instances ) {
            RuleModelInstance oldInstance = instanceRepository.findByCourseidAndUserid(instance.getCourseid(), instance.getUserid());

            if( oldInstance != null ) {
                instance.setId(oldInstance.getId());
            }

            instanceRepository.save(instance);
        }

        this.instanceRepository.saveAll(instances);
    }

    @Override
    public void updateModel() throws Exception {
        List<RuleModelInstance> instances = this.instanceRepository.findAll();

        List<Rule> rules = new ArrayList<>();

        rules.addAll(( new AssignRuleMining() ).getRules(this.instanceService, instances, this.ruleService));
        rules.addAll(( new ForumRuleMining() ).getRules(this.instanceService, instances, this.ruleService));
        rules.addAll(( new ResourceRuleMining() ).getRules(this.instanceService, instances, this.ruleService));

        rules = this.ruleService.filter(rules, 1.1, 1.1);

        this.ruleRepository.deleteAll();
        this.ruleRepository.saveAll(rules);
    }

}
