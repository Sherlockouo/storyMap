-- MySQL dump 10.13  Distrib 8.0.23, for macos10.15 (x86_64)
--
-- Host: 121.40.193.32    Database: storymap
-- ------------------------------------------------------
-- Server version	5.6.47

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
-- Table structure for table `collect`
--

DROP TABLE IF EXISTS `collect`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `collect` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `posterid` bigint(20) NOT NULL,
  `userid` bigint(20) NOT NULL,
  `collectuserid` bigint(20) NOT NULL,
  `collectstatus` tinyint(1) DEFAULT '1',
  PRIMARY KEY (`id`),
  UNIQUE KEY `collect_pk` (`posterid`,`userid`,`collectuserid`),
  KEY `userid` (`userid`),
  KEY `collectuserid` (`collectuserid`),
  CONSTRAINT `collect_ibfk_2` FOREIGN KEY (`collectuserid`) REFERENCES `user` (`id`),
  CONSTRAINT `collect_user_id_fk` FOREIGN KEY (`posterid`) REFERENCES `poster` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `collect`
--


--
-- Table structure for table `file`
--

DROP TABLE IF EXISTS `file`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `file` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `userid` bigint(20) DEFAULT NULL,
  `upload_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `filename` varchar(256) DEFAULT NULL,
  `fileurl` varchar(512) DEFAULT NULL,
  `type` varchar(16) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=208 DEFAULT CHARSET=utf8mb4 COMMENT='文件';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `file`
--

INSERT INTO `file` VALUES (85,1,'2021-03-18 08:27:32','7vck3uO6Kq2ue8596554c534e41277aa2c48a98f906f.png','https://storymap.sherlockouo.com/files/images/398f5b9d85ff410da1f4ac7d6286d9e1.png','image'),(86,1,'2021-03-18 08:27:33','y2BKZqdymaUI1a48c54e8e3ca5ffc991170c8f468ace.png','https://storymap.sherlockouo.com/files/images/277514412a394a509a89a57073cd798d.png','image'),(87,1,'2021-03-18 08:31:02','IajskEzaUVqXe8596554c534e41277aa2c48a98f906f.png','https://storymap.sherlockouo.com/files/images/74384d330b5748eab0cf6dd02107e93c.png','image'),(88,1,'2021-03-18 08:32:05','kKvZJEtiB5js44e69e78f00d0fe4601fd12cf1a20b85.png','https://storymap.sherlockouo.com/files/images/64f8f4b69c264d0bb0be835eb5ae341c.png','image'),(89,1,'2021-03-18 08:32:53','OlvHsszvI5RJe8596554c534e41277aa2c48a98f906f.png','https://storymap.sherlockouo.com/files/images/0924f1ff27864fe49ab9741f5d9640ed.png','image'),(90,1,'2021-03-18 08:32:54','0JUcxNhORKeT1a48c54e8e3ca5ffc991170c8f468ace.png','https://storymap.sherlockouo.com/files/images/0d7b8d51701b412ca26c0f56286315eb.png','image'),(91,1,'2021-03-18 09:16:22','fyzUXwFEyA9h1a48c54e8e3ca5ffc991170c8f468ace.png','https://storymap.sherlockouo.com/files/images/a354def9f62f41e9805ba9d8588000d3.png','image'),(92,1,'2021-03-18 09:16:23','sy9LxR7bT8JG1a48c54e8e3ca5ffc991170c8f468ace.png','https://storymap.sherlockouo.com/files/images/4fcb874df8a845b887c57fb00cee0d3a.png','image'),(93,1,'2021-03-18 09:17:11','83WOSoJyiMrg1a48c54e8e3ca5ffc991170c8f468ace.png','https://storymap.sherlockouo.com/files/images/20fb27ba14274ed9891e2b30d06f8cbe.png','image'),(94,1,'2021-03-18 09:17:11','W9NHSCWBH39m0fbfd862c4e82b4c72fbf3de3bd7ee60.png','https://storymap.sherlockouo.com/files/images/b7612bbee22a4405a2f34eee1d9a4e20.png','image'),(95,1,'2021-03-18 09:52:12','ZgCqeLcTjrMmd5c475f0c43bcc81b70f133ef695b13e.jpg','https://storymap.sherlockouo.com/files/images/773d7139dc384a899a2c3fdbd0e606c3.jpg','image'),(96,1,'2021-03-18 09:56:04','tmp_8d9a0ba2b2f4a6599a85cc60083b4ee0f954f241557af755.jpg','https://storymap.sherlockouo.com/files/images/49658e5c8ea14c2793b61e2b56ac9688.jpg','image'),(97,4,'2021-03-18 14:56:17','tmp_3dddee59e43129a1cf732c94d72411d63cfa441922c0e69c.jpg','https://storymap.sherlockouo.com/files/images/23b127cbd3854dfd914d49bc80db668c.jpg','image'),(98,4,'2021-03-18 14:58:25','tmp_a556284ca69004530adbe168d9703717587b7439d713077f.jpg','https://storymap.sherlockouo.com/files/images/a54ed5528ecd4eb383970424e98078ed.jpg','image'),(99,3,'2021-03-18 16:04:01','tmp_0d3040371c72f92e4ba321ff4a11dd3c35df98183be115fc.jpg','https://storymap.sherlockouo.com/files/images/0b4a8ae90ac44fdfa4570b5466a9d6ed.jpg','image'),(100,3,'2021-03-18 16:04:01','tmp_0fa11847df9eb64b79c7a1fde43f7a99481557b2598c216d.jpg','https://storymap.sherlockouo.com/files/images/b2998409d8cd4e39bfa2da7ed9c13b4d.jpg','image'),(101,3,'2021-03-18 16:04:01','tmp_555a28a4328797dd525cbc7089a6317d11219eecd83cd284.jpg','https://storymap.sherlockouo.com/files/images/49504199257946bf834521af1a651ede.jpg','image'),(102,3,'2021-03-18 16:04:26','tmp_941497dc11e125142df2d35ab61d9339ca90c2229161696f.jpg','https://storymap.sherlockouo.com/files/images/6c1d811b1a47465fb7e6dffe3cb325ab.jpg','image'),(103,3,'2021-03-18 16:06:10','tmp_a9ab687c5c7455344376c995f34512906c0a19c9f4372f70.jpg','https://storymap.sherlockouo.com/files/images/f5cec7591d1441e1b94953bf047aa759.jpg','image'),(104,3,'2021-03-18 16:06:10','tmp_fd790370bfb985036aa6557a3724ef6bb8c8f56b453a7049.jpg','https://storymap.sherlockouo.com/files/images/fb2b4e3735e44868852dfd2846fae256.jpg','image'),(105,2,'2021-03-19 10:09:49','tmp_36127288c39a5be1b951bc85f6f90b0a90e8fa831a79e714.jpg','https://storymap.sherlockouo.com/files/images/a99f6ee42cbe4a5ebd82b8bf3bad0a5c.jpg','image'),(106,2,'2021-03-19 10:54:54','tmp_925bf371e457cee020ff59df31a26d0c34439592df62bc39.jpg','https://storymap.sherlockouo.com/files/images/f3b714f3fcc049da84265246a5d50a86.jpg','image'),(107,3,'2021-03-19 11:13:43','tmp_30c8de964aa511df81ebd56a59db50e8dc818d00af9079b6.jpg','https://storymap.sherlockouo.com/files/images/3c63660243e74b10be28f8b0c218f8b1.jpg','image'),(108,3,'2021-03-19 11:14:27','tmp_c1255bc72d4552c3eaca58866405928d1c1e433d97e9103c.jpg','https://storymap.sherlockouo.com/files/images/1e058ce31877424ea303058eb7d0d7cd.jpg','image'),(109,3,'2021-03-19 11:14:40','tmp_843695e553e70c5ce241121d1e38566a1dba5293d36e8665.jpg','https://storymap.sherlockouo.com/files/images/3155dbf50b5743199fa27d835eb07f2d.jpg','image'),(110,3,'2021-03-19 15:48:23','tmp_d31e8845c5f45f0671d2138ff5905adc33bbbecc171a2773.jpg','https://storymap.sherlockouo.com/files/images/eb1786d9e74b4f7d877c8548d5a1f092.jpg','image'),(111,3,'2021-03-19 15:48:24','tmp_2f8a570ddc93691958a4bfba3cb47e3d5e459519fb4c0cae.jpg','https://storymap.sherlockouo.com/files/images/61d6eaf1c27148c2ba544053d3c601b0.jpg','image'),(112,5,'2021-03-19 16:07:43','tmp_d1572e618d633b4b40d028c5d4ba946cf5fe3c0b7c2b342ca4fd429c6f6bacca.jpeg','https://storymap.sherlockouo.com/files/images/250325a09c1842c4ad50b418053f7960.jpeg','image'),(113,5,'2021-03-19 16:07:43','tmp_3f2f329503c3d5b3cf20093de2559f56e3f8913610a45179.jpeg','https://storymap.sherlockouo.com/files/images/8e7e6f0a094e480fa4f7470df40158b5.jpeg','image'),(114,6,'2021-03-19 16:10:59','tmp_309e95255562e4b930ab5314c68e180fa5cee82779e82d51.jpg','https://storymap.sherlockouo.com/files/images/5b8db74473fe47cb95b93e9a6392266d.jpg','image'),(115,6,'2021-03-19 16:10:59','tmp_e901ccc58b473dc7e458c1e7ff6060fa40fca3f2615380fc.jpg','https://storymap.sherlockouo.com/files/images/52789e3d8e404f74b00c620f2db76e35.jpg','image'),(116,6,'2021-03-19 16:10:59','tmp_3c213509fd097b9d9e4ccce35ec9945afeb776186f7c41d9.jpg','https://storymap.sherlockouo.com/files/images/8f03b0fffc0a45c2a2fd9f291276ec59.jpg','image'),(117,5,'2021-03-19 16:20:54','tmp_c5864da70ee6fead1e8f26e29795b5c23942dbcce3d09ed3.jpg','https://storymap.sherlockouo.com/files/images/87184b30168943cabcbb33d80a3aef4e.jpg','image'),(118,5,'2021-03-19 16:24:00','tmp_a99cbbfabec98e80c20bb724ea10801ba074f65ae23df6b7.jpg','https://storymap.sherlockouo.com/files/images/f0cb276c5fdc4e2aac3f4f759f870a6a.jpg','image'),(119,5,'2021-03-19 16:25:05','tmp_1812fd617221e0c13488ff3f0f968a41d1ebf5506d44265a.jpg','https://storymap.sherlockouo.com/files/images/45bcdccc4a9b498ebe4494c25538b0b3.jpg','image'),(120,5,'2021-03-19 16:26:51','tmp_3d7fab21cb74a7ff1df74c49441064618502feda8f6d9527.jpg','https://storymap.sherlockouo.com/files/images/26a1ccd7e73342bb8e2263f5c0bd026d.jpg','image'),(121,5,'2021-03-19 16:45:10','tmp_d6e9c65299ffd411a31b70dd569896ce01e253dbf42e9f1c.jpg','https://storymap.sherlockouo.com/files/images/566214ff513c49a88ffde4bdc3ab7bcb.jpg','image'),(122,5,'2021-03-19 16:45:10','tmp_a461e4c988e182a62307ce97887aa51e1bc0ad7585c2ee24.jpg','https://storymap.sherlockouo.com/files/images/a89281e0ccdc4cbd840f631eb1a54a69.jpg','image'),(123,3,'2021-03-20 01:48:06','tmp_be6c8e8ad322ad4e7b32fd4ff69476f83f3d2ea6fd01ae31.jpg','https://storymap.sherlockouo.com/files/images/3a495e6703ad498a92a1890cddebed88.jpg','image'),(124,3,'2021-03-20 03:01:11','BfXOt26EnjbH054e8c2923d010df145ce111a40354ba.png','https://storymap.sherlockouo.com/files/images/0e6be1abf0084008a9c598ae3de274c9.png','image'),(125,3,'2021-03-20 03:02:41','gUVt5jwy9hIx054e8c2923d010df145ce111a40354ba.png','https://storymap.sherlockouo.com/files/images/d6aaa40cd9ab464d8ab6ee2367827d15.png','image'),(126,3,'2021-03-20 03:54:37','tmp_29851edf69abbc677fe17b59fef7a7fc7d6b45e3f30e2947.jpg','https://storymap.sherlockouo.com/files/images/77485ab51d23492680bfba12d9664207.jpg','image'),(127,3,'2021-03-20 03:54:37','tmp_d9b8bfd22b2dfc9203a198f4820de39ed79cfa4ef2a5e73f.jpg','https://storymap.sherlockouo.com/files/images/c4febdce590e461aaeb67f19dd8c73d8.jpg','image'),(128,3,'2021-03-20 03:54:37','tmp_aab71459ecafe18c7fd4494fdd692a9627898d7e9c05097b.jpg','https://storymap.sherlockouo.com/files/images/56ea5180b8fe4846ab649d48a848a879.jpg','image'),(129,3,'2021-03-20 03:54:37','tmp_48cc54b04246cc342e2b8be3294ee2ac82fbfed590dd4e77.jpg','https://storymap.sherlockouo.com/files/images/923e39e4f66142fe9d1a6d2ef48e5379.jpg','image'),(130,2,'2021-03-20 10:41:20','IO7UBF3tpC7md52fab9831fd340b6d93458ca6bf17e5.png','https://storymap.sherlockouo.com/files/images/4535042d4b5c4ea3a7e99af7727a2af7.png','image'),(131,2,'2021-03-20 10:41:20','crlOy5EMT1Ee1bf052653eb4e6ce7bf73fcf5ae699fe.png','https://storymap.sherlockouo.com/files/images/f67201ca7b574decb6a2a47627cc76fb.png','image'),(132,2,'2021-03-20 10:43:28','rbcAS6VVfxYJc7077243dacedb076d256c9cd1f40ead.png','https://storymap.sherlockouo.com/files/images/c029325757c6498db3506edc20ca9a5b.png','image'),(133,2,'2021-03-20 10:43:29','4r8nCT2ZSUFJ0fbfd862c4e82b4c72fbf3de3bd7ee60.png','https://storymap.sherlockouo.com/files/images/9d9b582b46784594b2e9e2c66252e8a5.png','image'),(134,2,'2021-03-20 10:46:18','slr3mQCFydfg86051eea8d4d8ebb19a7a3756983cb52.png','https://storymap.sherlockouo.com/files/images/ab495b668ae6449886f72fd61b895fcf.png','image'),(135,2,'2021-03-20 10:46:18','PoLrO9rttyVR1bf052653eb4e6ce7bf73fcf5ae699fe.png','https://storymap.sherlockouo.com/files/images/7f91aff2b5e343eebd1769dc84564d5a.png','image'),(136,2,'2021-03-20 10:49:14','kwwRbG3MHev8c7077243dacedb076d256c9cd1f40ead.png','https://storymap.sherlockouo.com/files/images/51aca1fdfe99426ea8360b7574d2a4e3.png','image'),(137,2,'2021-03-20 10:49:14','IPQbTcs8nwrF1bf052653eb4e6ce7bf73fcf5ae699fe.png','https://storymap.sherlockouo.com/files/images/8164036bdbd94efb978e7e970f9caecc.png','image'),(138,2,'2021-03-20 11:55:37','14rmaiYZIogmd52fab9831fd340b6d93458ca6bf17e5.png','https://storymap.sherlockouo.com/files/images/6ccf2e6205444777a447148fa79c3a0c.png','image'),(139,2,'2021-03-20 11:55:37','k7cgnKUa0OmD1bf052653eb4e6ce7bf73fcf5ae699fe.png','https://storymap.sherlockouo.com/files/images/589e4a6c437a47b988a1d6e2066a082a.png','image'),(140,2,'2021-03-20 11:56:50','p95Lpn2E9SEdd52fab9831fd340b6d93458ca6bf17e5.png','https://storymap.sherlockouo.com/files/images/fc0a11db491940e286625a2cade74d41.png','image'),(141,2,'2021-03-20 11:56:51','7TIYptV06UUP86051eea8d4d8ebb19a7a3756983cb52.png','https://storymap.sherlockouo.com/files/images/33ad4d4272f84845a5b8daab603dbe75.png','image'),(142,2,'2021-03-20 11:56:51','hVhK6lvLehZQc7077243dacedb076d256c9cd1f40ead.png','https://storymap.sherlockouo.com/files/images/cd7203584b4041368a7c2cc6b949c2c3.png','image'),(143,2,'2021-03-20 11:58:17','T2krIWCwoSzoe8596554c534e41277aa2c48a98f906f.png','https://storymap.sherlockouo.com/files/images/76c561ac9ba843efaf9a59e62c7e1294.png','image'),(144,2,'2021-03-20 11:58:21','HSvyPcjUOcWY0fbfd862c4e82b4c72fbf3de3bd7ee60.png','https://storymap.sherlockouo.com/files/images/f7cd04f056ca41758e1a91bb15b691c3.png','image'),(145,2,'2021-03-20 11:59:33','OFRgf5MuHVaJa2f5576ca61321a98fdc717ee032ec66.png','https://storymap.sherlockouo.com/files/images/d678c09affa3458c8980dae24abb98d9.png','image'),(146,2,'2021-03-20 11:59:47','kbNfCWOiZfsn0f4ff0c9faa34dff6d7dbd493ce86a4a.png','https://storymap.sherlockouo.com/files/images/3ebd83af7723488ca6cc424fe1e56a0c.png','image'),(147,2,'2021-03-20 12:00:09','2NHKbTaEOnsx44e69e78f00d0fe4601fd12cf1a20b85.png','https://storymap.sherlockouo.com/files/images/fb2d1b920559496894541de0450882f9.png','image'),(148,2,'2021-03-20 12:00:16','vXl4trE82tPE1a48c54e8e3ca5ffc991170c8f468ace.png','https://storymap.sherlockouo.com/files/images/3e325b4f1066412c8181da0c9d239cf8.png','image'),(149,2,'2021-03-20 12:01:22','uK1kCN4GWkVne8596554c534e41277aa2c48a98f906f.png','https://storymap.sherlockouo.com/files/images/f70a8dc9ef0e4c67a4e45b118289bbdb.png','image'),(150,2,'2021-03-20 12:01:23','oKf4TpzOK4mod52fab9831fd340b6d93458ca6bf17e5.png','https://storymap.sherlockouo.com/files/images/7a79c2a44ad145e4ba3a81774664abfb.png','image'),(151,2,'2021-03-20 12:01:23','awfIOv8YjtU086051eea8d4d8ebb19a7a3756983cb52.png','https://storymap.sherlockouo.com/files/images/73fae87118694a83bd7b94e99c9ec909.png','image'),(152,2,'2021-03-20 12:01:23','hzqvIrqEEOtsfa308e3957a6928ea93081ad4d439ae7.png','https://storymap.sherlockouo.com/files/images/6f4582ef204f4468929aca0cb1d3625e.png','image'),(153,2,'2021-03-20 12:01:27','FeRGvRzugda20fbfd862c4e82b4c72fbf3de3bd7ee60.png','https://storymap.sherlockouo.com/files/images/7e27b27ef38c4c64b24253eceb246ec3.png','image'),(154,2,'2021-03-20 12:04:06','NGeXi0yMTCKfd52fab9831fd340b6d93458ca6bf17e5.png','https://storymap.sherlockouo.com/files/images/a6ed1f90b3644ee1bdb94f95a4d70cc4.png','image'),(155,2,'2021-03-20 12:04:06','vSu7CTCueI5Zfa308e3957a6928ea93081ad4d439ae7.png','https://storymap.sherlockouo.com/files/images/f5c3c56415c64931a9028a23bcf92752.png','image'),(156,2,'2021-03-20 12:04:06','pvUy3jmzdIUv0fbfd862c4e82b4c72fbf3de3bd7ee60.png','https://storymap.sherlockouo.com/files/images/09d4320b7c96439590ec01d744807637.png','image'),(157,2,'2021-03-20 12:07:27','5Z5ZhIfXPcvHa2f5576ca61321a98fdc717ee032ec66.png','https://storymap.sherlockouo.com/files/images/91ba27f00b994733bb75f141fdcde518.png','image'),(158,2,'2021-03-20 12:07:27','jiT8NFHdoeQM44e69e78f00d0fe4601fd12cf1a20b85.png','https://storymap.sherlockouo.com/files/images/56589ee118a242f4856b7fd630df1ae2.png','image'),(159,2,'2021-03-20 12:08:31','p4s6Vp3e0UDFe8596554c534e41277aa2c48a98f906f.png','https://storymap.sherlockouo.com/files/images/73475a0b81f14c23af3470bfa70d8f8d.png','image'),(160,2,'2021-03-20 12:08:31','148IT56p2n0t1bf052653eb4e6ce7bf73fcf5ae699fe.png','https://storymap.sherlockouo.com/files/images/88b1117018bb4ad1a56ff8b6deb9d2a3.png','image'),(161,2,'2021-03-20 12:10:18','nS0jeshsItQhd52fab9831fd340b6d93458ca6bf17e5.png','https://storymap.sherlockouo.com/files/images/b8c5353ef76c4209bf3987de989ffb20.png','image'),(162,2,'2021-03-20 12:10:18','xoPcfjTnSnSQ86051eea8d4d8ebb19a7a3756983cb52.png','https://storymap.sherlockouo.com/files/images/62cc9823091145e9b6098d269219d76b.png','image'),(163,2,'2021-03-20 12:44:39','EJ9fkMVi5WJYe8596554c534e41277aa2c48a98f906f.png','https://storymap.sherlockouo.com/files/images/3be1701e6f8446bda563b1e162f1e8fd.png','image'),(164,2,'2021-03-20 12:44:40','MtZ8XHVQYwEffa308e3957a6928ea93081ad4d439ae7.png','https://storymap.sherlockouo.com/files/images/19cba323a31248bd9cf1d1e5f8ef64b4.png','image'),(165,2,'2021-03-20 12:44:40','yCDtQK4SDRPX0fbfd862c4e82b4c72fbf3de3bd7ee60.png','https://storymap.sherlockouo.com/files/images/a73c9a21e49a4afea8561bf3675d3ec2.png','image'),(166,2,'2021-03-20 12:54:23','YU225q1TzStae8596554c534e41277aa2c48a98f906f.png','https://storymap.sherlockouo.com/files/images/ec4f7b58c96742c7883a03094239a3fb.png','image'),(167,2,'2021-03-20 12:54:23','nEv9t8dADzg31bf052653eb4e6ce7bf73fcf5ae699fe.png','https://storymap.sherlockouo.com/files/images/2e8e00a4de244d3ba113ce3b0d9ca0e4.png','image'),(168,2,'2021-03-20 12:54:23','hVwH9fAbGn3x1a48c54e8e3ca5ffc991170c8f468ace.png','https://storymap.sherlockouo.com/files/images/1d0f597fa26843d196c0c30d7ce6c482.png','image'),(169,2,'2021-03-20 12:54:24','x8z1V9V3vfYa0fbfd862c4e82b4c72fbf3de3bd7ee60.png','https://storymap.sherlockouo.com/files/images/194cbd37d6544fef9ebb6ec1d72ff71f.png','image'),(170,2,'2021-03-20 12:57:46','C0nL0LLQKkx4e8596554c534e41277aa2c48a98f906f.png','https://storymap.sherlockouo.com/files/images/fbb1b2056f954a3d9400accb5972503b.png','image'),(171,2,'2021-03-20 12:57:46','BqzxWAwLmp1ja2f5576ca61321a98fdc717ee032ec66.png','https://storymap.sherlockouo.com/files/images/f0b0ea7ec4ba4f78a342a1caec1eb248.png','image'),(172,2,'2021-03-20 12:57:46','0rSrzrvBatop1bf052653eb4e6ce7bf73fcf5ae699fe.png','https://storymap.sherlockouo.com/files/images/89b9ee418ee541f286582628c0e36612.png','image'),(173,2,'2021-03-20 12:57:46','eE1www0qVkAW44e69e78f00d0fe4601fd12cf1a20b85.png','https://storymap.sherlockouo.com/files/images/65063b510b2d4d4188c735beef31e28e.png','image'),(174,2,'2021-03-20 12:57:47','cJsu1OirVyIT1a48c54e8e3ca5ffc991170c8f468ace.png','https://storymap.sherlockouo.com/files/images/a8a80439a5f142b98a684c8f579cd1e8.png','image'),(175,2,'2021-03-20 12:57:47','OSz4MOSgdPpo0fbfd862c4e82b4c72fbf3de3bd7ee60.png','https://storymap.sherlockouo.com/files/images/5c9ff459b62d4b58bb2650b03535775b.png','image'),(176,2,'2021-03-20 13:02:02','TMFIEICp4haQe8596554c534e41277aa2c48a98f906f.png','https://storymap.sherlockouo.com/files/images/0e82deff6e7049a0834ab94a74b6b311.png','image'),(177,2,'2021-03-20 13:02:02','MwSHbDbCmRUQa2f5576ca61321a98fdc717ee032ec66.png','https://storymap.sherlockouo.com/files/images/915e551650fd48d5a0fc430652e65f05.png','image'),(178,2,'2021-03-20 13:02:03','0YVtxuEsNG0S1bf052653eb4e6ce7bf73fcf5ae699fe.png','https://storymap.sherlockouo.com/files/images/f245363540bb437e9581bee5cd80f3f7.png','image'),(179,2,'2021-03-20 13:02:03','tOCLLL2ySsGj44e69e78f00d0fe4601fd12cf1a20b85.png','https://storymap.sherlockouo.com/files/images/39e0e3bf0b604a509204d1bd5a1b0764.png','image'),(180,2,'2021-03-20 13:02:04','MS8sxsU5Epfh1a48c54e8e3ca5ffc991170c8f468ace.png','https://storymap.sherlockouo.com/files/images/ce01287cab504481ba35375e7907a931.png','image'),(181,2,'2021-03-20 13:02:04','G1iXOt8GOnL90f4ff0c9faa34dff6d7dbd493ce86a4a.png','https://storymap.sherlockouo.com/files/images/50fc99406b6a4b9b92e99755952d4459.png','image'),(182,2,'2021-03-20 13:15:53','tmp_a27fc196a723f1c67e49792166e7154232fc6e7659b87f19.jpg','https://storymap.sherlockouo.com/files/images/7438f3fa2fef41c1a85b8239f55fe6d8.jpg','image'),(183,2,'2021-03-20 13:15:53','tmp_6f61bdbe9d786226204fb8a0f057f3a4f466bfdac497fe9a.jpg','https://storymap.sherlockouo.com/files/images/2a642ed609044933b7cb5119ecee7498.jpg','image'),(184,2,'2021-03-20 13:15:53','tmp_524813b9bd3f765d7726b2cd89710f38ade9aa5bfb8120fd.jpg','https://storymap.sherlockouo.com/files/images/2b8e98bd34204e1399d0f89aad92f4c4.jpg','image'),(185,2,'2021-03-20 13:20:39','NMhnJwn1AKVje8596554c534e41277aa2c48a98f906f.png','https://storymap.sherlockouo.com/files/images/a33e239440684922922135195cafbc4d.png','image'),(186,2,'2021-03-20 13:20:40','7L8U9A4oEauW1bf052653eb4e6ce7bf73fcf5ae699fe.png','https://storymap.sherlockouo.com/files/images/29e822961d0c4a62a9f7faa3365b444b.png','image'),(187,2,'2021-03-20 13:20:41','Iu41ScxmcC841a48c54e8e3ca5ffc991170c8f468ace.png','https://storymap.sherlockouo.com/files/images/a8de3a9ecdf544618f27cb109671c6ad.png','image'),(188,2,'2021-03-20 13:22:04','tmp_e634bb2e8d3f3d731b4fb797e74ff8a4826f163f993059e5.jpg','https://storymap.sherlockouo.com/files/images/9170453e45604b96a20d59eb4c107297.jpg','image'),(189,2,'2021-03-20 13:22:04','tmp_9b54ee716bfa5f895f95fe447d0d0d8b0b1f751c8cec3ac0.jpg','https://storymap.sherlockouo.com/files/images/9b86625276634d679733fabb5d82d092.jpg','image'),(190,2,'2021-03-20 13:22:04','tmp_37a44b371044eafcb5b253e3e0d15b6808a8d30f3ee640b6.jpg','https://storymap.sherlockouo.com/files/images/0a574089b69c4a4b82d0caf1f5b5c897.jpg','image'),(191,2,'2021-03-20 13:22:53','tmp_92043b3584b500b239b0eec1254491ae603c138a4b35133b.jpg','https://storymap.sherlockouo.com/files/images/ed4687bc12694a18bd1fc2198c9fd406.jpg','image'),(192,2,'2021-03-20 13:22:53','tmp_b37f7cd8bff810f525a4daafcfa396e4f5bf48c084ce91b4.jpg','https://storymap.sherlockouo.com/files/images/fb3f4d8e59f7461fa13fda8da602905a.jpg','image'),(193,2,'2021-03-20 13:24:11','tmp_bf2d80ccf59e2c65afa938827a8ba79685b1ddb8c5c3dade.jpg','https://storymap.sherlockouo.com/files/images/6c89eb77da7243ed92c27a6edcd149a1.jpg','image'),(194,2,'2021-03-20 13:24:11','tmp_09ed8cc18e8fbf4d97dc78dfacdf09cdf1046df4627f8c62.jpg','https://storymap.sherlockouo.com/files/images/bdb7dc410dad4cdda4d29ca8cdc2ec44.jpg','image'),(195,2,'2021-03-20 13:24:11','tmp_54f788857ab25e03f5a702f8767df70f584116bb82ac0621.jpg','https://storymap.sherlockouo.com/files/images/1dbdb3034db645d2a5e064328f7aa09a.jpg','image'),(196,2,'2021-03-20 13:32:28','tmp_4d93da3b8d5906f60bb330a9898c3947352509786cd752ae.jpg','https://storymap.sherlockouo.com/files/images/26179102d10541e090697523c69b420a.jpg','image'),(197,2,'2021-03-20 13:32:28','tmp_9b7a120cbd2fbf447def2c09b7924cf2de4d93dae5b0bf7e.jpg','https://storymap.sherlockouo.com/files/images/e986dccd0d10401895cfce63c7d0d186.jpg','image'),(198,2,'2021-03-20 13:32:28','tmp_b6a64cb7f6dcf5269bf160db8cd36318022663e35992e742.jpg','https://storymap.sherlockouo.com/files/images/517f9bb7a73c4a89929cff84973ec4b2.jpg','image'),(199,2,'2021-03-20 13:36:26','tmp_c1ebba43dd0d187f200e4cc978787518130679d0597406d7.jpg','https://storymap.sherlockouo.com/files/images/ae6798d0cc684f08b1d2bb04cc306fee.jpg','image'),(200,3,'2021-03-20 14:44:08','tmp_467c0e820ad678d2abc9a37ba302ea13e4522cf278b56141.jpg','https://storymap.sherlockouo.com/files/images/b672dfe3282f4a05ab7800aab26f8c86.jpg','image'),(201,3,'2021-03-20 14:44:08','tmp_1a16e042161a555b430ec0962896bbef369bede1d50a45e2.jpg','https://storymap.sherlockouo.com/files/images/818643c5390f4633aa791951898d535c.jpg','image'),(202,3,'2021-03-20 14:44:08','tmp_2e8e5b3dc1d8378fbcc76891f20d4e5787eb010bf5b82d49.jpg','https://storymap.sherlockouo.com/files/images/6955160fb7d94eb5ade0d393784b892e.jpg','image'),(203,3,'2021-03-20 14:45:27','tmp_11ddc66daa98c909b0fc4b469afbb7e75b1019000a0547f1.jpg','https://storymap.sherlockouo.com/files/images/98b72da8ff8c44acb085a7eb9c390260.jpg','image'),(204,3,'2021-03-20 14:45:27','tmp_6726bbd6eb73dd275999a940b090227e225920480d3ee1a1.jpg','https://storymap.sherlockouo.com/files/images/342a0d19164444889f921d84cac69303.jpg','image'),(205,3,'2021-03-20 14:46:28','tmp_8bfc2b820cac286ae7c7d0aab870064a4e8f77cc03ea84bc.jpg','https://storymap.sherlockouo.com/files/images/8b661bb0692f45e884dd9a5ca1044ca2.jpg','image'),(206,3,'2021-03-20 14:48:15','tmp_80587267ec5a6c720fd685388d7fd46ba54867e961a63844.jpg','https://storymap.sherlockouo.com/files/images/d7911ffbf78e48049c4537e56dab980e.jpg','image'),(207,3,'2021-03-20 14:50:11','tmp_1183a73947e0edf4b5c0287b9e84b452f3b7a6735fd9a524.jpg','https://storymap.sherlockouo.com/files/images/ba80c686f055474eaddff65d72d64d72.jpg','image');

--
-- Table structure for table `follow`
--

DROP TABLE IF EXISTS `follow`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `follow` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `userid` bigint(20) NOT NULL,
  `followerid` bigint(20) NOT NULL,
  `status` tinyint(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `follow_user_id_fk` (`userid`),
  CONSTRAINT `follow_user_id_fk` FOREIGN KEY (`userid`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `follow`
--


--
-- Table structure for table `like`
--

DROP TABLE IF EXISTS `like`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `like` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `posterid` bigint(20) NOT NULL,
  `userid` bigint(20) NOT NULL,
  `likeuserid` bigint(20) NOT NULL,
  `status` tinyint(1) DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `like_user_id_fk` (`posterid`),
  KEY `userid` (`userid`),
  KEY `likeuserid` (`likeuserid`),
  CONSTRAINT `like_ibfk_1` FOREIGN KEY (`userid`) REFERENCES `user` (`id`),
  CONSTRAINT `like_ibfk_2` FOREIGN KEY (`likeuserid`) REFERENCES `user` (`id`),
  CONSTRAINT `like_user_id_fk` FOREIGN KEY (`posterid`) REFERENCES `poster` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `like`
--


--
-- Table structure for table `poster`
--

DROP TABLE IF EXISTS `poster`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `poster` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `userid` bigint(20) DEFAULT NULL,
  `message` mediumtext,
  `address` varchar(32) DEFAULT NULL,
  `longtitude` double DEFAULT NULL,
  `latitude` double DEFAULT NULL,
  `files` mediumtext,
  `title` varchar(256) DEFAULT NULL,
  `likes` int(11) DEFAULT '0',
  `type` varchar(32) DEFAULT NULL,
  `tags` varchar(128) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `poster_user_id_fk` (`userid`),
  CONSTRAINT `poster_user_id_fk` FOREIGN KEY (`userid`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COMMENT='推文';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `poster`
--

INSERT INTO `poster` VALUES (1,2,'春，悄悄的就来了。','四川省成都市金牛区',103.95294867621527,30.776138780381945,'#https://storymap.sherlockouo.com/files/images/26179102d10541e090697523c69b420a.jpg##https://storymap.sherlockouo.com/files/images/e986dccd0d10401895cfce63c7d0d186.jpg##https://storymap.sherlockouo.com/files/images/517f9bb7a73c4a89929cff84973ec4b2.jpg#','春',0,'1','','2021-03-20 13:32:29'),(2,2,'我是在今天上午在八教上课的时候，忘记拿走了，我回去找的时候他就不见了。','四川省成都市金牛区',103.95294867621527,30.776138780381945,'#https://storymap.sherlockouo.com/files/images/ae6798d0cc684f08b1d2bb04cc306fee.jpg#','我的宝贝键盘掉了，你能帮我找找嘛？',0,'2','','2021-03-20 13:36:26'),(3,3,'出发','四川省成都市金牛区',103.95686414930556,30.774054090711804,'#https://storymap.sherlockouo.com/files/images/d7911ffbf78e48049c4537e56dab980e.jpg#','哈',0,'2','','2021-03-20 14:48:16'),(4,3,'在我们班里我最喜欢的同学就是小帅哥耿豪了，你瞧！他的个子不高，但长得很敦实，他的胳膊和腿真像成熟的玉米棒。他喜欢穿外套不扣扣子，听他说：“那样会更显得威风。”耿豪的头长得圆圆的，红扑扑的脸蛋是圆圆的，巧的是他那双乌黑发亮的眼睛也是圆圆的。我最喜欢他笑，他一笑那乌黑发亮的眼睛就变成','四川省成都市简阳市',103.95686414930556,30.774054090711804,'#https://storymap.sherlockouo.com/files/images/ba80c686f055474eaddff65d72d64d72.jpg#','天气很好',0,'1','','2021-03-20 14:50:11');

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `avatar` varchar(256) DEFAULT NULL,
  `username` varchar(128) DEFAULT NULL,
  `password` varchar(256) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `modify_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `openid` varchar(128) DEFAULT NULL,
  `phonenumber` varchar(11) DEFAULT NULL,
  `nickname` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_openid_uindex` (`openid`),
  UNIQUE KEY `user_username_uindex` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COMMENT='用户表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

INSERT INTO `user` VALUES (2,'https://storymap.sherlockouo.com/files/images/d6aaa40cd9ab464d8ab6ee2367827d15.png','oQrxX48ehpFeonZxlmaH9PZebh9s','$2a$10$w8E2pJG7a/AvaLuR7T4kzOkBttST72PzVRSb8kGMnwTUgsihPRVDu','2021-03-18 14:35:03','2021-03-20 13:37:25','oQrxX48ehpFeonZxlmaH9PZebh9s',NULL,'sherlockouo'),(3,'https://storymap.sherlockouo.com/files/images/d6aaa40cd9ab464d8ab6ee2367827d15.png','oQrxX4yFHASIjor5kCiFef3q8gBo','$2a$10$2czhP7/HhpYTQP4wJktkxeEM7A77sj8OMI4j0f0XEXGiFYjQpvnXy','2021-03-18 14:52:17','2021-03-20 13:37:24','oQrxX4yFHASIjor5kCiFef3q8gBo',NULL,'fanda'),(4,'https://storymap.sherlockouo.com/files/images/d6aaa40cd9ab464d8ab6ee2367827d15.png','oQrxX44ZviWPJ48HBlnQ0UiJ_Eyc','$2a$10$1d1fuh2/YBg0Vyw1ELcjUuRbBPKIkpXnhIW6DfYuRMN0C.1vi0OqC','2021-03-18 14:53:56','2021-03-20 10:15:21','oQrxX44ZviWPJ48HBlnQ0UiJ_Eyc',NULL,'我是谁？'),(5,'https://storymap.sherlockouo.com/files/images/d6aaa40cd9ab464d8ab6ee2367827d15.png','oQrxX419C3Vbc_TWPxVqGJpaS2xM','$2a$10$xTgFDcSTMXN7L4MYbr/U6.sXMLkGV2ILCUC8KtRo8UaOB3KZ51x16','2021-03-19 15:52:37','2021-03-20 10:15:21','oQrxX419C3Vbc_TWPxVqGJpaS2xM',NULL,'谁是我？'),(6,'https://storymap.sherlockouo.com/files/images/d6aaa40cd9ab464d8ab6ee2367827d15.png','oQrxX43xdz0iwMsRAwF0d8ZCb_wg','$2a$10$Z4sJm36lEqlmqd/F0bYnpeMYp7S9koKknxXRiC8kPX8ZJ4Rgch34W','2021-03-19 15:53:51','2021-03-20 10:15:21','oQrxX43xdz0iwMsRAwF0d8ZCb_wg',NULL,'你是谁？');
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-03-21 15:07:30
