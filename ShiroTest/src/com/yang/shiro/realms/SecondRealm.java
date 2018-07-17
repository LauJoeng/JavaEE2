package com.yang.shiro.realms;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.util.ByteSource;

public class SecondRealm extends AuthenticatingRealm {

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		
		System.out.println("[SecondRealm] doGetAuthenticationInfo");
		
		//1.��AuthenticationTokenת��ΪUsernamePasswordToken
		UsernamePasswordToken upToken = (UsernamePasswordToken) token;
		
		//2.��UsernamePasswordToken�л�ȡusername
		String username = upToken.getUsername();
		
		//3.�����ݿ��в�ѯ��username��Ӧ�ļ�¼
		System.out.println("ģ������ݿ��л�ȡusername:"+username+"�û�����Ӧ����Ϣ.");
		
		//4.���û������ڣ�������׳�UnknowAccountException�쳣
		if("unknow".equals(username)) {
			throw new UnknownAccountException();
		}
		
		//5.�����û���Ϣ������������Ƿ���Ҫ�׳�������AuthenticationException�쳣
		if("monster".equals(username)) {
			throw new LockedAccountException();
		}
		
		//6.�����û������������AuthenticationInfo���󲢷���.ͨ��ʵ��ΪSimpleAuthenticationInfo
		//һ��ϸ��Ϣ�Ǵ����ݿ��л�ȡ��.
		//1.principal;��֤��ʵ����Ϣ��������username��Ҳ���������ݱ��Ӧ��ʵ�����
		Object principal = username;
		//2.credentials:����
		Object credentials = null;//"123456";
		if("admin".equals(username)) {
			credentials = "ce2f6417c7e1d32c1d81a797ee0b499f87c5de06";
		}else if("user".equals(username)) {
			credentials = "073d4c3ae812935f23cb3f2a71943f49e082a718";
		}
		//3.realmName:��ǰrealm�����name�����ø����getName()��������
		String realmName = getName();
		//4.��ֵ
		ByteSource credentialsSalt = ByteSource.Util.bytes(username);
		SimpleAuthenticationInfo info = null;//new SimpleAuthenticationInfo(principal, credentials, realmName);
		info = new SimpleAuthenticationInfo(principal, credentials, credentialsSalt, realmName);
		
		return info;
	}
	
	public static void main(String[] args) {
		String hashAlgorithmName = "SHA1";
		Object credentials = "123456";
		Object salt =ByteSource.Util.bytes("admin");;
		int hashIterations = 1024;
		Object result = new SimpleHash(hashAlgorithmName,credentials,salt,hashIterations);
		System.out.println(result);
	}
	

}
