package com.example.repository;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.entity.Account;

//there seems to be some sort of problem in here?
//import com.example.entity.Account;
    //Ok so I was fucking lied to, and I DO need the <Account, ???> thing for this to work. No clue what the "long" bit is for, though!
    //Do we do things in here or not?!? I was told "no" but it seems like the answer is actually "Yes!"
public interface AccountRepository extends JpaRepository<Account, Integer>{

    //public Optional<Account> findByUsername(String username); //Is this all I do with this?


}

