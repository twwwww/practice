package utils.entity;


import java.util.List;

/**
 * @program: k2-stock-service
 * @description: 订单信息
 * @author: chengh
 * @create: 2019-07-16 10:06
 */
public class Order {

    // 订单id
    private Long id;

    // 支付时间
    private Long netPayTime;

    // 订单类型
    private Integer orderType;

    // 成单金额
    private Double netPayMoney;

    // 商品列表
    private List<Goods> goodsList;

    public Long getNetPayTime() {
        return netPayTime;
    }

    public void setNetPayTime(Long netPayTime) {
        this.netPayTime = netPayTime;
    }

    public Integer getOrderType() {
        return orderType;
    }

    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    public Double getNetPayMoney() {
        return netPayMoney;
    }

    public void setNetPayMoney(Double netPayMoney) {
        this.netPayMoney = netPayMoney;
    }

    public List<Goods> getGoodsList() {
        return goodsList;
    }

    public void setGoodsList(List<Goods> goodsList) {
        this.goodsList = goodsList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Order{" + "id=" + id + ", netPayTime=" + netPayTime + ", orderType=" + orderType + ", netPayMoney="
                + netPayMoney + ", goodsList=" + goodsList + '}';
    }


}
