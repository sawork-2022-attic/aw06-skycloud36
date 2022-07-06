# aw06 实验报告

## 1. aw06-batch

#### 实现目标:

把得到的Amazon Json数据转为product并存到数据库中

#### 数据选择

选择meta_Magazine_Subscriptions.json和meta_Gift_Cards.json作为原始数据

选择MySQL作为数据持久化的储存方式

#### 具体实现

使用已经提供的文件将json文件转为若干product；

使用mvn引入与MySQL相关的依赖，并配置properties文件，连接本地数据库；

在productWriter中注入JdbcTemplate并在write函数中执行SQL语句，实现product数据的写库

#### 实现结果

![db](result\db.png)

## 2. aw06-pos

#### 实现目标：

基于aw04的pos，利用batch中产生的数据库作为商品数据库，提供商品列表查询与单个商品查询功能

#### 数据选择

使用MySQL作为商品数据库

#### 具体实现

使用mvn引入与MySQL相关的依赖，并配置properties文件，连接本地数据库；

在AmazonDB中注入JdbcTemplate，在提供的查询功能中执行SQL语句，实现数据查找功能

#### 实现结果
![db](result\result.png)
