package com.nfricke.coursecrafter_selfmade;

public class FAQ {

    private String label;
    private String title;
    private String subtitle;
    private String Link;

    public FAQ(String label, String title, String subtitle,String Link) {
        this.label = label;
        this.title = title;
        this.subtitle = subtitle;
        this.Link = Link;
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
}