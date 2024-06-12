package com.itheima.controller;

import com.itheima.pojo.Address;
import com.itheima.pojo.Result;
import com.itheima.pojo.User;
import com.itheima.service.UserService;
import com.itheima.utils.JwtUtil;
import com.itheima.utils.Md5Util;
import com.itheima.utils.ThreadLocalUtil;
import jakarta.validation.constraints.Pattern;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: big-event
 * @description: UserController
 * @author: 樊福蕰
 * @create: 2024-05-21 08:43
 **/
@RestController
@RequestMapping("/user")
@Validated
public class UserController {
    @Autowired
    private UserService userService;
    
    @PostMapping("/register")
    public Result register(@Pattern(regexp = "^.{5,16}$") String username,@Pattern(regexp = "^.{5,16}$") String password){
            User u = userService.findByUserName(username);
            if(u==null){
                userService.register(username,password);
                return Result.success();
            }else{
                return Result.error("用户名已被占用");
            }
    }
    @PostMapping("/login")
    public Result<String> login(@Pattern(regexp = "^.{5,16}$") String username,@Pattern(regexp = "^.{5,16}$") String password){
        User loginUser = userService.findByUserName(username);
        if(loginUser==null){
            return Result.error("用户名错误");
        }
        if(Md5Util.getMD5String(password).equals(loginUser.getPassword())){
            Map<String,Object> claims = new HashMap<>();
            claims.put("id",loginUser.getId());
            claims.put("username",loginUser.getUsername());
            String token = JwtUtil.genToken(claims);
            return Result.success(token);
        }
        return Result.error("密码错误");
    }
    @GetMapping("/userInfo")
    public Result<User> userInfo(@RequestHeader(name = "Authorization") String token){
//        Map<String,Object> map = JwtUtil.parseToken(token);
//        String username = (String) map.get("username");
        Map<String,Object> map = ThreadLocalUtil.get();
        String username = (String) map.get("username");
        User user = userService.findByUserName(username);
        return Result.success(user);
    }
    @PutMapping("/update")
    public Result update(@RequestBody @Validated User user){
        userService.update(user);
        return Result.success();
    }
    @PatchMapping("/updateAvatar")
    public Result updateAvatar(@RequestParam String avatarUrl){
        userService.updateAvatar(avatarUrl);
        return Result.success();
    }
    @PatchMapping("/updatePwd")
    public Result updatePwd(@RequestBody Map<String,String> params){
        String oldPwd = params.get("old_pwd");
        String newPwd = params.get("new_pwd");
        String rePwd = params.get("re_pwd");
        if(!StringUtils.hasLength(oldPwd)||!StringUtils.hasLength(newPwd)||!StringUtils.hasLength(rePwd)){
            return Result.error("缺少必要的参数");
        }
        Map<String,Object> map = ThreadLocalUtil.get();
        String username = (String)map.get("username");
        User loginUser = userService.findByUserName(username);
        if(!loginUser.getPassword().equals(Md5Util.getMD5String(oldPwd))){
            return Result.error("原密码错误");
        }
        if(!rePwd.equals(newPwd)){
            return Result.error("两次密码不一致");
        }
        userService.updatePwd(newPwd);
        return Result.success();
    }
    @GetMapping("/getAddressInfoStore")
    public Result getAddressInfoStore(){

        List<Address> addressList= userService.getAddressInfoStore();

        return Result.success(addressList);
    }
    @PostMapping("/addAddressInfo")
    public Result addAddressInfo(@RequestBody Address address){
        if(address.getPhone().length()!=11){
            return Result.error("请填写正确手机号");
        }
        try{
            userService.addAddressInfo(address.getName(),address.getPhone(),address.getAddress());
            return Result.success("添加成功");
        }catch (Exception e){
            return Result.error("插入失败");
        }

    }

    @PutMapping("/updateAddressInfo")
    public Result updateAddressInfo(@RequestBody Address address){
        if(address.getPhone().length()!=11){
            return Result.error("请填写正确手机号");
        }else{
            userService.updateAddressInfo(address);
            return Result.success();
        }
    }
    @GetMapping("/deleteAddressInfo")
    public Result deleteAddressInfo(@Param("addressId") String addressId){
//        System.out.println("天赫哥"+addressId);
        userService.deleteAddressInfo(addressId);
        return Result.success();
    }

}
