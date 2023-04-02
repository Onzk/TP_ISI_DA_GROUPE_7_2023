package ucao.isi.G6.Tools;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

public class Tools {
    public static String randomString(){
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        int length = 5;
        for(int i = 0; i < length; i++) {
            int index = random.nextInt(alphabet.length());
            char randomChar = alphabet.charAt(index);
            sb.append(randomChar);
        }
        return sb.toString();
    }
    public static Map<Object, Object> getMap(String message, boolean status, Object data){
        Map<Object, Object> map = new Hashtable<>();
        map.put("timestamp", LocalDateTime.now());
        map.put("data", data);
        map.put("status", status);
        map.put("message", message);
        return map;
    }
    public static Map<Object, Object> getMap(String message, String description, boolean status, Object data){
        Map<Object, Object> map = new Hashtable<>();
        map.put("timestamp", LocalDateTime.now());
        map.put("description", description);
        map.put("data", data);
        map.put("status", status);
        map.put("message", message);
        return map;
    }
    public static Map<Object, Object> getMap(String message, String description){
        Map<Object, Object> map = new Hashtable<>();
        map.put("timestamp", LocalDateTime.now());
        map.put("description", description);
        map.put("status", false);
        map.put("message", message);
        return map;
    }
}
