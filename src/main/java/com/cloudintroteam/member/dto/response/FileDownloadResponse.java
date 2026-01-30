package com.cloudintroteam.member.dto.response;

import lombok.Getter;

@Getter
public class FileDownloadResponse {
    private final String url;

    public FileDownloadResponse(String url) {
        this.url = url;
    }
}
