package com.spider.base;

import java.io.Serializable;
import java.util.List;

/**
 * Created by qd on 2016/1/12.
 */
public class AppUser implements Serializable {
    private static final long serialVersionUID = -1103290135394247310L;
    private String id;
    private String username;
    private String realname;
    private List<Long> projectIds;
    private boolean allProjectVisable;

    public AppUser() {
    }

    public AppUser(String id, String username, String realname, List<Long> projectIds, boolean allProjectVisable) {
        this.id = id;
        this.username = username;
        this.realname = realname;
        this.projectIds = projectIds;
        this.allProjectVisable = allProjectVisable;
    }

    public List<Long> getProjectIds() {
        return this.projectIds;
    }

    public void setProjectIds(List<Long> projectIds) {
        this.projectIds = projectIds;
    }

    public boolean isAllProjectVisable() {
        return this.allProjectVisable;
    }

    public void setAllProjectVisable(boolean allProjectVisable) {
        this.allProjectVisable = allProjectVisable;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRealname() {
        return this.realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }
}
