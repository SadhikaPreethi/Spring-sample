package com.preethi.sample;

import org.springframework.web.bind.annotation.RequestMethod;

public @interface RequestMapping {
    String value();

    RequestMethod method();
}
