package com.hutservicewx.msg;

import org.w3c.dom.Document;

/**
 * 地理位置消息
 *
 * @author lid
 * @date 2017.2.22
 */
public class LocationMsg extends Msg {

    // 地理位置纬度
    private String location_X;
    // 地理位置经度
    private String location_Y;
    // 地图缩放大小
    private String scale;
    // 地理位置信息
    private String label;
    //消息id，64位整型
    private String msgId;

    /**
     * 开发者调用
     */
    public LocationMsg() {
        this.head = new MsgHead();
        this.head.setMsgType(Msg.MSG_TYPE_LOCATION);
    }

    /**
     * 程序内部调用
     */
    public LocationMsg(MsgHead head) {
        this.head = head;
    }

    @Override
    public void write(Document document) {
    }

    @Override
    public void read(Document document) {
        this.location_X = document.getElementsByTagName(WXXmlElementName.LOCATION_X).item(0).getTextContent();
        this.location_Y = document.getElementsByTagName(WXXmlElementName.LOCATION_Y).item(0).getTextContent();
        this.scale = document.getElementsByTagName(WXXmlElementName.SCALE).item(0).getTextContent();
        this.label = document.getElementsByTagName(WXXmlElementName.LABEL).item(0).getTextContent();
        this.msgId = document.getElementsByTagName(WXXmlElementName.MSG_ID).item(0).getTextContent();
    }

    public String getLocation_X() {
        return location_X;
    }

    public void setLocation_X(String location_X) {
        this.location_X = location_X;
    }

    public String getLocation_Y() {
        return location_Y;
    }

    public void setLocation_Y(String location_Y) {
        this.location_Y = location_Y;
    }

    public String getScale() {
        return scale;
    }

    public void setScale(String scale) {
        this.scale = scale;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }
}
