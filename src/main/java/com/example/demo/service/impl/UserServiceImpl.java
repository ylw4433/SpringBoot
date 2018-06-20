package com.example.demo.service.impl;

import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    @Cacheable(value = "user")
    public List<User> findUserInfo() {
    	User user = new User();
    	user.setId("123");
    	log.info("查询所有并保存缓存");
    	log.info("查询所有并保存缓存");
    	List<User> findUserInfo = userMapper.findUserInfo();
    	System.out.println(findUserInfo.toString());
        return findUserInfo;
    }
    
    @Override
    //@Cacheable缓存key为user 的id 数据到缓存people 中,如果没有指定key则方法参数作为key保存到缓存中。
    @Cacheable(value = "user")//调用 此方法是会查看是否拥有缓存。有缓存则直接返回缓存，没有则执行此方法 并保存缓存。
    public List<User> findUserInfoById(String id) {
        return userMapper.findUserInfoById(id);
    }

	@Override
	//@CacheEvict 从缓存user中删除key为id 的数据
	@CacheEvict(value = "user")//删除缓存，关键字value和key，key无值则使用此方法参数作为默认。
	public int del(String id) {
		return userMapper.del(id);
	}
	 
	@CacheEvict(value = "user",allEntries = true)//删除缓存，关键字value和key，key无值则使用此方法参数作为默认。
	public int del() {
//		return userMapper.del(id);
		System.out.println("shanchu");
		return 1;
	}

	@Override
	@Cacheable(value = "user",key="#user.id")
	public int updateUserInfo(User user) {
		return userMapper.updateUserInfo(user);
	}

	@Override
	//@CachePut缓存新增的或更新的数据到缓存,其中缓存名字是 user 。数据的key是user的id
	@CachePut(value = "user", key = "#user.id")//无论怎样，将方法的返回值存入缓存。
	public int addUser(User user) {
		return userMapper.addUser(user);
	}

//	@Override
//	public SysUser findByUserName(String username) {
//		return userMapper.findByUserName(username);
//	}
}
