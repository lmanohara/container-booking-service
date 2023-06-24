package com.shperev.containerbookingservice.repository;

import com.shperev.containerbookingservice.model.BookingsSpec;
import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;

/**
 * The repository for database operations
 */
public interface BookingsRepository extends ReactiveCassandraRepository<BookingsSpec, String> {
}
