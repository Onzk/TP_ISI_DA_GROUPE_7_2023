package ucao.isi.G6.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ucao.isi.G6.Entities.Account;
import ucao.isi.G6.Repositories.ClientRepository;
import ucao.isi.G6.Repositories.AccountRepository;
import ucao.isi.G6.Tools.Response;
import ucao.isi.G6.Tools.Tools;

import java.util.Hashtable;
import java.util.Map;

@Service
public class AccountService {
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    ClientRepository clientRepository;

    public Response saveAccount(Account account){
        try{
            account.setType(account.getType().toUpperCase());
            if(clientRepository.findById(account.getClientId()).orElse(null) == null){
                return Response.error("Client not found or not specified", account);
            }
            if(!account.getType().equals("COURANT") && !account.getType().equals("EPARGNE")){
                return Response.error("Invalid account type. Accepted are : COURANT | EPARGNE", account);
            }
            account.setNumber(Tools.randomString() + account.getCreationDate().getYear());
            return Response.success("Account created successfully", accountRepository.save(account));
        }catch(Exception e){
            return Response.error("Account creation failed", e.getMessage(), account);
        }
    }

    public Response showAccounts(){
        try{
            return Response.ok("Accounts list retrieved", accountRepository.findAll());
        }catch(Exception e){
            return Response.error("Accounts list retrieve failed", e.getMessage(), null);
        }
    }

    public Response deleteAccount(String number){
        try{
            Account account = accountRepository.findByNumber(number);
            if(account == null){
                return Response.error("Account not found or not specified", number);
            }
            accountRepository.deleteById(account.getId());
            return Response.ok("Account deleted successfully", number);
        }catch(Exception e){
            return Response.error("Account deletion failed", e.getMessage(), number);
        }
    }

    public Response getOneAccount(String number){
        try{
            Object compte = accountRepository.findByNumber(number);
            return compte == null
                    ? Response.error("Account not found or not specified", number)
                    : Response.ok("Account found", compte);
        }catch(Exception e){
            return Response.error("Account retrieve failed", e.getMessage(), number);
        }
    }

    public Response updateAccount(Account account, String number){
        Map<Object, Object> map = new Hashtable<>();
        try{
            map.put("number", number);
            map.put("account", account);
            if(accountRepository.findByNumber(number) == null){
                return Response.error("Account not found", map);
            }
            account.setType(account.getType().toUpperCase());
            if(clientRepository.findById(account.getClientId()).orElse(null) == null){
                return Response.error("Client not found or not specified", account);
            }
            if(!account.getType().equals("COURANT") && !account.getType().equals("EPARGNE")){
                return Response.error("Invalid account type. Accepted are : COURANT | EPARGNE", map);
            }
            return Response.success("Account modified successfully", accountRepository.save(account));
        }catch(Exception e){
            return Response.error("Account update failed", e.getMessage(), map);
        }
    }
}
