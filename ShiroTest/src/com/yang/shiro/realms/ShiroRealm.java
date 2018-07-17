package com.yang.shiro.realms;

import java.util.HashSet;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

public class ShiroRealm extends AuthorizingRealm {

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		
		System.out.println("[FirstRealm] doGetAuthenticationInfo");
		
		//1.把AuthenticationToken转换为UsernamePasswordToken
		UsernamePasswordToken upToken = (UsernamePasswordToken) token;
		
		//2.从UsernamePasswordToken中获取username
		String username = upToken.getUsername();
		
		//3.从数据库中查询出username对应的记录
		System.out.println("模拟从数据库中获取username:"+username+"用户所对应的信息.");
		
		//4.若用户不存在，则可以抛出UnknowAccountException异常
		if("unknow".equals(username)) {
			throw new UnknownAccountException();
		}
		
		//5.根据用户信息的情况，决定是否需要抛出其他得AuthenticationException异常
		if("monster".equals(username)) {
			throw new LockedAccountException();
		}
		
		//6.根据用户情况，来构建AuthenticationInfo对象并返回.通常实现为SimpleAuthenticationInfo
		//一下细信息是从数据库中获取的.
		//1.principal;认证的实体信息，可以是username，也可以是数据表对应的实体对象
		Object principal = username;
		//2.credentials:密码
		Object credentials = null;//"123456";
		if("admin".equals(username)) {
			credentials = "038bdaf98f2037b31f1e75b5b4c9b26e";
		}else if("user".equals(username)) {
			credentials = "098d2c478e9c11555ce2823231e02ec1";
		}
		//3.realmName:当前realm对象的name，调用父类的getName()方法即可
		String realmName = getName();
		//4.盐值
		ByteSource credentialsSalt = ByteSource.Util.bytes(username);
		SimpleAuthenticationInfo info = null;//new SimpleAuthenticationInfo(principal, credentials, realmName);
		info = new SimpleAuthenticationInfo(principal, credentials, credentialsSalt, realmName);
		
		return info;
	}
	
	public static void main(String[] args) {
		String hashAlgorithmName = "MD5";
		Object credentials = "123456";
		Object salt =ByteSource.Util.bytes("admin");;
		int hashIterations = 1024;
		Object result = new SimpleHash(hashAlgorithmName,credentials,salt,hashIterations);
		System.out.println(result);
	}

	//授权会被shiro回调的方法
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		System.out.println("doGetAuthorizationInfo");
		//1.从PrincipalCollection中获取登录用户的信息
		Object principal = principals.getPrimaryPrincipal();
		//2.利用登录的用户的信息来用户当前用户的角色或权限
		Set<String>roles = new HashSet<>();
		roles.add("user");
		if("admin".equals(principal)) {
			roles.add("admin");
		}
		//3.创建SimpleAuthenticationInfo 并设置roles属性
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo(roles);
		//4.返回SimpleAuthenticationInfo对象
		return info;
	}
	

}
