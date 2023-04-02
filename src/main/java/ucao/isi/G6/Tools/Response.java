package ucao.isi.G6.Tools;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;

import java.util.HashMap;
import java.util.Map;

public class Response extends ResponseEntity<Map<Object, Object>> {
    public Response(HttpStatusCode status) {
        super(status);
    }

    public Response(Map<Object, Object> body, HttpStatusCode status) {
        super(body, status);
    }

    public Response(MultiValueMap<String, String> headers, HttpStatusCode status) {
        super(headers, status);
    }

    public Response(Map<Object, Object> body, MultiValueMap<String, String> headers, HttpStatusCode status) {
        super(body, headers, status);
    }

    public Response(Map<Object, Object> body, MultiValueMap<String, String> headers, int rawStatus) {
        super(body, headers, rawStatus);
    }

    public static Response success(String message, Object data){
        return new Response(Tools.getMap(message, true, data), HttpStatus.OK);
    }
    public static Response error(String message, Object data){
        return new Response(Tools.getMap(message, false, data), HttpStatus.BAD_REQUEST);
    }
    public static Response error(String message, String description, Object data){
        return new Response(Tools.getMap(message, description, false, data), HttpStatus.BAD_REQUEST);
    }
    public static Response serverError(String message, String description){
        return new Response(Tools.getMap(message, description), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    public static Response ok(String message, Object data){
        return new Response(Tools.getMap(message, true, data), HttpStatus.OK);
    }
}
