package io.codelex.flightplanner.common.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class PageResult<T> {
    private long page;
    private long totalItems;
    private List<T> items;

    public static <T> PageResult<T> fromPage(Page<T> source) {
        return PageResult.<T>builder()
                .page(source.getNumber())
                .totalItems(source.getTotalElements())
                .items(source.getContent())
                .build();
    }
}
