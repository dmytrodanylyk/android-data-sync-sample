package com.todo.sync.event;

import android.support.annotation.NonNull;
import org.greenrobot.eventbus.EventBus;

public class EventBusManager {

    public static void send(@NonNull Object o) {
        EventBus.getDefault().post(o);
    }

    public static void register(@NonNull Object subscriber) {
        EventBus.getDefault().register(subscriber);
    }

    public static void unregister(@NonNull Object subscriber) {
        EventBus.getDefault().unregister(subscriber);
    }
}
