package ru.eva.oriokslive.models.miet.group;

import io.realm.RealmList;
import io.realm.RealmObject;

public class LocalGroup extends RealmObject {

    private RealmList<String> group;
    public RealmList<String> getGroup() {
        return group;
    }

    public void setGroup(RealmList<String> group) {
        this.group = group;
    }
}
