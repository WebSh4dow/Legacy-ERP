package com.developer.ERP.Legacy.API.domain.exceptions.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Builder;
import lombok.Getter;
import java.time.OffsetDateTime;
import java.util.List;

@Getter
@Builder
@JsonInclude(Include.NON_NULL)
public class ProblemApi {
    private Integer status;
    private String type;
    private String title;
    private String detail;
    private OffsetDateTime timestamp;
    private String message;
    private List<Object> objects;

    @Getter
    @Builder
    public static class Object {

        private String name;
        private String message;

    }
}
