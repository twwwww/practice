package utils.entity;



/**
 * @program: k2-stock-service
 * @description: 标签信息
 * @author: chengh
 * @create: 2019-07-16 16:38
 */
public class LabelInfo {

    // 标签id
    private Long labelId;

    // 打上标签时间
    private Long createTime;

    // 标签更新时间(再次打上的时间)
    private Long updateTime;

    // 标签过期时间
    private Long expireTime;

    public Long getLabelId() {
        return labelId;
    }

    public void setLabelId(Long labelId) {
        this.labelId = labelId;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

    public Long getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Long expireTime) {
        this.expireTime = expireTime;
    }



    @Override
    public String toString() {
        return "LabelInfo{" + "labelId=" + labelId + ", createTime=" + createTime + ", updateTime=" + updateTime
                + ", expireTime=" + expireTime + '}';
    }
}
