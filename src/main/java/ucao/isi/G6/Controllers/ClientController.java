package ucao.isi.G6.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ucao.isi.G6.Entities.Client;
import ucao.isi.G6.Services.ClientService;
import ucao.isi.G6.Tools.Response;

@RestController
public class ClientController {
    @Autowired
    ClientService clientService;

    @PostMapping("/clients")
    public Response saveClient(@RequestBody Client client){
        try{
            return clientService.saveClient(client);
        }catch (Exception e){
            return  Response.serverError("An internal error occurred", e.getMessage());
        }
    }

    @GetMapping("/clients")
    public Response showClients(){
        try{
            return clientService.showClients();
        }catch (Exception e){
            return Response.serverError("An internal error occurred",e.getMessage());
        }
    }

    @DeleteMapping("/clients/{id}")
    public Response deleteClient(@PathVariable Integer id){
        try{
            return  clientService.deleteClient(id);
        }catch (Exception e){
            return Response.serverError("An internal error occurred",e.getMessage());
        }
    }

    @GetMapping("/clients/{id}")
    public Response getOneClient(@PathVariable Integer id){
        try{
            return  clientService.getOneClient(id);
        }catch (Exception e){
            return Response.serverError("An internal error occurred",e.getMessage());
        }
    }

    @PutMapping("/clients/{id}")
    public Response updateClient(@PathVariable Integer id, @RequestBody Client client){
        try{
            return clientService.updateClient(client, id);
        }catch (Exception e){
            return Response.serverError("An internal error occurred",e.getMessage());
        }
    }
}