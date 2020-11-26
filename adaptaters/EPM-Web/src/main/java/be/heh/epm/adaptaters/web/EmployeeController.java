/*
    Controller
*/

@RestController
public class EmployeeController
{
    @GetMapping(value = "/")
    public ResponseEntity test()
    {
        return ResponseEntity.ok("TEST");
    }

    @GetMapping(value = "/getuser/{name}")
    public ResponseEntity getEmployee(@PathVariable(name = "name") String name)
    {

    }
}