package com.azerion.sso.controller;

import org.springframework.util.StringUtils;

import javax.servlet.ServletRequest;
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

    public static boolean isM2MAuth(ServletRequest request) {
        final String attribute = (String)request.getAttribute("x-auth-type");
        return X_AUTH_TYPE.M2M.xAuthTypeHeaderValue().equals(attribute);
    }

    public static boolean isEndUserAuth(MutableHttpServletRequest request) {
        return X_AUTH_TYPE.EndUser.xAuthTypeHeaderValue().equals(request.getHeader("x-auth-type"));
    }

    public static boolean isAdminAuth(MutableHttpServletRequest request) {
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

    public static String getClientIdFromHeader(MutableHttpServletRequest request) {
        return request.getHeader("x-client-id");
    }

    public static String getClientSecretFromHeader(MutableHttpServletRequest request) {
        return request.getHeader("x-client-secret");
    }

    public static String getClientIdFromHeader(ServletRequest request) {
        return (String) request.getAttribute("x-client-id");
    }

    public static String getClientSecretFromHeader(ServletRequest request) {
        return (String)request.getAttribute("x-client-secret");
    }

    public static void putClientIdToHeader(MutableHttpServletRequest request, String clientId) {
        request.putHeader("x-client-id",clientId);
    }

    public static void putClientSecretToHeader(MutableHttpServletRequest request, String clientSecret) {
        request.putHeader("x-client-secret",clientSecret);
    }

    public static void putClientIdToHeader(ServletRequest request, String clientId) {
        request.setAttribute("x-client-id",clientId);
    }

    public static void putClientSecretToHeader(ServletRequest request, String clientSecret) {
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

    public static void putClientIdAndSecretToHeader(ServletRequest wrapperRequest,String clientId, String clientSecret) {
        if (StringUtils.isEmpty(getClientIdFromHeader(wrapperRequest))){
            putClientIdToHeader(wrapperRequest,clientId);
        }
        if (StringUtils.isEmpty(getClientSecretFromHeader(wrapperRequest))){
            putClientSecretToHeader(wrapperRequest,clientSecret);
        }
    }

    public static void putClientIdAndSecretToHeaderAsHttpBasic(ServletRequest wrapperRequest,String clientId, String clientSecret) {
        wrapperRequest.setAttribute("username",clientId);
        wrapperRequest.setAttribute("password",clientSecret);
    }
}
