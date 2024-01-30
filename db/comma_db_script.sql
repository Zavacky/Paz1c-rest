-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema comma
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema comma
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `comma` DEFAULT CHARACTER SET utf8 ;
USE `comma` ;

-- -----------------------------------------------------
-- Table `comma`.`porotca`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `comma`.`porotca` (
  `id_porotca` BIGINT NOT NULL AUTO_INCREMENT,
  `meno` VARCHAR(45) NOT NULL,
  `priezvisko` VARCHAR(45) NOT NULL,
  `uzivatelske_meno` VARCHAR(45) NOT NULL,
  `heslo` VARCHAR(45) NULL,
  `je_admin` TINYINT NOT NULL,
  `salt` VARCHAR(45) NULL,
  PRIMARY KEY (`id_porotca`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `comma`.`sutaz`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `comma`.`sutaz` (
  `id_sutaz` BIGINT NOT NULL AUTO_INCREMENT,
  `nazov` VARCHAR(45) NOT NULL,
  `od_datnum` DATETIME NOT NULL,
  `do_datum` DATETIME NOT NULL,
  PRIMARY KEY (`id_sutaz`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `comma`.`kategoria`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `comma`.`kategoria` (
  `id_kategoria` BIGINT NOT NULL AUTO_INCREMENT,
  `styl` VARCHAR(45) NOT NULL,
  `vekova_skupina` VARCHAR(45) NOT NULL,
  `velkostna_skupina` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id_kategoria`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `comma`.`tanecne_teleso`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `comma`.`tanecne_teleso` (
  `id_tanecne_teleso` BIGINT NOT NULL AUTO_INCREMENT,
  `nazov` VARCHAR(45) NOT NULL,
  `umiestnenie` VARCHAR(45) NULL,
  `hudba` VARCHAR(45) NOT NULL,
  `klub` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `telefonne_cislo` VARCHAR(45) NULL,
  `tanecnici` VARCHAR(45) NOT NULL,
  `sutaz_id_sutaz` BIGINT NOT NULL,
  `kategoria_id_kategoria` BIGINT NOT NULL,
  PRIMARY KEY (`id_tanecne_teleso`),
  INDEX `fk_tanecne_teleso_sutaz1_idx` (`sutaz_id_sutaz` ASC) VISIBLE,
  INDEX `fk_tanecne_teleso_kategoria1_idx` (`kategoria_id_kategoria` ASC) VISIBLE,
  CONSTRAINT `fk_tanecne_teleso_sutaz1`
    FOREIGN KEY (`sutaz_id_sutaz`)
    REFERENCES `comma`.`sutaz` (`id_sutaz`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tanecne_teleso_kategoria1`
    FOREIGN KEY (`kategoria_id_kategoria`)
    REFERENCES `comma`.`kategoria` (`id_kategoria`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `comma`.`hodnotenie`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `comma`.`hodnotenie` (
  `id_hodnotenie` BIGINT NOT NULL AUTO_INCREMENT,
  `hodnotenie` INT NULL,
  `porotca_id_porotca` BIGINT NOT NULL,
  `tanecne_teleso_id_tanecne_teleso` BIGINT NOT NULL,
  PRIMARY KEY (`id_hodnotenie`),
  INDEX `fk_hodnotenie_porotca_idx` (`porotca_id_porotca` ASC) VISIBLE,
  INDEX `fk_hodnotenie_tanecne_teleso1_idx` (`tanecne_teleso_id_tanecne_teleso` ASC) VISIBLE,
  CONSTRAINT `fk_hodnotenie_porotca`
    FOREIGN KEY (`porotca_id_porotca`)
    REFERENCES `comma`.`porotca` (`id_porotca`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_hodnotenie_tanecne_teleso1`
    FOREIGN KEY (`tanecne_teleso_id_tanecne_teleso`)
    REFERENCES `comma`.`tanecne_teleso` (`id_tanecne_teleso`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `comma`.`porotca_has_sutaz`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `comma`.`porotca_has_sutaz` (
  `porotca_id_porotca` BIGINT NOT NULL,
  `sutaz_id_sutaz` BIGINT NOT NULL,
  PRIMARY KEY (`porotca_id_porotca`, `sutaz_id_sutaz`),
  INDEX `fk_porotca_has_sutaz_sutaz1_idx` (`sutaz_id_sutaz` ASC) VISIBLE,
  INDEX `fk_porotca_has_sutaz_porotca1_idx` (`porotca_id_porotca` ASC) VISIBLE,
  CONSTRAINT `fk_porotca_has_sutaz_porotca1`
    FOREIGN KEY (`porotca_id_porotca`)
    REFERENCES `comma`.`porotca` (`id_porotca`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_porotca_has_sutaz_sutaz1`
    FOREIGN KEY (`sutaz_id_sutaz`)
    REFERENCES `comma`.`sutaz` (`id_sutaz`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
