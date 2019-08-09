package utils.entity;


/**
 * @program: k2-stock-service
 * @description: 商品
 * @author: chengh
 * @create: 2019-07-16 10:09
 */
public class Goods {

    // 商品主键id
    private Long id;

    // 商品名称
    private String goodsName;

    // 商品类别
    private Long categoryId;

    // 商品属性
    private Integer goodsAttribute;

    // 商品子类别
    private Long categoryChildId;

    // 商品类别来源
    private Integer cateType;

    //商品销售渠道
    private Integer goodsSaleType;

    // 商品标签
    private Integer goodsLabel;

    public Integer getGoodsSaleType() {
        return goodsSaleType;
    }

    public void setGoodsSaleType(Integer goodsSaleType) {
        this.goodsSaleType = goodsSaleType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getGoodsAttribute() {
        return goodsAttribute;
    }

    public void setGoodsAttribute(Integer goodsAttribute) {
        this.goodsAttribute = goodsAttribute;
    }

    public Long getCategoryChildId() {
        return categoryChildId;
    }

    public void setCategoryChildId(Long categoryChildId) {
        this.categoryChildId = categoryChildId;
    }

    public Integer getCateType() {
        return cateType;
    }

    public void setCateType(Integer cateType) {
        this.cateType = cateType;
    }

    public Integer getGoodsLabel() {
        return goodsLabel;
    }

    public void setGoodsLabel(Integer goodsLabel) {
        this.goodsLabel = goodsLabel;
    }

    @Override
    public String toString() {
        return "Goods{" + "id=" + id + ", goodsName='" + goodsName + '\'' + ", categoryId=" + categoryId
                + ", goodsAttribute=" + goodsAttribute + ", categoryChildId=" + categoryChildId + ", cateType="
                + cateType + ", goodsLabel=" + goodsLabel + '}';
    }


}
