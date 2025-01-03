package Kuitso.demo.controller;

import Kuitso.demo.common.response.BaseResponse;
import Kuitso.demo.domain.User;
import Kuitso.demo.domain.base.BaseStatus;
import Kuitso.demo.dto.user.*;
import Kuitso.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @GetMapping("/{userId}/myPage")
    public BaseResponse<GetUserMyPageResponse> myPage(@PathVariable("userId") Long userId) {
        Optional<User> findUser = userService.findByUserIdAndStatus(userId, BaseStatus.ACTIVE);
        String nickName = findUser.get().getNickName();
        String department = findUser.get().getDepartment();

        return new BaseResponse<>(new GetUserMyPageResponse(nickName, department));
    }

    @GetMapping("/{userId}/info")
    public BaseResponse<GetUserInfoResponse> getInfo(@PathVariable("userId") Long userId) {
        Optional<User> findUser = userService.findByUserIdAndStatus(userId, BaseStatus.ACTIVE);

        String nickName = findUser.get().getNickName();
        String department = findUser.get().getDepartment();
        String email = findUser.get().getEmail();
        String password = findUser.get().getPassword();

        return new BaseResponse<>(new GetUserInfoResponse(nickName, department, email, password));
    }

    @PatchMapping("/{userId}/info")
    public BaseResponse<Void> patchInfo(@PathVariable("userId") Long userId,
                                        @RequestBody PatchUserInfoRequest patchUserInfoRequest) {
        userService.modifyUser(userId, patchUserInfoRequest);
        return new BaseResponse<>(null);
    }

    @GetMapping("{userId}/buy-product")
    public BaseResponse<GetUserProductResponse> buyProduct(@PathVariable("userId") Long userId) {
        Optional<List<SellBuyWishProductDTO>> buyProduct = userService.findBuyProduct(userId);
        return new BaseResponse<>(new GetUserProductResponse(buyProduct.get()));
    }

    @GetMapping("/{userId}/sell-product")
    public BaseResponse<GetUserProductResponse> sellProduct(@PathVariable("userId") Long userId) {
        Optional<List<SellBuyWishProductDTO>> sellProduct = userService.findSellProduct(userId);
        return new BaseResponse<>(new GetUserProductResponse(sellProduct.get()));
    }

    @GetMapping("/{userId}/wish-product")
    public BaseResponse<GetUserProductResponse> wishProduct(@PathVariable("userId") Long userId) {
        Optional<List<SellBuyWishProductDTO>> wishProduct = userService.findWishProduct(userId);
        return new BaseResponse<>(new GetUserProductResponse(wishProduct.get()));
    }





}
