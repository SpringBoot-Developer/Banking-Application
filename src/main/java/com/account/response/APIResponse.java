package com.account.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class APIResponse {

    private String message;

}
