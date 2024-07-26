# SURF0335

### Deployment

##### Frontend


1. 安装依赖项 run ```npm install ```

2. 控制台运行 ```http-server -p 8080``` 启动前端

3. run ```npx tailwindcss -i ./css/input.css -o ./css/output.css --watch``` 以启动tailwind自动编译

前端会部署在8080端口上
访问```localhost:8080```


##### Backend

run ```mvn spring-boot:run``` 或者使用idea右上角启动

后端会部署在8000端口上
访问```localhost:8000```

##### Database

1.  下个xampp 或者 navicat 按照docker的init.sql创建数据库
2.  查看自己数据库设置的用户名和密码，到druid.properties改一下。就相当于登录校验
3.  运行userService 没问题就没问题



