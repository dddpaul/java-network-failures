package com.github.dddpaul;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Utils
{
    public static String waitForOutput( Process process ) throws IOException
    {
        StringBuilder result = new StringBuilder();
        BufferedReader b = new BufferedReader( new InputStreamReader( process.getInputStream() ) );
        String line;
        while( ( line = b.readLine() ) != null ) {
            result.append( line ).append( System.lineSeparator() );
        }
        return result.toString();
    }
}
