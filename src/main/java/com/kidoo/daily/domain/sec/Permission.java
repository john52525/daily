package com.kidoo.daily.domain.sec;

import java.util.Date;

public class Permission {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sec_permission.permission_id
     *
     * @mbggenerated
     */
    private Integer permissionId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sec_permission.permission_name
     *
     * @mbggenerated
     */
    private String permissionName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sec_permission.created_time
     *
     * @mbggenerated
     */
    private Date createdTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sec_permission.update_time
     *
     * @mbggenerated
     */
    private Date updateTime;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sec_permission
     *
     * @mbggenerated
     */
    public Permission(Integer permissionId, String permissionName, Date createdTime, Date updateTime) {
        this.permissionId = permissionId;
        this.permissionName = permissionName;
        this.createdTime = createdTime;
        this.updateTime = updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sec_permission
     *
     * @mbggenerated
     */
    public Permission() {
        super();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sec_permission.permission_id
     *
     * @return the value of sec_permission.permission_id
     *
     * @mbggenerated
     */
    public Integer getPermissionId() {
        return permissionId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sec_permission.permission_id
     *
     * @param permissionId the value for sec_permission.permission_id
     *
     * @mbggenerated
     */
    public void setPermissionId(Integer permissionId) {
        this.permissionId = permissionId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sec_permission.permission_name
     *
     * @return the value of sec_permission.permission_name
     *
     * @mbggenerated
     */
    public String getPermissionName() {
        return permissionName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sec_permission.permission_name
     *
     * @param permissionName the value for sec_permission.permission_name
     *
     * @mbggenerated
     */
    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName == null ? null : permissionName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sec_permission.created_time
     *
     * @return the value of sec_permission.created_time
     *
     * @mbggenerated
     */
    public Date getCreatedTime() {
        return createdTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sec_permission.created_time
     *
     * @param createdTime the value for sec_permission.created_time
     *
     * @mbggenerated
     */
    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sec_permission.update_time
     *
     * @return the value of sec_permission.update_time
     *
     * @mbggenerated
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sec_permission.update_time
     *
     * @param updateTime the value for sec_permission.update_time
     *
     * @mbggenerated
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}