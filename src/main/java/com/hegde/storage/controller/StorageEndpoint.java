package com.hegde.storage.controller;


import com.hegde.storage.api.DefaultApiDelegate;
import com.hegde.storage.model.FileUploadResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.http.codec.multipart.Part;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Controller for storage APIs.
 */
@Slf4j
@Service
public class StorageEndpoint implements DefaultApiDelegate {

    /**
     * Endpoint for uploading and storing the file metadata in db.
     *
     * @param userId   (required)
     * @param file     (optional)
     * @param exchange
     * @return a file metadata.
     */
    @Override
    public Mono<ResponseEntity<Flux<FileUploadResponse>>> uploadDocument(String userId,
                                                                         Flux<Part> file,
                                                                         ServerWebExchange exchange) {
        return file.flatMap(part -> {
            if (part instanceof FilePart) {
                return processFile((FilePart) part);
            } else {
                return Flux.empty();
            }
        }).collectList().map(responseList -> ResponseEntity.ok().body(Flux.fromIterable(responseList)));
    }

    private Flux<FileUploadResponse> processFile(FilePart filePart) {
        FileUploadResponse response = new FileUploadResponse();
        response.fileName(filePart.filename());
        return Flux.just(response);
    }
}
