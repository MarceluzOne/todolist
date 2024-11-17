package br.com.marceluzone.todolist.user;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
@RequestMapping("/users")
public class UserController {

  @Autowired
  private IUserRepository userRepository;

  @PostMapping("/create")
  public ResponseEntity registerUser(@RequestBody UserModel userModel){
    var user = this.userRepository.findByUserName(userModel.getUserName());
    if(user != null){
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuario já cadastrado");
    }
    var  userCreated  = this.userRepository.save(userModel);
    return ResponseEntity.status(HttpStatus.CREATED).body(userCreated);
  }

}
