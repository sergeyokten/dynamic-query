package com.example.dynamicquery.models;


import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CustomCondition {

    String value;
    String field;
    String comparison;


}
