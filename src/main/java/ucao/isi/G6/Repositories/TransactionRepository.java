package ucao.isi.G6.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ucao.isi.G6.Entities.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

}
