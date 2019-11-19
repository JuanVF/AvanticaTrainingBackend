package com.avantica.proa.Services;

import com.avantica.proa.FBTokenUtils;
import com.avantica.proa.Models.FBUser;
import com.avantica.proa.Repositories.FBUserRepository;
import com.avantica.proa.Security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;

@Service
public class FBUserServiceImpl implements FBUserService {
    @Autowired
    private FBUserRepository userRepository;

    @Autowired
    private FBTokenUtils fbTokenUtils;

    @Override
    public FBUser findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public void loginWithFB(HttpServletResponse res, FBUser fbUser) {
        try {
            FBUser resultUser = findByEmail(fbUser.getEmail());
            boolean isAValidToken = fbTokenUtils.checkFBToken(fbUser.getFbtoken());
            if (resultUser != null && isAValidToken) {
                JwtUtil.addAuthentication(res, resultUser.getEmail());
                res.setStatus(200);
            } else {
                res.setStatus(406);
            }
        } catch (Exception e) {
            res.setStatus(406);
        }
    }

    @Override
    public FBUser saveFBUser(FBUser user) throws Exception {
        FBUser verifier = findByEmail(user.getEmail());

        if (verifier == null) {
            boolean existsToken = new FBTokenUtils().checkFBToken(user.getFbtoken());

            if (existsToken) return userRepository.save(user);

            throw new Exception("Expected a real FB Token");
        }

        return null;
    }

    @Override
    public void deleteFBUser(String email) throws Exception{
        userRepository.deleteByEmail(email);
    }
}
