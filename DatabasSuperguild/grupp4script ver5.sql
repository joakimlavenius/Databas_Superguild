

CREATE DATABASE Superguild;
USE Superguild;

CREATE  TABLE IF NOT EXISTS `Superguild`.`Member` (
  `memberId` INT NOT NULL ,
  `notes` TEXT NULL ,
  `isOfficer` TINYINT(1) NOT NULL ,
  `rankNumber` INT NOT NULL ,
  `guildTaxPaid` TINYINT(1) NOT NULL ,
  PRIMARY KEY (`memberId`) );

CREATE  TABLE IF NOT EXISTS `Superguild`.`Characters` (
  `Name` VARCHAR(45) NOT NULL ,
  `Level` INT NOT NULL ,
  `MainAltOrBank` INT NOT NULL ,
  `Race` VARCHAR(15) NOT NULL ,
  `MemberId` INT NOT NULL ,
  `Class` VARCHAR(15) NOT NULL ,
  PRIMARY KEY (`Name`) ,
  INDEX `fk_Characters_Member_idx` (`MemberId` ASC) ,
  CONSTRAINT `fk_Characters_Member`
    FOREIGN KEY (`MemberId` )
    REFERENCES `Superguild`.`Member` (`memberId` )
);

CREATE  TABLE IF NOT EXISTS `Superguild`.`Professions` (
  `ProfessionName` VARCHAR(45) NOT NULL ,
  `CharacterName` VARCHAR(45) NOT NULL ,
  `Level` INT NOT NULL ,
  `Notes` TEXT NULL ,
  PRIMARY KEY (`ProfessionName`, `CharacterName`) ,
  INDEX `fk_Professions_Characters1_idx` (`CharacterName` ASC) ,
  CONSTRAINT `fk_Professions_Characters1`
    FOREIGN KEY (`CharacterName` )
    REFERENCES `Superguild`.`Characters` (`Name` )
);

CREATE  TABLE IF NOT EXISTS `Superguild`.`TalentInfo` (
  `Class` VARCHAR(15) NOT NULL ,
  `RowNumber` INT NOT NULL ,
  `ColumnNumber` INT NOT NULL ,
  `Description` TEXT NOT NULL ,
  `TalentId` INT NOT NULL AUTO_INCREMENT ,
  PRIMARY KEY (`TalentId`) 
);

CREATE  TABLE IF NOT EXISTS `Superguild`.`Spec` (
  `SpecId` INT NOT NULL ,
  `CharacterName` VARCHAR(45) NOT NULL ,
  `level15` INT NULL ,
  `level30` INT NULL ,
  `level45` INT NULL ,
  `level60` INT NULL ,
  `level75` INT NULL ,
  `level90` INT NULL ,
  `isMainSpec` TINYINT(1) NULL ,
  `isRanged` TINYINT(1) NULL ,
  `Role` INT NULL ,
  `isPVE` TINYINT(1) NULL ,
  `SpecName` VARCHAR(45) NOT NULL ,
  `iLvl` INT NULL ,
  PRIMARY KEY (`SpecId`, `CharacterName`) ,
  INDEX `fk_Spec_Characters1_idx` (`CharacterName` ASC) ,
  INDEX `fk_Spec_TalentInfo1_idx` (`level15` ASC) ,
  INDEX `fk_Spec_TalentInfo2_idx` (`level30` ASC) ,
  INDEX `fk_Spec_TalentInfo3_idx` (`level45` ASC) ,
  INDEX `fk_Spec_TalentInfo4_idx` (`level60` ASC) ,
  INDEX `fk_Spec_TalentInfo5_idx` (`level75` ASC) ,
  INDEX `fk_Spec_TalentInfo6_idx` (`level90` ASC) ,
  CONSTRAINT `fk_Spec_Characters1`
    FOREIGN KEY (`CharacterName` )
    REFERENCES `Superguild`.`Characters` (`Name` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Spec_TalentInfo1`
    FOREIGN KEY (`level15` )
    REFERENCES `Superguild`.`TalentInfo` (`TalentId` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Spec_TalentInfo2`
    FOREIGN KEY (`level30` )
    REFERENCES `Superguild`.`TalentInfo` (`TalentId` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Spec_TalentInfo3`
    FOREIGN KEY (`level45` )
    REFERENCES `Superguild`.`TalentInfo` (`TalentId` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Spec_TalentInfo4`
    FOREIGN KEY (`level60` )
    REFERENCES `Superguild`.`TalentInfo` (`TalentId` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Spec_TalentInfo5`
    FOREIGN KEY (`level75` )
    REFERENCES `Superguild`.`TalentInfo` (`TalentId` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Spec_TalentInfo6`
    FOREIGN KEY (`level90` )
    REFERENCES `Superguild`.`TalentInfo` (`TalentId` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

USE `Superguild` ;


flush privileges;

ALTER TABLE characters
MODIFY MainALtOrBank VARCHAR(4);