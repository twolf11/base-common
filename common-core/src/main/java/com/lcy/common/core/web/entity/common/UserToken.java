package com.lcy.common.core.web.entity.common;

import lombok.Builder;

/**
 * @Description token信息
 * @Author lcy
 * @Date 2021/5/17 14:39
 */
@Builder(toBuilder = true)
public class UserToken {

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 用户登录名--这里使用email
     */
    private String userCode;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 企业-组织id
     */
    private String orgId;

    /**
     * 企业-组织名称
     */
    private String orgName;

    /**
     * 角色-角色id
     */
    private Integer roleId;

    /**
     * 角色-角色名称
     */
    private String roleName;

    public UserToken(){
    }

    public UserToken(Long userId,String userCode,String userName,String orgId,String orgName,Integer roleId,String roleName){
        this.userId = userId;
        this.userCode = userCode;
        this.userName = userName;
        this.orgId = orgId;
        this.orgName = orgName;
        this.roleId = roleId;
        this.roleName = roleName;
    }

    public Long getUserId(){
        return userId;
    }

    public UserToken setUserId(Long userId){
        this.userId = userId;
        return this;
    }

    public String getUserCode(){
        return userCode;
    }

    public UserToken setUserCode(String userCode){
        this.userCode = userCode;
        return this;
    }

    public String getUserName(){
        return userName;
    }

    public UserToken setUserName(String userName){
        this.userName = userName;
        return this;
    }

    public String getOrgId(){
        return orgId;
    }

    public UserToken setOrgId(String orgId){
        this.orgId = orgId;
        return this;
    }

    public String getOrgName(){
        return orgName;
    }

    public UserToken setOrgName(String orgName){
        this.orgName = orgName;
        return this;
    }

    public Integer getRoleId(){
        return roleId;
    }

    public UserToken setRoleId(Integer roleId){
        this.roleId = roleId;
        return this;
    }

    public String getRoleName(){
        return roleName;
    }

    public UserToken setRoleName(String roleName){
        this.roleName = roleName;
        return this;
    }

    @Override public String toString(){
        return "UserToken{" +
                "userId=" + userId +
                ", userCode='" + userCode + '\'' +
                ", userName='" + userName + '\'' +
                ", orgId='" + orgId + '\'' +
                ", orgName='" + orgName + '\'' +
                ", roleId=" + roleId +
                ", roleName='" + roleName + '\'' +
                '}';
    }
}
