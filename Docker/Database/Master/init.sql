CREATE DATABASE IF NOT EXISTS `online_order` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;

-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- 主机： 127.0.0.1
-- 生成日期： 2024-03-16 06:24:29
-- 服务器版本： 10.4.32-MariaDB
-- PHP 版本： 8.2.12
USE `online_order`;

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;

-- MySQL dump 10.13  Distrib 8.0.19, for Win64 (x86_64)
--
-- Host: 1.94.103.66    Database: test
-- ------------------------------------------------------
-- Server version	8.0.36-0ubuntu0.22.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `active_user`
--

DROP TABLE IF EXISTS `active_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `active_user` (
  `active_user_id` int NOT NULL,
  `last_login_time` timestamp NULL DEFAULT NULL,
  `ip` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`active_user_id`),
  CONSTRAINT `active_user_user_FK` FOREIGN KEY (`active_user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `active_user`
--

LOCK TABLES `active_user` WRITE;
/*!40000 ALTER TABLE `active_user` DISABLE KEYS */;
/*!40000 ALTER TABLE `active_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `address`
--

DROP TABLE IF EXISTS `address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `address` (
  `address_id` int NOT NULL AUTO_INCREMENT,
  `user_id` int DEFAULT NULL,
  `address_detail` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
  `is_recent_use` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`address_id`),
  KEY `address_user_FK` (`user_id`),
  CONSTRAINT `address_user_FK` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `address`
--

LOCK TABLES `address` WRITE;
/*!40000 ALTER TABLE `address` DISABLE KEYS */;
/*!40000 ALTER TABLE `address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `case`
--

DROP TABLE IF EXISTS `case`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `case` (
  `case_id` int NOT NULL AUTO_INCREMENT,
  `staff_id` int DEFAULT NULL,
  `user_id` int DEFAULT NULL,
  `record` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
  `state` int DEFAULT NULL,
  `start_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `end_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`case_id`),
  KEY `case_user_FK_1` (`user_id`),
  KEY `case_staff_FK` (`staff_id`),
  CONSTRAINT `case_staff_FK` FOREIGN KEY (`staff_id`) REFERENCES `staff` (`staff_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `case_user_FK` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `case`
--

LOCK TABLES `case` WRITE;
/*!40000 ALTER TABLE `case` DISABLE KEYS */;
/*!40000 ALTER TABLE `case` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `category` (
  `category_id` int NOT NULL AUTO_INCREMENT,
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
  `image` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`category_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comment_of_feedback`
--

DROP TABLE IF EXISTS `comment_of_feedback`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comment_of_feedback` (
  `comment_id` int NOT NULL AUTO_INCREMENT,
  `feedback_id` int DEFAULT NULL,
  `comment_user_id` int DEFAULT NULL,
  `time` timestamp NULL DEFAULT NULL,
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
  `to_user_id` int DEFAULT NULL,
  PRIMARY KEY (`comment_id`),
  KEY `comment_of_feedback_user_FK` (`comment_user_id`),
  KEY `comment_of_feedback_user_FK_1` (`to_user_id`),
  KEY `comment_of_feedback_feedback_of_order_FK` (`feedback_id`),
  CONSTRAINT `comment_of_feedback_feedback_of_order_FK` FOREIGN KEY (`feedback_id`) REFERENCES `feedback_of_order` (`order_feedback_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `comment_of_feedback_user_FK` FOREIGN KEY (`comment_user_id`) REFERENCES `user` (`user_id`),
  CONSTRAINT `comment_of_feedback_user_FK_1` FOREIGN KEY (`to_user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment_of_feedback`
--

LOCK TABLES `comment_of_feedback` WRITE;
/*!40000 ALTER TABLE `comment_of_feedback` DISABLE KEYS */;
/*!40000 ALTER TABLE `comment_of_feedback` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer_requirement`
--

DROP TABLE IF EXISTS `customer_requirement`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer_requirement` (
  `requirement_id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `requirement_detail` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
  `is_recent_use` int DEFAULT NULL,
  PRIMARY KEY (`requirement_id`),
  KEY `customer_requirement_user_FK` (`user_id`),
  CONSTRAINT `customer_requirement_user_FK` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer_requirement`
--

LOCK TABLES `customer_requirement` WRITE;
/*!40000 ALTER TABLE `customer_requirement` DISABLE KEYS */;
/*!40000 ALTER TABLE `customer_requirement` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `device`
--

DROP TABLE IF EXISTS `device`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `device` (
  `device_id` int NOT NULL AUTO_INCREMENT,
  `token` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `mac_address` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `enable` int NOT NULL,
  `remove_times` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`device_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `device`
--

LOCK TABLES `device` WRITE;
/*!40000 ALTER TABLE `device` DISABLE KEYS */;
/*!40000 ALTER TABLE `device` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `feedback_of_delivery`
--

DROP TABLE IF EXISTS `feedback_of_delivery`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `feedback_of_delivery` (
  `delivery_feedback_id` int NOT NULL AUTO_INCREMENT,
  `stars` int DEFAULT NULL,
  `feedback` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `order_id` int DEFAULT NULL,
  `rider_id` int DEFAULT NULL,
  `user_id` int DEFAULT NULL,
  PRIMARY KEY (`delivery_feedback_id`),
  KEY `feedback_of_delivery_rider_FK` (`rider_id`),
  KEY `feedback_of_delivery_order_FK` (`order_id`),
  KEY `feedback_of_delivery_user_FK` (`user_id`),
  CONSTRAINT `feedback_of_delivery_order_FK` FOREIGN KEY (`order_id`) REFERENCES `order` (`order_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `feedback_of_delivery_rider_FK` FOREIGN KEY (`rider_id`) REFERENCES `rider` (`rider_id`),
  CONSTRAINT `feedback_of_delivery_user_FK` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `feedback_of_delivery`
--

LOCK TABLES `feedback_of_delivery` WRITE;
/*!40000 ALTER TABLE `feedback_of_delivery` DISABLE KEYS */;
/*!40000 ALTER TABLE `feedback_of_delivery` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `feedback_of_order`
--

DROP TABLE IF EXISTS `feedback_of_order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `feedback_of_order` (
  `order_feedback_id` int NOT NULL AUTO_INCREMENT,
  `stars` int DEFAULT NULL,
  `order_item_id` int DEFAULT NULL,
  `feedback` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `store_id` int NOT NULL,
  PRIMARY KEY (`order_feedback_id`),
  KEY `feedback_of_order_store_list_FK` (`store_id`),
  CONSTRAINT `feedback_of_order_store_list_FK` FOREIGN KEY (`store_id`) REFERENCES `store_list` (`store_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `feedback_of_order`
--

LOCK TABLES `feedback_of_order` WRITE;
/*!40000 ALTER TABLE `feedback_of_order` DISABLE KEYS */;
/*!40000 ALTER TABLE `feedback_of_order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order`
--

DROP TABLE IF EXISTS `order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order` (
  `order_id` int NOT NULL AUTO_INCREMENT,
  `total_price` double DEFAULT NULL,
  `total_discount_id` int DEFAULT NULL,
  `order_date` datetime(6) DEFAULT NULL,
  `purchased_date` datetime(6) DEFAULT NULL,
  `payment_method` int DEFAULT NULL,
  `order_state` int DEFAULT NULL,
  `location` int DEFAULT NULL,
  `dining_style` int DEFAULT NULL,
  `requirement_id` int DEFAULT NULL,
  `table_number` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `address_id` int DEFAULT NULL,
  `actual_payment` double DEFAULT NULL,
  `rider_id` int DEFAULT NULL,
  `user_id` int DEFAULT NULL,
  `store_id` int NOT NULL,
  PRIMARY KEY (`order_id`),
  KEY `order_user_FK` (`user_id`),
  KEY `order_total_discount_FK` (`total_discount_id`),
  KEY `order_customer_requirement_FK` (`requirement_id`),
  KEY `order_address_FK` (`address_id`),
  KEY `order_rider_FK` (`rider_id`),
  KEY `order_store_list_FK` (`store_id`),
  CONSTRAINT `order_address_FK` FOREIGN KEY (`address_id`) REFERENCES `address` (`address_id`),
  CONSTRAINT `order_customer_requirement_FK` FOREIGN KEY (`requirement_id`) REFERENCES `customer_requirement` (`requirement_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `order_rider_FK` FOREIGN KEY (`rider_id`) REFERENCES `rider` (`rider_id`),
  CONSTRAINT `order_store_list_FK` FOREIGN KEY (`store_id`) REFERENCES `store_list` (`store_id`),
  CONSTRAINT `order_total_discount_FK` FOREIGN KEY (`total_discount_id`) REFERENCES `total_discount` (`total_discount_id`),
  CONSTRAINT `order_user_FK` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order`
--

LOCK TABLES `order` WRITE;
/*!40000 ALTER TABLE `order` DISABLE KEYS */;
/*!40000 ALTER TABLE `order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_detail`
--

DROP TABLE IF EXISTS `order_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order_detail` (
  `order_detail_id` int NOT NULL AUTO_INCREMENT,
  `product_id` int NOT NULL,
  `order_id` int DEFAULT NULL,
  `quantity` int DEFAULT NULL,
  PRIMARY KEY (`order_detail_id`),
  KEY `order_detail_product_FK` (`product_id`),
  KEY `order_detail_order_FK` (`order_id`),
  CONSTRAINT `order_detail_order_FK` FOREIGN KEY (`order_id`) REFERENCES `order` (`order_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `order_detail_product_FK` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_detail`
--

LOCK TABLES `order_detail` WRITE;
/*!40000 ALTER TABLE `order_detail` DISABLE KEYS */;
/*!40000 ALTER TABLE `order_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_detail_spice`
--

DROP TABLE IF EXISTS `order_detail_spice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order_detail_spice` (
  `order_detail_id` int NOT NULL,
  `spice_id` int NOT NULL,
  PRIMARY KEY (`order_detail_id`, `spice_id`),
  KEY `order_detail_spice_order_detail_FK` (`order_detail_id`),
  KEY `order_detail_spice_spice_FK` (`spice_id`),
  CONSTRAINT `order_detail_spice_order_detail_FK` FOREIGN KEY (`order_detail_id`) REFERENCES `order_detail` (`order_detail_id`),
  CONSTRAINT `order_detail_spice_spice_FK` FOREIGN KEY (`spice_id`) REFERENCES `spice` (`spice_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_detail_spice`
--

LOCK TABLES `order_detail_spice` WRITE;
/*!40000 ALTER TABLE `order_detail_spice` DISABLE KEYS */;
/*!40000 ALTER TABLE `order_detail_spice` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product` (
  `product_id` int NOT NULL AUTO_INCREMENT,
  `product_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `price` double NOT NULL,
  `discount` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `category_id` int DEFAULT NULL,
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
  `image` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `favorite` int DEFAULT NULL,
  `sale_count` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `is_recommend` int DEFAULT NULL,
  `is_new` int DEFAULT NULL,
  `discount_starttime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `discount_endtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `quantity` int DEFAULT NULL,
  `specification_id` INT,
  PRIMARY KEY (`product_id`),
  KEY `product_category_FK` (`category_id`),
  CONSTRAINT `product_category_FK` FOREIGN KEY (`category_id`) REFERENCES `category` (`category_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rider`
--

DROP TABLE IF EXISTS `rider`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `rider` (
  `rider_id` int NOT NULL AUTO_INCREMENT,
  `full_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `phone_number` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `email` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `vehicle_type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `license_plate` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `status` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `store_id` int NOT NULL,
  PRIMARY KEY (`rider_id`),
  KEY `rider_store_list_FK` (`store_id`),
  CONSTRAINT `rider_store_list_FK` FOREIGN KEY (`store_id`) REFERENCES `store_list` (`store_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rider`
--

LOCK TABLES `rider` WRITE;
/*!40000 ALTER TABLE `rider` DISABLE KEYS */;
/*!40000 ALTER TABLE `rider` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `spice`
--

DROP TABLE IF EXISTS `spice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `spice` (
  `spice_id` int NOT NULL AUTO_INCREMENT,
  `category` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `country_of_origin` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `price` decimal(10,0) NOT NULL,
  `stock_quantity` int NOT NULL,
  `store_id` int DEFAULT NULL,
  PRIMARY KEY (`spice_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `spice`
--

LOCK TABLES `spice` WRITE;
/*!40000 ALTER TABLE `spice` DISABLE KEYS */;
/*!40000 ALTER TABLE `spice` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `staff`
--

DROP TABLE IF EXISTS `staff`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `staff` (
  `staff_id` int NOT NULL,
  `staff_name` varchar(100) NOT NULL,
  `position` varchar(100) NOT NULL,
  `date_birth` varchar(100) NOT NULL,
  `address` varchar(100) NOT NULL,
  `phone` varchar(100) NOT NULL,
  `shift` varchar(100) NOT NULL,
  `salary` double DEFAULT NULL,
  `employment_status` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `store_id` int NOT NULL,
  PRIMARY KEY (`staff_id`),
  KEY `staff_store_list_FK` (`store_id`),
  CONSTRAINT `staff_store_list_FK` FOREIGN KEY (`store_id`) REFERENCES `store_list` (`store_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `staff`
--

LOCK TABLES `staff` WRITE;
/*!40000 ALTER TABLE `staff` DISABLE KEYS */;
/*!40000 ALTER TABLE `staff` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `store_list`
--

DROP TABLE IF EXISTS `store_list`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `store_list` (
  `store_id` int NOT NULL AUTO_INCREMENT,
  `restaurant_name` varchar(100) NOT NULL,
  `position` varchar(100) NOT NULL,
  `manager` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `phone` varchar(100) NOT NULL,
  `status` varchar(100) NOT NULL,
  `start_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `close_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`store_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `store_list`
--

LOCK TABLES `store_list` WRITE;
/*!40000 ALTER TABLE `store_list` DISABLE KEYS */;
/*!40000 ALTER TABLE `store_list` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `total_discount`
--

DROP TABLE IF EXISTS `total_discount`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `total_discount` (
  `total_discount_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `discount` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `description` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`total_discount_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `total_discount`
--

LOCK TABLES `total_discount` WRITE;
/*!40000 ALTER TABLE `total_discount` DISABLE KEYS */;
/*!40000 ALTER TABLE `total_discount` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `userName` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `fullName` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `passwordHash` char(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `enable` int DEFAULT '1',
  `github_login` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL,
  `facebook_login` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `google_login` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `membership` int DEFAULT NULL,
  `avatar` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `credits` int DEFAULT NULL,
  `visa_pay_id` int DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `Email` (`email`),
  UNIQUE KEY `UserName` (`userName`),
  KEY `user_visa_pay_FK` (`visa_pay_id`),
  CONSTRAINT `user_visa_pay_FK` FOREIGN KEY (`visa_pay_id`) REFERENCES `visa_pay` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1212122 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
/*!40101 SET character_set_client = @saved_cs_client */;



--
-- Table structure for table `verification_code`
--

DROP TABLE IF EXISTS `verification_code`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `verification_code` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `email` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `code` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `time` timestamp NULL DEFAULT NULL,
  `type` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `verification_code`
--

LOCK TABLES `verification_code` WRITE;
/*!40000 ALTER TABLE `verification_code` DISABLE KEYS */;
/*!40000 ALTER TABLE `verification_code` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `visa_pay`
--

DROP TABLE IF EXISTS `visa_pay`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `visa_pay` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int DEFAULT NULL,
  `bank_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `type` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `card_number` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `expire_date` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `secret` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `is_recent_use` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `visa_pay_user_FK` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `visa_pay`
--

LOCK TABLES `visa_pay` WRITE;
/*!40000 ALTER TABLE `visa_pay` DISABLE KEYS */;
/*!40000 ALTER TABLE `visa_pay` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'test'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-04-20 13:22:38
