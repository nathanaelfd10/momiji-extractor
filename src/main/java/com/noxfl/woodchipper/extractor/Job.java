package com.noxfl.woodchipper.extractor;

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

    List<ContentParsingGuide> contentParsingGuides;
    List<Content> contents;

}
