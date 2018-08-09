/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.inf.ufrgs.tecmides.services;

import br.inf.ufrgs.tecmides.repositories.InteractionRepository;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author preto
 */
@Service
public class InteractionService {
    
    InteractionRepository repository;
    public void saveAll(List<Integer> trainingSet){
        
        trainingSet.stream().forEach((i) -> repository.save(i));
        
    }
}
