package com.avantica.proa.Services;

import com.avantica.proa.Models.FBUser;

import javax.servlet.http.HttpServletResponse;

public interface FBUserService {
    FBUser findByEmail(String email);

    void loginWithFB(HttpServletResponse res, FBUser fbUser);

    FBUser saveFBUser(FBUser user) throws Exception;

    void deleteFBUser(String email) throws Exception;
}
