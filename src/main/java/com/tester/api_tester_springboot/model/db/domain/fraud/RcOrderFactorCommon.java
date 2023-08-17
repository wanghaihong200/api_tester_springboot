package com.tester.api_tester_springboot.model.db.domain.fraud;

import lombok.Data;

import java.io.Serializable;

@Data
public class RcOrderFactorCommon implements Serializable {
    private String id;

    private String memberId;

    private String phone;

    private String unionId;

    private String dcBuyerAccount;

    private String fp;

    private String product;

    private String registeredTime;

    private String scheduledTime;

    private String star;

    private String quantity;

    private String roomNight;

    private String mhotelid;

    private String hotelcity;

    private String startTime;

    private String endTime;

    private String orderState;

    private String travelerNames;

    private String contactName;

    private String isMemberAgent;

    private Integer outside;

    private String deviceId;

    private String amount;

    private String orderType;

    private String actionTime;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId == null ? null : memberId.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getUnionId() {
        return unionId;
    }

    public void setUnionId(String unionId) {
        this.unionId = unionId == null ? null : unionId.trim();
    }

    public String getDcBuyerAccount() {
        return dcBuyerAccount;
    }

    public void setDcBuyerAccount(String dcBuyerAccount) {
        this.dcBuyerAccount = dcBuyerAccount == null ? null : dcBuyerAccount.trim();
    }

    public String getFp() {
        return fp;
    }

    public void setFp(String fp) {
        this.fp = fp == null ? null : fp.trim();
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product == null ? null : product.trim();
    }

    public String getRegisteredTime() {
        return registeredTime;
    }

    public void setRegisteredTime(String registeredTime) {
        this.registeredTime = registeredTime == null ? null : registeredTime.trim();
    }

    public String getScheduledTime() {
        return scheduledTime;
    }

    public void setScheduledTime(String scheduledTime) {
        this.scheduledTime = scheduledTime == null ? null : scheduledTime.trim();
    }

    public String getStar() {
        return star;
    }

    public void setStar(String star) {
        this.star = star == null ? null : star.trim();
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity == null ? null : quantity.trim();
    }

    public String getRoomNight() {
        return roomNight;
    }

    public void setRoomNight(String roomNight) {
        this.roomNight = roomNight == null ? null : roomNight.trim();
    }

    public String getMhotelid() {
        return mhotelid;
    }

    public void setMhotelid(String mhotelid) {
        this.mhotelid = mhotelid == null ? null : mhotelid.trim();
    }

    public String getHotelcity() {
        return hotelcity;
    }

    public void setHotelcity(String hotelcity) {
        this.hotelcity = hotelcity == null ? null : hotelcity.trim();
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime == null ? null : startTime.trim();
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime == null ? null : endTime.trim();
    }

    public String getOrderState() {
        return orderState;
    }

    public void setOrderState(String orderState) {
        this.orderState = orderState == null ? null : orderState.trim();
    }

    public String getTravelerNames() {
        return travelerNames;
    }

    public void setTravelerNames(String travelerNames) {
        this.travelerNames = travelerNames == null ? null : travelerNames.trim();
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName == null ? null : contactName.trim();
    }

    public String getIsMemberAgent() {
        return isMemberAgent;
    }

    public void setIsMemberAgent(String isMemberAgent) {
        this.isMemberAgent = isMemberAgent == null ? null : isMemberAgent.trim();
    }

    public Integer getOutside() {
        return outside;
    }

    public void setOutside(Integer outside) {
        this.outside = outside;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId == null ? null : deviceId.trim();
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount == null ? null : amount.trim();
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType == null ? null : orderType.trim();
    }

    public String getActionTime() {
        return actionTime;
    }

    public void setActionTime(String actionTime) {
        this.actionTime = actionTime == null ? null : actionTime.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", memberId=").append(memberId);
        sb.append(", phone=").append(phone);
        sb.append(", unionId=").append(unionId);
        sb.append(", dcBuyerAccount=").append(dcBuyerAccount);
        sb.append(", fp=").append(fp);
        sb.append(", product=").append(product);
        sb.append(", registeredTime=").append(registeredTime);
        sb.append(", scheduledTime=").append(scheduledTime);
        sb.append(", star=").append(star);
        sb.append(", quantity=").append(quantity);
        sb.append(", roomNight=").append(roomNight);
        sb.append(", mhotelid=").append(mhotelid);
        sb.append(", hotelcity=").append(hotelcity);
        sb.append(", startTime=").append(startTime);
        sb.append(", endTime=").append(endTime);
        sb.append(", orderState=").append(orderState);
        sb.append(", travelerNames=").append(travelerNames);
        sb.append(", contactName=").append(contactName);
        sb.append(", isMemberAgent=").append(isMemberAgent);
        sb.append(", outside=").append(outside);
        sb.append(", deviceId=").append(deviceId);
        sb.append(", amount=").append(amount);
        sb.append(", orderType=").append(orderType);
        sb.append(", actionTime=").append(actionTime);
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
        RcOrderFactorCommon other = (RcOrderFactorCommon) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getMemberId() == null ? other.getMemberId() == null : this.getMemberId().equals(other.getMemberId()))
            && (this.getPhone() == null ? other.getPhone() == null : this.getPhone().equals(other.getPhone()))
            && (this.getUnionId() == null ? other.getUnionId() == null : this.getUnionId().equals(other.getUnionId()))
            && (this.getDcBuyerAccount() == null ? other.getDcBuyerAccount() == null : this.getDcBuyerAccount().equals(other.getDcBuyerAccount()))
            && (this.getFp() == null ? other.getFp() == null : this.getFp().equals(other.getFp()))
            && (this.getProduct() == null ? other.getProduct() == null : this.getProduct().equals(other.getProduct()))
            && (this.getRegisteredTime() == null ? other.getRegisteredTime() == null : this.getRegisteredTime().equals(other.getRegisteredTime()))
            && (this.getScheduledTime() == null ? other.getScheduledTime() == null : this.getScheduledTime().equals(other.getScheduledTime()))
            && (this.getStar() == null ? other.getStar() == null : this.getStar().equals(other.getStar()))
            && (this.getQuantity() == null ? other.getQuantity() == null : this.getQuantity().equals(other.getQuantity()))
            && (this.getRoomNight() == null ? other.getRoomNight() == null : this.getRoomNight().equals(other.getRoomNight()))
            && (this.getMhotelid() == null ? other.getMhotelid() == null : this.getMhotelid().equals(other.getMhotelid()))
            && (this.getHotelcity() == null ? other.getHotelcity() == null : this.getHotelcity().equals(other.getHotelcity()))
            && (this.getStartTime() == null ? other.getStartTime() == null : this.getStartTime().equals(other.getStartTime()))
            && (this.getEndTime() == null ? other.getEndTime() == null : this.getEndTime().equals(other.getEndTime()))
            && (this.getOrderState() == null ? other.getOrderState() == null : this.getOrderState().equals(other.getOrderState()))
            && (this.getTravelerNames() == null ? other.getTravelerNames() == null : this.getTravelerNames().equals(other.getTravelerNames()))
            && (this.getContactName() == null ? other.getContactName() == null : this.getContactName().equals(other.getContactName()))
            && (this.getIsMemberAgent() == null ? other.getIsMemberAgent() == null : this.getIsMemberAgent().equals(other.getIsMemberAgent()))
            && (this.getOutside() == null ? other.getOutside() == null : this.getOutside().equals(other.getOutside()))
            && (this.getDeviceId() == null ? other.getDeviceId() == null : this.getDeviceId().equals(other.getDeviceId()))
            && (this.getAmount() == null ? other.getAmount() == null : this.getAmount().equals(other.getAmount()))
            && (this.getOrderType() == null ? other.getOrderType() == null : this.getOrderType().equals(other.getOrderType()))
            && (this.getActionTime() == null ? other.getActionTime() == null : this.getActionTime().equals(other.getActionTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getMemberId() == null) ? 0 : getMemberId().hashCode());
        result = prime * result + ((getPhone() == null) ? 0 : getPhone().hashCode());
        result = prime * result + ((getUnionId() == null) ? 0 : getUnionId().hashCode());
        result = prime * result + ((getDcBuyerAccount() == null) ? 0 : getDcBuyerAccount().hashCode());
        result = prime * result + ((getFp() == null) ? 0 : getFp().hashCode());
        result = prime * result + ((getProduct() == null) ? 0 : getProduct().hashCode());
        result = prime * result + ((getRegisteredTime() == null) ? 0 : getRegisteredTime().hashCode());
        result = prime * result + ((getScheduledTime() == null) ? 0 : getScheduledTime().hashCode());
        result = prime * result + ((getStar() == null) ? 0 : getStar().hashCode());
        result = prime * result + ((getQuantity() == null) ? 0 : getQuantity().hashCode());
        result = prime * result + ((getRoomNight() == null) ? 0 : getRoomNight().hashCode());
        result = prime * result + ((getMhotelid() == null) ? 0 : getMhotelid().hashCode());
        result = prime * result + ((getHotelcity() == null) ? 0 : getHotelcity().hashCode());
        result = prime * result + ((getStartTime() == null) ? 0 : getStartTime().hashCode());
        result = prime * result + ((getEndTime() == null) ? 0 : getEndTime().hashCode());
        result = prime * result + ((getOrderState() == null) ? 0 : getOrderState().hashCode());
        result = prime * result + ((getTravelerNames() == null) ? 0 : getTravelerNames().hashCode());
        result = prime * result + ((getContactName() == null) ? 0 : getContactName().hashCode());
        result = prime * result + ((getIsMemberAgent() == null) ? 0 : getIsMemberAgent().hashCode());
        result = prime * result + ((getOutside() == null) ? 0 : getOutside().hashCode());
        result = prime * result + ((getDeviceId() == null) ? 0 : getDeviceId().hashCode());
        result = prime * result + ((getAmount() == null) ? 0 : getAmount().hashCode());
        result = prime * result + ((getOrderType() == null) ? 0 : getOrderType().hashCode());
        result = prime * result + ((getActionTime() == null) ? 0 : getActionTime().hashCode());
        return result;
    }
}