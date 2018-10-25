package br.inf.ufrgs.tecmides.services.models.rule;

import java.util.Map;
import org.springframework.stereotype.Service;

@Service
public class MatchServiceImpl implements MatchService {
    
    @Override
    public Boolean matches(Map<String, String> reference, Map<String, String> toCompare) {
        for( String key : toCompare.keySet() ) {
            if( reference.containsKey(key) ) {
                if(  ! reference.get(key).equalsIgnoreCase(toCompare.get(key)) ) {
                    return false;
                }
            } else {
                return false;
            }
        }

        return true;
    }
}
