package one.prompt.context;

import one.prompt.entity.SysUser;

import java.util.ArrayList;

/**
 * @author klein
 */
public class MockSecretContextHolder {

    public static SysUser getCurrentUser() {
        return SysUser.builder()
                .id(1L)
                .name("mock")
                .phone("18888888888")
                .sharedPrompts(new ArrayList<>())
                .likedPrompts(new ArrayList<>())
                .comments(new ArrayList<>())
                .build();

    }
}
