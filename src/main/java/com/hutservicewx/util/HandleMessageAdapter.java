package com.hutservicewx.util;

import com.hutservicewx.msg.*;

/**
 * 处理消息适配器(适配器模式)
 *
 * @author lid
 * @date 2017.2.22
 */

public class HandleMessageAdapter implements HandleMessageListener {


    @Override
    public void onTextMsg(TextMsg msg) {

    }

    @Override
    public void onImageMsg(ImageMsg msg) {

    }

    @Override
    public void onImageTextMsg(ImageTextMsg msg) {

    }

    @Override
    public void onEventMsg(EventMsg msg) {

    }

    @Override
    public void onLinkMsg(LinkMsg msg) {

    }

    @Override
    public void onLocationMsg(LocationMsg msg) {

    }

    @Override
    public void onVoiceMsg(VoiceMsg msg) {

    }

    @Override
    public void onErrorMsg(int errorCode) {

    }

    @Override
    public void onVideoMsg(VideoMsg msg) {

    }
}
