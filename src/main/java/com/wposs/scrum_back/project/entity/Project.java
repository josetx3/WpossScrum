package com.wposs.scrum_back.project.entity;

import com.wposs.scrum_back.area.entity.Area;
import com.wposs.scrum_back.client.entity.Client;
import com.wposs.scrum_back.subProject.entity.SubProject;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.core.codec.ByteArrayEncoder;
import org.w3c.dom.Text;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "project", schema = "wposs")
public class Project {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "project_id")
    private UUID projectId;

    @Column(name = "project_name")
    private String projectName;

    @Column(name = "area_id")
    private UUID areaId;

    @Column(name = "client_id")
    private String clientId;

    @Column(name = "project_img", columnDefinition = "text null")
    private String archive;

    @ManyToOne
    @JoinColumn(name = "area_id", insertable = false, updatable = false)
    private Area area;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "client_id", insertable = false, updatable = false)
    private Client client;

    @OneToMany(mappedBy = "project",cascade ={CascadeType.DETACH,CascadeType.REMOVE,CascadeType.MERGE})
    private List<SubProject> subProjects;

    public String getArchive() {
        return archive;
    }

    public void setArchive(String archive) {
        this.archive = archive;
    }

    public UUID getProjectId() {
        return projectId;
    }

    public void setProjectId(UUID projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public UUID getAreaId() {
        return areaId;
    }

    public void setAreaId(UUID areaId) {
        this.areaId = areaId;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public List<SubProject> getSubProjects() {
        return subProjects;
    }

    public void setSubProjects(List<SubProject> subProjects) {
        this.subProjects = subProjects;
    }
}
