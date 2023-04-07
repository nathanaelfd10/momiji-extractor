package com.noxfl.woodchipper.schema.message;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Job {

    private String name;
    private Site site;
    private Category category;
    private String targetUrl;
    private PageType pageType;
    private int minPage;
    private int maxPage;
    private ContentType contentType;
    private String splitPath;
    private boolean scrapeDetail;
    private List<ContentParsingGuide> contentParsingGuides;
    private Content content;

}
