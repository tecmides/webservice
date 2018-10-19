package br.inf.ufrgs.tecmides.entities.tree;

import br.inf.ufrgs.tecmides.entities.AuditModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import br.inf.ufrgs.tecmides.entities.ModelInstance;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import weka.core.Attribute;

@Entity
public class TreeModelInstance extends AuditModel implements ModelInstance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    private Long courseid;
    private Long userid;
    private String q_assign_view;
    private String q_assign_submit;
    private String q_forum_create;
    private String q_forum_group_access;
    private String q_forum_discussion_access;
    private String q_resource_view;
    private String approved;

    protected TreeModelInstance() {
    }

    public TreeModelInstance( Long courseid, Long userid, String q_assign_view, String q_assign_submit, String q_forum_create, String q_forum_group_access, String q_forum_discussion_access, String q_resource_view, String approved ) {
        this.courseid = courseid;
        this.userid = userid;
        this.q_assign_view = q_assign_view;
        this.q_assign_submit = q_assign_submit;
        this.q_forum_create = q_forum_create;
        this.q_forum_group_access = q_forum_group_access;
        this.q_forum_discussion_access = q_forum_discussion_access;
        this.q_resource_view = q_resource_view;
        this.approved = approved;
    }

    @Override
    public String toString() {
        return String.format(
                "TreeModelInstance["
                + "id=%d, "
                + "courseid=%d, "
                + "userid=%d, "
                + "q_assign_view=%s, "
                + "q_assign_submit=%s, "
                + "q_forum_create=%s, "
                + "q_forum_group_access=%s, "
                + "q_forum_discussion_access=%s, "
                + "q_resource_view=%s, "
                + "approved=%s"
                + "]",
                id,
                courseid,
                userid,
                q_assign_view,
                q_assign_submit,
                q_forum_create,
                q_forum_group_access,
                q_forum_discussion_access,
                q_resource_view,
                approved
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

    public String getApproved() {
        return approved;
    }

    public static List<Attribute> getAttributes() {
        String[] quartiles = {"low", "medium", "medium-high", "high"};

        List<Attribute> attributes = new ArrayList<>();
        attributes.add(new Attribute("q_assign_view", new ArrayList<>(Arrays.asList(quartiles))));
        attributes.add(new Attribute("q_assign_submit", new ArrayList<>(Arrays.asList(quartiles))));
        attributes.add(new Attribute("q_forum_create", new ArrayList<>(Arrays.asList(quartiles))));
        attributes.add(new Attribute("q_forum_group_access", new ArrayList<>(Arrays.asList(quartiles))));
        attributes.add(new Attribute("q_forum_discussion_access", new ArrayList<>(Arrays.asList(quartiles))));
        attributes.add(new Attribute("q_resource_view", new ArrayList<>(Arrays.asList(quartiles))));

        return attributes;
    }

    public static Attribute getClassificaitonAttribute() {
        String[] approvedValues = {"no", "yes"};

        return new Attribute("approved", new ArrayList<>(Arrays.asList(approvedValues)));
    }

    @Override
    public void setClassification( double classification ) {
        this.approved = TreeModelInstance.getClassificaitonAttribute().value((int) classification);
    }

}
