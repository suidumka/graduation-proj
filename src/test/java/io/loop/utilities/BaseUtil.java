package io.loop.utilities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

public class BaseUtil {

    static Logger LOG = LogManager.getLogger();

    public static Map<String, String> getUserInfo (String userType) {
        Map <String, String> userMap = new HashMap<>();
        String username = "";
        String password = "";
        if (userType.equalsIgnoreCase("advisor")) {
            username = DocuportConstants.USERNAME_ADVISOR;
            password = DocuportConstants.PASSWORD_FOR_LOGIN;
        } else if (userType.equalsIgnoreCase("supervisor")) {
            username = DocuportConstants.USERNAME_SUPERVISOR;
            password = DocuportConstants.PASSWORD_FOR_LOGIN;
        } else if (userType.equalsIgnoreCase("employee")) {
            username = DocuportConstants.USERNAME_EMPLOYEE;
            password = DocuportConstants.PASSWORD_FOR_LOGIN;
        } else if (userType.equalsIgnoreCase("client")) {
            username = DocuportConstants.USERNAME_CLIENT;
            password = DocuportConstants.PASSWORD_FOR_LOGIN;
        } else {
            LOG.error("Invalid user type");
        }

        userMap.put("userEmail", username);
        userMap.put("userPassword", password);

        return userMap;
    }
}
