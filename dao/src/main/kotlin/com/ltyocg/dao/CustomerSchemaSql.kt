package com.ltyocg.dao

object CustomerSchemaSql {
    const val CREATE_SCHEMA_SQL = "CREATE TABLE CUSTOMERS (ID NUMBER, FNAME VARCHAR(100), LNAME VARCHAR(100))"
    const val DELETE_SCHEMA_SQL = "DROP TABLE CUSTOMERS"
}