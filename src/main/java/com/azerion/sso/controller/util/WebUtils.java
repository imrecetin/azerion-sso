package com.azerion.sso.controller.util;

import com.azerion.sso.controller.MutableHttpServletRequest;
import org.springframework.util.StringUtils;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class WebUtils {

    public enum M2M_CLIENT{
        clientId1("client1","pass1"),clientId2("client2","pass2");
        private String clientUserName;
        private String clientSecret;
        M2M_CLIENT(String clientUserName, String clientSecret) {
            this.clientUserName=clientUserName;
            this.clientSecret=clientSecret;
        }

        public String  clientUserName(){
            return this.clientUserName;
        }

        public String clientSecret(){
            return this.clientSecret;
        }

        public static Optional<M2M_CLIENT> of(String clientUserName){
            return Arrays.stream(M2M_CLIENT.values()).filter(m2MClient -> m2MClient.clientUserName().equals(clientUserName)).findAny();
        }
    }

    public enum X_AUTH_TYPE{
        EndUser("EndUser"),Admin("Admin"),M2M("M2M");
        private String xAuthTypeHeaderValue;

        X_AUTH_TYPE(String xAuthTypeHeaderValue){
            this.xAuthTypeHeaderValue=xAuthTypeHeaderValue;
        }
        public String xAuthTypeHeaderValue(){
            return this.xAuthTypeHeaderValue;
        }
        public static List<String> allValidxAuthTypeHeaderValues(){
            return Arrays.stream(X_AUTH_TYPE.values()).map(X_AUTH_TYPE::xAuthTypeHeaderValue).collect(Collectors.toList());
        }
    }

    public static String getXAuthType(MutableHttpServletRequest request) {
        return request.getHeader("x-auth-type");
    }

    public static boolean isM2MAuth(MutableHttpServletRequest request) {
        return X_AUTH_TYPE.M2M.xAuthTypeHeaderValue().equals(request.getHeader("x-auth-type"));
    }

    public static boolean isM2MAuth(HttpServletRequest request) {
        return X_AUTH_TYPE.M2M.xAuthTypeHeaderValue().equals(request.getHeader("x-auth-type"));
    }

    public static boolean isEndUserAuth(HttpServletRequest request) {
        return X_AUTH_TYPE.EndUser.xAuthTypeHeaderValue().equals(request.getHeader("x-auth-type"));
    }

    public static boolean isAdminAuth(HttpServletRequest request) {
        return X_AUTH_TYPE.Admin.xAuthTypeHeaderValue().equals(request.getHeader("x-auth-type"));
    }

    public static String getClientIdFromParameter(MutableHttpServletRequest request) {
        return request.getParameter("clientId");
    }

    public static String getClientSecretFromParameter(MutableHttpServletRequest request) {
        return request.getParameter("clientSecret");
    }

    public static String getClientIdFromParameter(ServletRequest request) {
        return request.getParameter("clientId");
    }

    public static String getClientSecretFromParameter(ServletRequest request) {
        return request.getParameter("clientSecret");
    }

    public static boolean existInParameter(MutableHttpServletRequest request,String parameterName) {
        return request.getParameterMap().containsKey(parameterName);
    }

    public static String getClientIdFromHeader(HttpServletRequest request) {
        String clientID=request.getHeader("x-client-id");
        if (clientID==null){
            clientID=getClientIdFromParameter(request);
        }
        return clientID;
    }

    public static String getClientSecretFromHeader(HttpServletRequest request) {
        String clientSecret=request.getHeader("x-client-secret");
        if (clientSecret==null){
            clientSecret=getClientSecretFromAttribute(request);
        }
        return clientSecret;
    }

    public static String getClientIdFromAttribute(ServletRequest request) {
        return (String) request.getAttribute("x-client-id");
    }

    public static String getClientSecretFromAttribute(ServletRequest request) {
        return (String)request.getAttribute("x-client-secret");
    }

    public static void putClientIdToHeader(MutableHttpServletRequest request, String clientId) {
        request.putHeader("x-client-id",clientId);
    }

    public static void putClientSecretToHeader(MutableHttpServletRequest request, String clientSecret) {
        request.putHeader("x-client-secret",clientSecret);
    }

    public static void putClientIdToAttribute(ServletRequest request, String clientId) {
        request.setAttribute("x-client-id",clientId);
    }

    public static void putClientSecretToAttribute(ServletRequest request, String clientSecret) {
        request.setAttribute("x-client-secret",clientSecret);
    }

    public static void putClientIdAndSecretToHeader(MutableHttpServletRequest wrapperRequest,String clientId, String clientSecret) {
        if (StringUtils.isEmpty(getClientIdFromHeader(wrapperRequest))){
            putClientIdToHeader(wrapperRequest,clientId);
        }
        if (StringUtils.isEmpty(getClientSecretFromHeader(wrapperRequest))){
            putClientSecretToHeader(wrapperRequest,clientSecret);
        }
    }

    public static void putClientIdAndSecretToAttribute(ServletRequest request,String clientId, String clientSecret) {
        if (StringUtils.isEmpty(getClientIdFromAttribute(request))){
            putClientIdToAttribute(request,clientId);
        }
        if (StringUtils.isEmpty(getClientSecretFromAttribute(request))){
            putClientSecretToAttribute(request,clientSecret);
        }
    }

    public static void putClientIdAndSecretToAttributeAsHttpBasic(ServletRequest wrapperRequest,String clientId, String clientSecret) {
        wrapperRequest.setAttribute("username",clientId);
        wrapperRequest.setAttribute("password",clientSecret);
    }

    public static String parseJwt(HttpServletRequest request) {
        String headerAuth = request.getHeader("Authorization");
        if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
            return headerAuth.substring(7, headerAuth.length());
        }
        return null;
    }
}
