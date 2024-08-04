package com.hegde.storage.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.http.codec.multipart.Part;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * Controller for storage APIs.
 */
@Slf4j
@RequestMapping("v0/test")
@RestController
public class TestController {

    /**
     * Endpoint for uploading and storing the file metadata in db.
     *
     * @param userId   (required)
     * @param file     (optional)
     * @param exchange
     * @return a file metadata.
     */
    @PostMapping
    public Mono<ResponseEntity<Object>> uploadDocument(@RequestHeader("X-USER-ID") String X_USER_ID,
                                                       @RequestPart("file") Mono<Part> filePartMono,
                                                       ServerWebExchange exchange) {
        return filePartMono.flatMap(filePart -> {
            String fileName = filePart.name();
            // Log the filename
            System.out.println("Uploaded filename: " + fileName);

            // Process the file
            // ...

            // Return response
            return Mono.just(ResponseEntity.ok().build());
        }).switchIfEmpty(Mono.just(ResponseEntity.badRequest().build()));
    }

}
