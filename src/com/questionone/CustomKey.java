package com.questionone;

import java.util.Objects;

public class CustomKey {
    Integer first;

    Integer last;

    public CustomKey(String input) {
        this.first = Integer.parseInt(input.substring(0, 1));
        this.last = Integer.parseInt(input.substring(input.length() - 1, input.length()));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomKey customKey = (CustomKey) o;
        return Objects.equals(first, customKey.first) && Objects.equals(last, customKey.last);
    }

    @Override
    public int hashCode() {
        return Objects.hash(first, last);
    }
}
