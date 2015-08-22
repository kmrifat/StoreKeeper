/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataBase;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rifat
 */
public class DBModel {

    PreparedStatement pst;

    public void createDataBase() {
        DBConnection con = new DBConnection();
        try {
            pst = con.mkDataBase().prepareStatement("create database if not exists StorKeeper2 DEFAULT CHARACTER SET utf8 \n"
                    + "  DEFAULT COLLATE utf8_general_ci");
            pst.execute();
            pst = con.mkDataBase().prepareStatement("CREATE TABLE if not exists `StorKeeper2`.`User` (\n"
                    + "  `Id` int(11) NOT NULL AUTO_INCREMENT,\n"
                    + "  `UsrName` VARCHAR(20) NOT NULL,\n"
                    + "  `FullName` VARCHAR(100) ,\n"
                    + "  `EmailAddress` VARCHAR(100) ,\n"
                    + "  `ContactNumber` VARCHAR(100) ,\n"
                    + "  `Salary` double DEFAULT NULL,\n"
                    + "  `Address` text,\n"
                    + "  `Password` VARCHAR(45),\n"
                    + "  `Status` tinyint(1) NOT NULL DEFAULT '0',\n"
                    + "  `UserImage` mediumblob,\n"
                    + "  `Date` date NOT NULL,\n"
                    + "  `CreatorId` int(11),\n"
                    + "  PRIMARY KEY (`Id`),\n"
                    + "  UNIQUE INDEX `Id` (`Id` ASC));");
            pst.execute();
            pst = con.mkDataBase().prepareStatement("CREATE TABLE if not exists `StorKeeper2`.`UserPermission` (\n"
                    + "  `Id` int(11) NOT NULL AUTO_INCREMENT,\n"
                    + "  `AddProduct` tinyint(1) DEFAULT NULL,\n"
                    + "  `AddSupplyer` tinyint(1) DEFAULT NULL,\n"
                    + "  `AddBrand` tinyint(1) DEFAULT NULL,\n"
                    + "  `AddCatagory` tinyint(1) DEFAULT NULL,\n"
                    + "  `AddUnit` tinyint(1) DEFAULT NULL,\n"
                    + "  `AddCustomer` tinyint(1) DEFAULT NULL,\n"
                    + "  `UpdateProduct` tinyint(1) DEFAULT NULL,\n"
                    + "  `UpdateSupplyer` tinyint(1) DEFAULT NULL,\n"
                    + "  `UpdateBrand` tinyint(1) DEFAULT NULL,\n"
                    + "  `UpdateCatagory` tinyint(1) DEFAULT NULL,\n"
                    + "  `UpdateUnit` tinyint(1) DEFAULT NULL,\n"
                    + "  `UpdateCustomer` tinyint(1) DEFAULT NULL,\n"
                    + "  `RMAManage` tinyint(1) DEFAULT NULL,\n"
                    + "  `SellProduct` tinyint(1) DEFAULT NULL,\n"
                    + "  `ProvideDiscount` tinyint(1) DEFAULT NULL,\n"
                    + "  `EmployeManage` tinyint(1) DEFAULT NULL,\n"
                    + "  `OrgManage` tinyint(1) DEFAULT NULL,\n"
                    + "  `ChangeOwnPass` tinyint(1) DEFAULT NULL,\n"
                    + "  `UserId` int(11) NOT NULL, \n"
                    + "  PRIMARY KEY (`Id`),\n"
                    + "  UNIQUE INDEX `Id` (`Id` ASC));");
            pst.execute();
            pst = con.mkDataBase().prepareStatement("CREATE TABLE if not exists `StorKeeper2`.`Organize` (\n"
                    + "  `Id` int(1) NOT NULL ,\n"
                    + "  `OrgName` varchar(100) DEFAULT NULL,\n"
                    + "  `OrgWeb` varchar(100) DEFAULT NULL,\n"
                    + "  `OrgContactNumbers` text DEFAULT NULL,\n"
                    + "  `OrgContactAddress` text DEFAULT NULL,\n"
                    + "  `OrgLogo` mediumblob,\n"
                    + "  `UserId` int(11) DEFAULT NULL,\n"
                    + "  PRIMARY KEY (`Id`),\n"
                    + "  UNIQUE INDEX `Id` (`Id` ASC));");
            pst.execute();

            pst = con.mkDataBase().prepareStatement("CREATE TABLE if not exists `StorKeeper2`.`Supplyer` (\n"
                    + "  `Id` int(11) NOT NULL AUTO_INCREMENT,\n"
                    + "  `SupplyerName` varchar(100) DEFAULT NULL,\n"
                    + "  `SupplyerPhoneNumber` text DEFAULT NULL,\n"
                    + "  `SupplyerAddress` text DEFAULT NULL,\n"
                    + "  `SuplyerDescription` text DEFAULT NULL,\n"
                    + "  `CreatorId` int(11) DEFAULT NULL,\n"
                    + "  `Date` date NOT NULL,\n"
                    + "  PRIMARY KEY (`Id`),\n"
                    + "  UNIQUE INDEX `Id` (`Id` ASC));");
            pst.execute();

            pst = con.mkDataBase().prepareStatement("CREATE TABLE if not exists `StorKeeper2`.`Brands` (\n"
                    + "  `Id` int(11) NOT NULL AUTO_INCREMENT,\n"
                    + "  `BrandName` varchar(70) DEFAULT NULL,\n"
                    + "  `Description` text DEFAULT NULL,\n"
                    + "  `SupplyerId` varchar(20)  DEFAULT NULL,\n"
                    + "  `CreatorId` int DEFAULT NULL,\n"
                    + "  `Date` date,\n"
                    + "  PRIMARY KEY (`Id`),\n"
                    + "  UNIQUE INDEX `Id` (`Id` ASC));");

            pst.execute();

            pst = con.mkDataBase().prepareStatement("CREATE TABLE if not exists `StorKeeper2`.`Catagory` (\n"
                    + "  `Id` int(11) NOT NULL AUTO_INCREMENT,\n"
                    + "  `CatagoryName` varchar(70) DEFAULT NULL,\n"
                    + "  `CatagoryDescription` text DEFAULT NULL,\n"
                    + "  `BrandId` varchar(20) DEFAULT NULL,\n"
                    + "  `SupplyerId` int(11) DEFAULT NULL,\n"
                    + "  `CreatorId` int(11) DEFAULT NULL,\n"
                    + "  `Date` date,\n"
                    + "  PRIMARY KEY (`Id`),\n"
                    + "  UNIQUE INDEX `Id` (`Id` ASC));");

            pst.execute();
            pst = con.mkDataBase().prepareStatement("CREATE TABLE if not exists `StorKeeper2`.`Unit` (\n"
                    + "  `Id` int(11) NOT NULL AUTO_INCREMENT,\n"
                    + "  `UnitName` varchar(50) DEFAULT NULL,\n"
                    + "  `UnitDescription` text DEFAULT NULL,\n"
                    + "  `CreatorId` int(11) DEFAULT NULL,\n"
                    + "  `Date` date,\n"
                    + "  PRIMARY KEY (`Id`),\n"
                    + "  UNIQUE INDEX `Id` (`Id` ASC));");

            pst.execute();

            pst = con.mkDataBase().prepareStatement("CREATE TABLE if not exists `StorKeeper2`.`RMA` (\n"
                    + "  `Id` int(11) NOT NULL AUTO_INCREMENT,\n"
                    + "  `RMAName` varchar(100) DEFAULT NULL,\n"
                    + "  `RMADays` varchar(11) NOT NULL,\n"
                    + "  `Comment` text DEFAULT NULL,\n"
                    + "  `CreatorId` int(11) DEFAULT NULL,\n"
                    + "  `Date` date,\n"
                    + "  PRIMARY KEY (`Id`),\n"
                    + "  UNIQUE INDEX `Id` (`Id` ASC));");

            pst.execute();
            pst = con.mkDataBase().prepareStatement("CREATE TABLE IF NOT EXISTS `StorKeeper2`.`Products` (\n"
                    + "  `Id` int(11) NOT NULL AUTO_INCREMENT,\n"
                    + "  `ProductId` varchar(20) NOT NULL,\n"
                    + "  `ProductName` varchar(150) NOT NULL,\n"
                    + "  `Quantity` varchar(11) NOT NULL DEFAULT '0', \n"
                    + "  `Description` text ,\n"
                    + "  `SupplyerId` varchar(11) NOT NULL,\n"
                    + "  `BrandId` varchar(11) NOT NULL,\n"
                    + "  `CatagoryId` varchar(11) NOT NULL,\n"
                    + "  `UnitId` varchar(11) NOT NULL,\n"
                    + "  `PursesPrice` varchar(100) NOT NULL,\n"
                    + "  `SellPrice` varchar(100) NOT NULL,\n"
                    + "  `RMAId` varchar(11) NOT NULL,\n"
                    + "  `UserId` varchar(11) NOT NULL,\n"
                    + "  `Date` date NOT NULL,\n"
                    + "  PRIMARY KEY (`Id`),\n"
                    + "  UNIQUE INDEX `Id` (`Id` ASC));");
            pst.execute();

            pst = con.mkDataBase().prepareStatement("CREATE TABLE IF NOT EXISTS `StorKeeper2`.`Customer` (\n"
                    + "  `Id` int(11) NOT NULL AUTO_INCREMENT,\n"
                    + "  `CustomerName` varchar(200) NOT NULL,\n"
                    + "  `CustomerContNo` varchar(200) DEFAULT NULL,\n"
                    + "  `CustomerAddress` text,\n"
                    + "  `TotalBuy` varchar(50) DEFAULT NULL,\n"
                    + "  `CreatorId` varchar(11) DEFAULT NULL,\n"
                    + "  `Date` datetime NOT NULL,\n"
                    + "  PRIMARY KEY (`Id`),\n"
                    + "  UNIQUE INDEX `Id` (`Id` ASC));");
            pst.execute();

            pst = con.mkDataBase().prepareStatement("CREATE TABLE IF NOT EXISTS `StorKeeper2`.`Sell` (\n"
                    + "  `Id` int(11) NOT NULL AUTO_INCREMENT,\n"
                    + "  `SellId` varchar(10) NOT NULL,\n"
                    + "  `CustomerId` varchar(11) NOT NULL,\n"
                    + "  `ProductId` varchar(11) NOT NULL,\n"
                    + "  `PursesPrice` double NOT NULL,\n"
                    + "  `SellPrice` double NOT NULL,\n"
                    + "  `Quantity` int(10) NOT NULL,\n"
                    + "  `TotalPrice` double NOT NULL,\n"
                    + "  `WarrentyVoidDate` varchar(20) NOT NULL,\n"
                    + "  `SellerId` int(11) NOT NULL,\n"
                    + "  `SellDate` datetime NOT NULL,\n"
                    + "  PRIMARY KEY (`Id`)\n"
                    + ") ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;");
            pst.execute();
            
            System.out.println("Create Database Sucessfuly");

        } catch (SQLException ex) {
            Logger.getLogger(DBModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
