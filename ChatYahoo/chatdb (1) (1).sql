-- phpMyAdmin SQL Dump
-- version 4.0.4
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Nov 11, 2013 at 03:41 PM
-- Server version: 5.6.12-log
-- PHP Version: 5.4.16

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `chatdb`
--
CREATE DATABASE IF NOT EXISTS `chatdb` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `chatdb`;

-- --------------------------------------------------------

--
-- Table structure for table `tblchathistory`
--

CREATE TABLE IF NOT EXISTS `tblchathistory` (
  `id` bigint(20) NOT NULL,
  `useridA` varchar(255) NOT NULL,
  `useridB` varchar(255) NOT NULL,
  `message` varchar(255) NOT NULL,
  `time` datetime NOT NULL,
  `sender` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `useridA` (`useridA`),
  KEY `useridB` (`useridB`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `tblfriendlist`
--

CREATE TABLE IF NOT EXISTS `tblfriendlist` (
  `id` bigint(255) NOT NULL,
  `useridA` varchar(255) NOT NULL,
  `useridB` varchar(255) NOT NULL,
  `displayNameA` varchar(255) NOT NULL,
  `displayNameB` varchar(255) NOT NULL,
  `isAOnline` int(2) NOT NULL,
  `isBOnline` int(2) NOT NULL,
  `groupidInA` varchar(255) NOT NULL,
  `groupidInB` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `groupidInA` (`groupidInA`),
  KEY `groupidInB` (`groupidInB`),
  KEY `useridB` (`useridB`),
  KEY `useridA` (`useridA`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `tblgroup`
--

CREATE TABLE IF NOT EXISTS `tblgroup` (
  `groupid` varchar(255) NOT NULL,
  `userid` varchar(255) NOT NULL,
  `groupname` varchar(255) NOT NULL,
  PRIMARY KEY (`groupid`),
  KEY `groupname` (`groupname`),
  KEY `userid` (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `tblsmileicon`
--

CREATE TABLE IF NOT EXISTS `tblsmileicon` (
  `iconid` varchar(255) NOT NULL,
  `iconImage` varchar(255) NOT NULL,
  `shortKey` varchar(255) NOT NULL,
  `iconName` varchar(255) NOT NULL,
  PRIMARY KEY (`iconid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `tbluser`
--

CREATE TABLE IF NOT EXISTS `tbluser` (
  `userid` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  `userpassword` varchar(255) NOT NULL,
  `userFirstName` varchar(255) NOT NULL,
  `userLastName` varchar(255) NOT NULL,
  `userNickname` varchar(255) NOT NULL,
  `useremail` varchar(255) NOT NULL,
  `userphone` varchar(255) NOT NULL,
  `dateOfBirth` varchar(255) NOT NULL,
  `gender` int(2) NOT NULL,
  `isOnline` int(2) NOT NULL,
  `statusUpdate` varchar(255) NOT NULL,
  `userHost` varchar(255) NOT NULL,
  `userPort` int(10) NOT NULL,
  `avatarUrl` varchar(255) NOT NULL,
  PRIMARY KEY (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `tblchathistory`
--
ALTER TABLE `tblchathistory`
  ADD CONSTRAINT `user_useridA_fk` FOREIGN KEY (`useridA`) REFERENCES `tbluser` (`userid`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `user_useridB_fk` FOREIGN KEY (`useridB`) REFERENCES `tbluser` (`userid`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `tblfriendlist`
--
ALTER TABLE `tblfriendlist`
  ADD CONSTRAINT `tblfriendlist_groupidInA_fk` FOREIGN KEY (`groupidInA`) REFERENCES `tblgroup` (`groupid`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `tblfriendlist_groupidInB_fk` FOREIGN KEY (`groupidInB`) REFERENCES `tblgroup` (`groupid`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `tblfriendlist_useridA_fk` FOREIGN KEY (`useridA`) REFERENCES `tbluser` (`userid`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `tblfriendlist_useridB_fk` FOREIGN KEY (`useridB`) REFERENCES `tbluser` (`userid`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `tblgroup`
--
ALTER TABLE `tblgroup`
  ADD CONSTRAINT `tblgroup_userid_fk` FOREIGN KEY (`userid`) REFERENCES `tbluser` (`userid`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
