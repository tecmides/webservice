package br.inf.ufrgs.tecmides.entities.rule;

import br.inf.ufrgs.tecmides.entities.AuditModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class RuleModelInstance extends AuditModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    private Long courseid;
    private Long userid;
    private String grade;
    private String q_assign_view;
    private String q_assign_submit;
    private String q_forum_create;
    private String q_forum_group_access;
    private String q_forum_discussion_access;
    private String q_resource_view;
    private String st_indiv_assign_ltsubmit;
    private String st_group_assign_ltsubmit;
    private String st_indiv_subject_diff;
    private String rc_indiv_assign_ltsubmit;
    private String rc_group_assign_ltsubmit;
    private String rc_indiv_subject_keepup;
    private String rc_indiv_subject_diff;
    private Double discouraged_coeficient;
    private String discouraged;

    protected RuleModelInstance() {
    }

    public RuleModelInstance( Long courseid, Long userid, String grade, String q_assign_view, String q_assign_submit, String q_forum_create, String q_forum_group_access, String q_forum_discussion_access, String q_resource_view, String st_indiv_assign_ltsubmit, String st_group_assign_ltsubmit, String st_indiv_subject_diff, String rc_indiv_assign_ltsubmit, String rc_group_assign_ltsubmit, String rc_indiv_subject_keepup, String rc_indiv_subject_diff, Double discouraged_coeficient, String discouraged ) {
        this.courseid = courseid;
        this.userid = userid;
        this.grade = grade;
        this.q_assign_view = q_assign_view;
        this.q_assign_submit = q_assign_submit;
        this.q_forum_create = q_forum_create;
        this.q_forum_group_access = q_forum_group_access;
        this.q_forum_discussion_access = q_forum_discussion_access;
        this.q_resource_view = q_resource_view;
        this.st_indiv_assign_ltsubmit = st_indiv_assign_ltsubmit;
        this.st_group_assign_ltsubmit = st_group_assign_ltsubmit;
        this.st_indiv_subject_diff = st_indiv_subject_diff;
        this.rc_indiv_assign_ltsubmit = rc_indiv_assign_ltsubmit;
        this.rc_group_assign_ltsubmit = rc_group_assign_ltsubmit;
        this.rc_indiv_subject_keepup = rc_indiv_subject_keepup;
        this.rc_indiv_subject_diff = rc_indiv_subject_diff;
        this.discouraged_coeficient = discouraged_coeficient;
        this.discouraged = discouraged;
    }

    @Override
    public String toString() {
        return String.format(
                "RuleModelInstance["
                + "id=%d, "
                + "courseid=%d, "
                + "userid=%d, "
                + "grade=%s, "
                + "q_assign_view=%s, "
                + "q_assign_submit=%s, "
                + "q_forum_create=%s, "
                + "q_forum_group_access=%s, "
                + "q_forum_discussion_access=%s, "
                + "q_resource_view=%s, "
                + "st_indiv_assign_ltsubmit=%s, "
                + "st_group_assign_ltsubmit=%s, "
                + "st_indiv_subject_diff=%s, "
                + "rc_indiv_assign_ltsubmit=%s, "
                + "rc_group_assign_ltsubmit=%s, "
                + "rc_indiv_subject_keepup=%s, "
                + "rc_indiv_subject_diff=%s, "
                + "discouraged=%s"
                + "]",
                id,
                courseid,
                userid,
                grade,
                q_assign_view,
                q_assign_submit,
                q_forum_create,
                q_forum_group_access,
                q_forum_discussion_access,
                q_resource_view,
                st_indiv_assign_ltsubmit,
                st_group_assign_ltsubmit,
                st_indiv_subject_diff,
                rc_indiv_assign_ltsubmit,
                rc_group_assign_ltsubmit,
                rc_indiv_subject_keepup,
                rc_indiv_subject_diff,
                discouraged
        );
    }
    
    public Long getId() {
        return id;
    }

    public void setId( Long id ) {
        this.id = id;
    }

    public Long getCourseid() {
        return courseid;
    }

    public void setCourseid( Long courseid ) {
        this.courseid = courseid;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid( Long userid ) {
        this.userid = userid;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade( String grade ) {
        this.grade = grade;
    }

    public String getQ_assign_view() {
        return q_assign_view;
    }

    public void setQ_assign_view( String q_assign_view ) {
        this.q_assign_view = q_assign_view;
    }

    public String getQ_assign_submit() {
        return q_assign_submit;
    }

    public void setQ_assign_submit( String q_assign_submit ) {
        this.q_assign_submit = q_assign_submit;
    }

    public String getQ_forum_create() {
        return q_forum_create;
    }

    public void setQ_forum_create( String q_forum_create ) {
        this.q_forum_create = q_forum_create;
    }

    public String getQ_forum_group_access() {
        return q_forum_group_access;
    }

    public void setQ_forum_group_access( String q_forum_group_access ) {
        this.q_forum_group_access = q_forum_group_access;
    }

    public String getQ_forum_discussion_access() {
        return q_forum_discussion_access;
    }

    public void setQ_forum_discussion_access( String q_forum_discussion_access ) {
        this.q_forum_discussion_access = q_forum_discussion_access;
    }

    public String getQ_resource_view() {
        return q_resource_view;
    }

    public void setQ_resource_view( String q_resource_view ) {
        this.q_resource_view = q_resource_view;
    }

    public String getSt_indiv_assign_ltsubmit() {
        return st_indiv_assign_ltsubmit;
    }

    public void setSt_indiv_assign_ltsubmit( String st_indiv_assign_ltsubmit ) {
        this.st_indiv_assign_ltsubmit = st_indiv_assign_ltsubmit;
    }

    public String getSt_group_assign_ltsubmit() {
        return st_group_assign_ltsubmit;
    }

    public void setSt_group_assign_ltsubmit( String st_group_assign_ltsubmit ) {
        this.st_group_assign_ltsubmit = st_group_assign_ltsubmit;
    }

    public String getSt_indiv_subject_diff() {
        return st_indiv_subject_diff;
    }

    public void setSt_indiv_subject_diff( String st_indiv_subject_diff ) {
        this.st_indiv_subject_diff = st_indiv_subject_diff;
    }

    public String getRc_indiv_assign_ltsubmit() {
        return rc_indiv_assign_ltsubmit;
    }

    public void setRc_indiv_assign_ltsubmit( String rc_indiv_assign_ltsubmit ) {
        this.rc_indiv_assign_ltsubmit = rc_indiv_assign_ltsubmit;
    }

    public String getRc_group_assign_ltsubmit() {
        return rc_group_assign_ltsubmit;
    }

    public void setRc_group_assign_ltsubmit( String rc_group_assign_ltsubmit ) {
        this.rc_group_assign_ltsubmit = rc_group_assign_ltsubmit;
    }

    public String getRc_indiv_subject_keepup() {
        return rc_indiv_subject_keepup;
    }

    public void setRc_indiv_subject_keepup( String rc_indiv_subject_keepup ) {
        this.rc_indiv_subject_keepup = rc_indiv_subject_keepup;
    }

    public String getRc_indiv_subject_diff() {
        return rc_indiv_subject_diff;
    }

    public void setRc_indiv_subject_diff( String rc_indiv_subject_diff ) {
        this.rc_indiv_subject_diff = rc_indiv_subject_diff;
    }

    public Double getDiscouraged_coeficient() {
        return discouraged_coeficient;
    }

    public void setDiscouraged_coeficient( Double discouraged_coeficient ) {
        this.discouraged_coeficient = discouraged_coeficient;
    }

    public String getDiscouraged() {
        return discouraged;
    }
    
    public void setDiscouraged(String discouraged) {
        this.discouraged = discouraged;
    }
}
