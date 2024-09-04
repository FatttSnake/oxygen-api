<div align="center">
    <h1>
        <img alt="Logo" src="doc/logo.svg" width="128">
        <br>
        <span>API of Oxygen Toolbox</span>
    </h1>
</div>
<div align="center">
    <a href="https://ci.fatweb.top/job/Oxygen%20Toolbox%20API/">
        <img alt="Build" src="https://ci.fatweb.top/job/Oxygen%20Toolbox%20API/badge/icon">
    </a>
    <a href="https://github.com/FatttSnake/oxygen-api/releases/latest">
        <img alt="Release" src="https://img.shields.io/github/v/release/FatttSnake/oxygen-api">
    </a>
    <a href="LICENSE">
        <img alt="LICENSE" src="https://img.shields.io/github/license/FatttSnake/oxygen-api">
    </a>
</div>

# 概述 (ZH, [EN](README.md))

本项目为 Oxygen Toolbox 的后端 API。提供工具商店、工具管理、认证鉴权、用户管理等功能。

# 环境要求

- Java 17+
- MySQL
- Redis

# 关联项目

[Web UI of Oxygen Toolbox](https://github.com/FatttSnake/oxygen-ui)

[Desktop Client of Oxygen Toolbox](https://github.com/FatttSnake/oxygen-desktop)

[Android Client of Oxygen Toolbox](https://github.com/FatttSnake/oxygen-android)

# 快速开始

1. 初次运行，生成配置文件模板

```shell
java -jar oxygen-api.jar
```

2. 将 `data` 目录下的 `application-config.example.yml` 文件复制到运行目录下，并重命名为 `application-config.yml`

```shell
cp ./data/application-config.example.yml application-config.yml
```

3. 编辑配置文件 `application-config.yml` 内容


4. 再次运行

```shell
java -jar oxygen-api.jar
```

# 安全

集成 Spring Security 并采用 jwt 令牌， 密钥存储在 `application-config.yml` 中。

# 数据库

采用 MySQL + SQLite 双数据库，MySQL 用于存放关键数据，SQLite 用于存放日志等需要大量读写的数据。

# Q&A

> **Q: 默认管理员账号和密码是什么？**
> 
> A: 初始化数据库前配置在 `application-config.yml` 中，则使用所指定账号密码。未配置则默认生成随机密码，详见控制台输出。

> **Q: 是否需要初始化数据库？**
> 
> A: 本项目采用 `Flyway` 自动初始化数据库，无需手动定义数据表结构。为保证数据安全，升级时请先备份数据库。
