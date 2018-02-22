-- MySQL Script generated by MySQL Workbench
-- 05/20/16 02:17:37
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema ticket3
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema ticket3
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `ticket3` DEFAULT CHARACTER SET utf8 ;
USE `ticket3` ;

-- -----------------------------------------------------
-- Table `ticket3`.`Producto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ticket3`.`Producto` (
  `idProducto` INT NOT NULL,
  `nombre` VARCHAR(90) NOT NULL,
  `precio` FLOAT NOT NULL,
  PRIMARY KEY (`idProducto`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ticket3`.`Sucursal`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ticket3`.`Sucursal` (
  `idSucursal` INT NOT NULL,
  `nombreSucursal` VARCHAR(90) NOT NULL,
  `direccion` VARCHAR(90) NOT NULL,
  `telefono` VARCHAR(10) NULL,
  PRIMARY KEY (`idSucursal`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ticket3`.`Caja`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ticket3`.`Caja` (
  `idCaja` INT NOT NULL,
  `idSucursal` INT NOT NULL,
  PRIMARY KEY (`idCaja`),
  INDEX `idSucursal_idx` (`idSucursal` ASC),
  CONSTRAINT `idSucursal`
    FOREIGN KEY (`idSucursal`)
    REFERENCES `ticket3`.`Sucursal` (`idSucursal`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ticket3`.`Ticket`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ticket3`.`Ticket` (
  `idTicket` INT NOT NULL,
  `idSucursal` INT NOT NULL,
  `fecha` DATE NULL,
  PRIMARY KEY (`idTicket`),
  INDEX `idSucursal_idx` (`idSucursal` ASC),
  CONSTRAINT `idSucursal1`
    FOREIGN KEY (`idSucursal`)
    REFERENCES `ticket3`.`Sucursal` (`idSucursal`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ticket3`.`Detalle`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ticket3`.`Detalle` (
  `idDetalle` INT NOT NULL,
  `idTicket` INT NOT NULL,
  `idProducto` INT NOT NULL,
  `Cantidad` INT NOT NULL,
  `Precio` FLOAT NOT NULL,
  PRIMARY KEY (`idDetalle`, `idTicket`),
  INDEX `idProducto_idx` (`idProducto` ASC),
  INDEX `idTicket_idx` (`idTicket` ASC),
  CONSTRAINT `idProducto`
    FOREIGN KEY (`idProducto`)
    REFERENCES `ticket3`.`Producto` (`idProducto`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `idTicket`
    FOREIGN KEY (`idTicket`)
    REFERENCES `ticket3`.`Ticket` (`idTicket`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;