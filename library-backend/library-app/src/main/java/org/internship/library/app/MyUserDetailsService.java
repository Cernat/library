package org.internship.library.app;

import org.internship.library.app.persistence.entity.UserEntity;
import org.internship.library.app.persistence.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
           Optional<UserEntity> user = userRepository.findByUserName(userName);

           user.orElseThrow(() -> new UsernameNotFoundException("Not found: " + userName));

//           return user.map(MyUserDetails::new).get();
            return new MyUserDetails(user.get());
    }
}
