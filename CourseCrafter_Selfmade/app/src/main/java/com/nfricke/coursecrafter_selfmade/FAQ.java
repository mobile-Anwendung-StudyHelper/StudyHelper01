package com.nfricke.coursecrafter_selfmade;

import java.io.Serializable;

public class FAQ implements Serializable {

    private String label;
    private String title;
    private String subtitle;
    private String Link;
    private String content1;
    private String content2;

    public FAQ(String label, String title, String subtitle,String Link, String content1, String content2)  {
        this.label = label;
        this.title = title;
        this.subtitle = subtitle;
        this.Link = Link;
        this.content1 = content1;
        this.content2 = content2;
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

    public String getLink(){
        return Link;
    }

    public String getContent1() {
        return content1;
    }

    public String getContent2(){
        return content2;
    }
}