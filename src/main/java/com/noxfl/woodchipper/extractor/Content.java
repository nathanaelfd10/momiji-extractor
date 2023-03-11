package com.noxfl.woodchipper.extractor;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Content {

    private String name;
    private ContentType contentType;
    private String content;

}
