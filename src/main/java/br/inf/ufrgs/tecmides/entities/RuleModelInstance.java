package br.inf.ufrgs.tecmides.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private Integer q_assign_view;
    private Integer q_assign_submit;
    private Integer q_forum_create;
    private Integer q_forum_group_access;
    private Integer q_forum_discussion_access;
    private Integer q_resource_view;
    private Integer st_indiv_assign_ltsubmit;
    private Integer st_group_assign_ltsubmit;
    private Integer st_indiv_subject_diff;
    private Integer rc_indiv_assign_ltsubmit;
    private Integer rc_group_assign_ltsubmit;
    private Integer rc_indiv_subject_keepup;
    private Integer rc_indiv_subject_diff;
    private Boolean discouraged;
    
    private RuleModelInstance() {}
    
    public RuleModelInstance(Long courseid, Long userid, String grade, Integer q_assign_view, Integer q_assign_submit, Integer q_forum_create, Integer q_forum_group_access, Integer q_forum_discussion_access, Integer q_resource_view, Integer st_indiv_assign_ltsubmit, Integer st_group_assign_ltsubmit, Integer st_indiv_subject_diff, Integer rc_indiv_assign_ltsubmit, Integer rc_group_assign_ltsubmit, Integer rc_indiv_subject_keepup, Integer rc_indiv_subject_diff, Boolean discouraged) {
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
        this.discouraged = discouraged;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCourseid() {
        return courseid;
    }

    public void setCourseid(Long courseid) {
        this.courseid = courseid;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public Integer getQ_assign_view() {
        return q_assign_view;
    }

    public void setQ_assign_view(Integer q_assign_view) {
        this.q_assign_view = q_assign_view;
    }

    public Integer getQ_assign_submit() {
        return q_assign_submit;
    }

    public void setQ_assign_submit(Integer q_assign_submit) {
        this.q_assign_submit = q_assign_submit;
    }

    public Integer getQ_forum_create() {
        return q_forum_create;
    }

    public void setQ_forum_create(Integer q_forum_create) {
        this.q_forum_create = q_forum_create;
    }

    public Integer getQ_forum_group_access() {
        return q_forum_group_access;
    }

    public void setQ_forum_group_access(Integer q_forum_group_access) {
        this.q_forum_group_access = q_forum_group_access;
    }

    public Integer getQ_forum_discussion_access() {
        return q_forum_discussion_access;
    }

    public void setQ_forum_discussion_access(Integer q_forum_discussion_access) {
        this.q_forum_discussion_access = q_forum_discussion_access;
    }

    public Integer getQ_resource_view() {
        return q_resource_view;
    }

    public void setQ_resource_view(Integer q_resource_view) {
        this.q_resource_view = q_resource_view;
    }

    public Integer getSt_indiv_assign_ltsubmit() {
        return st_indiv_assign_ltsubmit;
    }

    public void setSt_indiv_assign_ltsubmit(Integer st_indiv_assign_ltsubmit) {
        this.st_indiv_assign_ltsubmit = st_indiv_assign_ltsubmit;
    }

    public Integer getSt_group_assign_ltsubmit() {
        return st_group_assign_ltsubmit;
    }

    public void setSt_group_assign_ltsubmit(Integer st_group_assign_ltsubmit) {
        this.st_group_assign_ltsubmit = st_group_assign_ltsubmit;
    }

    public Integer getSt_indiv_subject_diff() {
        return st_indiv_subject_diff;
    }

    public void setSt_indiv_subject_diff(Integer st_indiv_subject_diff) {
        this.st_indiv_subject_diff = st_indiv_subject_diff;
    }

    public Integer getRc_indiv_assign_ltsubmit() {
        return rc_indiv_assign_ltsubmit;
    }

    public void setRc_indiv_assign_ltsubmit(Integer rc_indiv_assign_ltsubmit) {
        this.rc_indiv_assign_ltsubmit = rc_indiv_assign_ltsubmit;
    }

    public Integer getRc_group_assign_ltsubmit() {
        return rc_group_assign_ltsubmit;
    }

    public void setRc_group_assign_ltsubmit(Integer rc_group_assign_ltsubmit) {
        this.rc_group_assign_ltsubmit = rc_group_assign_ltsubmit;
    }

    public Integer getRc_indiv_subject_keepup() {
        return rc_indiv_subject_keepup;
    }

    public void setRc_indiv_subject_keepup(Integer rc_indiv_subject_keepup) {
        this.rc_indiv_subject_keepup = rc_indiv_subject_keepup;
    }

    public Integer getRc_indiv_subject_diff() {
        return rc_indiv_subject_diff;
    }

    public void setRc_indiv_subject_diff(Integer rc_indiv_subject_diff) {
        this.rc_indiv_subject_diff = rc_indiv_subject_diff;
    }

    public Boolean getDiscouraged() {
        return discouraged;
    }

    public void setDiscouraged(Boolean discouraged) {
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
                + "q_assign_view=%d, "
                + "q_assign_submit=%d, "
                + "q_forum_create=%d, "
                + "q_forum_group_access=%d, "
                + "q_forum_discussion_access=%d, "
                + "q_resource_view=%d, "
                + "st_indiv_assign_ltsubmit=%d, "
                + "st_group_assign_ltsubmit=%d, "
                + "st_indiv_subject_diff=%d, "
                + "rc_indiv_assign_ltsubmit=%d, "
                + "rc_group_assign_ltsubmit=%d, "
                + "rc_indiv_subject_keepup=%d, "
                + "rc_indiv_subject_diff=%d, "
                + "discouraged=%d"
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
}
