package com.nfricke.coursecrafter_selfmade;

import java.io.Serializable;

public class FAQ implements Serializable {

    private String label;
    private String title;
    private String subtitle;
    private String content1;

    public FAQ(String label, String title, String subtitle, String content1)  {
        this.label = label;
        this.title = title;
        this.subtitle = subtitle;
        this.content1 = content1;
    }

    public String getLabel() {
        return label;
    }

    public String getTitle() {
        return title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public String getContent1() {
        return content1;
    }

}