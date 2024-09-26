package org.models;

import java.io.File;
import java.io.Serializable;

public class Content implements Serializable
{
    public File f;
    public long marshall_time;

    public Content(File f, long marshall_time) {
        this.f = f;
        this.marshall_time = marshall_time;
    }

    public File getF() {
        return f;
    }

    public void setF(File f) {
        this.f = f;
    }

    public long getMarshall_time() {
        return marshall_time;
    }

    public void setMarshall_time(long marshall_time) {
        this.marshall_time = marshall_time;
    }

    @Override
    public String toString() {
        return "Content{" +
                "f=" + f +
                ", marshall_time=" + marshall_time +
                '}';
    }
}
