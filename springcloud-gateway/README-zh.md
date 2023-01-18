# springcloud-gateway
***

## 笔记
* ** gateway 配置URI的一些规则
  ````
  #配置需要转发到的服务地址
  # http 方式
  uri: http://localhost:28814
  # web socket 方式
  uri: wb://localhost:28814
  # 注册中心 方式， 利用目的地服务在注册中心的服务名，进行配置
  uri: lb://order-ribbon
  ````
  __其中ws和http方式不容易出错，因为http格式比较固定，但是lb方式比较灵活自由。不考虑网关，只考虑服务时，服务名命名时比较自由，都能启动被访问，被注册到注册中心，但是如果提供给gateway使用时，就会对服务命名方式有特殊要求了。__
  
    __能被gateway的lb方式识别到的命名规则为：__
  ````
  "[a-zA-Z]([a-zA-Z]|\\d|\\+|\\.|-)*:.*"
  ````

  **这也意味着，java命名规范中可以使用的英文下划线（"_"）不能被识别,而我命名为：brilliance_consumer，刚好带下划线，改为brilliance-consumer后则可以正常通过网关访问自己项目。**
  __如果名字中有非*“a-zA-Z:.”*规则字符，则会报错，__
  ***
##