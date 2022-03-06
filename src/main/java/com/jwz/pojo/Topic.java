package com.jwz.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Topic{
    private String title;
    private String titleURL;
    private String modulo;
    private String moduloURL;
    private Site site;
}
