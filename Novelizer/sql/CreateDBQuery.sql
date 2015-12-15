-- MySQL Script generated by MySQL Workbench
-- Wed Dec 16 05:16:30 2015
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema novelizer
-- -----------------------------------------------------
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
  `action_id` INT NOT NULL AUTO_INCREMENT,
  `type` VARCHAR(10) NULL DEFAULT NULL,
  `option_data` TEXT(500) NULL DEFAULT NULL,
  `block_id` INT NOT NULL,
  PRIMARY KEY (`action_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `novelizer`.`preset_background`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `novelizer`.`preset_background` (
  `preset_id` INT NOT NULL,
  `project_id` INT NOT NULL,
  `image` TEXT(200) NOT NULL,
  `preset_background_name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`preset_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `novelizer`.`block`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `novelizer`.`block` (
  `block_id` INT NOT NULL AUTO_INCREMENT,
  `next_block_id` INT NULL DEFAULT '0',
  `scene_id` INT NOT NULL,
  PRIMARY KEY (`block_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `novelizer`.`charater`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `novelizer`.`charater` (
  `character_id` INT NOT NULL,
  `project_id` INT NOT NULL,
  `character_name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`character_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `novelizer`.`project`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `novelizer`.`project` (
  `project_id` INT NOT NULL AUTO_INCREMENT,
  `project_name` VARCHAR(45) NULL,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`project_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `novelizer`.`scene`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `novelizer`.`scene` (
  `scene_id` INT NOT NULL AUTO_INCREMENT,
  `project_id` INT NOT NULL,
  `start_block_id` INT NULL,
  `scene_name` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`scene_id` ))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `novelizer`.`text`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `novelizer`.`text` (
  `action_id` INT(11) NOT NULL,
  `character_id` VARCHAR(10) NOT NULL,
  `text` TEXT NOT NULL,
  `option` VARCHAR(10) NOT NULL,
  PRIMARY KEY (`action_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
