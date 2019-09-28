package pro.jianbing.aboutme.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pro.jianbing.aboutme.entity.User;
import pro.jianbing.aboutme.repository.UserRepositoty;

import java.time.LocalDateTime;

/**
 * @author 李建兵
 */
@Service
public class UserService {

    private final
    UserRepositoty userRepositoty;

    @Autowired
    public UserService(UserRepositoty userRepositoty) {
        this.userRepositoty = userRepositoty;
    }

    public User FindUserByUsername(String username){
        return userRepositoty.findByUsername(username);
    }
    public Integer updateLoginInfo(String lastIP, Long id){
        return userRepositoty.updateLoginInfo(lastIP,LocalDateTime.now(),id);
    }

    public int saveUser(User user) {
        User save = userRepositoty.save(user);
        if (null != save){
            return 1;
        } else {
            return 0;
        }
    }

    public User FindUserByUsernameAndUserId(String username, Long id) {
        return userRepositoty.findByUsernameAndId(username,id);
    }
}
