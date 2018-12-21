package com.example.student.realmtest;

import io.realm.RealmObject;

/**
 * Created by student on 2018-12-21.
 */

class ActorVO extends RealmObject {
    String actor;

    public String getActor() {
        return actor;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }
}
