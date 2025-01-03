package Kuitso.demo.service;

import Kuitso.demo.common.exception.UserException;
import Kuitso.demo.domain.Product;
import Kuitso.demo.domain.User;
import Kuitso.demo.dto.HomeResponse;
import Kuitso.demo.repository.UserRepository;
import jakarta.transaction.Transactional;
import jdk.jshell.spi.ExecutionControl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static Kuitso.demo.common.response.status.BaseExceptionResponseStatus.CANNOT_FOUND_USER;
import static Kuitso.demo.domain.base.BaseStatus.ACTIVE;

@Slf4j
@Service
@RequiredArgsConstructor
public class HomeService {

    private final UserRepository userRepository;

//    @Transactional
//    public HomeResponse getHomeData(Long userId) {
//
//       User user = userRepository.findByUserIdAndStatus(userId,ACTIVE)
//                .orElseThrow(() -> new UserException(CANNOT_FOUND_USER));
//        String nickname = user.getNickName();
//
//        List<Product> productList = user.getProductList();
//
//        return new HomeResponse();
//    }

}
