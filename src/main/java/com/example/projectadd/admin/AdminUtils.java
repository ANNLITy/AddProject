package com.example.projectadd.admin;

import com.example.projectadd.config.Role;
import com.example.projectadd.model.Ads;
import com.example.projectadd.model.Comment;
import com.example.projectadd.model.User;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Component;


@Component
public class AdminUtils {
    public AdminUtils() {
    }

    public void checkPermissionToAds(Ads ads, User user) {
        UserDetails userDetails = new UserDetails(user);

        if (!userDetails.getAuthorities().contains(Role.ADMIN) && userDetails.getId() != ads.getUser().getId()) {
            throw new AccessDeniedException("Чтобы изменить/удалить объявление, нужно иметь роль ADMIN или быть владельцем этого объявления");
        }
    }

    public void checkPermissionToAdsComment(Comment adsComment, User user) {
        UserDetails userDetails = new UserDetails(user);

        if (!userDetails.getAuthorities().contains(Role.ADMIN) && userDetails.getId() != adsComment.getUser().getId()) {
            throw new AccessDeniedException("Чтобы изменить/удалить комментарий, нужно иметь роль ADMIN или быть владельцем этого комментария");
        }
    }
}
