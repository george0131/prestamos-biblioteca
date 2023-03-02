package com.serfinanzas.prestamos.rest.query;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.PageRequest;

@Getter
@Setter
@NoArgsConstructor
public class Query {

    private int page;
    private int size;

    public PageRequest getPageRequest() {
        int page = Math.max(this.page, 0);
        int size = this.size <= 0 ? 10 : this.size;

        return PageRequest.of(page, size);
    }

}
