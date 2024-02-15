package net.java.arap;

import net.java.arap.Utils.EntityResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/accounts")
@CrossOrigin
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/add")
    public EntityResponse create(@RequestBody Account account){
        return accountService.create(account);

    }
    @GetMapping("/findById")
    public EntityResponse findById(@RequestParam Long id){
        return accountService.findById(id);
    }
    @DeleteMapping("/delete")
    public EntityResponse delete(@RequestParam Long id){
        return accountService.delete(id);
    }
    @PutMapping("/modify")
    public EntityResponse modify(@RequestBody Account account){
        return accountService.modify(account);
    }

}
