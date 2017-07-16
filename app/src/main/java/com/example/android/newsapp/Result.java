package com.example.android.newsapp;

/**
 * {@link Result} represents a single news result
 * It consist of a section name, publication date, title, authors and a url.
 */

public class Result {
    private String mSectionName;
    private String mPublicationDate;
    private String mTitle;
    private String mAuthors;
    private String mUrl;

    public Result(String sectionName, String publicationDate, String title, String authors, String url) {
        mSectionName = sectionName;
        mPublicationDate = publicationDate;
        mTitle = title;
        mAuthors = authors;
        mUrl = url;
    }

    public String getPublicationDate() {
        return mPublicationDate;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getAuthors() {
        return mAuthors;
    }

    public String getUrl() {
        return mUrl;
    }
}
