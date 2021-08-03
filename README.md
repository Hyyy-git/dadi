## 初始化构建工程


## 手动加载MAVEN包到本地仓库
进入到lib目录

* 加载 oracle jar 

```
mvn install:install-file -Dfile=ojdbc8-18.3.0.0.jar  -DgroupId=com.oracle  -DartifactId=ojdbc8 -Dversion=18.3.0.0 -Dpackaging=jar
```
