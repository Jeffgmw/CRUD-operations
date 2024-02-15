package net.java.arap;

import lombok.extern.slf4j.Slf4j;
import net.java.arap.Utils.EntityResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Slf4j
public class AccountService {
    @Autowired
    AccountRepository accountRepository;


    public EntityResponse create(Account account) {
        EntityResponse entityResponse = new EntityResponse<>();

        try {
            Account savedAccount = accountRepository.save(account);
            savedAccount.setPostedBy("System");
            savedAccount.setPostedTime(LocalDateTime.now());
            savedAccount.setPostedFlag('N');

            Account account1 = accountRepository.save(savedAccount);
            entityResponse.setMessage("Account created successfully");
            entityResponse.setStatusCode(HttpStatus.CREATED.value());
            entityResponse.setEntity(account1);


        } catch (Exception e) {
            log.error("Exception {}", e);
            entityResponse.setMessage(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
            entityResponse.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            entityResponse.setEntity(null);
        }
        return entityResponse;
    }

    public EntityResponse findById(Long id) {
        EntityResponse entityResponse = new EntityResponse();

        try {
            Optional<Account> existingAccount = accountRepository.findById(id);
            if (existingAccount.isPresent()) {
                entityResponse.setMessage("Account retrieved successfully" + id);
                entityResponse.setStatusCode(HttpStatus.FOUND.value());
                entityResponse.setEntity(existingAccount);
            } else {
                entityResponse.setMessage("Account not found");
                entityResponse.setStatusCode(HttpStatus.NO_CONTENT.value());
                entityResponse.setEntity(null);

            }
        } catch (Exception e) {
            log.error("Error while searching");
        }
        return entityResponse;
    }

    public EntityResponse delete(Long id) {
        EntityResponse entityResponse = new EntityResponse();
        try {
            Optional<Account> getAccount = accountRepository.findById(id);
            if (getAccount.isPresent()) {
                Account account = getAccount.get();
                accountRepository.delete(account);

                entityResponse.setMessage("Account deleted successfully");
                entityResponse.setStatusCode(HttpStatus.OK.value());
                entityResponse.setEntity(null);


            } else {
                entityResponse.setMessage("Account not found");
                entityResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
                entityResponse.setEntity(null);

            }

        } catch (Exception e) {
            log.error("Error {}" + e);
        }
        return entityResponse;
    }

    public EntityResponse modify(Account account) {
        EntityResponse entityResponse = new EntityResponse<>();
        try {
            Optional<Account> existingAccount = accountRepository.findById(account.getId());

            if(existingAccount.isPresent()){
                Account updatedAccount = existingAccount.get();

                updatedAccount.setFirstName(account.getFirstName());
                updatedAccount.setLastName(account.getLastName());
                updatedAccount.setEmailId(account.getEmailId());

                updatedAccount.setModifiedBy("System");
                updatedAccount.setModifiedTime(LocalDateTime.now());
                updatedAccount.setModifiedFlag('Y');

                accountRepository.save(updatedAccount);

                entityResponse.setMessage("Account modified successfully");
                entityResponse.setStatusCode(HttpStatus.OK.value());
                entityResponse.setEntity(null);

            }else {
                entityResponse.setMessage("Account not found");
                entityResponse.setStatusCode(HttpStatus.NO_CONTENT.value());
                entityResponse.setEntity(null);
            }
        }catch (Exception e){
            log.error("Error {}" + e);
        }
        return entityResponse;
    }
}
