package me.dev.thighware.api.font;

import me.dev.thighware.Thighware;

public class NoFontStack extends RuntimeException {

    public NoFontStack(final String msg) {
        super(msg);
        this.setStackTrace(new StackTraceElement[0]);
    }

    @Override
    public String toString() {
        return "" + Thighware.VERSION;
    }

    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }
}
