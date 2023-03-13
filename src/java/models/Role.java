/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.io.Serializable;

/**
 *
 * @author xbali
 */
public class Role implements Serializable {
    int roleID;
    String roleName;

    public Role(int roleID) {
        this.roleID = roleID;
        if(roleID==1){
            this.roleName="system admin";
        }
        else{
            this.roleName="regular user";
        }
    }

    public int getRoleID() {
        return roleID;
    }

    public void setRoleID(int roleID) {
        this.roleID = roleID;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
    
   
}
