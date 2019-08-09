package utils.entity;

import java.util.List;




public class StockChance {
    // 机会id
    private Long id;
    // 类别
    private Long categoryId;
    // 父类别
    private Long parentCategoryId;
    // 电话归属地
    private String mobileProvince;
    // 登陆总次数
    private Integer loginTotalAmount;
    // 上次登陆时间
    private Long lastLoginTime;
    // 创建时间
    private Long createTime;
    // 咨询状态
    private String advisoryStatus;
    // 备注
    private String remark;
    // 机会来源
    private String chanceSource;
    // 订单总数
    private Integer totalOrderAmount;
    // 订单总金额
    private Double totalOrderMoney;
    // 最后成单时间
    private Long lastOrderTime;
    // 最后购买商品项目
    private Long lastOrderCategoryId;
    // 最后商品类别来源 如续报中心
    private Integer lastOrderCateType;
    // 最后商品类型
    private Integer lastOrderGoodType;
    // 最后订单记录类型
    private Integer lastOrderType;
    // 最后购买商品金额
    private Double lastOrderMoney;
    // 上次沟通时间
    private Long lastCommunicationTime;
    // abc机会分类
    private String level;
    // 电话
    private String mobile;
    // 上次拨打时间
    private Long lastCallTime;
    // 拨打次数
    private Integer callTotalAmount;
    // 用户id
    private Long customerId;
    // 推广方式
    private Long spreadWay;
    // 推广平台
    private Long spreadPlat;
    // 最后分配记录所在组
    private Long lastInGroup;
    // 最后分配记录所在组所属项目 销售类别来源
    private Integer lastInGroupCateType;
    // 用户类型
    private Integer customerGroup;
    // 产生方式 1，注册；2，留言；3，在线；4，后台创建；0，其他
    private Integer createType;
    // 机会流转次数
    private Integer transferAmount;

    private List<Long> detailConditionIds;

    private String flowSource;

    // 最后认领时间
    private Long lastAssignTime;

    // 最后回库时间
    private Long lastBackStockTime;

    // 最后更新时间
    private Long lastUpdateTime;

    // 最后成单销售类别来源
    private Integer lastOrderUserCateType;

    // 订单列表
    private List<Order> orderList;

    // 标签列表
    private List<LabelInfo> labels;

    public List<LabelInfo> getLabels() {
        return labels;
    }

    public void setLabels(List<LabelInfo> labels) {
        this.labels = labels;
    }

    public Integer getLastOrderUserCateType() {
        return lastOrderUserCateType;
    }

    public void setLastOrderUserCateType(Integer lastOrderUserCateType) {
        this.lastOrderUserCateType = lastOrderUserCateType;
    }

    public Long getLastAssignTime() {
        return lastAssignTime;
    }

    public void setLastAssignTime(Long lastAssignTime) {
        this.lastAssignTime = lastAssignTime;
    }

    public Long getLastBackStockTime() {
        return lastBackStockTime;
    }

    public void setLastBackStockTime(Long lastBackStockTime) {
        this.lastBackStockTime = lastBackStockTime;
    }

    public Long getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Long lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public List<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
    }

    public String getFlowSource() {
        return flowSource;
    }

    public void setFlowSource(String flowSource) {
        this.flowSource = flowSource;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Long getParentCategoryId() {
        return parentCategoryId;
    }

    public void setParentCategoryId(Long parentCategoryId) {
        this.parentCategoryId = parentCategoryId;
    }

    public String getMobileProvince() {
        return mobileProvince;
    }

    public void setMobileProvince(String mobileProvince) {
        this.mobileProvince = mobileProvince;
    }

    public Integer getLoginTotalAmount() {
        return loginTotalAmount;
    }

    public void setLoginTotalAmount(Integer loginTotalAmount) {
        this.loginTotalAmount = loginTotalAmount;
    }

    public Long getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Long lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public String getAdvisoryStatus() {
        return advisoryStatus;
    }

    public void setAdvisoryStatus(String advisoryStatus) {
        this.advisoryStatus = advisoryStatus;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getChanceSource() {
        return chanceSource;
    }

    public void setChanceSource(String chanceSource) {
        this.chanceSource = chanceSource;
    }

    public Integer getTotalOrderAmount() {
        return totalOrderAmount;
    }

    public void setTotalOrderAmount(Integer totalOrderAmount) {
        this.totalOrderAmount = totalOrderAmount;
    }

    public Double getTotalOrderMoney() {
        return totalOrderMoney;
    }

    public void setTotalOrderMoney(Double totalOrderMoney) {
        this.totalOrderMoney = totalOrderMoney;
    }

    public Long getLastOrderTime() {
        return lastOrderTime;
    }

    public void setLastOrderTime(Long lastOrderTime) {
        this.lastOrderTime = lastOrderTime;
    }

    public Long getLastOrderCategoryId() {
        return lastOrderCategoryId;
    }

    public void setLastOrderCategoryId(Long lastOrderCategoryId) {
        this.lastOrderCategoryId = lastOrderCategoryId;
    }

    public Integer getLastOrderCateType() {
        return lastOrderCateType;
    }

    public void setLastOrderCateType(Integer lastOrderCateType) {
        this.lastOrderCateType = lastOrderCateType;
    }

    public Integer getLastOrderGoodType() {
        return lastOrderGoodType;
    }

    public void setLastOrderGoodType(Integer lastOrderGoodType) {
        this.lastOrderGoodType = lastOrderGoodType;
    }

    public Integer getLastOrderType() {
        return lastOrderType;
    }

    public void setLastOrderType(Integer lastOrderType) {
        this.lastOrderType = lastOrderType;
    }

    public Double getLastOrderMoney() {
        return lastOrderMoney;
    }

    public void setLastOrderMoney(Double lastOrderMoney) {
        this.lastOrderMoney = lastOrderMoney;
    }

    public Long getLastCommunicationTime() {
        return lastCommunicationTime;
    }

    public void setLastCommunicationTime(Long lastCommunicationTime) {
        this.lastCommunicationTime = lastCommunicationTime;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Long getLastCallTime() {
        return lastCallTime;
    }

    public void setLastCallTime(Long lastCallTime) {
        this.lastCallTime = lastCallTime;
    }

    public Integer getCallTotalAmount() {
        return callTotalAmount;
    }

    public void setCallTotalAmount(Integer callTotalAmount) {
        this.callTotalAmount = callTotalAmount;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getSpreadWay() {
        return spreadWay;
    }

    public void setSpreadWay(Long spreadWay) {
        this.spreadWay = spreadWay;
    }

    public Long getSpreadPlat() {
        return spreadPlat;
    }

    public void setSpreadPlat(Long spreadPlat) {
        this.spreadPlat = spreadPlat;
    }

    public Long getLastInGroup() {
        return lastInGroup;
    }

    public void setLastInGroup(Long lastInGroup) {
        this.lastInGroup = lastInGroup;
    }

    public Integer getLastInGroupCateType() {
        return lastInGroupCateType;
    }

    public void setLastInGroupCateType(Integer lastInGroupCateType) {
        this.lastInGroupCateType = lastInGroupCateType;
    }

    public Integer getCustomerGroup() {
        return customerGroup;
    }

    public void setCustomerGroup(Integer customerGroup) {
        this.customerGroup = customerGroup;
    }

    public Integer getCreateType() {
        return createType;
    }

    public void setCreateType(Integer createType) {
        this.createType = createType;
    }

    public Integer getTransferAmount() {
        return transferAmount;
    }

    public void setTransferAmount(Integer transferAmount) {
        this.transferAmount = transferAmount;
    }

    public List<Long> getDetailConditionIds() {
        return detailConditionIds;
    }

    public void setDetailConditionIds(List<Long> detailConditionIds) {
        this.detailConditionIds = detailConditionIds;
    }

    @Override
    public String toString() {
        return "StockChance{" + "id=" + id + ", categoryId=" + categoryId + ", parentCategoryId=" + parentCategoryId
                + ", mobileProvince='" + mobileProvince + '\'' + ", loginTotalAmount=" + loginTotalAmount
                + ", lastLoginTime=" + lastLoginTime + ", createTime=" + createTime + ", advisoryStatus='"
                + advisoryStatus + '\'' + ", remark='" + remark + '\'' + ", chanceSource='" + chanceSource + '\''
                + ", totalOrderAmount=" + totalOrderAmount + ", totalOrderMoney=" + totalOrderMoney + ", lastOrderTime="
                + lastOrderTime + ", lastOrderCategoryId=" + lastOrderCategoryId + ", lastOrderCateType="
                + lastOrderCateType + ", lastOrderGoodType=" + lastOrderGoodType + ", lastOrderType=" + lastOrderType
                + ", lastOrderMoney=" + lastOrderMoney + ", lastCommunicationTime=" + lastCommunicationTime
                + ", level='" + level + '\'' + ", mobile='" + mobile + '\'' + ", lastCallTime=" + lastCallTime
                + ", callTotalAmount=" + callTotalAmount + ", customerId=" + customerId + ", spreadWay=" + spreadWay
                + ", spreadPlat=" + spreadPlat + ", lastInGroup=" + lastInGroup + ", lastInGroupCateType="
                + lastInGroupCateType + ", customerGroup=" + customerGroup + ", createType=" + createType
                + ", transferAmount=" + transferAmount + ", detailConditionIds=" + detailConditionIds + ", flowSource='"
                + flowSource + '\'' + ", lastAssignTime=" + lastAssignTime + ", lastBackStockTime=" + lastBackStockTime
                + ", lastUpdateTime=" + lastUpdateTime + ", lastOrderUserCateType=" + lastOrderUserCateType
                + ", orderList=" + orderList + ", labels=" + labels + '}';
    }
}
