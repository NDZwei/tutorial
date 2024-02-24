package com.ndz.app.utils;

/*
    author: NMDuc
    created_at: 2/24/2024
    github: https://github.com/NDZwei
*/
public class EnumClass {
    public enum RoleEnum {
        ROLE_ADMIN("ROLE_ADMIN", "Role admin"),
        ROLE_INSTRUCTOR("ROLE_INSTRUCTOR", "Role instructor"),
        ROLE_USER("ROLE_USER", "Role user");
        private String name;
        private String description;

        RoleEnum(String name, String description) {
            this.name = name;
            this.description = description;
        }

        public String getName() {
            return name;
        }

        public String getDescription() {
            return description;
        }
    }
}
