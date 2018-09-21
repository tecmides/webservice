package br.inf.ufrgs.tecmides.tecmidesserver.service.tool;

import br.inf.ufrgs.tecmides.entities.Rule;
import br.inf.ufrgs.tecmides.entities.RuleOperand;
import br.inf.ufrgs.tecmides.services.tool.filter.RuleFilter;
import br.inf.ufrgs.tecmides.services.tool.filter.RuleFilterImpl;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RuleFilterTest {

    private RuleFilter filter;
    
    @Before
    public void init(){
        this.filter = new RuleFilterImpl();
    }
    
    @Test
    public void filterByMinLiftTest() throws Exception {
        List<Rule> rules = new ArrayList<>();
        rules.add(new Rule("st_indiv_subject_diff=discouraged rc_indiv_subject_keepup=always 11 ==> st_indiv_assign_ltsubmit=discouraged 10    <conf:(0.91)> lift:(1.75) lev:(0.09) [4] conv:(2.64)"));
        rules.add(new Rule("st_indiv_subject_diff=discouraged rc_indiv_subject_keepup=always 11 ==> st_indiv_assign_ltsubmit=discouraged 10    <conf:(0.91)> lift:(1.0) lev:(0.09) [4] conv:(2.64)"));
        rules.add(new Rule("st_indiv_subject_diff=discouraged rc_indiv_subject_keepup=always 11 ==> st_indiv_assign_ltsubmit=discouraged 10    <conf:(0.91)> lift:(1.1) lev:(0.09) [4] conv:(2.64)"));
        
        rules.get(0).setId(0L);
        rules.get(1).setId(1L);
        rules.get(2).setId(2L);
        
        List<Rule> filteredRules = this.filter.filterByMinLift(rules, 1.1);
        
        assertThat(filteredRules.size(), equalTo(2));
        assertThat(filteredRules.get(0).getId().intValue(), equalTo(0));
        assertThat(filteredRules.get(1).getId().intValue(), equalTo(2));
    }
    
    @Test
    public void filterByMinConvictionTest() throws Exception {
        List<Rule> rules = new ArrayList<>();
        rules.add(new Rule("st_indiv_subject_diff=discouraged rc_indiv_subject_keepup=always 11 ==> st_indiv_assign_ltsubmit=discouraged 10    <conf:(0.91)> lift:(1.75) lev:(0.09) [4] conv:(1.1)"));
        rules.add(new Rule("st_indiv_subject_diff=discouraged rc_indiv_subject_keepup=always 11 ==> st_indiv_assign_ltsubmit=discouraged 10    <conf:(0.91)> lift:(1.0) lev:(0.09) [4] conv:(1)"));
        rules.add(new Rule("st_indiv_subject_diff=discouraged rc_indiv_subject_keepup=always 11 ==> st_indiv_assign_ltsubmit=discouraged 10    <conf:(0.91)> lift:(1.1) lev:(0.09) [4] conv:(2.64)"));
        
        rules.get(0).setId(0L);
        rules.get(1).setId(1L);
        rules.get(2).setId(2L);
        
        List<Rule> filteredRules = this.filter.filterByMinConviction(rules, 1.1);
        
        assertThat(filteredRules.size(), equalTo(2));
        assertThat(filteredRules.get(0).getId().intValue(), equalTo(0));
        assertThat(filteredRules.get(1).getId().intValue(), equalTo(2));
    }
    
    @Test
    public void filterByConsequentTest() throws Exception {
        List<Rule> rules = new ArrayList<>();
        rules.add(new Rule("st_indiv_subject_diff=discouraged 12 ==> rc_indiv_subject_keepup=always 10    <conf:(0.91)> lift:(1.75) lev:(0.09) [4] conv:(1.1)"));
        rules.add(new Rule("st_indiv_subject_diff=discouraged 12 ==> rc_indiv_subject_keepup=never 10    <conf:(0.91)> lift:(1.75) lev:(0.09) [4] conv:(1.1)"));
        rules.add(new Rule("st_indiv_subject_diff=discouraged 12 ==> rc_indiv_subject_keepup=sometimes 10    <conf:(0.91)> lift:(1.75) lev:(0.09) [4] conv:(1.1)"));
        rules.add(new Rule("st_indiv_subject_diff=discouraged 12 ==> rc_indiv_subject_keepup=always 10    <conf:(0.91)> lift:(1.75) lev:(0.09) [4] conv:(1.1)"));
        
        rules.get(0).setId(0L);
        rules.get(1).setId(1L);
        rules.get(2).setId(2L);
        rules.get(3).setId(3L);
        
        List<RuleOperand> operands = new ArrayList<>();
        operands.add(new RuleOperand("rc_indiv_subject_keepup=always"));
        operands.add(new RuleOperand("st_indiv_subject_diff=discouraged"));
        
        List<Rule> filteredRules = this.filter.filterByConsequent(rules, operands);
        
        assertThat(filteredRules.size(), equalTo(2));
        assertThat(filteredRules.get(0).getId().intValue(), equalTo(0));
        assertThat(filteredRules.get(1).getId().intValue(), equalTo(3));
    }
    
    @Test
    public void filterByAntecedentTest() throws Exception {
        List<Rule> rules = new ArrayList<>();
        rules.add(new Rule("st_indiv_subject_diff=discouraged rc_indiv_subject_keepup=always 11 ==> st_indiv_assign_ltsubmit=discouraged 10    <conf:(0.91)> lift:(1.75) lev:(0.09) [4] conv:(1.1)"));
        rules.add(new Rule("st_indiv_subject_diff=satisfied rc_indiv_subject_keepup=never 11 ==> st_indiv_assign_ltsubmit=discouraged 10    <conf:(0.91)> lift:(1.0) lev:(0.09) [4] conv:(1)"));
        rules.add(new Rule("st_indiv_subject_diff=satisfied rc_indiv_subject_keepup=always 11 ==> st_indiv_assign_ltsubmit=discouraged 10    <conf:(0.91)> lift:(1.0) lev:(0.09) [4] conv:(1)"));
        rules.add(new Rule("st_indiv_subject_diff=discouraged rc_indiv_subject_keepup=none 11 ==> st_indiv_assign_ltsubmit=discouraged 10    <conf:(0.91)> lift:(1.1) lev:(0.09) [4] conv:(2.64)"));
        
        rules.get(0).setId(0L);
        rules.get(1).setId(1L);
        rules.get(2).setId(2L);
        rules.get(3).setId(3L);
        
        List<RuleOperand> operands = new ArrayList<>();
        operands.add(new RuleOperand("rc_indiv_subject_keepup=always"));
        operands.add(new RuleOperand("st_indiv_subject_diff=discouraged"));
        
        List<Rule> filteredRules = this.filter.filterByAntecedent(rules, operands);
        
        assertThat(filteredRules.size(), equalTo(3));
        assertThat(filteredRules.get(0).getId().intValue(), equalTo(0));
        assertThat(filteredRules.get(1).getId().intValue(), equalTo(2));
        assertThat(filteredRules.get(2).getId().intValue(), equalTo(3));
    }
    
    @Test
    public void filterByOperandsTest() throws Exception {
        List<Rule> rules = new ArrayList<>();
        rules.add(new Rule("st_indiv_subject_diff=discouraged rc_indiv_subject_keepup=never 11 ==> st_indiv_assign_ltsubmit=discouraged 10    <conf:(0.91)> lift:(1.75) lev:(0.09) [4] conv:(1.1)"));
        rules.add(new Rule("st_indiv_subject_diff=satisfied rc_indiv_subject_keepup=never 11 ==> st_indiv_assign_ltsubmit=discouraged 10    <conf:(0.91)> lift:(1.0) lev:(0.09) [4] conv:(1)"));
        rules.add(new Rule("st_indiv_subject_diff=satisfied rc_indiv_subject_keepup=always 11 ==> st_indiv_assign_ltsubmit=discouraged 10    <conf:(0.91)> lift:(1.0) lev:(0.09) [4] conv:(1)"));
        rules.add(new Rule("st_indiv_subject_diff=discouraged rc_indiv_subject_keepup=none 11 ==> st_indiv_assign_ltsubmit=discouraged 10    <conf:(0.91)> lift:(1.1) lev:(0.09) [4] conv:(2.64)"));
        rules.add(new Rule("st_indiv_subject_diff=discouraged 12 ==> rc_indiv_subject_keepup=always 10    <conf:(0.91)> lift:(1.75) lev:(0.09) [4] conv:(1.1)"));
        rules.add(new Rule("st_indiv_subject_diff=discouraged 12 ==> rc_indiv_subject_keepup=never 10    <conf:(0.91)> lift:(1.75) lev:(0.09) [4] conv:(1.1)"));
        rules.add(new Rule("st_indiv_subject_diff=discouraged 12 ==> st_indiv_subject_diff=satisfied 10    <conf:(0.91)> lift:(1.75) lev:(0.09) [4] conv:(1.1)"));
        rules.add(new Rule("st_indiv_subject_diff=discouraged 12 ==> rc_indiv_subject_keepup=always 10    <conf:(0.91)> lift:(1.75) lev:(0.09) [4] conv:(1.1)"));
        
        rules.get(0).setId(0L);
        rules.get(1).setId(1L);
        rules.get(2).setId(2L);
        rules.get(3).setId(3L);
        rules.get(4).setId(4L);
        rules.get(5).setId(5L);
        rules.get(6).setId(6L);
        rules.get(7).setId(7L);
        
        List<RuleOperand> operands = new ArrayList<>();
        operands.add(new RuleOperand("st_indiv_subject_diff=satisfied"));
        operands.add(new RuleOperand("rc_indiv_subject_keepup=never"));
        
        List<Rule> filteredRules = this.filter.filterByOperands(rules, operands);
        
        // Reordering just for the sake of the tests
        Collections.sort(filteredRules, Comparator.comparing(s -> s.getId()));
        
        assertThat(filteredRules.size(), equalTo(5));
        assertThat(filteredRules.get(0).getId().intValue(), equalTo(0));
        assertThat(filteredRules.get(1).getId().intValue(), equalTo(1));
        assertThat(filteredRules.get(2).getId().intValue(), equalTo(2));
        assertThat(filteredRules.get(3).getId().intValue(), equalTo(5));
        assertThat(filteredRules.get(4).getId().intValue(), equalTo(6));
    }
}
