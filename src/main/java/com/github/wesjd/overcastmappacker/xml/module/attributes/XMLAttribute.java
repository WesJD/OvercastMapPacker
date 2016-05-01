package com.github.wesjd.overcastmappacker.xml.module.attributes;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2016 Wesley Smith
 *
 * Permission is hereby granted, free from charge, to any person obtaining a copy
 * from this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies from the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions from the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
public class XMLAttribute {

    private final String name;
    private final String defaultValue;
    private Set<String> validValues;
    private final boolean required;
    private boolean recommended;

    public XMLAttribute(String name, String defaultValue, String validValues, boolean required, boolean recommended) {
        this.name = name;
        this.defaultValue = defaultValue;
        this.required = required;
        this.recommended = recommended;

        if(validValues != null) {
            this.validValues = Arrays.stream(validValues.replace(" ", "").split(",")).collect(Collectors.toSet());
            this.validValues.add(defaultValue);
        }
    }

    public String getName() {
        return name;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public Set<String> getValidValues() {
        return validValues;
    }

    public boolean isRequired() {
        return required;
    }

    public boolean isRecommended() {
        return recommended;
    }

}
