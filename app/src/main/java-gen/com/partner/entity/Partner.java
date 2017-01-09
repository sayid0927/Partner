package com.partner.entity;

import org.greenrobot.greendao.annotation.*;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit.
/**
 * Entity mapped to table "PARTNER".
 */
@Entity
public class Partner {

    @Id
    private Long id;
    private String type;
    private String date;
    private String time;
    private String sleep;
    private String lightsleep;
    private String sleeping;
    private String awake;
    private String exercisetime;
    private String exercisedistance;
    private String calcalNum;
    private String stepsumsnum;

    @Generated(hash = 40105147)
    public Partner() {
    }

    public Partner(Long id) {
        this.id = id;
    }

    @Generated(hash = 1670602223)
    public Partner(Long id, String type, String date, String time, String sleep, String lightsleep, String sleeping, String awake, String exercisetime, String exercisedistance, String calcalNum, String stepsumsnum) {
        this.id = id;
        this.type = type;
        this.date = date;
        this.time = time;
        this.sleep = sleep;
        this.lightsleep = lightsleep;
        this.sleeping = sleeping;
        this.awake = awake;
        this.exercisetime = exercisetime;
        this.exercisedistance = exercisedistance;
        this.calcalNum = calcalNum;
        this.stepsumsnum = stepsumsnum;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getSleep() {
        return sleep;
    }

    public void setSleep(String sleep) {
        this.sleep = sleep;
    }

    public String getLightsleep() {
        return lightsleep;
    }

    public void setLightsleep(String lightsleep) {
        this.lightsleep = lightsleep;
    }

    public String getSleeping() {
        return sleeping;
    }

    public void setSleeping(String sleeping) {
        this.sleeping = sleeping;
    }

    public String getAwake() {
        return awake;
    }

    public void setAwake(String awake) {
        this.awake = awake;
    }

    public String getExercisetime() {
        return exercisetime;
    }

    public void setExercisetime(String exercisetime) {
        this.exercisetime = exercisetime;
    }

    public String getExercisedistance() {
        return exercisedistance;
    }

    public void setExercisedistance(String exercisedistance) {
        this.exercisedistance = exercisedistance;
    }

    public String getCalcalNum() {
        return calcalNum;
    }

    public void setCalcalNum(String calcalNum) {
        this.calcalNum = calcalNum;
    }

    public String getStepsumsnum() {
        return stepsumsnum;
    }

    public void setStepsumsnum(String stepsumsnum) {
        this.stepsumsnum = stepsumsnum;
    }

}
