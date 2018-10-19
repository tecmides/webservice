package br.inf.ufrgs.tecmides.services.models.rule;

import java.util.Map;
import org.springframework.stereotype.Service;

@Service
public interface MatchableObjectService<T> {
    
    public Map<String, String> mappedFields(T toMap);
    
}
