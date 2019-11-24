# rest-api

[![MIT license](https://img.shields.io/badge/license-MIT-blue.svg)](./LICENSE.md)
[![Release](https://jitpack.io/v/inpercima/rest-api.svg)](https://jitpack.io/#inpercima/rest-api)
![Github Action](https://github.com/inpercima/rest-api/workflows/Java%20CI/badge.svg)

Java REST-API for specific apps.

## Prerequisites

### Java

* `jdk 8` or higher

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

### Install

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
  <version>v0.0.1</version>
</dependency>
```
