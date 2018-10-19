package br.inf.ufrgs.tecmides.tecmidesserver.service.rule;

import br.inf.ufrgs.tecmides.entities.rule.Rule;
import br.inf.ufrgs.tecmides.entities.rule.RuleModelInstance;
import br.inf.ufrgs.tecmides.services.rule.RuleModelInstanceService;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RuleModelInstanceServiceTest {

    @Autowired
    private RuleModelInstanceService instanceService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void instanceMathesSimpleRuleTest() throws Exception {
        RuleModelInstance instance = new RuleModelInstance(
                0L,
                0L,
                "B",
                "low",
                "medium",
                "low",
                "medium-high",
                "medium",
                "high",
                "discouraged",
                "discouraged",
                "discouraged",
                "sometimes",
                "sometimes",
                "sometimes",
                "sometimes",
                0.0,
                "no"
        );

        Rule rule = new Rule("grade=B 14 ==> st_indiv_assign_ltsubmit=discouraged 11    <conf:(0.79)> lift:(1.48) lev:(0.07) [3] conv:(1.64)");

        assertThat(this.instanceService.instanceMatchesRule(instance, rule), equalTo(true));
    }

    @Test
    public void instanceMathesComplexRuleTest() throws Exception {
        RuleModelInstance instance = new RuleModelInstance(
                0L,
                0L,
                "B",
                "low",
                "medium",
                "low",
                "medium-high",
                "medium",
                "high",
                "discouraged",
                "discouraged",
                "discouraged",
                "sometimes",
                "sometimes",
                "sometimes",
                "sometimes",
                0.0,
                "no"
        );

        Rule rule = new Rule("q_assign_submit=medium 14 rc_indiv_subject_diff 12 ==> st_indiv_assign_ltsubmit=discouraged 11    <conf:(0.79)> lift:(1.48) lev:(0.07) [3] conv:(1.64)");

        assertThat(this.instanceService.instanceMatchesRule(instance, rule), equalTo(true));
    }

    @Test
    public void instanceDonotMathesRuleTest() throws Exception {
        RuleModelInstance instance = new RuleModelInstance(
                0L,
                0L,
                "B",
                "low",
                "medium",
                "low",
                "medium-high",
                "medium",
                "high",
                "discouraged",
                "discouraged",
                "discouraged",
                "sometimes",
                "sometimes",
                "sometimes",
                "sometimes",
                0.0,
                "no"
        );

        Rule rule = new Rule("q_assign_submit=low 14 rc_indiv_subject_diff 12 ==> st_indiv_assign_ltsubmit=discouraged 11    <conf:(0.79)> lift:(1.48) lev:(0.07) [3] conv:(1.64)");

        assertThat(this.instanceService.instanceMatchesRule(instance, rule), equalTo(false));
    }

}
