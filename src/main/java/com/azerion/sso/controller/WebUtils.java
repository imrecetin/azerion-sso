package com.azerion.sso.controller;

import org.springframework.util.StringUtils;
import javax.servlet.http.HttpServletRequest;

public class WebUtils {

    public static String M2M_AUTH_TYPE_VALUE="M2M";

    public static String getXAuthType(HttpServletRequest request) {
        return request.getHeader("x-auth-type");
    }

    public static boolean isM2MAuth(HttpServletRequest request) {
        return M2M_AUTH_TYPE_VALUE.equals(request.getHeader("x-auth-type"));
    }

    public static String getClientIdFromParameter(HttpServletRequest request) {
        return request.getParameter("clientId");
    }

    public static String getClientSecretFromParameter(HttpServletRequest request) {
        return request.getParameter("clientSecret");
    }

    public static String getClientIdFromHeader(HttpServletRequest request) {
        return request.getHeader("x-client-id");
    }

    public static String getClientSecretFromHeader(HttpServletRequest request) {
        return request.getHeader("x-client-secret");
    }

    public static void putClientIdToHeader(HttpServletRequest request, String clientId) {

    }

    public static void putClientSecretToHeader(HttpServletRequest request, String clientSecret) {

    }

    public static void putClientIdAndSecretToHeader(HttpServletRequest request,String clientId, String clientSecret) {
        if (StringUtils.isEmpty(getClientIdFromHeader(request))){
            putClientIdToHeader(request,clientId);
        }
        if (StringUtils.isEmpty(getClientSecretFromHeader(request))){
            putClientSecretToHeader(request,clientSecret);
        }
    }
}
