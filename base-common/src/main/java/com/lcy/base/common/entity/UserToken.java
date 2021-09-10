package com.lcy.base.common.entity;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @Description token信息
 * @Author lcy
 * @Date 2021/5/17 14:39
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
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

    /**
     * 场景权限集合
     */
    private Set<String> privilegeList;

}
