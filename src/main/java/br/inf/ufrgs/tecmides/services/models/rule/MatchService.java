package br.inf.ufrgs.tecmides.services.models.rule;

import java.util.Map;
import org.springframework.stereotype.Service;

@Service
public interface MatchService {
    
    public Boolean matches( Map<String, String> reference, Map<String, String> toCompare );
    
}
