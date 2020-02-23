package com.htp.entity;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;

public class Tracking {
    private Long id;
    private Long idTask;
    private Long idOrganaizer;
    private String status;
    private Date confirmDate;
    private Long cost;

    public Tracking() {

    }

    public Tracking(Long id, Long idTask, Long idOrganaizer, String status, Date confirmDate, Long cost) {
        this.id = id;
        this.idTask = idTask;
        this.idOrganaizer = idOrganaizer;
        this.status = status;
        this.confirmDate = confirmDate;
        this.cost = cost;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdTask() {
        return idTask;
    }

    public void setIdTask(Long idTask) {
        this.idTask = idTask;
    }

    public Long getIdOrganaizer() {
        return idOrganaizer;
    }

    public void setIdOrganaizer(Long idOrganaizer) {
        this.idOrganaizer = idOrganaizer;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getConfirmDate() {
        return confirmDate;
    }

    public void setConfirmDate(Date confirmDate) {
        this.confirmDate = confirmDate;
    }

    public Long getCost() {
        return cost;
    }

    public void setCost(Long cost) {
        this.cost = cost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tracking)) return false;
        Tracking tracking = (Tracking) o;
        return getId().equals(tracking.getId()) &&
                getIdTask().equals(tracking.getIdTask()) &&
                getIdOrganaizer().equals(tracking.getIdOrganaizer()) &&
                getStatus().equals(tracking.getStatus()) &&
                getConfirmDate().equals(tracking.getConfirmDate()) &&
                getCost().equals(tracking.getCost());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getIdTask(), getIdOrganaizer(), getStatus(), getConfirmDate(), getCost());
    }

    @Override
    public String toString() {
        return "Tracking{" +
                "id=" + id +
                ", idTask=" + idTask +
                ", idOrganaizer=" + idOrganaizer +
                ", status='" + status + '\'' +
                ", confirmDate=" + confirmDate +
                ", cost=" + cost +
                '}'+"\n";
    }
}
