-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Máy chủ: localhost
-- Thời gian đã tạo: Th12 31, 2022 lúc 09:26 AM
-- Phiên bản máy phục vụ: 10.4.21-MariaDB
-- Phiên bản PHP: 7.3.31

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `assJ5`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `carts`
--

CREATE TABLE `carts` (
  `id` int(11) NOT NULL,
  `customer_id` int(11) DEFAULT NULL,
  `product_id` int(11) DEFAULT NULL,
  `quantity` int(11) NOT NULL,
  `price` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `categorys`
--

CREATE TABLE `categorys` (
  `id` int(11) NOT NULL,
  `name_category` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `categorys`
--

INSERT INTO `categorys` (`id`, `name_category`) VALUES
(1, 'Iphone'),
(2, 'SamSung'),
(3, 'Xiaomi'),
(5, 'OPPO');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `comments`
--

CREATE TABLE `comments` (
  `id` int(11) NOT NULL,
  `customer_id` int(11) DEFAULT NULL,
  `product_id` int(11) DEFAULT NULL,
  `create_date` datetime NOT NULL,
  `nd` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `rate` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `comments`
--

INSERT INTO `comments` (`id`, `customer_id`, `product_id`, `create_date`, `nd`, `rate`) VALUES
(13, 2, 29, '2022-01-25 16:35:57', 'aaa', 3),
(14, 2, 30, '2022-01-25 16:43:03', 'xịn xò', 3),
(15, 3, 46, '2022-01-25 16:45:17', 'tệ', 2),
(16, 2, 27, '2022-02-03 20:45:50', 'hay\n', 3),
(17, 2, 32, '2022-02-03 20:51:49', 'tốt', 2),
(18, 5, 27, '2022-03-16 21:01:07', 'xịn đó', 2),
(19, 2, 34, '2022-05-08 16:34:59', 'sản phẩm rất tốt', 2),
(20, 2, 44, '2022-05-17 22:31:16', 'hihih', 2),
(21, 2, 43, '2022-06-06 15:46:20', 'san pham ok', 4),
(22, 2, 46, '2022-12-31 15:20:07', 'san pham tot', 3);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `customers`
--

CREATE TABLE `customers` (
  `id` int(11) NOT NULL,
  `email` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `fullname` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `pass` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `phone` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `point` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `customers`
--

INSERT INTO `customers` (`id`, `email`, `fullname`, `pass`, `phone`, `point`) VALUES
(2, 'hieuntph15836@fpt.edu.vn', 'Nguyễn Trung Hiếu', '$2a$10$ft61q1Sw3kS./7moJmADAeVlp9OnXlpmJFPncgjTFT8D.AL1T1nMW', '0868673823', 1),
(3, 'ngoc@gmail.com', 'Nguyễn Bảo Ngọc', '$2a$10$ZHT45/gRhQE3o7ncR.PIhO6d8EMgvJZzqIJoO2a79VVW.xsx9j4OG', '0372623892', 1),
(4, 'nam@gmail.com', 'Nguyễn Trung Hiếu', '$2a$10$fjSpF491zg4a6Oz9cmr5NuwSPaUy4uKhjxkX6JoJK4zeI59oXL5Ju', '0868673823', 1),
(5, 'admin1@gmail.com', 'aaaaa', '$2a$10$lgCfWW0P5v4uIP5TgcBr8Ot3fzwj9edZkBuuVgtBZYotNaCvUi01i', '0372623892', 1),
(6, 'phupm@gmail.com', 'Phú đẹp trai', '$2a$10$/CT9WEb88yncoYyc3xs.GenF2cYthx40Zh1H4auYWraaGU/o1I4gq', '123', 1),
(7, 'viet@gmail.com', 'Mạnh Việt', '$2a$10$uZJvVPRS6ZHiVrmX6v9UJOtcTuhzh7s7e.Zb84gcX5OayM0rN/N4m', '0372623892', 1),
(8, 'phupm1@gmail.com', 'Phú PM', '$2a$10$O/TfLcNnKyFBfe9JUTT7fuAXoklKLga7EM6h87C524akFqfX4OOti', '0868673821', 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `orders`
--

CREATE TABLE `orders` (
  `id` int(11) NOT NULL,
  `address` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `create_date` datetime NOT NULL,
  `status_money` int(11) NOT NULL,
  `status_ship` int(11) NOT NULL,
  `total` int(11) NOT NULL,
  `customer_id` int(11) DEFAULT NULL,
  `status` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `orders`
--

INSERT INTO `orders` (`id`, `address`, `create_date`, `status_money`, `status_ship`, `total`, `customer_id`, `status`) VALUES
(66, '23', '2022-12-31 15:19:28', 1, 2, 10000000, 2, 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `order_details`
--

CREATE TABLE `order_details` (
  `id` int(11) NOT NULL,
  `price` int(11) NOT NULL,
  `quantity` int(11) NOT NULL,
  `order_id` int(11) DEFAULT NULL,
  `product_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `order_details`
--

INSERT INTO `order_details` (`id`, `price`, `quantity`, `order_id`, `product_id`) VALUES
(125, 10000000, 1, 66, 46);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `products`
--

CREATE TABLE `products` (
  `id` int(11) NOT NULL,
  `name_product` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `photo` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `price` int(11) NOT NULL,
  `quantity` int(11) NOT NULL,
  `category_id` int(11) DEFAULT NULL,
  `capacity` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `active` int(11) DEFAULT NULL,
  `rate` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `products`
--

INSERT INTO `products` (`id`, `name_product`, `photo`, `price`, `quantity`, `category_id`, `capacity`, `active`, `rate`) VALUES
(27, 'IPhone 13 64 GB', '27.jpeg', 2000000, 50, 1, '64 GB', 1, 2),
(28, 'Iphone 12 Pro 64 GB', '28.jpeg', 10000000, 256, 1, '64 GB', 1, 5),
(29, 'Iphone 13 Pro Max 64 GB', '29.jpeg', 10000000, 52, 1, '64 GB', 1, 5),
(30, 'Iphone 5 ', '30.jpeg', 10000000, 14, 1, '64 GB', 1, 4),
(31, 'Iphone 13 Pro  120 GB', '31.jpeg', 520, 17, 1, '120 GB', 0, 5),
(32, 'Iphone 14 Limited ', '32.jpeg', 900, 18, 1, '120 GB', 0, 2),
(34, 'SamSung A12', '34.jpeg', 10000000, 9, 2, '120 GB', 1, 2),
(35, 'SamSung A3', '35.jpeg', 10000000, -2, 2, '120 GB', 1, 5),
(36, 'SamSung A4', '36.jpeg', 10000000, 97, 2, '120 GB', 1, 5),
(37, 'Samsung Galaxy Z Flip3 5G 120GB', '37.jpeg', 10000000, 5, 2, '120 GB', 1, 5),
(38, 'SamSung A512 120 GB', '38.jpg', 10000000, 30, 2, '120 GB', 1, 5),
(39, 'Xiaomi Redmi Note 8 120 GB', '39.jpeg', 10000000, 42, 3, '120 GB', 1, 5),
(40, 'Xiaomi Note 9 120 GB', '40.jpeg', 300, 20, 3, '120 GB', 1, 5),
(41, 'Xiaomi note 10 120 GB', '41.jpeg', 600, 30, 3, '120 GB', 1, 5),
(42, 'Xiaomi Redmi 60 GB', '42.jpeg', 400, 50, 3, '64 GB', 1, 5),
(43, 'Xiaomi Redmi 9 60 GB', '43.jpeg', 500, 9, 3, '64 GB', 1, 4),
(44, 'Oppo A15 64 GB', '44.jpeg', 250, 17, 5, '64 GB', 1, 2),
(45, 'OPPO A53 64 GB', '45.jpeg', 400, 3, 5, '64 GB', 1, 5),
(46, 'OPPO A74 64 GB', '46.jpeg', 10000000, 21, 5, '64 GB', 1, 2),
(47, 'OPPO NTH 120 GB', '47.jpeg', 650, 23, 5, '120 GB', 1, 5),
(48, 'OPPO MTP 120 GB', '48.jpeg', 680, 25, 5, '120 GB', 1, 5),
(49, 'OPPO A92 120 GB', '49.jpeg', 150, 26, 5, '120 GB', 1, 5),
(50, 'Iphone XS Max', '50.jpeg', 155, 11, 1, '120 GB', 0, 5),
(57, 'bbbb', '57', 123000000, 11, 1, '123', 0, 5),
(58, 'aaazzzz', '58', 1000000000, 11, 2, '123', 0, 5),
(59, 'aaazzzzádfádf', '59', 1000000000, 123, 1, '123', 0, 5),
(60, 'fsdfasdf', '60', 1000000000, 11, 1, '123', 1, 5),
(61, 'hieupro', '61', 1000000000, 11, 1, '123', 1, 5),
(62, 'bbbzqwe', '62', 100, 11, 1, '123', 1, 5);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `active` int(11) NOT NULL DEFAULT 1,
  `email` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `fullname` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `photo` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `role` int(11) NOT NULL,
  `pass` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `users`
--

INSERT INTO `users` (`id`, `active`, `email`, `fullname`, `photo`, `role`, `pass`) VALUES
(1, 1, 'phupm@gmail.com', 'Phúuuuu', '1.jpg', 0, '$2a$10$sJH08IFfMU03CIQ3l.APSe7GO8HAWVHKwYQVJjEK9a37nnAs.7Jki'),
(6, 1, 'nam@gmail.com', 'Nguyễn Bảo Ngọc', '6.png', 0, '$2a$10$Ua6sXgRtJGiSiTng4r8MA.2Z6QLnB5/Vglts1bUa.9a4jXxWPXvgC'),
(8, 1, 'hieu@gmail.com', 'Nguyễn Văn Tèooo', '8.png', 0, '$2a$10$ORG8j74GWltYk5jd65YYPuBj8dAP7jzY6Q3WCHiGK9s6O2SokGeoS'),
(9, 1, 'hieuntph15836@fpt.edu.vn', 'Nguyễn Trung Hiếu', '9.jpg', 1, '$2a$10$W/GbOUJMYIKmxtdHq7XE/.KtG1hOuAyRdA7VIg.u9Aq5AT8bMaGqC'),
(12, 1, 'hieu111111@gmail.com', 'Trong IntentService, phương thức onHandlerIntent sẽ được tự động gọi trong phương thức nào?', '12', 0, '$2a$10$Ee992XClPUm.aymA8AD8hOG05d0JPJ8QCmNu8ZdBKNis0rDx4qsva');

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `carts`
--
ALTER TABLE `carts`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK8ba3sryid5k8a9kidpkvqipyt` (`customer_id`),
  ADD KEY `FKmd2ap4oxo3wvgkf4fnaye532i` (`product_id`);

--
-- Chỉ mục cho bảng `categorys`
--
ALTER TABLE `categorys`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `comments`
--
ALTER TABLE `comments`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKev1bd87g1c51ujncao608e6qa` (`customer_id`),
  ADD KEY `FK6uv0qku8gsu6x1r2jkrtqwjtn` (`product_id`);

--
-- Chỉ mục cho bảng `customers`
--
ALTER TABLE `customers`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_rfbvkrffamfql7cjmen8v976v` (`email`);

--
-- Chỉ mục cho bảng `orders`
--
ALTER TABLE `orders`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKpxtb8awmi0dk6smoh2vp1litg` (`customer_id`);

--
-- Chỉ mục cho bảng `order_details`
--
ALTER TABLE `order_details`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKjyu2qbqt8gnvno9oe9j2s2ldk` (`order_id`),
  ADD KEY `FK4q98utpd73imf4yhttm3w0eax` (`product_id`);

--
-- Chỉ mục cho bảng `products`
--
ALTER TABLE `products`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKr638shrnkkh3wy5llr9cwyi4t` (`category_id`);

--
-- Chỉ mục cho bảng `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_6dotkott2kjsp8vw4d0m25fb7` (`email`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `carts`
--
ALTER TABLE `carts`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=167;

--
-- AUTO_INCREMENT cho bảng `categorys`
--
ALTER TABLE `categorys`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT cho bảng `comments`
--
ALTER TABLE `comments`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=23;

--
-- AUTO_INCREMENT cho bảng `customers`
--
ALTER TABLE `customers`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT cho bảng `orders`
--
ALTER TABLE `orders`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=67;

--
-- AUTO_INCREMENT cho bảng `order_details`
--
ALTER TABLE `order_details`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=126;

--
-- AUTO_INCREMENT cho bảng `products`
--
ALTER TABLE `products`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=63;

--
-- AUTO_INCREMENT cho bảng `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `carts`
--
ALTER TABLE `carts`
  ADD CONSTRAINT `FK8ba3sryid5k8a9kidpkvqipyt` FOREIGN KEY (`customer_id`) REFERENCES `customers` (`id`),
  ADD CONSTRAINT `FKmd2ap4oxo3wvgkf4fnaye532i` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`);

--
-- Các ràng buộc cho bảng `comments`
--
ALTER TABLE `comments`
  ADD CONSTRAINT `FK6uv0qku8gsu6x1r2jkrtqwjtn` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`),
  ADD CONSTRAINT `FKev1bd87g1c51ujncao608e6qa` FOREIGN KEY (`customer_id`) REFERENCES `customers` (`id`);

--
-- Các ràng buộc cho bảng `orders`
--
ALTER TABLE `orders`
  ADD CONSTRAINT `FKpxtb8awmi0dk6smoh2vp1litg` FOREIGN KEY (`customer_id`) REFERENCES `customers` (`id`);

--
-- Các ràng buộc cho bảng `order_details`
--
ALTER TABLE `order_details`
  ADD CONSTRAINT `FK4q98utpd73imf4yhttm3w0eax` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`),
  ADD CONSTRAINT `FKjyu2qbqt8gnvno9oe9j2s2ldk` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`);

--
-- Các ràng buộc cho bảng `products`
--
ALTER TABLE `products`
  ADD CONSTRAINT `FKr638shrnkkh3wy5llr9cwyi4t` FOREIGN KEY (`category_id`) REFERENCES `categorys` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
