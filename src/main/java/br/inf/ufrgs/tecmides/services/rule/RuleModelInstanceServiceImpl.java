package br.inf.ufrgs.tecmides.services.rule;

import br.inf.ufrgs.tecmides.entities.Matchable;
import java.util.Map;
import org.springframework.stereotype.Service;
import weka.core.Instances;

@Service
public class RuleModelInstanceServiceImpl implements RuleModelInstanceService {

    @Override
    public Boolean instanceMatchesRule( Matchable instance, Matchable rule ) {
        Map<String, String> map = instance.getMatchableProperties();
        Map<String, String> toMap = rule.getMatchableProperties();

        for( String key : toMap.keySet() ) {
            if( map.containsKey(key) ) {
                if(  ! map.get(key).equalsIgnoreCase(toMap.get(key)) ) {
                    return false;
                }
            } else {
                return false;
            }
        }

        return true;
    }

}
