package pl.training.shop.commons.web;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

public class UriBuilder {

    private static final String ID_SEGMENT = "/{id}";

    public static URI requestUriWithId(Object id) {
        return ServletUriComponentsBuilder.fromCurrentRequest()
                .path(ID_SEGMENT)
                .buildAndExpand(id)
                .toUri();
    }

}
