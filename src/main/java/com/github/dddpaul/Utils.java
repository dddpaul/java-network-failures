package com.github.dddpaul;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.BindException;
import java.net.ServerSocket;

public class Utils {

    /**
     * Check local port usage
     *
     * @return <tt>true</tt> if port is available to listen on, <tt>false</tt> if port is used
     */
    public static boolean isAvailable(int port) throws IOException {
        try (ServerSocket ignored = new ServerSocket(port)) {
            return true;
        } catch (BindException ignored) {
            return false;
        }
    }

    public static String waitForOutput(Process process) throws IOException {
        StringBuilder result = new StringBuilder();
        BufferedReader b = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line;
        while ((line = b.readLine()) != null) {
            result.append(line).append(System.lineSeparator());
        }
        return result.toString();
    }
}
