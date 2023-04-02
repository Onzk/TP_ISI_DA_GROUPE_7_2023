package ucao.isi.G6.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ucao.isi.G6.Entities.Account;
public interface AccountRepository extends JpaRepository<Account, Integer> {
    @Query("select u from Account u where u.number=?1")
    Account findByNumber(String number);

    @Query("delete from Account u where u.number =?1")
    Account deleteByNmuber(String number);
}