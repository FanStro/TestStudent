package com.itheima.service.impl;

import com.itheima.mapper.UserMapper;
import com.itheima.pojo.Address;
import com.itheima.pojo.User;
import com.itheima.service.UserService;
import com.itheima.utils.Md5Util;
import com.itheima.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @program: big-event
 * @description: UserServiceImpl
 * @author: 樊福蕰
 * @create: 2024-05-21 08:46
 **/
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public User findByUserName(String username) {
        User u = userMapper.findByUserName(username);
        return u;
    }

    @Override
    public void register(String username, String password) {
        String md5String = Md5Util.getMD5String(password);
        userMapper.add(username,md5String);
    }

    @Override
    public void update(User user) {
        user.setUpdateTime(LocalDateTime.now());
        userMapper.update(user);
    }

    @Override
    public void updateAvatar(String avatarUrl) {
        Map<String,Object> map = ThreadLocalUtil.get();
        Integer id = (Integer) map.get("id");
        userMapper.updateAvatar(avatarUrl,id);
    }

    @Override
    public void updatePwd(String newPwd) {
        Map<String,Object> map = ThreadLocalUtil.get();
        Integer id = (Integer) map.get("id");
        userMapper.updatePwd(Md5Util.getMD5String(newPwd),id);
    }

    @Override
    public List<Address> getAddressInfoStore() {
        UUID uuid = UUID.randomUUID();
        String id = uuid.toString();
        return userMapper.getAddressInfoStore();
    }

    @Override
    public void addAddressInfo(String name, String phone, String address) {
        UUID uuid = UUID.randomUUID();
        String id = uuid.toString();
        userMapper.addAddressInfo(id,name,phone,address);
    }

    @Override
    public void updateAddressInfo(Address address) {
        userMapper.updateAddressInfo(address.getId(),address.getName(),address.getPhone(),address.getAddress());
    }

    @Override
    public void deleteAddressInfo(String addressId) {
        userMapper.deleteAddressInfo(addressId);
    }
}
