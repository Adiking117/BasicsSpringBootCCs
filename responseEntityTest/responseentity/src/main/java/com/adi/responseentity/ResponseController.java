package com.adi.responseentity;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/response")
public class ResponseController {

    @GetMapping("/header/body")
    public ResponseEntity<String> getHeadersWithBody(){

        HttpHeaders headers = new HttpHeaders();
        headers.add("Header1","Value1");
        headers.add("Header2","Value2");

        return ResponseEntity.status(HttpStatus.OK)
                .headers(headers)
                .body("Headers Fetched Successfully");
    }

    @GetMapping("/header/nobody")
    public ResponseEntity<Void> getHeadersWithNoBody(){

        HttpHeaders headers = new HttpHeaders();
        headers.add("Header1","Value1");
        headers.add("Header2","Value2");

        return ResponseEntity.status(HttpStatus.OK)
                .headers(headers)
                .build();
    }


    @GetMapping("/oldpage")
    public ResponseEntity<Void> getOldPage(){
        return ResponseEntity.status(HttpStatus.MOVED_PERMANENTLY)
                .header("Location","/api/newpage")
                .build();
    }

    @GetMapping("/newpage")
    public ResponseEntity<String> getNewPage(){
        return ResponseEntity.status(HttpStatus.OK).body("SUCCESS");
    }

}
