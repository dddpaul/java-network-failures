package com.github.dddpaul;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.invoke.MethodHandles;
import java.util.Arrays;
import java.util.List;

public class IpTables
{

    private static final Logger log = LogManager.getLogger( MethodHandles.lookup().lookupClass() );

    public static enum Target
    {
        DROP,
        REJECT
    }

    public static enum Proto
    {
        TCP,
        UDP
    }

    public static enum Chain
    {
        INPUT,
        OUTPUT
    }

    public static void drop( int port )
    {
        addTcpRule( Chain.INPUT, port, Target.DROP );
    }

    public static void reject( int port )
    {
        addTcpRule( Chain.INPUT, port, Target.REJECT );
    }

    public static void allow( int port )
    {
        for( Target target : Target.values() ) {
            removeTcpRule( Chain.INPUT, port, target );
        }
    }

    public static void drop( Chain chain, int port )
    {
        addTcpRule( chain, port, Target.DROP );
    }

    public static void reject( Chain chain, int port )
    {
        addTcpRule( chain, port, Target.REJECT );
    }

    public static void allow( Chain chain, int port )
    {
        for( Target target : Target.values() ) {
            removeTcpRule( chain, port, target );
        }
    }

    public static void addTcpRule( Chain chain, int port, Target target )
    {
        addRule( Proto.TCP, chain, port, target );
    }

    public static void removeTcpRule( Chain chain, int port, Target target )
    {
        removeRule( Proto.TCP, chain, port, target );
    }

    public static void addRule( Proto proto, Chain chain, int port, Target target )
    {
        iptables( proto, chain, port, target, true );
    }

    public static void removeRule( Proto proto, Chain chain, int port, Target target )
    {
        iptables( proto, chain, port, target, false );
    }

    public static void iptables( Proto proto, Chain chain, int port, Target target, boolean add )
    {
        try {
            String action = add ? "-A" : "-D";
            List<String> cmd = Arrays.asList( "sudo", "iptables", action, chain.toString(), "-p", proto.toString(), "--dport", String.valueOf( port ), "-j", target.toString() );
            Process process = new ProcessBuilder( cmd ).redirectErrorStream( true ).start();
            if( process.waitFor() > 0 ) {
                log.warn( Utils.waitForOutput( process ) );
            }
        } catch( Exception e ) {
            throw new RuntimeException( e );
        }
    }
}
