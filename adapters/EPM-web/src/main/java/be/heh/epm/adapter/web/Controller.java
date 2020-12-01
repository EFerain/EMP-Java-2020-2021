/*
    Controller
*/

package be.heh.epm.adapter.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class Controller
{
    @GetMapping(value = "/test")
    public ResponseEntity test()
    {
        return ResponseEntity.ok("TEST");
    }
}
