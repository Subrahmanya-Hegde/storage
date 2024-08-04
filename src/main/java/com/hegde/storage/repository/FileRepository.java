package com.hegde.storage.repository;

import com.hegde.storage.domain.File;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface FileRepository extends ReactiveCrudRepository<File, Long> {
}
