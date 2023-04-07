package com.noxfl.woodchipper.schema.message;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ExtraContent {
    String name;
    Object content;
}
