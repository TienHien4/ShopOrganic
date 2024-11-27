-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th10 25, 2024 lúc 09:33 AM
-- Phiên bản máy phục vụ: 10.4.32-MariaDB
-- Phiên bản PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `shopmikuh`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `admin`
--

CREATE TABLE `admin` (
  `admin_id` bigint(20) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `cart_item`
--

CREATE TABLE `cart_item` (
  `item_id` int(11) NOT NULL,
  `quantity` int(11) DEFAULT NULL,
  `total_price` double DEFAULT NULL,
  `shopping_cart_id` int(11) DEFAULT NULL,
  `product_id` int(11) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `cart_item`
--

INSERT INTO `cart_item` (`item_id`, `quantity`, `total_price`, `shopping_cart_id`, `product_id`) VALUES
(21, 1, 3750, 17, 20),
(19, 1, 2250, 17, 18);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `customer_roles`
--

CREATE TABLE `customer_roles` (
  `customer_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `customer_roles`
--

INSERT INTO `customer_roles` (`customer_id`, `role_id`) VALUES
(1, 2),
(2, 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `don_hang`
--

CREATE TABLE `don_hang` (
  `id` int(11) NOT NULL,
  `date` varchar(255) DEFAULT NULL,
  `name_buyer` varchar(255) DEFAULT NULL,
  `name_product` varchar(255) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `size` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `hibernate_sequence`
--

CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `hibernate_sequence`
--

INSERT INTO `hibernate_sequence` (`next_val`) VALUES
(22);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `nguoi_dung`
--

CREATE TABLE `nguoi_dung` (
  `customer_id` int(11) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `full_name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  `sex` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `nguoi_dung`
--

INSERT INTO `nguoi_dung` (`customer_id`, `address`, `email`, `full_name`, `password`, `phone_number`, `sex`, `username`) VALUES
(1, NULL, 'member@gmail.com', NULL, '$2a$10$.muELCQWqsCddUWoRCTMi.gdE5S3qP/V66ECs.RXeXMB7FnX.rmb6', NULL, NULL, 'member'),
(2, NULL, 'admin@gmail.com', NULL, '$2a$10$nRmFyTIA3bK3YkwzGMA6X.dAL1Jhkt0pNYIKTXNVuAR9MZPLeup9C', NULL, NULL, 'admin');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `role`
--

CREATE TABLE `role` (
  `role_id` int(11) NOT NULL,
  `role_name` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `role`
--

INSERT INTO `role` (`role_id`, `role_name`) VALUES
(1, 'ROLE_ADMIN'),
(2, 'ROLE_USER');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `roles_ad`
--

CREATE TABLE `roles_ad` (
  `admin_id` bigint(20) NOT NULL,
  `role_id` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `san_pham`
--

CREATE TABLE `san_pham` (
  `product_id` int(11) NOT NULL,
  `name_product` varchar(255) DEFAULT NULL,
  `sale` double DEFAULT NULL,
  `photo` varchar(255) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `product_information` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `weight` double DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `san_pham`
--

INSERT INTO `san_pham` (`product_id`, `name_product`, `sale`, `photo`, `price`, `product_information`, `type`, `weight`) VALUES
(20, 'Hamburger', 25, 'product-5.jpg', 5000, '.....', 'Thịt', 2),
(18, 'Chuối', 10, 'product-2.jpg', 2500, '.....', 'Hoa quả', 1),
(7, 'Nho', 15, 'product-4.jpg', 4500, '.....', 'Hoa quả', 2),
(8, 'Xoài', 0, 'product-6.jpg', 2000, '.....', 'Hoa quả', 3),
(9, 'Dưa hấu', 30, 'product-7.jpg', 3000, '.....', 'Hoa quả', 5),
(10, 'Táo', 10, 'product-8.jpg', 1000, '.....', 'Hoa quả', 1),
(11, 'Sữa thiên nhiên', 10, 'product-11.jpg', 2500, '.....', 'Sữa', 2),
(12, 'Đùi gà chiên', 0, 'product-10.jpg', 5000, '.....', 'Thịt', 3);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `shopping_cart`
--

CREATE TABLE `shopping_cart` (
  `shopping_cart_id` int(11) NOT NULL,
  `total_items` int(11) DEFAULT NULL,
  `total_prices` double DEFAULT NULL,
  `customer_id` int(11) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `shopping_cart`
--

INSERT INTO `shopping_cart` (`shopping_cart_id`, `total_items`, `total_prices`, `customer_id`) VALUES
(17, 2, 6000, 1);

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`admin_id`);

--
-- Chỉ mục cho bảng `cart_item`
--
ALTER TABLE `cart_item`
  ADD PRIMARY KEY (`item_id`),
  ADD KEY `FKe89gjdx91fxnmkkssyoim8xfu` (`shopping_cart_id`),
  ADD KEY `FKjwdbnguytqu0qap69q0ye86et` (`product_id`);

--
-- Chỉ mục cho bảng `customer_roles`
--
ALTER TABLE `customer_roles`
  ADD KEY `FK6jcf0ued4skjxo97h7s2l2s8o` (`role_id`),
  ADD KEY `FKsxq15rt0bm5t10r94xppue113` (`customer_id`);

--
-- Chỉ mục cho bảng `don_hang`
--
ALTER TABLE `don_hang`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `nguoi_dung`
--
ALTER TABLE `nguoi_dung`
  ADD PRIMARY KEY (`customer_id`);

--
-- Chỉ mục cho bảng `role`
--
ALTER TABLE `role`
  ADD PRIMARY KEY (`role_id`);

--
-- Chỉ mục cho bảng `roles_ad`
--
ALTER TABLE `roles_ad`
  ADD KEY `FKeae2kipyxnbdq0j90n8lqiqmj` (`role_id`),
  ADD KEY `FKdi27u7p6aw82ubr85bor74s26` (`admin_id`);

--
-- Chỉ mục cho bảng `san_pham`
--
ALTER TABLE `san_pham`
  ADD PRIMARY KEY (`product_id`);

--
-- Chỉ mục cho bảng `shopping_cart`
--
ALTER TABLE `shopping_cart`
  ADD PRIMARY KEY (`shopping_cart_id`),
  ADD KEY `FKix2gn93t37q3cjnompr65fexo` (`customer_id`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `admin`
--
ALTER TABLE `admin`
  MODIFY `admin_id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `nguoi_dung`
--
ALTER TABLE `nguoi_dung`
  MODIFY `customer_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
