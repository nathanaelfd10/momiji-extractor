package com.noxfl.woodchipper.extractor;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ContentParsingGuide {

    private String source;
    private ContentType contentType;
    List<Field> fields;

}
