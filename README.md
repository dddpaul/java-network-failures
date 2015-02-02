# Java network failures

## Bane

Listen but never respond on port 54321:

```java
Bane.neverRespond( 54321 );
```

See [danielwellman/bane](https://github.com/danielwellman/bane/) project for all options.

## IpTables

Add to /etc/sudoers:

```
user ALL= NOPASSWD: /sbin/iptables
```

Block all traffic to port 54321:

```java
IpTables.drop( 54321 );
```
