package pl.training.shop.commons;

import lombok.Value;

import java.util.List;

@Value
public class ResultPage<T> {

    List<T> data;
    int pageNumber;
    int totalPages;

    public <OT> ResultPage<OT> with(List<OT> data) {
        return new ResultPage<>(data, pageNumber, totalPages);
    }

}
