package com.noxfl.woodchipper.schema;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ContentParsingGuide {

    private String source;
    private FormatType contentType;
    private List<Field> fields;

}
