-- MySQL Script generated by MySQL Workbench
-- Thu Nov 26 05:48:29 2015
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
CREATE SCHEMA IF NOT EXISTS `novelizer` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `novelizer` ;

-- -----------------------------------------------------
-- Table `novelizer`.`project`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `novelizer`.`project` (
  `projectId` INT NULL,
  `name` VARCHAR(45) NULL,
  PRIMARY KEY (`projectId`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `novelizer`.`scene`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `novelizer`.`scene` (
  `sceneId` INT NULL,
  `projectId` INT NULL,
  `startBlockId` INT NULL,
  `name` VARCHAR(45) NULL,
  INDEX `fk_scene_project1_idx` (`projectId` ASC),
  INDEX `fk_scene_block1_idx` (`startBlockId` ASC),
  CONSTRAINT `fk_scene_project1`
    FOREIGN KEY (`projectId`)
    REFERENCES `novelizer`.`project` (`projectId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_scene_block1`
    FOREIGN KEY (`startBlockId`)
    REFERENCES `novelizer`.`block` (`blockId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `novelizer`.`block`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `novelizer`.`block` (
  `blockId` INT NULL,
  `nextBlockId` INT NULL,
  `sceneId` INT NULL,
  PRIMARY KEY (`blockId`),
  INDEX `fk_block_block1_idx` (`nextBlockId` ASC),
  INDEX `fk_block_scene1_idx` (`sceneId` ASC),
  CONSTRAINT `fk_block_block1`
    FOREIGN KEY (`nextBlockId`)
    REFERENCES `novelizer`.`block` (`blockId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_block_scene1`
    FOREIGN KEY (`sceneId`)
    REFERENCES `novelizer`.`scene` (`sceneId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `novelizer`.`action`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `novelizer`.`action` (
  `actionId` INT NULL,
  `type` VARCHAR(10) NULL,
  `optionData` TEXT(500) NULL,
  `blockId` INT NOT NULL,
  PRIMARY KEY (`actionId`),
  INDEX `fk_action_block1_idx` (`blockId` ASC),
  CONSTRAINT `fk_action_block1`
    FOREIGN KEY (`blockId`)
    REFERENCES `novelizer`.`block` (`blockId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `novelizer`.`text`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `novelizer`.`text` (
  `actionId` INT NOT NULL,
  `characterId` VARCHAR(10) NOT NULL,
  `text` TEXT(300) NOT NULL,
  `option` VARCHAR(10) NOT NULL,
  PRIMARY KEY (`actionId`),
  INDEX `fk_text_action1_idx` (`actionId` ASC),
  CONSTRAINT `fk_text_action1`
    FOREIGN KEY (`actionId`)
    REFERENCES `novelizer`.`action` (`actionId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `novelizer`.`background`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `novelizer`.`background` (
  `actionId` INT NOT NULL,
  `presetId` INT NOT NULL,
  `option` VARCHAR(10) NOT NULL,
  `position` VARCHAR(10) NOT NULL,
  PRIMARY KEY (`actionId`),
  INDEX `fk_background_action1_idx` (`actionId` ASC),
  CONSTRAINT `fk_background_action1`
    FOREIGN KEY (`actionId`)
    REFERENCES `novelizer`.`action` (`actionId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `novelizer`.`charater`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `novelizer`.`charater` (
  `actionId` INT NOT NULL,
  `presetId` INT NOT NULL,
  `option` VARCHAR(10) NOT NULL,
  `position` VARCHAR(10) NOT NULL,
  PRIMARY KEY (`actionId`),
  INDEX `fk_charater_action1_idx` (`actionId` ASC),
  CONSTRAINT `fk_charater_action1`
    FOREIGN KEY (`actionId`)
    REFERENCES `novelizer`.`action` (`actionId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
