package com.tester.api_tester_springboot.model.db.domain.namelist;

import java.io.Serializable;
import java.util.Date;

public class RcSmzDwcjList implements Serializable {
    private Long id;

    private Date createTime;

    private Date updateTime;

    private String createUser;

    private String hotelId;

    private Integer source;

    private Integer state;

    private String expireTime;

    private String description;

    private Integer riskScene;

    private String riskSceneNew;

    private String hotelName;

    private Integer dwcjList;

    private Integer smzList;

    private String operator;

    private Integer listType;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser == null ? null : createUser.trim();
    }

    public String getHotelId() {
        return hotelId;
    }

    public void setHotelId(String hotelId) {
        this.hotelId = hotelId == null ? null : hotelId.trim();
    }

    public Integer getSource() {
        return source;
    }

    public void setSource(Integer source) {
        this.source = source;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(String expireTime) {
        this.expireTime = expireTime == null ? null : expireTime.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Integer getRiskScene() {
        return riskScene;
    }

    public void setRiskScene(Integer riskScene) {
        this.riskScene = riskScene;
    }

    public String getRiskSceneNew() {
        return riskSceneNew;
    }

    public void setRiskSceneNew(String riskSceneNew) {
        this.riskSceneNew = riskSceneNew == null ? null : riskSceneNew.trim();
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName == null ? null : hotelName.trim();
    }

    public Integer getDwcjList() {
        return dwcjList;
    }

    public void setDwcjList(Integer dwcjList) {
        this.dwcjList = dwcjList;
    }

    public Integer getSmzList() {
        return smzList;
    }

    public void setSmzList(Integer smzList) {
        this.smzList = smzList;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator == null ? null : operator.trim();
    }

    public Integer getListType() {
        return listType;
    }

    public void setListType(Integer listType) {
        this.listType = listType;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", createUser=").append(createUser);
        sb.append(", hotelId=").append(hotelId);
        sb.append(", source=").append(source);
        sb.append(", state=").append(state);
        sb.append(", expireTime=").append(expireTime);
        sb.append(", description=").append(description);
        sb.append(", riskScene=").append(riskScene);
        sb.append(", riskSceneNew=").append(riskSceneNew);
        sb.append(", hotelName=").append(hotelName);
        sb.append(", dwcjList=").append(dwcjList);
        sb.append(", smzList=").append(smzList);
        sb.append(", operator=").append(operator);
        sb.append(", listType=").append(listType);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        RcSmzDwcjList other = (RcSmzDwcjList) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getCreateUser() == null ? other.getCreateUser() == null : this.getCreateUser().equals(other.getCreateUser()))
            && (this.getHotelId() == null ? other.getHotelId() == null : this.getHotelId().equals(other.getHotelId()))
            && (this.getSource() == null ? other.getSource() == null : this.getSource().equals(other.getSource()))
            && (this.getState() == null ? other.getState() == null : this.getState().equals(other.getState()))
            && (this.getExpireTime() == null ? other.getExpireTime() == null : this.getExpireTime().equals(other.getExpireTime()))
            && (this.getDescription() == null ? other.getDescription() == null : this.getDescription().equals(other.getDescription()))
            && (this.getRiskScene() == null ? other.getRiskScene() == null : this.getRiskScene().equals(other.getRiskScene()))
            && (this.getRiskSceneNew() == null ? other.getRiskSceneNew() == null : this.getRiskSceneNew().equals(other.getRiskSceneNew()))
            && (this.getHotelName() == null ? other.getHotelName() == null : this.getHotelName().equals(other.getHotelName()))
            && (this.getDwcjList() == null ? other.getDwcjList() == null : this.getDwcjList().equals(other.getDwcjList()))
            && (this.getSmzList() == null ? other.getSmzList() == null : this.getSmzList().equals(other.getSmzList()))
            && (this.getOperator() == null ? other.getOperator() == null : this.getOperator().equals(other.getOperator()))
            && (this.getListType() == null ? other.getListType() == null : this.getListType().equals(other.getListType()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getCreateUser() == null) ? 0 : getCreateUser().hashCode());
        result = prime * result + ((getHotelId() == null) ? 0 : getHotelId().hashCode());
        result = prime * result + ((getSource() == null) ? 0 : getSource().hashCode());
        result = prime * result + ((getState() == null) ? 0 : getState().hashCode());
        result = prime * result + ((getExpireTime() == null) ? 0 : getExpireTime().hashCode());
        result = prime * result + ((getDescription() == null) ? 0 : getDescription().hashCode());
        result = prime * result + ((getRiskScene() == null) ? 0 : getRiskScene().hashCode());
        result = prime * result + ((getRiskSceneNew() == null) ? 0 : getRiskSceneNew().hashCode());
        result = prime * result + ((getHotelName() == null) ? 0 : getHotelName().hashCode());
        result = prime * result + ((getDwcjList() == null) ? 0 : getDwcjList().hashCode());
        result = prime * result + ((getSmzList() == null) ? 0 : getSmzList().hashCode());
        result = prime * result + ((getOperator() == null) ? 0 : getOperator().hashCode());
        result = prime * result + ((getListType() == null) ? 0 : getListType().hashCode());
        return result;
    }
}