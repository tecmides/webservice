package br.inf.ufrgs.tecmides.repositories;

import br.inf.ufrgs.tecmides.entities.RuleModelInstance;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RuleModelInstanceRepository extends JpaRepository<RuleModelInstance, Long> {
    
    public List<RuleModelInstance> findByCourseidAndUserid(Long courseid, Long userid);
    
}
