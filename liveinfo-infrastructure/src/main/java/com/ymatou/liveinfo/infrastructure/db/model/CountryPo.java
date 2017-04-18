package com.ymatou.liveinfo.infrastructure.db.model;

public class CountryPo {
    /**
     * INTEGER(10) 必填<br>
     * 
     */
    private Integer countryId;

    /**
     * VARCHAR(200) 必填<br>
     * 
     */
    private String countryName;

    /**
     * VARCHAR(200) 必填<br>
     * 
     */
    private String countryNameZh;

    /**
     * VARCHAR(200) 必填<br>
     * 
     */
    private String continent;

    /**
     * VARCHAR(500) 必填<br>
     * 
     */
    private String flag;

    /**
     * VARCHAR(16) 必填<br>
     * 
     */
    private String prohibitedStartTime;

    /**
     * VARCHAR(16) 必填<br>
     * 
     */
    private String prohibitedEndTime;

    /**
     * VARCHAR(16) 必填<br>
     * 
     */
    private String prohibitedStartTimeLocal;

    /**
     * VARCHAR(16) 必填<br>
     * 
     */
    private String prohibitedEndTimeLocal;

    /**
     * VARCHAR(200) 必填<br>
     * 
     */
    private String timeZone;

    /**
     * INTEGER(10) 必填<br>
     * 
     */
    private Integer action;

    /**
     * INTEGER(10) 默认值[((-1))] 必填<br>
     * 
     */
    private Integer groupId;

    /**
     * INTEGER(10) 默认值[((-1))] 必填<br>
     * 
     */
    private Integer countryGroupId;

    /**
     * INTEGER(10) 默认值[((0))] 必填<br>
     * 
     */
    private Integer sort;

    /**
     * INTEGER(10) 必填<br>
     */
    public Integer getCountryId() {
        return countryId;
    }

    /**
     * INTEGER(10) 必填<br>
     */
    public void setCountryId(Integer countryId) {
        this.countryId = countryId;
    }

    /**
     * VARCHAR(200) 必填<br>
     */
    public String getCountryName() {
        return countryName;
    }

    /**
     * VARCHAR(200) 必填<br>
     */
    public void setCountryName(String countryName) {
        this.countryName = countryName == null ? null : countryName.trim();
    }

    /**
     * VARCHAR(200) 必填<br>
     */
    public String getCountryNameZh() {
        return countryNameZh;
    }

    /**
     * VARCHAR(200) 必填<br>
     */
    public void setCountryNameZh(String countryNameZh) {
        this.countryNameZh = countryNameZh == null ? null : countryNameZh.trim();
    }

    /**
     * VARCHAR(200) 必填<br>
     */
    public String getContinent() {
        return continent;
    }

    /**
     * VARCHAR(200) 必填<br>
     */
    public void setContinent(String continent) {
        this.continent = continent == null ? null : continent.trim();
    }

    /**
     * VARCHAR(500) 必填<br>
     */
    public String getFlag() {
        return flag;
    }

    /**
     * VARCHAR(500) 必填<br>
     */
    public void setFlag(String flag) {
        this.flag = flag == null ? null : flag.trim();
    }

    /**
     * VARCHAR(16) 必填<br>
     */
    public String getProhibitedStartTime() {
        return prohibitedStartTime;
    }

    /**
     * VARCHAR(16) 必填<br>
     */
    public void setProhibitedStartTime(String prohibitedStartTime) {
        this.prohibitedStartTime = prohibitedStartTime == null ? null : prohibitedStartTime.trim();
    }

    /**
     * VARCHAR(16) 必填<br>
     */
    public String getProhibitedEndTime() {
        return prohibitedEndTime;
    }

    /**
     * VARCHAR(16) 必填<br>
     */
    public void setProhibitedEndTime(String prohibitedEndTime) {
        this.prohibitedEndTime = prohibitedEndTime == null ? null : prohibitedEndTime.trim();
    }

    /**
     * VARCHAR(16) 必填<br>
     */
    public String getProhibitedStartTimeLocal() {
        return prohibitedStartTimeLocal;
    }

    /**
     * VARCHAR(16) 必填<br>
     */
    public void setProhibitedStartTimeLocal(String prohibitedStartTimeLocal) {
        this.prohibitedStartTimeLocal = prohibitedStartTimeLocal == null ? null : prohibitedStartTimeLocal.trim();
    }

    /**
     * VARCHAR(16) 必填<br>
     */
    public String getProhibitedEndTimeLocal() {
        return prohibitedEndTimeLocal;
    }

    /**
     * VARCHAR(16) 必填<br>
     */
    public void setProhibitedEndTimeLocal(String prohibitedEndTimeLocal) {
        this.prohibitedEndTimeLocal = prohibitedEndTimeLocal == null ? null : prohibitedEndTimeLocal.trim();
    }

    /**
     * VARCHAR(200) 必填<br>
     */
    public String getTimeZone() {
        return timeZone;
    }

    /**
     * VARCHAR(200) 必填<br>
     */
    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone == null ? null : timeZone.trim();
    }

    /**
     * INTEGER(10) 必填<br>
     */
    public Integer getAction() {
        return action;
    }

    /**
     * INTEGER(10) 必填<br>
     */
    public void setAction(Integer action) {
        this.action = action;
    }

    /**
     * INTEGER(10) 默认值[((-1))] 必填<br>
     */
    public Integer getGroupId() {
        return groupId;
    }

    /**
     * INTEGER(10) 默认值[((-1))] 必填<br>
     */
    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    /**
     * INTEGER(10) 默认值[((-1))] 必填<br>
     */
    public Integer getCountryGroupId() {
        return countryGroupId;
    }

    /**
     * INTEGER(10) 默认值[((-1))] 必填<br>
     */
    public void setCountryGroupId(Integer countryGroupId) {
        this.countryGroupId = countryGroupId;
    }

    /**
     * INTEGER(10) 默认值[((0))] 必填<br>
     */
    public Integer getSort() {
        return sort;
    }

    /**
     * INTEGER(10) 默认值[((0))] 必填<br>
     */
    public void setSort(Integer sort) {
        this.sort = sort;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table App_Country
     *
     * @mbggenerated Mon Apr 17 15:29:22 CST 2017
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", countryId=").append(countryId);
        sb.append(", countryName=").append(countryName);
        sb.append(", countryNameZh=").append(countryNameZh);
        sb.append(", continent=").append(continent);
        sb.append(", flag=").append(flag);
        sb.append(", prohibitedStartTime=").append(prohibitedStartTime);
        sb.append(", prohibitedEndTime=").append(prohibitedEndTime);
        sb.append(", prohibitedStartTimeLocal=").append(prohibitedStartTimeLocal);
        sb.append(", prohibitedEndTimeLocal=").append(prohibitedEndTimeLocal);
        sb.append(", timeZone=").append(timeZone);
        sb.append(", action=").append(action);
        sb.append(", groupId=").append(groupId);
        sb.append(", countryGroupId=").append(countryGroupId);
        sb.append(", sort=").append(sort);
        sb.append("]");
        return sb.toString();
    }
}