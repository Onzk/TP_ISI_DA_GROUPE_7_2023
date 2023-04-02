package ucao.isi.G6.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ucao.isi.G6.Entities.Transaction;
import ucao.isi.G6.Entities.Account;
import ucao.isi.G6.Repositories.AccountRepository;
import ucao.isi.G6.Repositories.TransactionRepository;
import ucao.isi.G6.Tools.Response;

import java.util.Hashtable;
import java.util.Map;

@Service
public class TransactionService {
    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    AccountRepository accountRepository;

    public Response transfer(Transaction transaction){
        Map<Object, Object> map = new Hashtable<>();
        try{
            Account from = accountRepository.findByNumber(transaction.getFromNumber());
            if(from == null){
                return Response.error("Account to be debited not found or not specified", transaction);
            }
            Account to = accountRepository.findByNumber(transaction.getToNumber());
            if(to == null){
                return Response.error("Account to be credited not found or not specified", transaction);
            }
            if(from.getAmount() < transaction.getAmount()){
                return Response.ok("Amount of account to be debited, insufficient", transaction);
            }
            from.setAmount(from.getAmount() - transaction.getAmount());
            to.setAmount(to.getAmount() + transaction.getAmount());
            transactionRepository.save(transaction);
            map.put("Account to be debited", from);
            map.put("Account to be credited", to);
            map.put("Transaction", transaction);
            return Response.success("Wire transfer done successfully", map);
        }catch (Exception e){
            return Response.error("Wire transfer failed", e.getMessage(), transaction);
        }
    }
    public Response retreat(Transaction transaction){
        Map<Object, Object> map = new Hashtable<>();
        try{
            Account to = accountRepository.findByNumber(transaction.getToNumber());
            if(to == null){
                return Response.error("Account to be debited not found or not specified", transaction);
            }
            if(to.getAmount() < transaction.getAmount()){
                return Response.error("Amount of account to be debited, insufficient", transaction);
            }
            to.setAmount(to.getAmount() - transaction.getAmount());
            transactionRepository.save(transaction);
            map.put("Account to be debited", to);
            map.put("Transaction", transaction);
            return Response.success("Retreat done successfully", map);
        }catch (Exception e){
            return Response.error("Retreat failed", e.getMessage(), transaction);
        }
    }
    public Response versement(Transaction transaction){
        Map<Object, Object> map = new Hashtable<>();
        try{
            Account to = accountRepository.findByNumber(transaction.getToNumber());
            if(to == null){
                return Response.error("Account to be credited not found or not specified", transaction);
            }
            to.setAmount(to.getAmount() + transaction.getAmount());
            transactionRepository.save(transaction);
            map.put("Account to be credited", to);
            map.put("Transaction", transaction);
            return Response.success("Versement done successfully", map);
        }catch (Exception e){
            return Response.error("Versement failed", e.getMessage(), transaction);
        }
    }
/*
    public Transaction saveTransaction(Transaction transaction){
        return transactionRepository.save(Transaction);
    }

    public List<Transaction> showTransactions(){
        return transactionRepository.findAll();
    }

    public void deleteTransaction(Integer id){
        transactionRepository.deleteById(id);
    }

    public Transaction getOneTransaction(Integer id){
        return transactionRepository.findById(id).orElse(null);
    }

    public Transaction updateTransaction(Integer id, Transaction Transaction){
        Transaction.setId(id);
        return transactionRepository.save(Transaction);
    }

 */
}
