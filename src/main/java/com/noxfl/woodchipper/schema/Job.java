package com.noxfl.woodchipper.schema;

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

    private List<ContentParsingGuide> contentParsingGuides;
    private List<Content> contents;

}
