package com.example.openfire.demo;

import com.example.openfire.demo.model.Chatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Arrays;
import java.util.List;


@RestController
public class UserController {

   /* AuthenticationToken authenticationToken = new AuthenticationToken("admin", "12345");
    RestApiClient restApiClient = new RestApiClient("http://172.17.0.2", 9090, authenticationToken);
*/
    private final RestTemplate restTemplate;
    private static final String url = "http://172.17.0.2:9090/plugins/restapi/v1/users";
    private static final String pais= "https://restcountries.com/v2/all";
    private Chatter chatterService;


     @Autowired
   public UserController(RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
    }


    @GetMapping(value = "/countries", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Object> getCountries() {
        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth("admin", "12345");
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("authorization","Basic YWRtaW46MTIzNDU=");
        Object[] countries = restTemplate.getForObject(pais, Object[].class);

        return  Arrays.asList(countries);
    }

    @GetMapping(
            value = "/users",
            headers = "Authorization= Basic YWRtaW46MTIzNDU= ",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Object> getUserWithHeader() {
        Object[] user = restTemplate.getForObject(url, Object[].class);
        return Arrays.asList(user);
    }


    @GetMapping(value = "/users2", produces = "application/json")
    public List<ResponseEntity<String>> getUsers() {
        ResponseEntity<String> users = restTemplate.exchange("http://172.17.0.2:9090/plugins/restapi/v1/users",HttpMethod.GET, null, String.class);

        return List.of(users);
    }
    @GetMapping(value = "/users3", produces = MediaType.APPLICATION_JSON_VALUE)
        public List<Object> getUsers1() {
            MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
            headers.add("HeaderName", "value");
            headers.add("Content-Type", "application/json");
            headers.add("authorization", "Basic YWRtaW46MTIzNDU=");

            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

            HttpEntity<Object> request = new HttpEntity<Object>(headers);

            restTemplate.postForObject(url, request, Object[].class);

            return  Arrays.asList(request);
        }





    @PostMapping(
            value = "/createUser",
            headers = "Authorization= Basic YWRtaW46MTIzNDU= ",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Chatter> createUser(@RequestBody Chatter chatter) {

        Chatter persistedPerson = chatterService.save(chatter);
        return ResponseEntity
                .created(URI
                        .create(String.format("/Chatter/%s", chatter.getUsername())))
                .body(persistedPerson);
    }

    @DeleteMapping(
            value = "/createUser",
            headers = "Authorization= Basic YWRtaW46MTIzNDU= ",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Chatter> deleteUser(@RequestBody Chatter chatter) {

        Chatter persistedPerson = chatterService.save(chatter);
        return ResponseEntity
                .created(URI
                        .create(String.format("/Chatter/%s", chatter.getUsername())))
                .body(persistedPerson);
    }
    @PutMapping(
            value = "/createUser",
            headers = "Authorization= Basic YWRtaW46MTIzNDU= ",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Chatter> updateUser(@RequestBody Chatter chatter) {

        Chatter persistedPerson = chatterService.save(chatter);
        return ResponseEntity
                .created(URI
                        .create(String.format("/Chatter/%s", chatter.getUsername())))
                .body(persistedPerson);
    }


    }

