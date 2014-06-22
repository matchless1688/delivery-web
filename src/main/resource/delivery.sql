CREATE TABLE `tb_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userName` varchar(255) DEFAULT NULL,
  `telPhone` varchar(255) DEFAULT NULL,
  `pwd` varchar(255) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `companyId` int(11) DEFAULT NULL,
  `info` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

CREATE TABLE `tb_station` (
  `id` int(11) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `boxes` varchar(255) DEFAULT NULL,
  `tdjh` varchar(255) DEFAULT NULL,
  `tdd` varchar(255) DEFAULT NULL,
  `createDate` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `tb_boxinfo` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `length` varchar(6) DEFAULT NULL,
  `higth` varchar(6) DEFAULT NULL,
  `width` varchar(6) DEFAULT NULL,
  `status` varchar(2) DEFAULT NULL,
  `type` varchar(2) DEFAULT NULL,
  `stationID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `tb_expressinfo` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `barCode` varchar(60) DEFAULT NULL,
  `tdjh` varchar(6) DEFAULT NULL,
  `status` varchar(6) DEFAULT NULL,
  `dateTime` varchar(12) DEFAULT NULL,
  `deliTel` varchar(11) DEFAULT NULL,
  `stationID` varchar(10) DEFAULT NULL,
  `boxID` varchar(3) DEFAULT NULL,
  `expressCompanyCode` varchar(10) DEFAULT NULL,
  `OwnerPhone` varchar(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
