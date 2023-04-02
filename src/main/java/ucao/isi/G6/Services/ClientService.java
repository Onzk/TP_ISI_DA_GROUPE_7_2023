package ucao.isi.G6.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ucao.isi.G6.Entities.Account;
import ucao.isi.G6.Entities.Client;
import ucao.isi.G6.Repositories.AccountRepository;
import ucao.isi.G6.Repositories.ClientRepository;
import ucao.isi.G6.Tools.Response;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service

public class ClientService {

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    AccountRepository accountRepository;

    public Response saveClient(Client client){
        try{
            client.setGender(client.getGender().toUpperCase());
            if(!client.getGender().equals("F") && !client.getGender().equals("M")){
                return Response.error("Invalid client gender.Accepted are : M| F", client);

            }
            return Response.success("Client created successfully", clientRepository.save(client));
        }catch (Exception e){
            return Response.error("Client creation failed", e.getMessage(), client);
        }
    }

    public Response showClients(){
        try {
            return Response.ok("Clients list retrieved", clientRepository.findAll());
        }catch (Exception e){
            return Response.error("Clients list retrieve failed", e.getMessage(), null);
        }
    }

    public Response deleteClient(Integer id){
        try{
            if(clientRepository.findById(id).orElse(null) == null){
                return Response.error("Client not found", id);
            }
            for(Account account : accountRepository.findAll()){
                if(Objects.equals(account.getClientId(), id)){
                    accountRepository.delete(account);
                }
            }
            clientRepository.deleteById(id);
            return Response.ok("Client deleted successfully", id);
        }catch (Exception e){
            return Response.error("Client deletion failed : ", e.getMessage(), id);
        }
    }

    public Response getOneClient(Integer id){
        try{
            Object client = clientRepository.findById(id).orElse(null);
            return client == null
                    ? Response.error("Client not found", null)

                    : Response.ok("Client found", client);
        }catch (Exception e){
            return Response.error("Client retrieve failed", e.getMessage(), id);
        }
    }


    public Response updateClient(Client client, Integer id){
        Map<Object, Object> map = new HashMap<>();
        try{
            map.put("Client", client);
            map.put("id", id);
            if(clientRepository.findById(id).orElse(null) == null){
                return Response.error("Client not found", map);
            }

            client.setId(id);
            client.setGender(client.getGender().toUpperCase());
            if(!client.getGender().equals("F") && !client.getGender().equals("M")){
                return Response.error("Invalid client gender.Accepted are : M | F", map);
            }
            return Response.success("Client modified successfully", clientRepository.save(client));
        }catch (Exception e){
            return Response.error("Client update failed", e.getMessage(), map);
        }
    }
}
