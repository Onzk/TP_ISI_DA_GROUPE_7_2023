package ucao.isi.G6.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ucao.isi.G6.Entities.Transaction;
import ucao.isi.G6.Services.TransactionService;
import ucao.isi.G6.Tools.Response;

@RestController
public class TransactionController {
    @Autowired
    TransactionService transactionService;

    @PostMapping("/transactions/transfer")
    public Response transfer(@RequestBody Transaction transaction){
        try{
            return transactionService.transfer(transaction);
        }catch (Exception e){
            return Response.serverError("An internal error occurred",e.getMessage());
        }
    }
    @PostMapping("/transactions/retreat")
    public Response retreat(@RequestBody Transaction transaction){
        try{
            return transactionService.retreat(transaction);
        }catch (Exception e){
            return Response.serverError("An internal error occurred",e.getMessage());
        }
    }
    @PostMapping("/transactions/versement")
    public Response versement(@RequestBody Transaction transaction){
        try{
            return transactionService.versement(transaction);
        }catch (Exception e){
            return Response.serverError("An internal error occurred",e.getMessage());
        }
    }
}
