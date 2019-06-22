package com.nikdemo.e_bookbookreadingapp.model;

public class PlanModel {

    private String plan_price,plan;
    private int plan_time;

    public PlanModel(String plan_price, String plan, int plan_time) {
        this.plan_price = plan_price;
        this.plan = plan;
        this.plan_time = plan_time;
    }

    public String getPlan_price() {
        return plan_price;
    }

    public void setPlan_price(String plan_price) {
        this.plan_price = plan_price;
    }

    public String getPlan() {
        return plan;
    }

    public void setPlan(String plan) {
        this.plan = plan;
    }

    public int getPlan_time() {
        return plan_time;
    }

    public void setPlan_time(int plan_time) {
        this.plan_time = plan_time;
    }
}
