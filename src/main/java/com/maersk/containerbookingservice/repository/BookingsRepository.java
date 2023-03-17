package com.maersk.containerbookingservice.repository;

import com.maersk.containerbookingservice.model.BookingsSpec;
import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;

/**
 * The repository for database operations
 */
public interface BookingsRepository extends ReactiveCassandraRepository<BookingsSpec, String> {
}
