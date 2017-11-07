package com.centling.conference.sysuser.service;

import java.io.IOException;
import java.util.Collection;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service("mySimpleUrlAuthenticationSuccessHandler")
public class MySimpleUrlAuthenticationSuccessHandler implements
		AuthenticationSuccessHandler {
	/** 
     * url参数 
     */  
    private Map<String, String> map;  
    /** 
     * 多role选择,默认取得权限表第一个权限 
     */  
    private boolean isFirst = true;  
    @Override  
    public void onAuthenticationSuccess(HttpServletRequest request,  
            HttpServletResponse response, Authentication authentication)  
            throws IOException, ServletException {  
        Assert.notNull(map, "AuthInterceptMap is null!");  
        String url = "";  
        Collection<GrantedAuthority> authCollection = authentication.getAuthorities();  
  
        if (authCollection.isEmpty()) {  
            return;  
        }  
        //对于一个登录用户有多种角色,只取得第一个  
        if (isFirst) {  
            GrantedAuthority[] a = new GrantedAuthorityImpl[]{};  
            url = map.get(authCollection.toArray(a)[0].toString());  
            response.sendRedirect(request.getContextPath() + url);  
            return;  
        }  
        //选择取得最后一个role掉转;这里一个用户的多个角色较少  
        //迭代的速度比转换成数组的速度要快  
        for (GrantedAuthority auth : authCollection) {  
            url = map.get(auth.getAuthority());  
        }  
        response.sendRedirect(url);  
    }  
      
    /** 
     * 权限跳转依据 
     * @param map 参数 
     *  key:url 
     *  value:role 
     */  
    public void setAuthDispatcherMap(Map<String, String> map) {  
        this.map = map;  
    }  
  
    /** 
     * 多种角色方案 
     * 设置是否只取得第一个role 
     * @param isFirst true:多种角色只取第一个，false:取得最后一个 
     */  
    public void setMultipleAuth(boolean isFirst) {  
        this.isFirst = isFirst;  
    }  
}