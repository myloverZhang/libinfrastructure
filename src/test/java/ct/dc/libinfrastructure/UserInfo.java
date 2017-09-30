package ct.dc.libinfrastructure;

import java.io.Serializable;

/**
 * Created by CTWLPC on 2017/9/30.
 */
public class UserInfo implements Serializable{
//    private String manageDepartment;
    private Integer inUseBeginTime;
//    private Integer inUseEndTime;
//    private String email;
    private Integer id;
    private String userName;
//    private String pwd;
    private Integer departmentId;
    private String roleId;
    private Integer siteId;
    private String createTime;
    private String lastLoginTime;
//    private String remark;
//    private String mac;
    private Boolean macFlag;
    private Integer status;
    private String trueName;
//    private String promotionCity;
//    private String promotionExceptCity;
//    private String safeCode;
    private Integer overDueTime;
//    private String mobile;

    public UserInfo() {
    }

    public UserInfo(Integer id, String userName, Integer departmentId, String roleId, Integer siteId,
                    String createTime, String lastLoginTime, Boolean macFlag, Integer status, String trueName,
                    Integer overDueTime) {
        this.id = id;
        this.userName = userName;
        this.departmentId = departmentId;
        this.roleId = roleId;
        this.siteId = siteId;
        this.createTime = createTime;
        this.lastLoginTime = lastLoginTime;
        this.macFlag = macFlag;
        this.status = status;
        this.trueName = trueName;
        this.overDueTime = overDueTime;
    }

    public Integer getInUseBeginTime() {
        return inUseBeginTime;
    }

    public void setInUseBeginTime(Integer inUseBeginTime) {
        this.inUseBeginTime = inUseBeginTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public Integer getSiteId() {
        return siteId;
    }

    public void setSiteId(Integer siteId) {
        this.siteId = siteId;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(String lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public Boolean getMacFlag() {
        return macFlag;
    }

    public void setMacFlag(Boolean macFlag) {
        this.macFlag = macFlag;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getTrueName() {
        return trueName;
    }

    public void setTrueName(String trueName) {
        this.trueName = trueName;
    }

    public Integer getOverDueTime() {
        return overDueTime;
    }

    public void setOverDueTime(Integer overDueTime) {
        this.overDueTime = overDueTime;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", departmentId=" + departmentId +
                ", roleId='" + roleId + '\'' +
                ", siteId=" + siteId +
                ", createTime='" + createTime + '\'' +
                ", lastLoginTime='" + lastLoginTime + '\'' +
                ", macFlag=" + macFlag +
                ", status=" + status +
                ", trueName='" + trueName + '\'' +
                ", overDueTime=" + overDueTime +
                '}';
    }
}
