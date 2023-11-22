package com.finance.service;

import com.finance.domain.city.City;
import com.finance.domain.state.State;
import com.finance.domain.user.User;
import com.finance.domain.user.UserRequest;
import com.finance.repo.CityRepo;
import com.finance.repo.StateRepo;
import com.finance.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

  private final UserRepo userRepo;
  private final CityRepo cityRepo;
  private final StateRepo stateRepo;
  private final PasswordEncoder passwordEncoder;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return userRepo.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));
  }

  public List<User> getUsers() {
    return userRepo.findAll();
  }

  public void updateUser(Long id, UserRequest req) {
    Optional<User> optionalUser = userRepo.findById(id);
    Optional<City> optionalCity = cityRepo.findById(req.getCity());
    Optional<State> optionalState = stateRepo.findById(req.getState());

    if (optionalUser.isEmpty()) {
      throw new RuntimeException("Usuário não encontrado");
    }
    if (optionalCity.isEmpty()) {
      throw new RuntimeException("Cidade não encontrada");
    }
    if (optionalState.isEmpty()) {
      throw new RuntimeException("Estado não encontrado");
    }

    User user = optionalUser.get();
    City city = optionalCity.get();
    State state = optionalState.get();

    user.setId(user.getId());
    user.setName(req.getName());
    user.setEmail(req.getEmail());
    user.setDate_birth(req.getDate_birth());
    user.setCel(req.getCel());
    user.setCity(city);
    user.setState(state);

    userRepo.save(user);
  }

}
