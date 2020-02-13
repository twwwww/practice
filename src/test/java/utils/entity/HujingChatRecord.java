package utils.entity;

import java.util.Date;

public class HujingChatRecord {

    private String id;
    private String wechat;
    private String user_wechat;
    private Date chat_time;
    private Integer send_type;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWechat() {
        return wechat;
    }

    public void setWechat(String wechat) {
        this.wechat = wechat;
    }

    public String getUser_wechat() {
        return user_wechat;
    }

    public void setUser_wechat(String user_wechat) {
        this.user_wechat = user_wechat;
    }

    public Date getChat_time() {
        return chat_time;
    }

    public void setChat_time(Date chat_time) {
        this.chat_time = chat_time;
    }

    public Integer getSend_type() {
        return send_type;
    }

    public void setSend_type(Integer send_type) {
        this.send_type = send_type;
    }
}
