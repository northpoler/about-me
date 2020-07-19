package pro.jianbing.aboutme.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pro.jianbing.aboutme.entity.User;
import pro.jianbing.aboutme.repository.UserRepository;

import java.time.LocalDateTime;

/**
 * @author 李建兵
 */
@Service
public class UserService {

    private final
    UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User FindUserByUsername(String username){
        return userRepository.findByUsername(username);
    }
    public Integer updateLoginInfo(String lastIP, Long id){
        return userRepository.updateLoginInfo(lastIP,LocalDateTime.now(),id);
    }

    public int saveUser(User user) {
        User save = userRepository.save(user);
        if (null != save){
            return 1;
        } else {
            return 0;
        }
    }

    public User FindUserByUsernameAndUserId(String username, Long id) {
        return userRepository.findByUsernameAndId(username,id);
    }
}
