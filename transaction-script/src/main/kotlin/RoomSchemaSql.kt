object RoomSchemaSql {
    const val CREATE_SCHEMA_SQL = "CREATE TABLE ROOMS (ID NUMBER, ROOM_TYPE VARCHAR(100), PRICE INT, BOOKED VARCHAR(100))"
    const val DELETE_SCHEMA_SQL = "DROP TABLE ROOMS IF EXISTS"
}