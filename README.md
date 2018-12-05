# cloud-oauth
spring-cloud-oauth2授权系统
-- -----------------------------------------------------
-- 实现：'授权码'、'密码'、'简易'、'客户端'四种授权模式；
采用MD5密码编码
采用JWt对称加密的Token形式，JWT加密省略了token数据库存储方式
-- -----------------------------------------------------
#数据库存储相关表结构
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
