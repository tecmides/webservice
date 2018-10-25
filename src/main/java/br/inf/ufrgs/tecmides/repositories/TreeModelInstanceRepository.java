package br.inf.ufrgs.tecmides.repositories;

import br.inf.ufrgs.tecmides.entities.tree.TreeModelInstance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TreeModelInstanceRepository extends JpaRepository<TreeModelInstance, Long> {

    public TreeModelInstance findByCourseidAndUserid( Long courseid, Long userid );

}
