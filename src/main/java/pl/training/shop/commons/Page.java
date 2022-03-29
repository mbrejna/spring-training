package pl.training.shop.commons;

import lombok.Value;

@Value
public class Page {

    int size;
    int number;

    public int getOffset() {
        return number * size;
    }

}
