package com.example.android.newsapp;


/**
 * {@link Result} represents a single news result
 * It consist of a type, section name, plblication date, title and a url.
 */

public class Result {

    private String mType;
    private String mSectionName;
    private String mPublicationDate;
    private String mTitle;
    private String mUrl;

    public Result(String type, String sectionName, String publicationDate, String title, String url) {
        mType = type;
        mSectionName = sectionName;
        mPublicationDate = publicationDate;
        mTitle = title;
        mUrl = url;
    }

    public String getType() {
        return mType;
    }

    public String getSectionName() {
        return mSectionName;
    }

    public String getPublicationDate() {
        return mPublicationDate;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getUrl() {
        return mUrl;
    }
}
