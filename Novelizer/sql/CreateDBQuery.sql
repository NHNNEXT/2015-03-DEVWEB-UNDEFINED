-- MySQL Script generated by MySQL Workbench
-- Tue Dec 15 16:02:13 2015
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema novelizer
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema novelizer
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `novelizer` DEFAULT CHARACTER SET utf8 ;
USE `novelizer` ;

-- -----------------------------------------------------
-- Table `novelizer`.`action`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `novelizer`.`action` (
  `actionId` INT(11) NOT NULL AUTO_INCREMENT,
  `type` VARCHAR(10) NULL DEFAULT NULL,
  `optionData` TEXT NULL DEFAULT NULL,
  `blockId` INT(11) NOT NULL,
  PRIMARY KEY (`actionId`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `novelizer`.`background`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `novelizer`.`background` (
  `actionId` INT(11) NOT NULL,
  `presetId` INT(11) NOT NULL,
  `option` VARCHAR(10) NOT NULL,
  `position` VARCHAR(10) NOT NULL,
  PRIMARY KEY (`actionId`),
  INDEX `fk_background_action1_idx` (`actionId` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `novelizer`.`block`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `novelizer`.`block` (
  `blockId` INT(11) NOT NULL AUTO_INCREMENT,
  `nextBlockId` INT(11) NULL DEFAULT '0',
  `sceneId` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`blockId`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `novelizer`.`charater`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `novelizer`.`charater` (
  `actionId` INT(11) NOT NULL,
  `presetId` INT(11) NOT NULL,
  `option` VARCHAR(10) NOT NULL,
  `position` VARCHAR(10) NOT NULL,
  PRIMARY KEY (`actionId`),
  INDEX `fk_charater_action1_idx` (`actionId` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `novelizer`.`project`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `novelizer`.`project` (
  `projectId` INT(11) NOT NULL AUTO_INCREMENT,
  `projectName` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`projectId`))
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `novelizer`.`scene`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `novelizer`.`scene` (
  `sceneId` INT(11) NOT NULL AUTO_INCREMENT,
  `projectId` INT(11) NULL DEFAULT NULL,
  `sceneName` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`sceneId`),
  INDEX `fk_scene_project1_idx` (`projectId` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `novelizer`.`text`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `novelizer`.`text` (
  `actionId` INT(11) NOT NULL,
  `characterId` VARCHAR(10) NOT NULL,
  `text` TEXT NOT NULL,
  `option` VARCHAR(10) NOT NULL,
  PRIMARY KEY (`actionId`),
  INDEX `fk_text_action1_idx` (`actionId` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
