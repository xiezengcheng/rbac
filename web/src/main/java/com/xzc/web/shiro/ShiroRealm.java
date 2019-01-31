/**
 * 
 */
package com.xzc.web.shiro;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import com.xzc.common.bean.Permission;
import com.xzc.common.bean.User;
import com.xzc.manager.service.PermissionService;
import com.xzc.manager.service.UserService;

/**
 * @author xiezengcheng
 * @version 2019年1月30日 下午3:01:30
 */
public class ShiroRealm extends AuthorizingRealm {
	
	@Autowired
	private UserService userService;
	@Autowired
	private PermissionService permissionService;
	
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		SimpleAuthenticationInfo info = null;
		UsernamePasswordToken currentToken = (UsernamePasswordToken)token;
		String loginacct = currentToken.getUsername();
		//实体类获取表单数据
		User dbUser = userService.queryLogin(loginacct);
		if(dbUser!=null) {
			
			Object principal = loginacct;
			Object credetials = dbUser.getUserpswd();
			ByteSource salt = ByteSource.Util.bytes(principal);
			SimpleHash sh = new SimpleHash("MD5",credetials,salt,1024);
			System.out.println(sh);
			info = new SimpleAuthenticationInfo(principal,sh,salt,this.getName());	
		}else {
			throw new AuthenticationException();
		}
		
		return info;
	}


	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		
		SimpleAuthorizationInfo info = null;
		String loginacct = principals.toString();
		
		User dbUser = userService.queryLogin(loginacct);
		if(dbUser!=null) {
			HashSet<String> permissionsURL = new HashSet<String>();
			List<Permission> permissions = new ArrayList<Permission>();
			permissions = permissionService.queryPermissionsByUser(dbUser);
			for (Permission permission : permissions) {
				if(permission.getUrl()!=null&&!permission.getUrl().equals("")) {
					permissionsURL.add(permission.getUrl());
				}
			}
			info = new SimpleAuthorizationInfo();
			info.addStringPermissions(permissionsURL);
			
		}else {
			throw new AuthenticationException();
		}
		
		return info;
	}
	
	
	
	

}
