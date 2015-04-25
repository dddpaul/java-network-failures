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

Block inbound traffic to port 54321:

```java
IpTables.drop( Chain.INPUT, 54321 );
```

## Maven usage

Add following to your pom.xml:
```xml
<repositories>
    <repository>
        <id>dddpaul</id>
        <url>http://dl.bintray.com/dddpaul/maven</url>
    </repository>
    <repository>
        <id>jcenter</id>
        <url>http://jcenter.bintray.com</url>
    </repository>
</repositories>

<dependencies>
    <dependency>
        <groupId>com.github.dddpaul</groupId>
        <artifactId>network-failures</artifactId>
        <version>1.1</version>
    </dependency>
</dependencies>
```
