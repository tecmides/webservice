/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.inf.ufrgs.tecmides.tecmidesserver;

import br.inf.ufrgs.tecmides.entities.RuleModelInstance;
import br.inf.ufrgs.tecmides.repositories.RuleRepository;
import br.inf.ufrgs.tecmides.services.rule.RuleModelInstanceService;
import br.inf.ufrgs.tecmides.services.rule.RuleModelService;
import java.util.ArrayList;
import java.util.List;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author preto
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RuleModelServiceTest {

    @Mock
    private RuleRepository ruleRepository;

    @Mock
    private RuleModelInstanceService instanceService;

    @Autowired
    private RuleModelService service;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
    }
    
    @Test
    public void ruleTest() {
        
        when(ruleRepository.findAll()).thenReturn(new ArrayList<>());
        
        List <RuleModelInstance> instances = new ArrayList<>();
        
        
        assertThat(service.classify(instances), equalTo(new ArrayList<RuleModelInstance>()));
    }
}
