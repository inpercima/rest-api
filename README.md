# rest-api

[![MIT license](https://img.shields.io/badge/license-MIT-blue.svg)](./LICENSE.md)
[![Release](https://jitpack.io/v/inpercima/rest-api.svg)](https://jitpack.io/#inpercima/rest-api)
![Github Action CI](https://github.com/inpercima/rest-api/workflows/CI/badge.svg)

Java REST-API for specific apps.

## Prerequisites

### Java

* `jdk 11` or higher

## Getting started

```bash
# clone project
git clone https://github.com/inpercima/rest-api
cd rest-api
```

## Usage

### Package

```bash
# package
./mvnw clean package

# package without tests
./mvnw clean package -DskipTests
```

### Install via jitpack

Add `jitpack.io` as repository to your project, for maven like

```xml
<repositories>
  <repository>
    <id>jitpack.io</id>
    <url>https://jitpack.io</url>
  </repository>
</repositories>
```

Add `rest-api` as dependency to your project, for maven like

```xml
<dependency>
  <groupId>com.github.inpercima</groupId>
  <artifactId>rest-api</artifactId>
  <version>v1.0.2</version>
</dependency>
```

### Install via github repository

Update or create settings.xml in your `.m2` folder

```xml
<settings
  xmlns="http://maven.apache.org/SETTINGS/1.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="http://maven.apache.org/SETTINGS/1.0.0 http://maven.apache.org/xsd/settings-1.0.0.xsd">

  <activeProfiles>
    <activeProfile>github</activeProfile>
  </activeProfiles>

  <profiles>
    <profile>
      <id>github</id>
      <repositories>
        <repository>
          <id>central</id>
          <url>https://repo1.maven.org/maven2</url>
        </repository>
        <repository>
          <id>github</id>
          <url>https://maven.pkg.github.com/inpercima/*</url>
          <snapshots>
            <enabled>true</enabled>
          </snapshots>
        </repository>
      </repositories>
    </profile>
  </profiles>

  <servers>
    <server>
      <id>github</id>
      <username>USERNAME</username>
      <password>PERSONAL_ACCESS_TOKEN</password>
    </server>
  </servers>
</settings>
```

Replace `USERNAME` with your github username.
Replace `PERSONAL_ACCESS_TOKEN` with your personal access tokens you have to create in github with the scope `read:packages`.

Add `rest-api` as dependency to your project, for maven like

```xml
<dependency>
  <groupId>net.inpercima</groupId>
  <artifactId>rest-api</artifactId>
  <version>1.0.2</version>
</dependency>
```
