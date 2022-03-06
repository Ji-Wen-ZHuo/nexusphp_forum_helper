package com.jwz.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Site {
    private String name;
    private String url;
    private String cookie;
}
