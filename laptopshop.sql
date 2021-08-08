-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Feb 27, 2021 at 09:14 AM
-- Server version: 10.4.17-MariaDB
-- PHP Version: 7.3.26

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `laptopshop`
--

-- --------------------------------------------------------

--
-- Table structure for table `cart`
--

CREATE TABLE `cart` (
  `id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `price` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `customer`
--

CREATE TABLE `customer` (
  `id` int(10) NOT NULL,
  `first_name` varchar(20) DEFAULT NULL,
  `last_name` varchar(20) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(20) DEFAULT NULL,
  `address` varchar(45) DEFAULT NULL,
  `contact` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `customer`
--

INSERT INTO `customer` (`id`, `first_name`, `last_name`, `username`, `password`, `address`, `contact`) VALUES
(1, 'Uzumaki', 'Naruto', 'naruto', 'naruto123', '1490, Lorong BSS, Konohagakure, Hi No Kuni, J', '601822475698'),
(2, 'Uchiha', 'Sasuke', 'sasuke', 'sasuke123', '1480, Lorong BSS, Konohagakure, Hi No Kuni, J', '6019874569'),
(3, 'Kakashi', 'Hatake', 'kakashi', 'kakashi123', '1371, Lorong BSS, Konohagakure, Hi No Kuni, J', '60134568796'),
(19, 'Shahrul', 'Asyraf', 'moltack', 'hello', '1490\r\nJalan BSS 2/3B', '60182249285');

-- --------------------------------------------------------

--
-- Table structure for table `product`
--

CREATE TABLE `product` (
  `id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `description` varchar(255) NOT NULL,
  `price` double NOT NULL,
  `image` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `product`
--

INSERT INTO `product` (`id`, `name`, `description`, `price`, `image`) VALUES
(1, 'Asus ROG Zephyrus G14', 'CPU: AMD Ryzen 7 4800HS – 9 4900HS\r\nGraphics: NVIDIA GeForce RTX 2060\r\nRAM: Up to 32GB\r\nScreen: 14-inch Non-glare Full HD (1920 x 1080) IPS-level panel, 120Hz – 14-inch Non-glare WQHD (2560 x 1440) IPS-level panel, 60Hz\r\nStorage: 512GB / 1TB M.2 NVMe PCIe', 6732, 'HGyrHVsM8ij44hUgTywwCB.jpg'),
(2, 'Asus TUF Dash F15', 'CPU: Up to Intel Core i7-11375H\r\nGraphics: Up to NVIDIA GeForce RTX 3070\r\nRAM: Up to 16GB DDR4-3200\r\nScreen: Up to 15.6-inch WQHD anti-glare 165Hz IPS-level display with Adaptive Sync\r\nStorage: Up to 1TB SSD', 7299, 'P_setting_fff_1_90_end_600.jpg'),
(3, 'Alienware M15 R3 (2020)', 'CPU: Up to 10th Generation Intel Core i9-10980HK\r\nGraphics: Up to NVIDIA GeForce RTX 2080 Super\r\nRAM: Up to 32GB DDR4\r\nDisplay: 15.6\" FHD (1920 x 1080) 144Hz 7ms 300-nits 72% NTSC – 15.6\" OLED UHD (3840 x 2160) 60Hz 1ms 400-nits DCI-P3\r\nStorage: Up to 4TB', 8499, 'uRdR3BUTUShLGKMqxDmG77.jpg'),
(4, 'Alienware m17 R4 (2021)', 'CPU: Up to 10th Generation Intel Core i9-10980HK\r\nGraphics: Up to NVIDIA GeForce RTX 3080\r\nRAM: Up to 32GB DDR4 2933MHz\r\nScreen: 17.3\" UHD 60Hz 25ms 500-nits display\r\nStorage: Up to 4TB SSD + 512GB SSD', 9999, 'dell-m15-m17-series-938929-1610614973.jpg'),
(5, 'Razer Blade Pro 17 (2021) ', 'CPU: 10th-generation Intel Core i7\r\nGraphics: NVIDIA GeForce RTX 3060 – 3080\r\nRAM: Up to 32GB\r\nScreen: 17.3\" Full HD 360Hz – 17.3\" 4K Touch 120Hz\r\nStorage: 512GB – 1TB SSD', 10999, 'blade15shot.jpg'),
(6, 'Gigabyte Aorus 17G', 'CPU: 10th-generation Intel Core i7 – i9\r\nGraphics: up to NVIDIA GeForce RTX 2080 Super with Max-Q\r\nRAM: Up to 64GB DDR4\r\nScreen: 17.3-inch Thin Bezel 240Hz FHD 1920x1080 anti-glare display LCD\r\nStorage: 2x M.2 SSD', 5799, 'gigabyte_aorus_17g_xb_8us2130mp_17_3_aorus_17g_gaming_1551909.jpg'),
(7, 'Dell G5 15 SE (2020)', 'CPU: AMD Ryzen 5 4600H – 7 4800H\r\nGraphics: AMD Radeon RX 5600M\r\nRAM: 8GB – 16GB\r\nScreen: 15.6-inch FHD (1920 x 1080) Anti-Glare LED Backlight Non-Touch Narrow Border WVA Display – 15.6 inch FHD(1920x1080) 300nits WVA Anti-Glare LED Backlit Display(non-to', 4199, 'Dell-G5-15-Special-Edition-Ryzen_display-view.jpg');

-- --------------------------------------------------------

--
-- Table structure for table `staff`
--

CREATE TABLE `staff` (
  `id` int(11) NOT NULL,
  `staffname` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `staff`
--

INSERT INTO `staff` (`id`, `staffname`, `username`, `password`) VALUES
(101, 'jamee', 'shahriyar', 'hello'),
(103, 'shahrul', 'shahrul', 'helloworld');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `cart`
--
ALTER TABLE `cart`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `customer`
--
ALTER TABLE `customer`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `staff`
--
ALTER TABLE `staff`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `cart`
--
ALTER TABLE `cart`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT for table `customer`
--
ALTER TABLE `customer`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
