-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: 02 ديسمبر 2025 الساعة 01:19
-- إصدار الخادم: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `smartwardrobe`
--

-- --------------------------------------------------------

--
-- بنية الجدول `clothing_items`
--

CREATE TABLE `clothing_items` (
  `item_id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL,
  `category` varchar(50) NOT NULL,
  `color` varchar(50) NOT NULL,
  `size` varchar(20) NOT NULL,
  `last_worn_date` date DEFAULT NULL,
  `weather_type` varchar(50) DEFAULT NULL,
  `is_clean` int(11) DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- إرجاع أو استيراد بيانات الجدول `clothing_items`
--

INSERT INTO `clothing_items` (`item_id`, `name`, `category`, `color`, `size`, `last_worn_date`, `weather_type`, `is_clean`) VALUES
(1, 'Blue Jeans', 'Pants', 'Blue', 'M', '2025-11-25', 'Cold', 1),
(2, 'White T-Shirt', 'Shirt', 'White', 'M', '2025-11-28', 'Warm', 1),
(3, 'Black Blazer', 'Jacket', 'Black', 'L', '2025-11-20', 'Cold', 1),
(4, 'Red Dress', 'Dress', 'Red', 'S', '2025-11-15', 'Warm', 0),
(5, 'Gray Sweater', 'Sweater', 'Gray', 'M', '2025-11-22', 'Cold', 1),
(6, 'Nike Sneakers', 'Shoes', 'White', '42', '2025-11-27', 'Any', 1),
(7, 'Leather Jacket', 'Jacket', 'Brown', 'L', '2025-11-10', 'Cold', 0),
(8, 'Summer Shorts', 'Shorts', 'Beige', 'M', '2025-11-05', 'Hot', 1),
(9, 'Silk Scarf', 'Accessory', 'Purple', 'One Size', NULL, 'Cold', 1),
(10, 'Denim Jacket', 'Jacket', 'Blue', 'M', '2025-11-18', 'Moderate', 1);

-- --------------------------------------------------------

--
-- بنية الجدول `outfits`
--

CREATE TABLE `outfits` (
  `outfit_id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL,
  `created_date` date DEFAULT curdate(),
  `weather_type` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- إرجاع أو استيراد بيانات الجدول `outfits`
--

INSERT INTO `outfits` (`outfit_id`, `name`, `created_date`, `weather_type`) VALUES
(1, 'SSFD\';L', '2025-12-02', 'HSFDH'),
(2, 'Casual Day Outfit', '2025-11-28', 'Moderate'),
(3, 'Business Meeting', '2025-11-25', 'Cold'),
(4, 'Summer Look', '2025-11-20', 'Hot'),
(5, 'Winter Elegant', '2025-11-15', 'Cold'),
(6, 'Weekend Casual', '2025-11-10', 'Moderate');

-- --------------------------------------------------------

--
-- بنية الجدول `outfit_items`
--

CREATE TABLE `outfit_items` (
  `outfit_id` int(11) NOT NULL,
  `item_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- إرجاع أو استيراد بيانات الجدول `outfit_items`
--

INSERT INTO `outfit_items` (`outfit_id`, `item_id`) VALUES
(1, 1),
(1, 2),
(1, 6),
(2, 2),
(2, 3),
(2, 6),
(3, 2),
(3, 6),
(3, 8),
(4, 3),
(4, 5),
(4, 6),
(5, 1),
(5, 2),
(5, 6);

-- --------------------------------------------------------

--
-- بنية الجدول `users`
--

CREATE TABLE `users` (
  `user_id` int(11) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- إرجاع أو استيراد بيانات الجدول `users`
--

INSERT INTO `users` (`user_id`, `username`, `password`) VALUES
(1, 'admin', 'admin123'),
(2, 'lamia', 'lamia123'),
(3, 'manar', 'manar123'),
(4, 'lubna', 'lubna123');

-- --------------------------------------------------------

--
-- بنية الجدول `weather`
--

CREATE TABLE `weather` (
  `weather_id` int(11) NOT NULL,
  `date` date NOT NULL,
  `temperature` double DEFAULT NULL,
  `weather_condition` varchar(50) DEFAULT NULL,
  `humidity` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- إرجاع أو استيراد بيانات الجدول `weather`
--

INSERT INTO `weather` (`weather_id`, `date`, `temperature`, `weather_condition`, `humidity`) VALUES
(1, '2025-11-28', 22.5, 'Sunny', 45),
(2, '2025-11-27', 18, 'Cloudy', 60),
(3, '2025-11-26', 15.5, 'Rainy', 75),
(4, '2025-11-25', 20, 'Partly Cloudy', 50),
(5, '2025-11-24', 25, 'Sunny', 40),
(6, '2025-11-23', 12, 'Cold', 65),
(7, '2025-11-22', 19.5, 'Moderate', 55);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `clothing_items`
--
ALTER TABLE `clothing_items`
  ADD PRIMARY KEY (`item_id`);

--
-- Indexes for table `outfits`
--
ALTER TABLE `outfits`
  ADD PRIMARY KEY (`outfit_id`);

--
-- Indexes for table `outfit_items`
--
ALTER TABLE `outfit_items`
  ADD PRIMARY KEY (`outfit_id`,`item_id`),
  ADD KEY `item_id` (`item_id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`user_id`),
  ADD UNIQUE KEY `username` (`username`);

--
-- Indexes for table `weather`
--
ALTER TABLE `weather`
  ADD PRIMARY KEY (`weather_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `clothing_items`
--
ALTER TABLE `clothing_items`
  MODIFY `item_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `outfits`
--
ALTER TABLE `outfits`
  MODIFY `outfit_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `user_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `weather`
--
ALTER TABLE `weather`
  MODIFY `weather_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- قيود الجداول المُلقاة.
--

--
-- قيود الجداول `outfit_items`
--
ALTER TABLE `outfit_items`
  ADD CONSTRAINT `outfit_items_ibfk_1` FOREIGN KEY (`outfit_id`) REFERENCES `outfits` (`outfit_id`) ON DELETE CASCADE,
  ADD CONSTRAINT `outfit_items_ibfk_2` FOREIGN KEY (`item_id`) REFERENCES `clothing_items` (`item_id`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
