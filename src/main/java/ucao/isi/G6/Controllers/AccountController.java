package ucao.isi.G6.Controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ucao.isi.G6.Entities.Account;
import ucao.isi.G6.Services.AccountService;
import ucao.isi.G6.Tools.Response;


@RestController
public class AccountController {
    @Autowired
    AccountService accountService;

    @PostMapping("/accounts")
    public Response saveCompte(@RequestBody Account account){
        try{
            return accountService.saveAccount(account);
        }catch (Exception e){
            return Response.serverError("An internal error occurred",e.getMessage());
        }
    }

    @GetMapping("/accounts")
    public Response showComptes(){
        try{
            return accountService.showAccounts();
        }catch (Exception e){
            return Response.serverError("An internal error occurred",e.getMessage());
        }
    }

    @DeleteMapping("/accounts/{number}")
    public Response deleteCompte(@PathVariable String number){
        try{
            return accountService.deleteAccount(number);
        }catch (Exception e){
            return Response.serverError("An internal error occurred",e.getMessage());
        }
    }

    @GetMapping("/accounts/{number}")
    public Response getOneCompte(@PathVariable String number){
        try{
            return accountService.getOneAccount(number);
        }catch (Exception e){
            return Response.serverError("An internal error occurred",e.getMessage());
        }
    }

    @PutMapping("/accounts/{number}")
    public Response updateCompte(@PathVariable String number, @RequestBody Account account){
        try{
            return accountService.updateAccount(account, number);
        }catch (Exception e){
            return Response.serverError("An internal error occurred",e.getMessage());
        }
    }
}

