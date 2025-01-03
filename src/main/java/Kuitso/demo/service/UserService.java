package Kuitso.demo.service;

import Kuitso.demo.domain.User;
import Kuitso.demo.domain.base.BaseStatus;
import Kuitso.demo.dto.user.PatchUserInfoRequest;
import Kuitso.demo.dto.product.SellBuyWishProductDTO;
import Kuitso.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public Optional<User> findByUserIdAndStatus(Long userId, BaseStatus status) {
        return userRepository.findByUserIdAndStatus(userId, status);
    }

    @Transactional
    public void modifyUser(Long userId, PatchUserInfoRequest patchUserInfoRequest) {
        String nickName = patchUserInfoRequest.getNickname();
        String department = patchUserInfoRequest.getDepartment();
        String password = patchUserInfoRequest.getPassword();

        User user = userRepository.findByUserIdAndStatus(userId, BaseStatus.ACTIVE).get();

        user.changeInfo(nickName, department, password);
    }

    public Optional<List<SellBuyWishProductDTO>> findBuyProduct(Long userId) {
        return userRepository.findBuyProduct(userId);
    }

    public Optional<List<SellBuyWishProductDTO>> findSellProduct(Long userId) {
        return userRepository.findSellProduct(userId);
    }

    public Optional<List<SellBuyWishProductDTO>> findWishProduct(Long userId) {
        return userRepository.findWishProduct(userId);
    }



}
