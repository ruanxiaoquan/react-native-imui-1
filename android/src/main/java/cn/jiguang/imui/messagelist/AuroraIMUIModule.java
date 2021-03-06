package cn.jiguang.imui.messagelist;


import android.content.Intent;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.uimanager.UIImplementation;
import com.facebook.react.uimanager.UIManagerModule;

import cn.jiguang.imui.messagelist.module.RCTMessage;

import static cn.jiguang.imui.messagelist.MessageUtil.configMessage;

/**
 * Created by caiyaoguan on 2017/6/2.
 */

public class AuroraIMUIModule extends ReactContextBaseJavaModule {


    private final String REACT_MSG_LIST_MODULE = "AuroraIMUIModule";

    public AuroraIMUIModule(ReactApplicationContext reactContext) {
        super(reactContext);
    }

    @Override
    public String getName() {
        return REACT_MSG_LIST_MODULE;
    }

    @Override
    public void initialize() {
        super.initialize();
        UIImplementation t;
        UIManagerModule a;
    }

    @ReactMethod
    public void appendMessages(ReadableArray messages) {
        String[] rctMessages = new String[messages.size()];
        for (int i = 0; i < messages.size(); i++) {
            RCTMessage rctMessage = configMessage(messages.getMap(i));
            rctMessages[i] = rctMessage.toString();
        }
        Intent intent = new Intent();
        intent.setAction(ReactMsgListManager.RCT_APPEND_MESSAGES_ACTION);
        intent.putExtra("messages", rctMessages);
        getReactApplicationContext().sendBroadcast(intent);
    }

    @ReactMethod
    public void updateMessage(ReadableMap message) {
        RCTMessage rctMessage = configMessage(message);
        Intent intent = new Intent();
        intent.setAction(ReactMsgListManager.RCT_UPDATE_MESSAGE_ACTION);
        intent.putExtra("message", rctMessage.toString());
        getReactApplicationContext().sendBroadcast(intent);
    }

    @ReactMethod
    public void insertMessagesToTop(ReadableArray messages) {
        String[] rctMessages = new String[messages.size()];
        for (int i = 0; i < messages.size(); i++) {
            RCTMessage rctMessage = configMessage(messages.getMap(i));
            rctMessages[i] = rctMessage.toString();
        }
        Intent intent = new Intent();
        intent.setAction(ReactMsgListManager.RCT_INSERT_MESSAGES_ACTION);
        intent.putExtra("messages", rctMessages);
        getReactApplicationContext().sendBroadcast(intent);
    }

    @ReactMethod
    public void insertMessage(ReadableMap message) {
    }

    @ReactMethod
    public void deleteMessage(ReadableMap message) {
    }



    @ReactMethod
    public void scrollToBottom() {
        Intent intent = new Intent();
        intent.setAction(ReactMsgListManager.RCT_SCROLL_TO_BOTTOM_ACTION);
        getReactApplicationContext().sendBroadcast(intent);
    }


}
