# cloud-oauth
spring-cloud-oauth2授权系统
-- -----------------------------------------------------
实现：'授权码'、'密码'、'简易'、'客户端'四种授权模式；
采用MD5密码编码；
采用JWt对称加密的Token形式，因JWT自包含信息特性、省略了token数据库存储方式；实现JWTtoken封装自定义信息
-- -----------------------------------------------------
#(一)数据库存储相关表结构
-- -----------------------------------------------------
-- Table `oauth_access_token` 授权认证记录表
-- -----------------------------------------------------
CREATE TABLE `oauth_access_token` (
  `token_id` varchar(256) DEFAULT NULL,
  `token` blob,
  `authentication_id` varchar(128) NOT NULL,
  `user_name` varchar(256) DEFAULT NULL,
  `client_id` varchar(256) DEFAULT NULL,
  `authentication` blob,
  `refresh_token` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`authentication_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8
-- -----------------------------------------------------
-- Table `oauth_approvals` 授权认证记录表
-- -----------------------------------------------------
CREATE TABLE `oauth_approvals` (
  `userId` varchar(256) COLLATE utf8_unicode_520_ci DEFAULT NULL,
  `clientId` varchar(256) COLLATE utf8_unicode_520_ci DEFAULT NULL,
  `scope` varchar(256) COLLATE utf8_unicode_520_ci DEFAULT NULL,
  `status` varchar(10) COLLATE utf8_unicode_520_ci DEFAULT NULL,
  `expiresAt` datetime DEFAULT NULL,
  `lastModifiedAt` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_520_ci
-- -----------------------------------------------------
-- Table `oauth_client_details` 客户端信息表
-- -----------------------------------------------------
CREATE TABLE `oauth_client_details` (
  `client_id` varchar(128) NOT NULL,
  `resource_ids` varchar(256) DEFAULT NULL,
  `client_secret` varchar(256) DEFAULT NULL,
  `scope` varchar(256) DEFAULT NULL,
  `authorized_grant_types` varchar(256) DEFAULT NULL,
  `web_server_redirect_uri` varchar(256) DEFAULT NULL,
  `authorities` varchar(256) DEFAULT NULL,
  `access_token_validity` int(11) DEFAULT NULL,
  `refresh_token_validity` int(11) DEFAULT NULL,
  `additional_information` varchar(4096) DEFAULT NULL,
  `autoapprove` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`client_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8

-- -----------------------------------------------------
-- Table `oauth_code` 授权码表
-- -----------------------------------------------------
CREATE TABLE `oauth_code` (
  `code` varchar(256) DEFAULT NULL,
  `authentication` blob
) ENGINE=InnoDB DEFAULT CHARSET=utf8

-- -----------------------------------------------------
-- Table `oauth_refresh_token` 刷新令牌
-- -----------------------------------------------------
CREATE TABLE `oauth_refresh_token` (
  `token_id` varchar(256) DEFAULT NULL,
  `token` blob,
  `authentication` blob
) ENGINE=InnoDB DEFAULT CHARSET=utf8
-- -----------------------------------------------------
#（二） 用Java 自带的keytool创建证书库(keyStroe)，生成公钥和私钥
-- -----------------------------------------------------
keytool -genkeypair \
        -alias www.mydomain.com \
        -keyalg RSA \
        –keysize 4096 \
        -keypass mypassword \
        -sigalg SHA256withRSA \
        -dname "cn=www.mydomain.com,ou=xxx,o=xxx,l=Beijing,st=Beijing,c=CN" \ 
        -validity 3650 \
        -keystore www.mydomain.com_keystore.jks \
        -storetype JKS \
        -storepass mypassword
-- -----------------------------------------------------
#（三） 自动授权或者手动授权（页面默认授权scope.read和scope.write）
-- -----------------------------------------------------
client_details表中字段 approve  = true    为自动授权，跳过授权页面
client_details表中字段 approve  =false    为手动授权，进入授权页，经用户确认
-- -----------------------------------------------------
#（四）访问地址
-- -----------------------------------------------------
访问登录也地址：>localhost:8081/oauth/authorize?response_type=code&client_id=ANDEY&redirect_uri=https://www.baidu.com    
获取授权码地址：>localhost:8081/oauth/token  
获取JWTToken的公钥地址>localhost:8081/oauth/token_key  
校验JWTToken信息地址>localhost:8081/oauth/check_token  
-- -----------------------------------------------------
#（五）重点理解：  
认证是由AuthenticationManager来管理，但是真正进行认证的是AuthenticationManager中定义的AuthenticationProvider。AuthenticationManager中可以定义有多个AuthenticationProvider.  
当我们使用authentication-provider元素来定义一个AuthenticationProvider时，如果没有指定对应关联的AuthenticationProvider对象，Spring Security默认会使用DaoAuthentiationProvider。DaoAuthenticationProvider再进行认证的时候需要一个UserDetailService来获取用户的信息UserDetails,其中包括用户名、密码和所拥有的权限等.  
所以如果我们需要改变认证的方式，我们可以实现自己的AuthenticationProvider.如果需要改变认证的用户的信息来源，我们可以实现UserDetailsService.
-- -----------------------------------------------------



