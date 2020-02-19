package com.htp.entity;

import java.sql.Timestamp;
import java.util.Date;

public class Tracking {
    private Long id;
    private Long id_task;
    private Long id_organaizer;
    private String status;
    private Date confirm_date;
    private Long cost;

    public Tracking() {

    }

    public Tracking(Long id, Long id_task, Long id_organaizer, String status, Date confirm_date, Long cost) {
        this.id = id;
        this.id_task = id_task;
        this.id_organaizer = id_organaizer;
        this.status = status;
        this.confirm_date = confirm_date;
        this.cost = cost;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId_task() {
        return id_task;
    }

    public void setId_task(Long id_task) {
        this.id_task = id_task;
    }

    public Long getId_organaizer() {
        return id_organaizer;
    }

    public void setId_organaizer(Long id_organaizer) {
        this.id_organaizer = id_organaizer;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getConfirm_date() {
        return confirm_date;
    }

    public void setConfirm_date(Date confirm_date) {
        this.confirm_date = confirm_date;
    }

    public Long getCost() {
        return cost;
    }

    public void setCost(Long cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "Tracking{" +
                "id=" + id +
                ", id_task=" + id_task +
                ", id_organaizer=" + id_organaizer +
                ", status='" + status + '\'' +
                ", confirm_date=" + confirm_date +
                ", cost=" + cost +
                '}'+"\n";
    }
}
