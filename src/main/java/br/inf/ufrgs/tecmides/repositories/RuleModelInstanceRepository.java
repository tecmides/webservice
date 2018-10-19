package br.inf.ufrgs.tecmides.repositories;

import br.inf.ufrgs.tecmides.entities.rule.RuleModelInstance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RuleModelInstanceRepository extends JpaRepository<RuleModelInstance, Long> {

    public RuleModelInstance findByCourseidAndUserid( Long courseid, Long userid );

}
