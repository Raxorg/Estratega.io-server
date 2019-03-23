package com.frontanilla.estrategaioserver;

import android.util.Log;
import com.frontanilla.estrategaioserver.interfacing.DebugLoggerInterface;

public class DebugLogger implements DebugLoggerInterface {

    @Override
    public void debugInfo(String tag, String message) {
        Log.i(tag, message);
    }
}
