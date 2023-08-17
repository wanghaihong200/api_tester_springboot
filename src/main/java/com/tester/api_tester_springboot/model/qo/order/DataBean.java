package com.tester.api_tester_springboot.model.qo.order;

import lombok.Data;

import java.util.List;

@Data
public class DataBean{
	// 1000   非扫码住订单 , 1001   扫码住订单
	private String orderType;
	private String reason;
	private String extraData;
	private String orderId;
	private String channel;
	private String mRoomTypeId;
	// 新单 N、已审 V、已确认 A、已入住 F、已结账 C、取消 E、NoShow B、删除 D
	private String orderState;
	private String hotelCity;
	private String exchangeRate;
	private int regionType;
	private boolean isMajiaProduct;
	private String distancefromhotel;
	private String day;
	private String invalidatedTicket;
	private String sHotelId;
	private String boroughId;
	private String scheduledTime;
	private String contactName;
	private String ip;
	private String provinceId;
	private List<TravelersItem> travelers;
	private String operationTime;
	private boolean hotelCarnivalPromotion;
	private List<CtripPromotionInfoListItem> ctripPromotionInfoList;
	private int otaType;
	private String phoneCalls;
	private String phone;
	private String realCost;
	private int operationType;
	private int orderFrom;
	private String provinceName;
	private int dataSource;
	private String exchangeCost;
	private double penaltyToMerchant;
	private String starType;
	private String sumPrice;
	private String city;
	private String isGuaranteed;
	private String hotelLat;
	// 是否分销订单
	private boolean nbOrder;
	private String rpId;
	private int orderPaymentType;
	private List<PriceListItem> priceList;
	private boolean privateCard;
	private String sRoomTypeId;
	private String hotelLng;
	private String hotelFrom;
	private String cityName;
	private int appClientType;
	private String priceAllContainCancel;
	private String email;
	private String mHotelId;
	private String amount;
	private MemberInfo memberInfo;
	private String quantity;
	private String star;
	private int businessSystemId;
	private String actualAmount;
	private String operationName;
	private ExperienceMemberInfo experienceMemberInfo;
	private String hotelName;
	private String operationIP;
	private List<PromotioninfoItem> promotioninfo;
	private boolean majiaProduct;
	private String priceAllContain;
	private String currencyCode;
	private String promotion;
	private String deviceId;
	private String createLocationType;
	private String orderLat;
	private String orderLng;
}