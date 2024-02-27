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
    };

    public enum EMAIL_TYPE {
        REGISTER, FORGOT_PASSWORD, NOTICE
    }

    public enum STATUS_SEND {
        SUCCESS, ERROR,
    }

    public enum SERVER_URL {
        LOCALHOST("http://localhost:8090/api"),
        IP_ADDRESS("http://192.168.1.235:8090/api"),
        ;
        private String url;

        SERVER_URL(String url) {
            this.url = url;
        }

        public String getUrl() {
            return url;
        }
    }
}
