package com.itheima.service;

import com.itheima.pojo.Address;
import com.itheima.pojo.User;

import java.util.List;

/**
 * @program: big-event
 * @description: UserService
 * @author: 高玉好
 * @create: 2024-05-21 08:46
 **/
public interface UserService {
    User findByUserName(String username);

    void register(String username, String password);

    void update(User user);

    void updateAvatar(String avatarUrl);

    void updatePwd(String newPwd);

    List<Address> getAddressInfoStore();

    void addAddressInfo(String name, String phone, String address);

    void updateAddressInfo(Address address);

    void deleteAddressInfo(String addressId);
}
