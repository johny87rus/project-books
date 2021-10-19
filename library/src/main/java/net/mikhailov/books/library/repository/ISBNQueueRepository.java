package net.mikhailov.books.library.repository;

import net.mikhailov.books.library.model.ISBNQueue;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Mikhailov Evgenii
 */
public interface ISBNQueueRepository extends CrudRepository<ISBNQueue, Integer> {
}
