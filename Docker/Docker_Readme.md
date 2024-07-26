
| DockerFile                  |                                                              |
| --------------------------- | ------------------------------------------------------------ |
| BackendApp_Dockerfile       | 后端程序在一个Container里运行(Model,Controller)              |
| Database_Master_Dockerfile  | Mysql服务，主备份                                            |
| Database_Slavery_Dockerfile | 如果Mysql_Master下线或者流量大于阈值，Slavery会自动上线      |
| LoadBalance_DockerFile      | 自动监控App,db状态，动态调整app容器数量，notify其他服务器上的集群 |



| Compose            |                                   |
| ------------------ | --------------------------------- |
| docker-compose.yml | 启动集群,会自动处理依赖关系和网络 |



```
默认情况下集群会有
	1个app容器=Model+Conteoller
	1+1 mysql容器(主从)=数据库
	1个Loadbalance容器=分布式+负载均衡
Loadbalance容器会根据流量自动启动销毁app容器
实际情况下集群会有
	n个app容器
	1+1 mysql容器(主从)
	1个Loadbalance容器
	n= Max((1min_load/threshold_1)+(5min_load/threshold_2),16)
```

