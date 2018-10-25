package br.inf.ufrgs.tecmides.repositories;

import br.inf.ufrgs.tecmides.entities.rule.Rule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RuleRepository extends JpaRepository<Rule, Long> {

}
