package com.kidoo.daily.domain.visit;

public class Visitor {

    private String id;

    private String ip;
    /**
     * register account time in this system
     */
    private int registerTime;


    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(int registerTime) {

        this.registerTime = registerTime;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
